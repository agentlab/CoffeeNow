/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;

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


import ru.agentlab.maia.hg.HGActionLink;
import ru.agentlab.maia.hg.HGAndLink;
import ru.agentlab.maia.hg.HGNotLink;
import ru.agentlab.maia.hg.HGOrLink;
import ru.agentlab.maia.hg.HGVariableLink;
import ru.agentlab.maia.hg.app.HGMaiaApplication

import ru.agentlab.coffeenow.cafeshypergraph.ParseJson;

/**
 * @author V
 *
 */
public class TestComponentImpl {
	private static final String PATH = "./test_hgdb"; //$NON-NLS-1$

    private HyperGraph graph = null;
    
    public void createHyperGraph(){
		HGManagement.ensureInstalled(graph, new HGMaiaApplication());
		HGTypeSystem typeSystem = graph.getTypeSystem();
		HGIndexManager indexManager = graph.getIndexManager();
		
		ParseJson parser = new ParseJson();
		List<Cafes> cafesList = parser.makerOfCafeList();
		for(Cafes cafe : cafesList){
			HGHandle actionName = hg.assertAtom(graph ,"акция" + cafe.getcafeName());
		}
		
    }
	
}
