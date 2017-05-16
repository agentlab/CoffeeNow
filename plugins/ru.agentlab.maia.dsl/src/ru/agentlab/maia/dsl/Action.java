package ru.agentlab.maia.dsl;

/**
 * Executable action.
 * 
 * @author Dmitriy Shishkin
 *
 */
public class Action {

	public final static Variable<?>[] EMPTY_VARIABLES = new Variable<?>[0];

	protected String name;
	protected Predicate precondition;
	protected Predicate effect;
	protected Executable executable;
	protected Variable<?>[] variables;
	protected Variable<?>[] using;

	/**
	 * Non-public constructor. Use {@link ActionBuilder} for constructing to
	 * avoid null-values, duplicate and unused variables
	 * 
	 * @param name
	 *            - the name of action
	 * @param precondition
	 *            - action preconditions. Should be non {@code null} array of
	 *            {@link Predicate}. For empty array {@link #EMPTY_PREDICATES}
	 *            should be used.
	 * @param effect
	 *            - action effect. Should be non {@code null} array of
	 *            {@link Predicate}. For empty array {@link #EMPTY_PREDICATES}
	 *            should be used.
	 * @param executable
	 *            - action executable. Executes when action perform to start.
	 *            Should be non {@code null}
	 * @param variables
	 *            - action variables. Should be non-{@code null}, distinct array
	 *            of {@link Variable}. For empty array {@link #EMPTY_VARIABLES}
	 *            should be used.
	 * @param using
	 *            - action variables, used in executable. Should be
	 *            non-{@code null}, distinct array of {@link Variable}. For
	 *            empty array {@link #EMPTY_VARIABLES} should be used.
	 */
	Action(String name, Predicate precondition, Predicate effect, Executable executable, Variable<?>[] variables,
			Variable<?>[] using) {
		super();
		this.name = name;
		this.precondition = precondition;
		this.effect = effect;
		this.executable = executable;
		this.variables = variables;
		this.using = using;
	}

}
