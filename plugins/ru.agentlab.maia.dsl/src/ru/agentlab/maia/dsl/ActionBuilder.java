package ru.agentlab.maia.dsl;

import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static ru.agentlab.maia.dsl.Action.EMPTY_VARIABLES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Builder for creating {@link Action} objects. Support thread safe up to 6
 * variables.
 * <h2>Basic Usage:</h2> <code>
 * <pre>
 *  Variable<String> $name = variable("name", String.class);
 *  Action changeName = action("change-name", $name)
 *          .preconditions(
 *                  not(predicate("has-name", $name)))
 *          .postconditions(
 *                  predicate("has-name", $name))
 *          .execute((name) -> {
 *                  System.out.println(name);
 *              })
 *          .build();
 * </pre>
 * </code>
 * 
 * <p>
 * If action executable needs variables, not specified in action signature, for
 * example from precondition, use {@link #using} declaration to declare using
 * variables.
 * 
 * <code>
 * <pre>
 *  Variable<String> $name = variable("name", String.class);
 *  Variable<String> $typeNew = variable("type-new", String.class);
 *  Variable<String> $typeOld = variable("type-old", String.class);
 *  Action changeType = action("change-type", $name, $typeNew)
 *          .preconditions(
 *                  predicate("has-name", $name),
 *                  predicate("has-type", $typeOld))
 *          .postconditions(
 *                  predicate("has-name", $name),
 *                  predicate("has-type", $typeNew))
 *          .using($name, $typeNew, $typeOld)
 *          .execute((name, typeNew, typeOld) -> {
 *                  System.out.println(name);
 *                  System.out.println(typeNew);
 *                  System.out.println(typeOld);
 *              })
 *          .build();
 * </pre>
 * </code>
 * 
 * <h2>Unused variables</h2>
 * <p>
 * Be aware variables should not duplicates and should be used in at least one
 * precondition or postcondition. So next code will throw
 * {@link IllegalStateException}:
 * 
 * <code>
 * <pre>
 *  Variable<String> $name = variable("name", String.class);
 *  Variable<String> $unknown = variable("unknown", String.class);
 *  Action changeName = action("change-name", $name)
 *          .preconditions(
 *                  not(predicate("has-name", $name)))
 *          .postconditions(
 *                  predicate("has-name", $name))
 *          .using($name, $unknown)
 *          .execute((name, unknown) -> {
 *                  System.out.println(name);
 *                  System.out.println(unknown);
 *              })
 *          .build();
 * </pre>
 * </code>
 * 
 * <h2>Duplicate variables</h2>
 * <p>
 * Be aware variables should not duplicates and should be used in at least one
 * precondition or postcondition. So next code will throw
 * {@link IllegalStateException}:
 * 
 * <code>
 * <pre>
 *  Variable<String> $name = variable("name", String.class);
 *  Action actionName = action("action-name", $name, $name)
 *          .execute((name0, name1) -> {
 *                  System.out.println(name0);
 *                  System.out.println(name1);
 *              })
 *          .build();
 * </pre>
 * </code>
 * 
 * @author Dmitriy Shishkin
 *
 * @param <T>
 *            - type of {@link Executable}. Used for type-safety.
 */
public class ActionBuilder<T extends Executable> {

	protected String name;
	protected Predicate precondition;
	protected Predicate effect;
	protected Executable executable;
	protected Variable<?>[] variables;
	protected Variable<?>[] using;

	ActionBuilder(String name, Variable<?>... variables) {
		this(name, null, null, null, variables, null);
	}

	ActionBuilder(String name, Predicate precondition, Predicate effect, Executable executable, Variable<?>[] variables,
			Variable<?>[] using) {
		this.name = name;
		this.precondition = precondition;
		this.effect = effect;
		this.executable = executable;
		this.variables = variables;
		this.using = using;
	}

	public ActionBuilder<T> precondition(Predicate preconditions) {
		checkSpecifiedPreconditions();
		return new ActionBuilder<T>(name, preconditions, effect, executable, variables, using);
	}

	public ActionBuilder<T> effect(Predicate postconditions) {
		checkSpecifiedPostconditions();
		return new ActionBuilder<T>(name, precondition, postconditions, executable, variables, using);
	}

	public ActionBuilder<T> execute(T executable) {
		checkState(this.executable == null, "Executable was already set to %s", this.executable);
		return new ActionBuilder<T>(name, precondition, effect, executable, variables, using);
	}

	public ActionBuilder<Executable0> using() {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable0>(name, precondition, effect, executable, variables, new Variable<?>[0]);
	}

	public <T1> ActionBuilder<Executable1<T1>> using(Variable<T1> variable) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable1<T1>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable });
	}

	public <T1, T2> ActionBuilder<Executable2<T1, T2>> using(Variable<T1> variable1, Variable<T2> variable2) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable2<T1, T2>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable1, variable2 });
	}

	public <T1, T2, T3> ActionBuilder<Executable3<T1, T2, T3>> using(Variable<T1> variable1, Variable<T2> variable2,
			Variable<T3> variable3) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable3<T1, T2, T3>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable1, variable2, variable3 });
	}

	public <T1, T2, T3, T4> ActionBuilder<Executable4<T1, T2, T3, T4>> using(Variable<T1> variable1,
			Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable4<T1, T2, T3, T4>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable1, variable2, variable3, variable4 });
	}

	public <T1, T2, T3, T4, T5> ActionBuilder<Executable5<T1, T2, T3, T4, T5>> using(Variable<T1> variable1,
			Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4, Variable<T5> variable5) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable5<T1, T2, T3, T4, T5>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable1, variable2, variable3, variable4, variable5 });
	}

	public <T1, T2, T3, T4, T5, T6> ActionBuilder<Executable6<T1, T2, T3, T4, T5, T6>> using(Variable<T1> variable1,
			Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4, Variable<T5> variable5,
			Variable<T6> variable6) {
		checkSpecifiedUsing();
		return new ActionBuilder<Executable6<T1, T2, T3, T4, T5, T6>>(
				name,
				precondition,
				effect,
				executable,
				variables,
				new Variable<?>[] { variable1, variable2, variable3, variable4, variable5, variable6 });
	}

	public ActionBuilder<ExecutableN> using(Variable<?>... variables) {
		checkSpecifiedUsing();
		return new ActionBuilder<ExecutableN>(name, precondition, effect, executable, variables, using);
	}

	public Action build() {
		// Check valid name
		checkState(name != null, "Action name should be specified");
		checkState(!name.isEmpty(), "Action name can't be empty string");

		// Check valid executable
		checkState(executable != null, "Action executable should be specified");

		// Handle null values
		Variable<?>[] cleanVariables = variables != null ? variables : EMPTY_VARIABLES;
		Variable<?>[] cleanUsing = using != null ? using : cleanVariables;

		// Check no duplicates
		checkDuplicateVariables(cleanVariables);
		checkDuplicateUsing(cleanUsing);

		// Check no unused variables
		List<Variable<?>> signature = new ArrayList<>();
		if (precondition != null) {
			signature.addAll(Arrays.asList(precondition.getVariables()));
		}
		if (effect != null) {
			signature.addAll(Arrays.asList(effect.getVariables()));
		}
		checkUnusedUsing(cleanUsing, signature);
		checkUnusedVariables(cleanVariables, signature);

		// Construct action
		return new Action(name, precondition, effect, executable, cleanVariables, cleanUsing);
	}

	private void checkUnusedUsing(Variable<?>[] using, List<Variable<?>> signature) {
		Optional<Variable<?>> unusedUsing = getAnyUnused(using, signature);
		checkState(
				!unusedUsing.isPresent(),
				"Using %s is unhandled by any preconditions and postconditions",
				unusedUsing.get().getName());
	}

	private void checkUnusedVariables(Variable<?>[] variables, List<Variable<?>> signature) {
		Optional<Variable<?>> unusedVariable = getAnyUnused(variables, signature);
		checkState(
				!unusedVariable.isPresent(),
				"Variable %s is unhandled by any preconditions and postconditions",
				unusedVariable.get().getName());
	}

	private void checkDuplicateUsing(Variable<?>[] using) {
		Optional<Variable<?>> duplicateUsing = getAnyDuplicates(using);
		checkState(
				!duplicateUsing.isPresent(),
				"Using %s using more then once. Delete it",
				duplicateUsing.get().getName());
	}

	private void checkDuplicateVariables(Variable<?>[] variables) {
		Optional<Variable<?>> duplicateVariable = getAnyDuplicates(variables);
		checkState(
				!duplicateVariable.isPresent(),
				"Variable %s using more then once. Delete it",
				duplicateVariable.get().getName());
	}

	private void checkSpecifiedUsing() {
		checkState(this.using == null, "Using variables was already set to %s", Arrays.toString(this.using));
	}

	private void checkSpecifiedPreconditions() {
		checkState(this.precondition == null, "Preconditions was already set to %s", (Object) this.precondition);
	}

	private void checkSpecifiedPostconditions() {
		checkState(this.effect == null, "Postconditions was already set to %s", (Object) this.effect);
	}

	private Optional<Variable<?>> getAnyUnused(Variable<?>[] variables, List<Variable<?>> signature) {
		return Arrays.stream(variables).filter(variable -> signature.contains(variable)).findAny();
	}

	private Optional<Variable<?>> getAnyDuplicates(Variable<?>[] variables) {
		return Arrays
				.stream(variables)
				.collect(groupingBy(c -> c, counting()))
				.entrySet()
				.stream()
				.filter(p -> p.getValue() > 1)
				.findAny()
				.map(p -> p.getKey());
	}

}
