package ru.agentlab.maia.hg;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

public class HGVariableLink extends HGPlainLink {

	public HGVariableLink(HGHandle name, HGHandle type) {
		super(name, type);
	}

	public HGHandle getName() {
		return outgoingSet[0];
	}

	public HGHandle getType() {
		return outgoingSet[1];
	}

	@Override
	public String toString() {
		return "HGVariableLink('" + getName() + "', " + getType() + ")";
	}

}
