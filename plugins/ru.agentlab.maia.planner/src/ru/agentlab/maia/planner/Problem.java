package ru.agentlab.maia.planner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.locks.Condition;

import ru.agentlab.maia.dsl.Action;
import ru.agentlab.maia.dsl.Predicate;
import ru.agentlab.maia.dsl.Variable;


public class Problem extends Parametized {
	public State init = new State();
	public Condition goal;
	public Domain domain;
	public Collection<Action> actions = new HashSet<Action>();

	public void computeActions() {
		buildTypeMap();
		for (Action action : domain.actions) {
			computeAllActions(action);
		}
	}

	private void computeAllActions(Action action) {
		int x = action.params.size();
		List<Variable> params = new ArrayList<Variable>();
		computeActions(params, action, x);
	}

	/**
	 * Suporte a tipos...
	 *
	 * @author Sávio Mota
	 *
	 */

	private void computeActions(List<Variable> params, Action action, int left) {
		if (left != 0) {
			Variable p = action.params.get(action.params.size() - left);
			if (typeMap.containsKey(p.type.name)){
				Collection<Variable> ps = typeMap.get(p.type.name);
				for (Variable parameter : ps) {
					List<Variable> newParams = new ArrayList<Variable>(params);
					newParams.add(parameter);
					computeActions(newParams,action,left-1);
				}
			}else{
				System.out.println("Nenhum objeto do tipo " + p.type.name.toUpperCase() + " foi declarado.");
				System.exit(0);
			}
		} else {
			Action a = action.copy();
			a.replaceParams(a.params, params);
			actions.add(a);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("(define (problem " + name + ")\n");
		builder.append("	(:domain " + domain.name);
		builder.append(")\n	(:objects ");
		for (Variable p : params) {
			builder.append(p.name + " - " + p.type);
		}
		builder.append("	)\n	(:init\n");
		for (Predicate p : init.predicates) {
			builder.append("		" + p + "\n");
		}
		builder.append("	)\n	(:goal " + goal);
		builder.append("	)\n");
		builder.append(")");

		return builder.toString();
	}
}
