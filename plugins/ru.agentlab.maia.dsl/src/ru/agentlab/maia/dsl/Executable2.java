package ru.agentlab.maia.dsl;

import java.util.Map;

@FunctionalInterface
public interface Executable2<T1, T2> extends Executable {

	void execute(T1 v1, T2 v2);

	@Override
	@SuppressWarnings("unchecked")
	default void execute(Map<String, Object> variables, Variable<?>[] usedVariables) {
		String name1 = usedVariables[0].getName();
		String name2 = usedVariables[1].getName();
		Object value1 = variables.get(name1);
		Object value2 = variables.get(name2);
		execute((T1) value1, (T2) value2);
	}

}
