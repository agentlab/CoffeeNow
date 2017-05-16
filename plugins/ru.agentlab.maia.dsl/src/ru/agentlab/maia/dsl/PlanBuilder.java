package ru.agentlab.maia.dsl;

public class PlanBuilder {

	protected String name;
	protected Predicate[] preconditions;
	protected Predicate[] postconditions;
	protected Executable executable;
	protected Variable<?>[] variables;
	protected Variable<?>[] using;

	public PlanBuilder name(String name) {
		this.name = name;
		return this;
	}

	public PlanBuilder precondition(Predicate predicate) {
		return this;
	}

	public PlanBuilder effect(Predicate predicate) {
		return this;
	}

	public PlanBuilder subtasks(Object... subtasks) {
		return this;
	}

	public Plan build() {
		return new Plan();
	}

	public String getName() {
		return name;
	}

}
