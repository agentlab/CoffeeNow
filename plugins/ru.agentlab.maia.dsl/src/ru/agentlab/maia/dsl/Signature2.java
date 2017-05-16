package ru.agentlab.maia.dsl;

public class Signature2<T1, T2> extends Signature {

	public Signature2(String name, Class<T1> type1, Class<T2> type2) {
		super(name, new Class<?>[] { type1, type2 });
	}

}
