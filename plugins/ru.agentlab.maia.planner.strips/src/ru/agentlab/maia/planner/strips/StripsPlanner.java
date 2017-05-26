/**
 *
 */
package ru.agentlab.maia.planner.strips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import ru.agentlab.maia.planner.AbstractPlanner;
import ru.agentlab.maia.planner.Planner;
import ru.agentlab.maia.planner.Problem;
import ru.agentlab.maia.planner.State;

/**
 * @author Ivanov_AM
 *
 */
public class StripsPlanner extends AbstractPlanner implements Planner {

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
			System.out.println("Computing level: " + count++ + "...");  //$NON-NLS-1$//$NON-NLS-2$
			for (State state : currentSolutions) {
				if (p.isGoalSatisfied(state)){
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

			System.out.println("   Expanded states:          "+newSolutions.size()); //$NON-NLS-1$
			System.out.println("   Number of visited states: "+visited.size()); //$NON-NLS-1$
			System.out.println("   Level width:              "+currentSolutions.size()); //$NON-NLS-1$

			if (currentSolutions.size() == 0)
				break;
			solution = searchSolution(currentSolutions, p);
		}
		if (solution != null)
			printPlan(solution);
		else
			System.out.println("NO SOLUTION FOUND"); //$NON-NLS-1$
	}

	private State searchSolution(List<State> solutions, Problem p) {
		for (State state : solutions) {
			if (p.isGoalSatisfied(state)){
				return state;
			}
		}
		return null;
	}
}
