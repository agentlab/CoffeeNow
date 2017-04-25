package ru.agentlab.maia.hg.app;

import static org.hypergraphdb.type.JavaTypeSchema.classToURI;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGIndexManager;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.HGTypeSystem;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.app.management.HGApplication;
import org.hypergraphdb.indexing.ByTargetIndexer;

import ru.agentlab.maia.hg.HGActionLink;
import ru.agentlab.maia.hg.HGNotLink;
import ru.agentlab.maia.hg.HGOrLink;
import ru.agentlab.maia.hg.HGVariableLink;
import ru.agentlab.maia.hg.type.HGActionLinkType;
import ru.agentlab.maia.hg.type.HGAndLinkType;
import ru.agentlab.maia.hg.type.HGNotLinkType;
import ru.agentlab.maia.hg.type.HGOrLinkType;
import ru.agentlab.maia.hg.type.HGVariableLinkType;

/**
 * {@code HGApplication} with MAIA atom types.
 * 
 * @author Dmitriy Shishkin
 *
 */
public class HGMaiaApplication extends HGApplication {

	public static final String PRECONDITION_INDEX = "hg_maia_precondition_index";
	public static final String EFFECT_INDEX = "hg_maia_effect_index";

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void install(HyperGraph graph) {
		System.out.println("Register MAIA Application!");
		HGTypeSystem typeSystem = graph.getTypeSystem();
		HGIndexManager indexManager = graph.getIndexManager();

		// Generate handles
		HGPersistentHandle hgOrLinkTypeHandle = graph.getHandleFactory().makeHandle();
		HGPersistentHandle hgAndLinkTypeHandle = graph.getHandleFactory().makeHandle();
		HGPersistentHandle hgNotLinkTypeHandle = graph.getHandleFactory().makeHandle();
		HGPersistentHandle hgVariableLinkTypeHandle = graph.getHandleFactory().makeHandle();
		HGPersistentHandle hgActionLinkTypeHandle = graph.getHandleFactory().makeHandle();
		// Add atom types
		typeSystem.addPredefinedType(hgOrLinkTypeHandle, new HGOrLinkType(), classToURI(HGOrLink.class));
		typeSystem.addPredefinedType(hgAndLinkTypeHandle, new HGAndLinkType(), classToURI(HGOrLink.class));
		typeSystem.addPredefinedType(hgNotLinkTypeHandle, new HGNotLinkType(), classToURI(HGNotLink.class));
		typeSystem.addPredefinedType(hgVariableLinkTypeHandle, new HGVariableLinkType(),
				classToURI(HGVariableLink.class));
		typeSystem.addPredefinedType(hgActionLinkTypeHandle, new HGActionLinkType(), classToURI(HGActionLink.class));

		// Create indices
		ByTargetIndexer preconditionIndexer = new ByTargetIndexer(PRECONDITION_INDEX,
				typeSystem.getTypeHandle(HGActionLink.class), 0);
		ByTargetIndexer effectIndexer = new ByTargetIndexer(EFFECT_INDEX, typeSystem.getTypeHandle(HGActionLink.class),
				1);
		// Register indices
		indexManager.register(preconditionIndexer);
		indexManager.register(effectIndexer);
		// Apply indices
		graph.runMaintenance();
	}

	@Override
	public void uninstall(HyperGraph graph) {
		HGTypeSystem typeSystem = graph.getTypeSystem();
		HGHandle hgOrLinkType = typeSystem.getTypeHandle(HGOrLink.class);
		graph.remove(hgOrLinkType);
	}

	@Override
	public void update(HyperGraph graph) {
		// Apply indices
		graph.runMaintenance();
	}

	@Override
	public void reset(HyperGraph graph) {
		// Do nothing
	}

}
