package ru.agentlab.maia.planner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import ru.agentlab.maia.dsl.Action;
import ru.agentlab.maia.dsl.Predicate;
import ru.agentlab.maia.dsl.Variable;


public class Problem extends Parametized {
	public State init = new State();
	public Predicate goal;
	public Domain domain;
	public Collection<Action> actions = new HashSet<Action>();

	public boolean isGoalSatisfied(State s) {
		return true;
	}

	public void computeActions() {
		buildTypeMap();
		for (Action action : domain.actions) {
			computeAllActions(action);
		}
	}

	private void computeAllActions(Action action) {
		int x = action.getVariables().length;
		List<Variable> params = new ArrayList<Variable>();
		computeActions(params, action, x);
	}

	/**
	 * Suporte a tipos...
	 *
	 * @author Sávio Mota
	 *
	 */

	private void computeActions(List<Variable> variables, Action action, int left) {
		if (left != 0) {
			Variable p = action.getVariables()[action.getVariables().length - left];
			if (typeMap.containsKey(p.getType().getName())){
				Collection<Variable> ps = typeMap.get(p.getType().getName());
				for (Variable parameter : ps) {
					List<Variable> newParams = new ArrayList<Variable>(variables);
					newParams.add(parameter);
					computeActions(newParams, action, left-1);
				}
			}else{
				System.out.println("Nenhum objeto do tipo " + p.getType().getName().toUpperCase() + " foi declarado.");
				System.exit(0);
			}
		} else {
			System.out.println("Action " + action);
			for (Variable v : variables) {
				System.out.println("\tparam " + v);
			}
			//Action a = action.copy();
			//a.replaceParams(a.getVariables(), params);
			//actions.add(a);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("(define (problem " + name + ")\n");
		builder.append("	(:domain " + domain.name);
		builder.append(")\n	(:objects ");
		for (Variable p : params) {
			builder.append(p.getName() + " - " + p.getType().getName());
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
