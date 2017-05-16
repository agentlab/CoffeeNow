package ru.agentlab.maia.dsl;

public class Signature1<T1> extends Signature {

	public Signature1(String name, Class<T1> type1) {
		super(name, new Class<?>[] { type1 });
	}

}
