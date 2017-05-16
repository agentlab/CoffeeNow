package ru.agentlab.maia.dsl.examples;

public interface BlockWorldService {

	void pickup(String block);

	void putdown(String block);

	void stack(String top, String bottom);

	void unstack(String top, String bottom);

}
