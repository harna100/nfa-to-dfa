import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {
	private Map<String,State> states;
	private Map<String,State> acceptStates;
	private Set<String> alphabet;
	private State initialState;
	private String fileName;

	public FileParser(String _fileName) {
		fileName = _fileName;
		states = new HashMap<>();
		acceptStates = new HashMap<>();
		alphabet = new HashSet<>();
	}

	public void parseFile(){
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String[] _states = br.readLine().trim().replaceAll("\\{","").replaceAll("}", "").split("\t");
			for (int i = 0; i < _states.length; ++i) {
				String state = _states[i];
				states.put(state, new State(state));
			}

			Collections.addAll(alphabet, br.readLine().trim().split("\t"));

			initialState = states.get(br.readLine().trim().replaceAll("\\{","").replaceAll("}", ""));

			String[] _acceptStates = br.readLine().trim().split("\t");
			for (int i = 0; i < _acceptStates.length; ++i) {
				String state = _acceptStates[i].replaceAll("\\{","").replaceAll("}", "");
				acceptStates.put(state, states.get(state));
			}

			String currLine;
			while ((currLine = br.readLine()) != null){
				currLine = currLine.replaceAll("\\{","").replaceAll("}", "");
				int commaIDX = currLine.indexOf(",");
				int equalIDX = currLine.indexOf("=");


				String stateString = currLine.substring(0,commaIDX).trim();
				String operationString = currLine.substring(commaIDX+1, equalIDX).trim();
				String endStateString = currLine.substring(equalIDX+1).trim();
//				System.out.println("stateString = '" + stateString + "'");
//				System.out.println("operationString = '" + operationString + "'");
//				System.out.println("endStateString = '" + endStateString + "'");
				states.get(stateString).addTransition(operationString, states.get(endStateString));
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public Map<String, State> getStates() {
		return states;
	}

	public Map<String, State> getAcceptStates() {
		return acceptStates;
	}

	public Set<String> getAlphabet() {
		return alphabet;
	}

	public State getInitialState() {
		return initialState;
	}

	@Override
	public String toString() {
		return "FileParser{" +
				"states=" + states +
				", acceptStates=" + acceptStates +
				", alphabet=" + alphabet +
				", initialState=" + initialState +
				", fileName='" + fileName + '\'' +
				'}';
	}
}
