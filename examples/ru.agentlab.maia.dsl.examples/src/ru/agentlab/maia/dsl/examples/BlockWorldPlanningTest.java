/**
 *
 */
package ru.agentlab.maia.dsl.examples;

import static ru.agentlab.maia.dsl.Dsl.and;
import static ru.agentlab.maia.dsl.Dsl.predicate;

import org.junit.Test;

import ru.agentlab.maia.dsl.Action;
import ru.agentlab.maia.dsl.Predicate;
import ru.agentlab.maia.planner.Domain;
import ru.agentlab.maia.planner.Planner;
import ru.agentlab.maia.planner.Problem;
import ru.agentlab.maia.planner.strips.StripsPlanner;

/**
 * @author Ivanov_AM
 *
 */
public class BlockWorldPlanningTest {

	@Test
	public void test() {
		Problem problem = new Problem();
		problem.domain = new Domain();

		BlockWorldActions bwa = new BlockWorldActions();

		problem.domain.actions.add(bwa.pickupAction().build());
		problem.domain.actions.add(bwa.putdownAction().build());
		problem.domain.actions.add(bwa.stackAction().build());
		problem.domain.actions.add(bwa.unstackAction().build());

		Predicate a = predicate("a"); //$NON-NLS-1$
		Predicate b = predicate("b"); //$NON-NLS-1$
		Predicate c = predicate("c"); //$NON-NLS-1$

		problem.domain.addPredicate(a);
		problem.domain.addPredicate(b);
		problem.domain.addPredicate(c);

		problem.init.addPredicate(predicate(BlockWorldActions.ONTABLE, a));
		problem.init.addPredicate(predicate(BlockWorldActions.ON, a, b));
		problem.init.addPredicate(predicate(BlockWorldActions.ON, c, b));
		problem.init.addPredicate(predicate(BlockWorldActions.CLEAR, c));
		problem.init.addPredicate(predicate(BlockWorldActions.ARMEMPTY));

		problem.goal = and(
			predicate(BlockWorldActions.ONTABLE, b)
		  , predicate(BlockWorldActions.ON, a, b)
		  , predicate(BlockWorldActions.ON, c, a)
		);

		Planner planner = new StripsPlanner();
		planner.plan(problem);
		for (Action action : problem.actions) {
			System.out.println(action);
		}
	}
}
