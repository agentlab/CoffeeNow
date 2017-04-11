/**
 *
 */
package org.hypergraphdb.prolog.examples;

import org.hypergraphdb.HyperGraph;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Theory;
import alice.tuprolog.hgdb.HGPrologLibrary;
import alice.tuprolog.hgdb.PrologHGDBApp;

/**
 * @author Ivanov_AM
 *
 */
@Component
public class TestPrologComponentImpl {

	static final String dbLocation = "./HGTestDB"; //$NON-NLS-1$

	@Activate
	public void start() {
		try {
			HyperGraph graph = new HyperGraph(dbLocation);
			setModules(graph);

			Prolog prolog = new Prolog();
			HGPrologLibrary lib = HGPrologLibrary.attach(graph, prolog);

			test(prolog, "my_append([],X,X).\n" + "my_append([X|L1],L2,[X|L3]) :- my_append(L1,L2,L3).\n", "my_append(X,Y,[1,2,3])."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		catch (Throwable t) {
			t.printStackTrace(System.err);
		}
	}

	/**
	 * TODO JavaDoc
	 *
	 * @param prolog
	 * @param theory_text
	 * @throws InvalidTheoryException
	 * @throws MalformedGoalException
	 * @throws NoSolutionException
	 * @throws NoMoreSolutionException
	 */
	private void test(Prolog prolog, String theory_text, String goal_text) throws InvalidTheoryException, MalformedGoalException, NoSolutionException, NoMoreSolutionException {
		Theory theory = new Theory(theory_text);
		try {
			prolog.setTheory(theory);
		}
		catch (InvalidTheoryException ex) {
			ex.printStackTrace();
		}

		SolveInfo solveInfo = prolog.solve(goal_text);
		while (solveInfo != null && solveInfo.isSuccess()) {
			System.out.println(solveInfo.getSolution());
			solveInfo = prolog.hasOpenAlternatives() ? prolog.solveNext() : null;
		}
	}

	public static void setupPrologModule(HyperGraph graph) {
		new PrologHGDBApp().install(graph);
	}

	public static void setModules(HyperGraph graph) {
		setupPrologModule(graph);
	}
}
