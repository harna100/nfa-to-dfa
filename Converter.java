import java.util.*;

public class Converter {
	private Set<String> alphabet;
	private Map<String, State> originalStates;
	private Map<String, State> originalAcceptStates;
	private State originalInitialState;
	private Set<Set<State>> usedStates;

	private Set<State> newInitialStates;
	private Set<Set<State>> newStates;
	private Set<Set<State>> newAcceptStates;
	private Map<Set<State>,Map<String,Set<State>>> transitions;


	public Converter(FileParser fp){
		alphabet = fp.getAlphabet();
		originalStates = fp.getStates();
		originalAcceptStates = fp.getAcceptStates();
		originalInitialState = fp.getInitialState();
		usedStates = new HashSet<>();
		newAcceptStates = new HashSet<>();
		transitions = new HashMap<>();
	}

	public void convert(){
		this.convertInitialStates();
		this.convertStates();
		this.findNewAcceptStates();

	}

	private void convertStates() {
		while(newStates.iterator().hasNext()){
			Set<State> curr = newStates.iterator().next();
			if(usedStates.contains(curr)){
				newStates.remove(curr);
				continue;
			}
			for (String s : alphabet) {
				Set<State> nextStates = new HashSet<>();
				for (State state : curr) {
					Set<State> toAdd = state.transition(s);
					nextStates.addAll(toAdd);
				}
				newStates.add(nextStates);
				Map<String, Set<State>> temp = transitions.getOrDefault(curr, new HashMap<>());
				temp.put(s, nextStates);
				transitions.put(curr, temp);
			}
			usedStates.add(curr);
			newStates.remove(curr);
		}
		newStates = usedStates;
	}

	private void findNewAcceptStates() {
		for (Set<State> stateSet : newStates) {
			for (State state : stateSet) {
				if(originalAcceptStates.containsValue(state)){
					newAcceptStates.add(stateSet);
					break;
				}
			}
		}
	}

	private void convertInitialStates(){
		Set<State> newInitials = new HashSet<>();
		State.EpsilonClose(this.originalInitialState, newInitials);
		newStates = new HashSet<>();
		newStates.add(newInitials);
		this.newInitialStates = newInitials;
	}


	public Set<String> getAlphabet() {
		return alphabet;
	}

	public Set<State> getNewInitialStates() {
		return newInitialStates;
	}

	public Set<Set<State>> getNewStates() {
		return newStates;
	}

	public Set<Set<State>> getNewAcceptStates() {
		return newAcceptStates;
	}

	public Map<Set<State>, Map<String, Set<State>>> getTransitions() {
		return transitions;
	}

	@Override
	public String toString() {
		return "Converter{" +
				"alphabet=" + alphabet +
				", originalStates=" + originalStates +
				", originalAcceptStates=" + originalAcceptStates +
				", originalInitialState=" + originalInitialState +
				", usedStates=" + usedStates +
				", newInitialStates=" + newInitialStates +
				", newStates=" + newStates +
				", newAcceptStates=" + newAcceptStates +
				'}';
	}
}
