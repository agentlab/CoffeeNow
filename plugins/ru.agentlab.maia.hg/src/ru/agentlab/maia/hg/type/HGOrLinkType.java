package ru.agentlab.maia.hg.type;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.IncidenceSetRef;
import org.hypergraphdb.LazyRef;
import org.hypergraphdb.type.PlainLinkType;

import ru.agentlab.maia.hg.HGOrLink;

public class HGOrLinkType extends PlainLinkType {

	@Override
	public Object make(HGPersistentHandle handle, LazyRef<HGHandle[]> targetSet, IncidenceSetRef incidenceSet) {
		return new HGOrLink(targetSet.deref());
	}

}
