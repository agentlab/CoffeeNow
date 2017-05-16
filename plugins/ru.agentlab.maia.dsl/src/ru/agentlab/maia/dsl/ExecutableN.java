package ru.agentlab.maia.dsl;

import java.util.Map;

@FunctionalInterface
public interface ExecutableN extends Executable {

	void execute(Map<String, Object> variables);

	@Override
	default void execute(Map<String, Object> variables, Variable<?>[] usedVariables) {
		execute(variables);
	}

}
