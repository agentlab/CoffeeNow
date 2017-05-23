/**
 *
 */
package ru.agentlab.maia.planner.strips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import ru.agentlab.maia.planner.Planner;
import ru.agentlab.maia.planner.Problem;

/**
 * @author Ivanov_AM
 *
 */
public class StripsPlanner implements Planner {

	@Override
	public void plan(Problem p) {
		List<State> currentSolutions = new ArrayList<State>();
		Collection<State> visited = new HashSet<State>();
		currentSolutions.add(p.init);
		State solution = null;
		int count = 1;
		while(solution == null){
			visited.addAll(currentSolutions);
			Collection<State> newSolutions = new HashSet<State>();
			System.out.println("Computing level: " + count++ + "...");
			for (State state : currentSolutions) {
				if (state.solves(p)){
					solution = state;
					break;
				}else{
					newSolutions.addAll(state.expand(p));
				}
			}
			currentSolutions = new ArrayList<State>();
			for (State state : newSolutions) {
				if (!visited.contains(state))
					currentSolutions.add(state);
			}

			System.out.println("   Expanded states:          "+newSolutions.size());
			System.out.println("   Number of visited states: "+visited.size());
			System.out.println("   Level width:              "+currentSolutions.size());

			if (currentSolutions.size() == 0)
				break;
			solution = searchSolution(currentSolutions, p);
		}
		if (solution != null)
			printPlan(solution);
		else
			System.out.println("NO SOLUTION FOUND");
	}

	private State searchSolution(List<State> solutions, Problem p) {
		for (State state : solutions) {
			if (state.solves(p)){
				return state;
			}
		}
		return null;
	}
}
