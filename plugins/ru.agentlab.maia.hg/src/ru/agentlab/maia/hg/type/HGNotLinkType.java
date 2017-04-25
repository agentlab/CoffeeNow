package ru.agentlab.maia.hg.type;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.IncidenceSetRef;
import org.hypergraphdb.LazyRef;
import org.hypergraphdb.type.PlainLinkType;

import ru.agentlab.maia.hg.HGNotLink;

public class HGNotLinkType extends PlainLinkType {

	@Override
	public Object make(HGPersistentHandle handle, LazyRef<HGHandle[]> targetSet, IncidenceSetRef incidenceSet) {
		return new HGNotLink(targetSet.deref()[0]);
	}

}
