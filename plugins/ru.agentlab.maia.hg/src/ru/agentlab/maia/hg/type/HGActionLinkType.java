package ru.agentlab.maia.hg.type;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.IncidenceSetRef;
import org.hypergraphdb.LazyRef;
import org.hypergraphdb.type.PlainLinkType;

import com.google.common.base.Preconditions;

import ru.agentlab.maia.hg.HGActionLink;

public class HGActionLinkType extends PlainLinkType {

	@Override
	public Object make(HGPersistentHandle handle, LazyRef<HGHandle[]> targetSet, IncidenceSetRef incidenceSet) {
		HGHandle[] set = targetSet.deref();
		// Check size of 4
		// 0 - action name
		// 1 - action precondition, can be null
		// 2 - action affect, can be null
		// 3 - action executable
		Preconditions.checkArgument(set.length == 4, "Atom should be arity of 4");
		return new HGActionLink(set[0], set[1], set[2], set[3]);
	}

}
