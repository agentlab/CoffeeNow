package ru.agentlab.maia.dsl;

public abstract class Signature {

	private final String name;
	private final Class<?>[] types;

	public Signature(String name, Class<?>[] types) {
		this.name = name;
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public Class<?>[] getTypes() {
		return types;
	}

}
