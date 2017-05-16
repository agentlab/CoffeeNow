package ru.agentlab.maia.dsl;

import java.util.Objects;

/**
 * Variable atom
 * 
 * @author Dmitriy Shishkin
 *
 * @param <T>
 *            type of variable value
 */
public class Variable<T> extends Atom {

	private final String name;

	private final Class<T> type;

	protected Variable(String name, Class<T> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Class<T> getType() {
		return type;
	}

	public Variable<?>[] getVariables() {
		return new Variable<?>[] { this };
	}

	@Override
	public String toString() {
		return "?" + name + ":" + type.getName();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Variable<?> other = (Variable<?>) obj;
		return Objects.equals(this.name, other.name) && Objects.equals(this.type, other.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.type);
	}

}
