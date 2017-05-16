package ru.agentlab.maia.dsl;

public class PredicateAnd extends Predicate {

	public PredicateAnd(Atom... atoms) {
		super("and", atoms);
	}

}