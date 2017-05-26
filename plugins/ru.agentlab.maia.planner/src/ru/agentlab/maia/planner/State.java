package ru.agentlab.maia.planner;

import java.util.Collection;
import java.util.HashSet;

import ru.agentlab.maia.dsl.Action;
import ru.agentlab.maia.dsl.Atom;
import ru.agentlab.maia.dsl.Predicate;

public class State {
	public Collection<Predicate> predicates = new HashSet<Predicate>();
	public State last;
	public Action cause;
	private Integer hashCode;

	public void addPredicate(Predicate p) {
		predicates.add(p);
	}

	public State copy() {
		State novo = new State();
		novo.predicates.addAll(predicates);
		return novo;
	}

	public Predicate findSame(Predicate predicate) {
		if (predicates.contains(predicate)){
			return predicate;
		}
		return null;
	}

	public boolean verify(Action a) {
		return verify(a.getPrecondition());
	}

	/**
	 * TODO JavaDoc
	 *
	 * @param precondition
	 * @return
	 */
	private boolean verify(Predicate precondition) {
		if (findSame(precondition) != null)
			return true;
		return false;
	}

	public Collection<State> expand(Problem p) {
		Collection<State> states = new HashSet<State>();
		for (Action a : p.actions) {
			if (this.verify(a)){
				State novo = apply(a);
				novo.last = this;
				novo.cause = a;
				states.add(novo);
			}
		}
		return states;
	}

	private State apply(Action a) {
		return apply(a.getEffect());
	}

	private State apply(Predicate p) {
		State novo = copy();
		if(p.getName().equals("and")) {
			for (Atom a : p.getAtoms()) {
				if(a instanceof Predicate) {
					if(((Predicate)a).getName().equals("not")) {
						Atom a2 = ((Predicate)a).getAtoms()[0];
						if(a2 instanceof Predicate) {
							novo.predicates.remove(p);
						}
					}
					else {
						novo.predicates.add(p);
					}
				}
			}
		}

		return novo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj instanceof State){
			State other = (State) obj;
			return (predicates.size() == other.predicates.size() && predicates.containsAll(other.predicates));
		}
		return false;
	}

	/**
	 * Implementação baseada no Item 9 do livro Effective java...
	 *
	 * @author Sávio Mota
	 *
	 */
	@Override
	public int hashCode() {
		if (hashCode == null){
			int result = 17;
			result = 31 * result + predicates.hashCode();
			hashCode = result;
		}
		return hashCode;
	}

	@Override
	public String toString() {
		return predicates.toString();
	}
}