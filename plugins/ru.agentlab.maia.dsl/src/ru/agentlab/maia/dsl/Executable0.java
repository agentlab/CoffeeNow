package ru.agentlab.maia.dsl;

import java.util.Map;

@FunctionalInterface
public interface Executable0 extends Executable {

	void execute();

	@Override
	default void execute(Map<String, Object> variables, Variable<?>[] usedVariables) {
		execute();
	}

}
