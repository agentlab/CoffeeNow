package ru.agentlab.maia.planner;

import java.util.Stack;

import ru.agentlab.maia.dsl.Action;

/**
 * @author <a href="mailto:saviod2@gmail.com">Sávio Mota</a>
 *
 */
public abstract class AbstractPlanner {

	public void printPlan(State solution) {
		Stack<Action> actionStack = new Stack<Action>();
		while (solution.cause != null){
			actionStack.add(solution.cause);
			solution = solution.last;
		}
		System.out.println("\nSOLUTION FOUND:");
		System.out.println("======================================================");
		while (!actionStack.empty()) {
			System.out.println("Action: "+actionStack.pop());
		}
		System.out.println("======================================================\n");
	}
}
