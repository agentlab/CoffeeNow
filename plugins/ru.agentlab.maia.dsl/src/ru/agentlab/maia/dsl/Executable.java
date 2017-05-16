package ru.agentlab.maia.dsl;

import java.util.Map;

interface Executable {

	void execute(Map<String, Object> variables, Variable<?>[] usedVariables);

}
