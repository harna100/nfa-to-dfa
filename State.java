import java.util.ArrayList;
import java.util.List;

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
