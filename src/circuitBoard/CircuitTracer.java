package circuitBoard;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board as
 * read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to a
 * GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	private Storage<TraceState> store;
	private CircuitBoard board;
	private ArrayList<TraceState> best;

	/**
	 * launch the program
	 * 
	 * @param args three required arguments: first arg: -s for stack or -q for queue
	 *             second arg: -c for console output or -g for GUI output third arg:
	 *             input file name
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); // create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		// TODO: print out clear usage instructions when there are problems with
		// any command line args
		// See https://en.wikipedia.org/wiki/Usage_message for format and content
		// guidance
	}

	/**
	 * Set up the CircuitBoard and all other components based on command line
	 * arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		// TODO: parse and validate command line args - first validation provided
		if (args.length != 3) {
			printUsage();
			return; // exit the constructor immediately
		}
		switch (args[0]) {
		case "-s":
			store = new Storage<TraceState>(Storage.DataStructure.stack);
			break;
		case "-q":
			store = new Storage<TraceState>(Storage.DataStructure.queue);
			break;
		}
		try {
			board = new CircuitBoard(args[2]);
		}
		catch (Exception e) {
			System.err.println("Error pasing file, error thrown was: " + e.toString());
			return;
		}
		best = new ArrayList<TraceState>();
		findPath();
		switch (args[1]) {
		case "-c":
			String s = "";
			for (TraceState path : best) {
				CircuitBoard temp = new CircuitBoard(board);
				for (Point p : path.getPath()) {
					temp.makeTrace((int) p.getX(), (int) p.getY());
				}
				s += temp + "\n";
			}
			System.out.print(s.strip());
			break;
		case "-g":
			System.out.println("GUI mode is currently unsupported");
			break;
		}
	}

	private void findPath() {
		double startX = board.getStartingPoint().getX(), startY = board.getStartingPoint().getY();
		if (board.isOpen((int) startX, (int) startY - 1))
			store.store(new TraceState(board, (int) startX, (int) startY - 1));
		if (board.isOpen((int) startX, (int) startY + 1))
			store.store(new TraceState(board, (int) startX, (int) startY + 1));
		if (board.isOpen((int) startX - 1, (int) startY))
			store.store(new TraceState(board, (int) startX - 1, (int) startY));
		if (board.isOpen((int) startX + 1, (int) startY))
			store.store(new TraceState(board, (int) startX + 1, (int) startY));
		while (!store.isEmpty()) {
			TraceState state = store.retrieve();
			double y = state.getCol(), x = state.getRow();
			if (state.isComplete()) {
				if (best.isEmpty() || best.get(0).pathLength() == state.pathLength())
					best.add(state);
				else if (best.get(0).pathLength() > state.pathLength()) {
					best.clear();
					best.add(state);
				}
				continue;
			}
			if (best.isEmpty() || best.get(0).pathLength() > state.pathLength()) {
				double lastX, lastY;
				try {
					lastX = state.getPath().get(state.pathLength() - 2).getX();
					lastY = state.getPath().get(state.pathLength() - 1).getY();
				}
				catch (IndexOutOfBoundsException e) {
					lastX = -1;
					lastY = -1;
				}
				if (y - 1 != lastY && state.isOpen((int) x, (int) y - 1))
					store.store(new TraceState(state, (int) x, (int) y - 1));
				if (y + 1 != lastY && state.isOpen((int) x, (int) y + 1))
					store.store(new TraceState(state, (int) x, (int) y + 1));
				if (x - 1 != lastX && state.isOpen((int) x - 1, (int) y))
					store.store(new TraceState(state, (int) x - 1, (int) y));
				if (x + 1 != lastX && state.isOpen((int) x + 1, (int) y))
					store.store(new TraceState(state, (int) x + 1, (int) y));
			}
		}
	}

} // class CircuitTracer
