package ru.agentlab.maia.dsl;

public class Dsl {

	public static ActionBuilder<Executable0> action(String name) {
		return new ActionBuilder<Executable0>(name);
	}

	public static <T1> ActionBuilder<Executable1<T1>> action(Signature1<T1> name, Variable<T1> variable1) {
		return new ActionBuilder<Executable1<T1>>(name.getName(), variable1);
	}

	public static <T1, T2> ActionBuilder<Executable2<T1, T2>> action(Signature2<T1, T2> signature,
			Variable<T1> variable1, Variable<T2> variable2) {
		return new ActionBuilder<Executable2<T1, T2>>(signature.getName(), variable1, variable2);
	}

	public static <T1, T2, T3> ActionBuilder<Executable3<T1, T2, T3>> action(String name, Variable<T1> variable1,
			Variable<T2> variable2, Variable<T3> variable3) {
		return new ActionBuilder<Executable3<T1, T2, T3>>(name, variable1, variable2, variable3);
	}

	public static <T1, T2, T3, T4> ActionBuilder<Executable4<T1, T2, T3, T4>> action(String name,
			Variable<T1> variable1, Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4) {
		return new ActionBuilder<Executable4<T1, T2, T3, T4>>(name, variable1, variable2, variable3, variable4);
	}

	public static <T1, T2, T3, T4, T5> ActionBuilder<Executable5<T1, T2, T3, T4, T5>> action(String name,
			Variable<T1> variable1, Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4,
			Variable<T5> variable5) {
		return new ActionBuilder<Executable5<T1, T2, T3, T4, T5>>(
				name,
				variable1,
				variable2,
				variable3,
				variable4,
				variable5);
	}

	public static <T1, T2, T3, T4, T5, T6> ActionBuilder<Executable6<T1, T2, T3, T4, T5, T6>> action(String name,
			Variable<T1> variable1, Variable<T2> variable2, Variable<T3> variable3, Variable<T4> variable4,
			Variable<T5> variable5, Variable<T6> variable6) {
		return new ActionBuilder<Executable6<T1, T2, T3, T4, T5, T6>>(
				name,
				variable1,
				variable2,
				variable3,
				variable4,
				variable5,
				variable6);
	}

	public static ActionBuilder<ExecutableN> action(SignatureN name, Variable<?>... variables) {
		return new ActionBuilder<ExecutableN>(name.getName(), variables);
	}

	public static PlanBuilder plan() {
		return new PlanBuilder();
	}

	public static PlanBuilder plan(String name) {
		return new PlanBuilder().name(name);
	}

	public static <T1> PlanBuilder plan(Signature1<T1> signature, Variable<T1> variable1) {
		return new PlanBuilder().name(signature.getName());
	}

	public static <T1, T2> PlanBuilder plan(Signature2<T1, T2> signature, Variable<T1> variable1,
			Variable<T2> variable2) {
		return new PlanBuilder().name(signature.getName());
	}

	public static PlanBuilder plan(String name, Variable<?>... variable) {
		return new PlanBuilder().name(name);
	}

	public static Predicate not(Atom atoms) {
		return new PredicateNot(atoms);
	}

	public static Predicate and(Atom... atoms) {
		return new PredicateAnd(atoms);
	}

	public static Predicate or(Atom... atoms) {
		return new PredicateOr(atoms);
	}

	public static Predicate predicate(String name) {
		return new Predicate(name, new Atom[0]);
	}

	public static <T1> Predicate predicate(Signature1<T1> signature, Atom atom) {
		return null;
	}

	public static <T1, T2> Predicate predicate(Signature2<T1, T2> signature, Atom atom1, Atom atom2) {
		return null;
	}

	public static Predicate predicate(Signature0 signature) {
		return null;
	}

	public static Signature0 signature(String name) {
		return new Signature0(name);
	}

	public static <T1> Signature1<T1> signature(String name, Class<T1> type1) {
		return new Signature1<T1>(name, type1);
	}

	public static <T1, T2> Signature2<T1, T2> signature(String name, Class<T1> type1, Class<T2> type2) {
		return new Signature2<T1, T2>(name, type1, type2);
	}

	public static SignatureN signature(String name, Class<?>... types) {
		return new SignatureN(name);
	}

	public static Predicate predicate(String name, Atom... atoms) {
		return new Predicate(name, atoms);
	}

	public static <T1, T2> Object achieve(Signature2<T1, T2> signature, Variable<T1> variable1,
			Variable<T2> variable2) {
		return new Predicate(signature.getName(), variable1, variable2);
	}

	public static <T1> Object achieve(Signature1<T1> signature, Atom atom) {
		return new Predicate(signature.getName(), atom);
	}

	public static Object achieve(String name, Variable<?>... variable) {
		return new Predicate(name, variable);
	}

	public static <T1> Object invoke(Signature1<T1> signature, Atom atom) {
		return new Predicate(signature.getName(), atom);
	}

	public static Object invoke(String name, Variable<?>... variable) {
		return new Predicate(name, variable);
	}

	public static Variable<Object> variable(String name) {
		return new Variable<Object>(name, Object.class);
	}

	public static <T> Variable<T> variable(String name, Class<T> clazz) {
		return new Variable<T>(name, clazz);
	}

	public static <T> Constant<T> constant(T value) {
		return new Constant<T>(value);
	}
}