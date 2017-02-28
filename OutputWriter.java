import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class OutputWriter {
	private Set<String> alphabet;
	private Set<Set<State>> states;
	private Set<Set<State>> acceptStates;
	private Set<State> initialStates;
	private Map<Set<State>, Map<String,Set<State>>> transitions;
	private String fileName;
	private PrintWriter printWriter;


	public OutputWriter(Converter converter, String outputFilename){
		alphabet = converter.getAlphabet();
		states = converter.getNewStates();
		acceptStates = converter.getNewAcceptStates();
		initialStates = converter.getNewInitialStates();
		fileName = outputFilename;
		transitions = converter.getTransitions();

	}

	public void writeFile(){
		try {
			printWriter = new PrintWriter(new FileWriter(fileName, false));
		} catch (IOException e) {
			System.out.println("Error opening file: " + fileName);
			e.printStackTrace();
			return;
		}
		this.writeStates();
		printWriter.print("\n");
		this.writeAlphabet();
		printWriter.print("\n");
		this.writeInitialState();
		printWriter.print("\n");
		this.writeAcceptStates();
		printWriter.print("\n");
		this.writeTransitions();


		printWriter.close();
	}

	private void writeStates() {
		for (Set<State> stateSet : states) {
			printWriter.print(concatSet(stateSet));
			printWriter.print('\t');
		}
	}

	private void writeAlphabet() {
		printWriter.print(String.join("\t", alphabet));
	}

	private void writeInitialState() {
		printWriter.print(concatSet(initialStates));
	}

	private void writeAcceptStates() {
		for (Set<State> stateSet : acceptStates) {
			printWriter.print(concatSet(stateSet));
			printWriter.print('\t');
		}
	}

	private void writeTransitions() {
		for (Set<State> stateSet : transitions.keySet()) {
			for (String s : transitions.get(stateSet).keySet()) {
				Set<State> nextSet = transitions.get(stateSet).get(s);
				printWriter.print(createTransition(stateSet,s,nextSet));
				printWriter.print('\n');
			}
		}
	}

	private String concatSet(Set<State> states){
		StringBuilder build = new StringBuilder();
		build.append('{');
		for (State state : states) {
			build.append(state.getName());
			build.append(',');
		}
		if(build.length() == 1){
			build.append("EM");
		}else{
			build.setLength(build.length()-1);
		}
		build.append('}');
		return build.toString();
	}

	private String createTransition(Set<State> start, String input, Set<State> end){
		return String.format("%1s,%2s = %3s", concatSet(start), input, concatSet(end));
	}

}
