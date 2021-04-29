package circuitBoard;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {
	
	private Storage<TraceState> store;
	private CircuitBoard board;
	private ArrayList<TraceState> best;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); //create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		// See https://en.wikipedia.org/wiki/Usage_message for format and content guidance
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		//TODO: parse and validate command line args - first validation provided
		if (args.length != 3) {
			printUsage();
			return; //exit the constructor immediately
		}
		switch(args[0]) {
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
			System.err.println("Issue parsing file, may be an invalid file name");
			return;
		}
		best = new ArrayList<TraceState>();
		findPath();
		switch(args[1]) {
		case "-c":
			for(TraceState path : best) {
				CircuitBoard temp = new CircuitBoard(board);
				for(Point p : path.getPath()) {
					temp.makeTrace((int)p.getX(), (int)p.getY());
				}
				System.out.println(temp + "\n");
			}
			break;
		case "-g":
			System.out.println("GUI mode is currently unsupported");
			break;
		}
	}
	
	private void findPath() {
		double startX = board.getStartingPoint().getX(), startY = board.getStartingPoint().getY();
		if(startY-1 >= 0 && board.isOpen((int)startY-1, (int)startX)) store.store(new TraceState(board, (int)startY-1, (int)startX));
		if(startY+1 <= board.numRows() && board.isOpen((int)startY+1, (int)startX)) store.store(new TraceState(board, (int)startY+1, (int)startX));
		if(startX-1 >= 0 && board.isOpen((int)startY, (int)startX-1)) store.store(new TraceState(board, (int)startY, (int)startX-1));
		if(startX+1 <= board.numCols() && board.isOpen((int)startY, (int)startX+1)) store.store(new TraceState(board, (int)startY, (int)startX+1));
		while(!store.isEmpty()) {
			TraceState state = store.retrieve();
			double y = state.getRow(), x = state.getCol();
			double targetY = board.getEndingPoint().getY(), targetX = board.getEndingPoint().getX();
			if(state.isComplete()) {
				if(best.isEmpty() || best.get(0).pathLength() == state.pathLength()) best.add(state);
				else if(best.get(0).pathLength() > state.pathLength()) {
					best.clear();
					best.add(state);
				}
				continue;
			}
			if(best.isEmpty() || best.get(0).pathLength() >  state.pathLength()) {
				double lastX = state.getPath().get(state.pathLength()-1).getX(), lastY = state.getPath().get(state.pathLength()-1).getY();
				if(y-1 >= 0 && y-1 != lastY && state.isOpen((int)y-1, (int)x)) store.store(new TraceState(state, (int)y-1, (int)x));
				if(y+1 <= board.numRows() && y+1 != lastY && state.isOpen((int)y+1, (int)x)) store.store(new TraceState(state, (int)y+1, (int)x));
				if(x-1 >= 0 && x-1 != lastX && state.isOpen((int)y, (int)x-1)) store.store(new TraceState(state, (int)y, (int)x-1));
				if(x+1 <= board.numCols() && x+1 != lastX && state.isOpen((int)y, (int)x+1)) store.store(new TraceState(state, (int)y, (int)x+1));
			}
		}
	}
	
} // class CircuitTracer
