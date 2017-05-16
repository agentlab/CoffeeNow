package ru.agentlab.maia.dsl;

public class Constant<T> extends Atom {

	T value;

	protected Constant(T value) {
		this.value = value;
	}

	public Variable<?>[] getVariables() {
		return new Variable<?>[0];
	}

}
