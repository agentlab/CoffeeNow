package ru.agentlab.maia.dsl.examples;

public class BlockWorldServiceImpl implements BlockWorldService {

	@Override
	public void pickup(String block) {
		System.out.println("PICKUP " + block);
	}

	@Override
	public void putdown(String block) {
		System.out.println("PUTDOWN " + block);
	}

	@Override
	public void stack(String top, String bottom) {
		System.out.println("STACK " + top + " " + bottom);
	}

	@Override
	public void unstack(String top, String bottom) {
		System.out.println("UNSTACK " + top + " " + bottom);
	}

}
