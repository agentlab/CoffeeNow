package ru.agentlab.maia.dsl;

import java.util.Map;

@FunctionalInterface
public interface Executable1<T1> extends Executable {

	void execute(T1 v1);

	@Override
	@SuppressWarnings("unchecked")
	default void execute(Map<String, Object> variables, Variable<?>[] usedVariables) {
		String name1 = usedVariables[0].getName();
		Object value1 = variables.get(name1);
		execute((T1) value1);
	}

}
