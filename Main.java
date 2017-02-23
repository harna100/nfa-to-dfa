import java.util.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World.");
		FileParser fp = new FileParser(args[0]);
		fp.parseFile();
		System.out.println(fp.toString());
		Set newInitials = convertInitialStates(fp.getInitialState());
		System.out.println("newInitials = " + newInitials);

	}

	public static Set<State> convertInitialStates(State initial){
		Set<State> newInitials = new HashSet<>();
		State.EpsilionClose(initial, newInitials);
		return newInitials;
	}

}