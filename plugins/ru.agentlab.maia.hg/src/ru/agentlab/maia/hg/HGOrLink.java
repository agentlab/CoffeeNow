package ru.agentlab.maia.hg;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

public class HGOrLink extends HGPlainLink {

	public HGOrLink(HGHandle... atoms) {
		super(atoms);
	}

	public HGHandle[] getAtoms() {
		return outgoingSet;
	}

	@Override
	public String toString() {
		return "HGOrLink(" + Arrays.stream(getAtoms()).map(Object::toString).collect(joining(", ")) + ")";
	}

}
