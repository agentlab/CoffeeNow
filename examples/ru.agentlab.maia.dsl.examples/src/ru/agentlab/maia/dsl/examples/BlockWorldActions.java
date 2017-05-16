package ru.agentlab.maia.dsl.examples;

import static ru.agentlab.maia.dsl.Dsl.action;
import static ru.agentlab.maia.dsl.Dsl.and;
import static ru.agentlab.maia.dsl.Dsl.not;
import static ru.agentlab.maia.dsl.Dsl.predicate;
import static ru.agentlab.maia.dsl.Dsl.signature;
import static ru.agentlab.maia.dsl.Dsl.variable;

import javax.inject.Inject;

import ru.agentlab.maia.annotations.Action;
import ru.agentlab.maia.dsl.ActionBuilder;
import ru.agentlab.maia.dsl.Signature0;
import ru.agentlab.maia.dsl.Signature1;
import ru.agentlab.maia.dsl.Signature2;
import ru.agentlab.maia.dsl.Variable;

public class BlockWorldActions {

	// @formatter:off
	public static final Signature2<String, String> ON       = signature("ON", String.class, String.class);
	public static final Signature1<String>         ONTABLE  = signature("ONTABLE", String.class);
	public static final Signature1<String>         CLEAR    = signature("CLEAR", String.class);
	public static final Signature1<String>         HOLDING  = signature("HOLDING", String.class);
	public static final Signature0                 ARMEMPTY = signature("ARMEMPTY");

	public static final Signature2<String, String> UNSTACK  = signature("UNSTACK", String.class, String.class);
	public static final Signature2<String, String> STACK    = signature("STACK", String.class, String.class);
	public static final Signature1<String>         PICKUP   = signature("PICKUP", String.class);
	public static final Signature1<String>         PUTDOWN  = signature("PUTDOWN", String.class);
	// @formatter:on
	
	@Inject
	BlockWorldService service;

	@Action
	public ActionBuilder<?> pickupAction() {
		Variable<String> $block = variable("block", String.class);
		return action(PICKUP, $block)
				.precondition(
					and(
						predicate(ARMEMPTY),
						predicate(CLEAR, $block),
						predicate(ONTABLE, $block)
					)
				)
				.effect(
					and(
						not(
							predicate(ARMEMPTY)
						),
						not(
							predicate(CLEAR, $block)
						),
						not(
							predicate(ONTABLE, $block)
						),
						predicate(HOLDING, $block)
					)
				)
				.execute(service::pickup);
	}

	@Action
	public ActionBuilder<?> putdownAction() {
		Variable<String> $block = variable("block", String.class);
		return action(PUTDOWN, $block)
				.precondition(
						predicate(HOLDING, $block))
				.effect(and(
						predicate(ARMEMPTY),
						not(predicate(HOLDING, $block)),
						predicate(ONTABLE, $block),
						predicate(CLEAR, $block)))
				.execute(service::putdown);
	}

	@Action
	public ActionBuilder<?> stackAction() {
		Variable<String> $top = variable("top", String.class);
		Variable<String> $bottom = variable("bottom", String.class);
		return action(STACK, $top, $bottom)
				.precondition(and(
						predicate(HOLDING, $top),
						predicate(CLEAR, $bottom)))
				.effect(and(
						predicate(ARMEMPTY),
						not(predicate(HOLDING, $top)),
						not(predicate(CLEAR, $bottom)),
						predicate(ON, $top, $bottom),
						predicate(CLEAR, $top)))
				.execute(service::stack);
	}

	@Action
	public ActionBuilder<?> unstackAction() {
		Variable<String> $top = variable("top", String.class);
		Variable<String> $bottom = variable("bottom", String.class);
		return action(UNSTACK, $top, $bottom)
				.precondition(and(
						predicate(ARMEMPTY),
						predicate(CLEAR, $top),
						predicate(ON, $top, $bottom)))
				.effect(and(
						not(predicate(ARMEMPTY)),
						not(predicate(CLEAR, $top)),
						not(predicate(ON, $top, $bottom)),
						predicate(HOLDING, $top),
						predicate(CLEAR, $bottom)))
				.execute(service::unstack);
	}

}
