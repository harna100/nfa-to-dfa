import java.util.Map;
import java.util.Set;

public class Main {
	private Map<String,State> states;
	private Map<String,State> acceptStates;
	private Set<String> alphabet;
	private State initialState;

	public static void main(String[] args) {
		System.out.println("Hello World.");
		FileParser fp = new FileParser(args[0]);
		fp.parseFile();
//		System.out.println(fp.toJSONString());
		System.out.println(fp.toString());
	}


}