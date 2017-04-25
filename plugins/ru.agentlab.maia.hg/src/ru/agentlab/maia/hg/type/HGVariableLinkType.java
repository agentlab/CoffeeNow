package ru.agentlab.maia.hg.type;

import static com.google.common.base.Preconditions.checkArgument;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.IncidenceSetRef;
import org.hypergraphdb.LazyRef;
import org.hypergraphdb.type.PlainLinkType;

import ru.agentlab.maia.hg.HGVariableLink;

public class HGVariableLinkType extends PlainLinkType {

	@Override
	public Object make(HGPersistentHandle handle, LazyRef<HGHandle[]> targetSet, IncidenceSetRef incidenceSet) {
		HGHandle[] set = targetSet.deref();
		// Check size of 2
		// 0 - variable name
		// 1 - variable type
		checkArgument(set.length == 2, "Atom should be arity of 2");
		return new HGVariableLink(set[0], set[1]);
	}

}
