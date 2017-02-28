import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;


public class OutputWriter {
	private Set<String> alphabet;
	private Set<Set<State>> states;
	private Set<Set<State>> acceptStates;
	private Set<State> initialStates;
	private String fileName;
	private PrintWriter printWriter;


	public OutputWriter(Converter converter, String outputFilename){
		alphabet = converter.getAlphabet();
		states = converter.getNewStates();
		acceptStates = converter.getNewAcceptStates();
		initialStates = converter.getNewInitialStates();
		fileName = outputFilename;
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
		this.writeAlphabet();
		this.writeInitialState();
		this.writeAcceptStates();
		this.writeTransitions();

		printWriter.close();
	}

	private void writeStates() {

	}

	private void writeAlphabet() {

	}

	private void writeInitialState() {

	}

	private void writeAcceptStates() {
	}

	private void writeTransitions() {

	}


}
