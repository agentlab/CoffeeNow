package ru.agentlab.maia.dsl;

import java.util.Arrays;

public class Predicate extends Atom {

	private final String name;

	private final Atom[] atoms;

	public Predicate(String name, Atom... atoms) {
		this.name = name;
		this.atoms = atoms;
	}

	public Atom[] getAtoms() {
		return atoms;
	}

	public String getName() {
		return name;
	}

	public Variable<?>[] getVariables() {
		return Arrays
				.stream(atoms)
				.map(Atom::getVariables)
				.flatMap(Arrays::stream)
				.toArray(size -> new Variable<?>[size]);
	}

}