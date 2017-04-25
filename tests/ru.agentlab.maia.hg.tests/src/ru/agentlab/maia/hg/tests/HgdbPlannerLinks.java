package ru.agentlab.maia.hg.tests;

import java.util.List;

import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGIndexManager;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGRandomAccessResult;
import org.hypergraphdb.HGTypeSystem;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.app.management.HGManagement;
import org.hypergraphdb.indexing.ByTargetIndexer;
import org.hypergraphdb.indexing.HGIndexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.agentlab.maia.hg.HGActionLink;
import ru.agentlab.maia.hg.HGAndLink;
import ru.agentlab.maia.hg.HGNotLink;
import ru.agentlab.maia.hg.HGOrLink;
import ru.agentlab.maia.hg.HGVariableLink;
import ru.agentlab.maia.hg.app.HGMaiaApplication;

/**
 * Example
 *
 * @author Dmitriy Shishkin
 *
 */
public class HgdbPlannerLinks {
    private static final String PATH = "./test_hgdb"; //$NON-NLS-1$

    private HyperGraph graph = null;

    @Before
    public void init() {
        System.out.println("DB exists: " + HGEnvironment.exists(PATH)); //$NON-NLS-1$
        System.out.println("DB open:   " + HGEnvironment.isOpen(PATH)); //$NON-NLS-1$
        graph = new HyperGraph(PATH);
        System.out.println("DB open:   " + HGEnvironment.isOpen(PATH)); //$NON-NLS-1$
    }

    @After
    public void destroy() {
        if (graph != null)
        {
            graph.close();
        }
    }

    @Test
    public void populate() {
		HGManagement.ensureInstalled(graph, new HGMaiaApplication());
		HGTypeSystem typeSystem = graph.getTypeSystem();
		HGIndexManager indexManager = graph.getIndexManager();

		// Create variables
        HGHandle $test = graph.add(new HGVariableLink(graph.add("$test"), typeSystem.getTypeHandle(Integer.class))); //$NON-NLS-1$
        HGHandle $vars = graph.add(new HGVariableLink(graph.add("$vars"), typeSystem.getTypeHandle(String.class))); //$NON-NLS-1$
		// Create action names, ensure no duplicates
        HGHandle actionName = hg.assertAtom(graph, "action-name"); //$NON-NLS-1$
        HGHandle actionName2 = hg.assertAtom(graph, "action-name2"); //$NON-NLS-1$
		// TODO: register Executable types
        Object object = "Executable"; //$NON-NLS-1$
		HGHandle exe = graph.add(object);

		// Add actions
		hg.assertAtom(
		        graph,
		        new HGActionLink(
		                actionName,
		                graph.add(new HGAndLink($test, $vars)),
		                graph.add(new HGOrLink($test, $vars, graph.add(new HGNotLink($test)))),
		                exe),
		        true);
		hg.assertAtom(
		        graph,
		        new HGActionLink(
		                actionName2,
		                graph.add(new HGAndLink($test, $vars)),
		                graph.add(new HGOrLink($test, $vars, graph.add(new HGNotLink($test)))),
		                exe));

		// graph.add(new HGOrLink(graph.add(new HGOrLink())));

		// Retrieve atoms
        System.out.println("---- all atoms ----"); //$NON-NLS-1$
        System.out.println("Total count: " + hg.count(graph, hg.all())); //$NON-NLS-1$
		hg.count(graph, hg.all());
		printAllByType(graph, HGActionLink.class);
		printAllByType(graph, HGAndLink.class);
		printAllByType(graph, HGOrLink.class);
		printAllByType(graph, HGNotLink.class);
		printAllByType(graph, HGVariableLink.class);

		// Retrieve all preconditions
		List<HGIndexer<?, ?>> actionIndices = indexManager
		        .getIndexersForType(typeSystem.getTypeHandle(HGActionLink.class));

		ByTargetIndexer preconditionIndexer = actionIndices
		        .stream()
		        .filter(i -> i instanceof ByTargetIndexer)
		        .map(indexer -> (ByTargetIndexer) indexer)
		        .filter(indexer -> indexer.getName().equals(HGMaiaApplication.PRECONDITION_INDEX))
		        .findFirst()
            .orElseThrow(() -> new RuntimeException("No index")); //$NON-NLS-1$
		ByTargetIndexer effectIndexer = actionIndices
		        .stream()
		        .filter(i -> i instanceof ByTargetIndexer)
		        .map(indexer -> (ByTargetIndexer) indexer)
		        .filter(indexer -> indexer.getName().equals(HGMaiaApplication.EFFECT_INDEX))
		        .findFirst()
            .orElseThrow(() -> new RuntimeException("No index")); //$NON-NLS-1$

		HGRandomAccessResult<Object> preconditions = null;
		try {
            System.out.println("---- all preconditions (from index) ----"); //$NON-NLS-1$
			preconditions = indexManager.getIndex(preconditionIndexer).scanKeys();
			while (preconditions.hasNext()) {
				System.out.println(preconditions.next());
			}
		} finally {
			if (preconditions != null) {
				preconditions.close();
			}
		}

		// Retrieve all effects
		HGRandomAccessResult<Object> effects = null;
		try {
            System.out.println("---- all effects (from index) ----"); //$NON-NLS-1$
			effects = indexManager.getIndex(effectIndexer).scanKeys();
			while (effects.hasNext()) {
				System.out.println(effects.next());
			}
		} finally {
			if (effects != null) {
				effects.close();
			}
		}
	}

	private static void printAllByType(HyperGraph graph, Class<?> type) {
		hg.findAll(graph, hg.type(type)).forEach(
            action -> System.out.println(action + ": " + graph.get((HGHandle)action))); //$NON-NLS-1$
	}

}
