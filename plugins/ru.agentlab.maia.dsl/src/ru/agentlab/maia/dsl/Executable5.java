package ru.agentlab.maia.dsl;

import java.util.Map;

@FunctionalInterface
public interface Executable5<T1, T2, T3, T4, T5> extends Executable {

	void execute(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5);

	@Override
	@SuppressWarnings("unchecked")
	default void execute(Map<String, Object> variables, Variable<?>[] usedVariables) {
		String name1 = usedVariables[0].getName();
		String name2 = usedVariables[1].getName();
		String name3 = usedVariables[2].getName();
		String name4 = usedVariables[3].getName();
		String name5 = usedVariables[4].getName();
		Object value1 = variables.get(name1);
		Object value2 = variables.get(name2);
		Object value3 = variables.get(name3);
		Object value4 = variables.get(name4);
		Object value5 = variables.get(name5);
		execute((T1) value1, (T2) value2, (T3) value3, (T4) value4, (T5) value5);
	}

}
