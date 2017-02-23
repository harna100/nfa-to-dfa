import java.util.*;

public class State {
	private List<Transition> transitions;
	private String name;

	public State(String _name){
		name = _name;
		transitions = new ArrayList<>();
	}

	public void addTransition(String operation, State next){
		transitions.add(new Transition(operation, next));
	}

	public String getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

/*	public static Set<State> EpsilionClose(State state){
		Set<State> states = new HashSet<>();
		states.add(state);
		List<Transition> transitions = state.getTransitions();
		for (Transition transition : transitions) {
			if(transition.isEp()){
				states.addAll(EpsilionClose(transition.getNext()));
			}
		}

		return states;

	}*/

	public static void EpsilionClose(State state, Set<State> states){
//		Set<State> states = new HashSet<>();
		states.add(state);
		List<Transition> transitions = state.getTransitions();
		for (Transition transition : transitions) {
			if(transition.isEp() && !states.contains(transition.getNext())){
				EpsilionClose(transition.getNext(),states);
			}
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		State state = (State) o;

		return name.equals(state.name);

	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "State{" +
				"transitions=" + transitions +
				", name='" + name + '\'' +
				'}';
	}
}
