package ru.agentlab.maia.dsl;

public class PredicateNot extends Predicate {

	public PredicateNot(Atom atom) {
		super("not", atom);
	}

}