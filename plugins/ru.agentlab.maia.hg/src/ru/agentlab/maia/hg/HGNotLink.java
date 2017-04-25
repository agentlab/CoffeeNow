package ru.agentlab.maia.hg;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

public class HGNotLink extends HGPlainLink {

	public HGNotLink(HGHandle atom) {
		super(atom);
	}

	public HGHandle getAtom() {
		return outgoingSet[0];
	}

	@Override
	public String toString() {
		return "HGNotLink(" + getAtom() + ")";
	}

}
