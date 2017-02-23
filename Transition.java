
public class Transition {
	private String operation;
	private State next;

	public Transition(String operation, State next) {
		this.operation = operation;
		this.next = next;
	}

	@Override
	public String toString() {
		return "Transition{" +
				"operation='" + operation + '\'' +
				", next=" + next.getName() +
				'}';
	}
}
