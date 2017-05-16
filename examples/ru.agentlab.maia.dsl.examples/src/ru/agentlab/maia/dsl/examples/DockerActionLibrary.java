package ru.agentlab.maia.dsl.examples;

import static ru.agentlab.maia.dsl.Dsl.achieve;
import static ru.agentlab.maia.dsl.Dsl.action;
import static ru.agentlab.maia.dsl.Dsl.and;
import static ru.agentlab.maia.dsl.Dsl.constant;
import static ru.agentlab.maia.dsl.Dsl.invoke;
import static ru.agentlab.maia.dsl.Dsl.not;
import static ru.agentlab.maia.dsl.Dsl.plan;
import static ru.agentlab.maia.dsl.Dsl.predicate;
import static ru.agentlab.maia.dsl.Dsl.signature;
import static ru.agentlab.maia.dsl.Dsl.variable;

import ru.agentlab.maia.annotations.Action;
import ru.agentlab.maia.annotations.Plan;
import ru.agentlab.maia.dsl.ActionBuilder;
import ru.agentlab.maia.dsl.PlanBuilder;
import ru.agentlab.maia.dsl.Signature1;
import ru.agentlab.maia.dsl.Signature2;
import ru.agentlab.maia.dsl.SignatureN;
import ru.agentlab.maia.dsl.Variable;

public class DockerActionLibrary {

	// @formatter:off
	public static final Signature1<String>         VIRTUAL_MACHINE            = signature("virtual-machine", String.class);
	public static final Signature1<String>         VIRTUAL_MACHINE_TYPE       = signature("virtual-machine-type", String.class);
	public static final Signature1<Integer>        VIRTUAL_MACHINE_TYPE_NAME  = signature("virtual-machine-type-name", Integer.class);

	public static final Signature2<String, String> START_VIRTUAL_MACHINE      = signature("start-virtual-machine", String.class, String.class);
	public static final SignatureN                 START_VIRTUAL_MACHINE_MORE = signature("start-virtual-machine", String.class, String.class, String.class, String.class, String.class, String.class);
	public static final Signature2<String, String> REPOSITORY_HAS             = signature("repository-has", String.class, String.class);
	public static final Signature2<String, String> DOWNLOADED                 = signature("downloaded", String.class, String.class);
	public static final Signature2<String, String> DEPLOYED                   = signature("deployed", String.class, String.class);
	public static final Signature1<String>         TEST                       = signature("test", String.class);
	// @formatter:on

	@Action
	public ActionBuilder<?> exampleActionFull() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<Integer> $typeNum = variable("type-name", Integer.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.precondition(and(
						not(predicate(VIRTUAL_MACHINE, $name)),
						not(predicate(VIRTUAL_MACHINE_TYPE, $type)),
						not(predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum))))
				.effect(and(
						predicate(VIRTUAL_MACHINE, $name),
						predicate(VIRTUAL_MACHINE_TYPE, $type),
						predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum)))
				.using($name, $type, $typeNum)
				.execute((name, type, typeNum) -> {
					System.out.println(name);
					System.out.println(type);
					System.out.println(typeNum);
				});
	}

	@Action
	public ActionBuilder<?> exampleActionOnlyActionVars() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<Integer> $typeNum = variable("type-name", Integer.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.precondition(and(
						not(predicate(VIRTUAL_MACHINE, $name)),
						not(predicate(VIRTUAL_MACHINE_TYPE, $type)),
						not(predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum))))
				.effect(and(
						predicate(VIRTUAL_MACHINE, $name),
						predicate(VIRTUAL_MACHINE_TYPE, $type),
						predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum)))
				.execute((name, type) -> {
					System.out.println(name);
					System.out.println(type);
				});
	}

	@Action
	public ActionBuilder<?> exampleActionNoVariables() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.using()
				.execute(() -> {
					System.out.println("empty");
				});
	}

	@Action
	public ActionBuilder<?> exampleActionMoreVariables() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<Integer> $typeNum = variable("type-name", Integer.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.using($name, $type, $typeNum, $name, $type, $typeNum, $typeNum)
				.execute(map -> {
					map.forEach((k, v) -> System.out.println(k));
				});
	}

	@Action
	public ActionBuilder<?> exampleActionMoreVariables2() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<Integer> $typeNum = variable("type-name", Integer.class);
		return action(START_VIRTUAL_MACHINE_MORE, $name, $type, $typeNum, $name, $type, $typeNum, $typeNum)
				.execute(map -> {
					map.forEach((k, v) -> System.out.println(k));
				});
	}

	@Action
	public ActionBuilder<?> exampleActionInstanceMethodReference() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.using($name, $type)
				.execute(this::handleInstance);
	}

	@Action
	public ActionBuilder<?> exampleActionClassMethodReference() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.using($name, $type)
				.execute(DockerActionLibrary::handleStatic);
	}

	@Action
	public ActionBuilder<?> exampleActionConstant() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		return action(START_VIRTUAL_MACHINE, $name, $type)
				.effect(and(
						predicate(VIRTUAL_MACHINE, $name),
						predicate(VIRTUAL_MACHINE_TYPE, $type),
						predicate(VIRTUAL_MACHINE_TYPE_NAME, constant("simple-name"))))
				.using($name, $type)
				.execute((name, type) -> {
					System.out.println(name);
					System.out.println(type);
				});
	}

	@Plan
	public PlanBuilder examplePlanFull() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<Integer> $typeNum = variable("type-name", Integer.class);
		return plan(START_VIRTUAL_MACHINE, $name, $type)
				.precondition(and(
						not(predicate(VIRTUAL_MACHINE, $name)),
						not(predicate(VIRTUAL_MACHINE_TYPE, $type)),
						not(predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum))))
				.effect(and(
						predicate(VIRTUAL_MACHINE, $name),
						predicate(VIRTUAL_MACHINE_TYPE, $type),
						predicate(VIRTUAL_MACHINE_TYPE_NAME, $typeNum)))
				.subtasks(
						achieve(TEST, $name),
						achieve(TEST, $name),
						invoke(TEST, $name),
						invoke(TEST, $name),
						invoke(TEST, $name),
						invoke(TEST, $name),
						invoke(TEST, $name));
	}

	@Plan
	public PlanBuilder examplePlanFull2() {
		Variable<String> $name = variable("name", String.class);
		Variable<String> $type = variable("type", String.class);
		Variable<String> $repo = variable("repo", String.class);
		return plan(START_VIRTUAL_MACHINE, $name, $type)
				.precondition(and(
						predicate(REPOSITORY_HAS, $repo, $type)))
				.effect(and(
						predicate(VIRTUAL_MACHINE, $name), 
						predicate(VIRTUAL_MACHINE_TYPE, $type)))
				.subtasks(
						achieve(DOWNLOADED, $repo, $type), 
						achieve(DEPLOYED, $name, $type));
	}

	protected void handleInstance(String name, String type) {
		System.out.println(name);
		System.out.println(type);
	}

	protected static void handleStatic(String name, String type) {
		System.out.println(name);
		System.out.println(type);
	}

}
