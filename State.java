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


	public static void EpsilonClose(State state, Set<State> states){
		states.add(state);
		List<Transition> transitions = state.getTransitions();
		for (Transition transition : transitions) {
			if(transition.isEp() && !states.contains(transition.getNext())){
				EpsilonClose(transition.getNext(),states);
			}
		}

	}

	public Set<State> transition(String input){
		Set<State> toReturn = new HashSet<>();

		for (Transition transition : transitions) {
			if(transition.getOperation().equals(input)){
				State toAdd = transition.getNext();
				toReturn.add(toAdd);
				// check for eps
				toReturn.addAll(toAdd.checkForEpsilons());
			}
			if(transition.isEp()){
//				toReturn.add(transition.getNext());
				Set<State> toAdd = transition.getNext().transition(input);
				if(toAdd.isEmpty()){
					toAdd.add(transition.getNext());
				}
				toReturn.addAll(toAdd);
			}
		}

		return toReturn;
	}

	private Set<State> checkForEpsilons(){
		Set<State> toReturn = new HashSet<>();
		for (Transition transition : transitions) {
			if(transition.isEp()){
				toReturn.add(transition.getNext());
			}
		}

		return toReturn;
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

	/*@Override
	public String toString() {
		return "State{" +
				"transitions=" + transitions +
				", name='" + name + '\'' +
				'}';
	}*/

	@Override
	public String toString(){
		return name;
	}
}
