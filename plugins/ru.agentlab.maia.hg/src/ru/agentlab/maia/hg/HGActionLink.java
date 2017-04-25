package ru.agentlab.maia.hg;

import java.util.Objects;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

public class HGActionLink extends HGPlainLink {

	public HGActionLink(HGHandle name, HGHandle precondition, HGHandle effect, HGHandle executable) {
		super(name, precondition, effect, executable);
	}

	public HGHandle getName() {
		return outgoingSet[0];
	}

	public HGHandle getPrecondition() {
		return outgoingSet[1];
	}

	public HGHandle getEffect() {
		return outgoingSet[2];
	}

	public HGHandle getExecutable() {
		return outgoingSet[3];
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		HGActionLink other = (HGActionLink) obj;
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public String toString() {
		return "HGActionLink('" + getName() + "', " + getPrecondition() + ", " + getEffect() + ", " + getExecutable()
				+ ")";
	}

}
