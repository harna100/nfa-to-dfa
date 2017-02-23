
public class Transition {
	private String operation;
	private State next;

	public Transition(String operation, State next) {
		this.operation = operation;
		this.next = next;
	}

	public boolean isEp(){
		return operation.equals("EPS");
	}

	public State getNext() {
		return next;
	}

	@Override
	public String toString() {
		return "Transition{" +
				"operation='" + operation + '\'' +
				", next=" + next.getName() +
				'}';
	}
}
