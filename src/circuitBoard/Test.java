package circuitBoard;

public class Test {

	public static void main(String[] args) {
		String[] someArgs = { "-s", "-c", "valid1.dat" };
		CircuitTracer neverUsed = new CircuitTracer(someArgs);
		neverUsed.toString();
	}

}
