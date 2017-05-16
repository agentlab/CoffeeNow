package ru.agentlab.maia.dsl;

public class PredicateOr extends Predicate {

	public PredicateOr(Atom... atoms) {
		super("or", atoms);
	}

}