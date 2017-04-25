/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;

import org.hypergraphdb.HyperGraph;
/**
 * @author V
 *
 */
public class TestComponentImpl {
	private static final String dbLocation = "./HGCafesDB";

	public void Start(){
		try (HyperGraph graph = new HyperGraph()) {

		}catch (Throwable t) {
				t.printStackTrace();
			}
	}
}
