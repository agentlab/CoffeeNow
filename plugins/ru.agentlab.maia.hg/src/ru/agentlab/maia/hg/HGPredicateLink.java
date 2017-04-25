package ru.agentlab.maia.hg;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.atom.HGRel;

public class HGPredicateLink extends HGRel {

	public HGPredicateLink(String name, HGHandle... atoms) {
		super(name, atoms);
	}

	public HGHandle[] getAtoms() {
		return outgoingSet;
	}

	@Override
	public String toString() {
		return "HGPredicateLink('" + getName() + "', "
				+ Arrays.stream(getAtoms()).map(Object::toString).collect(joining(", ")) + ")";
	}

}
