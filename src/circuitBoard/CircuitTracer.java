package circuitBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private JFrame frame;
	private final int WIDTH = 900, HEIGHT = 500;
	private JPanel boardContainer;

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
		System.out.println("Usage: java CircuitTracer [-q queue | -s stack ] [-c console | -g gui] filename\n" );
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
		//determine whether to use stacks or queues
		switch (args[0]) {
		case "-s":
			store = new Storage<TraceState>(Storage.DataStructure.stack);
			break;
		case "-q":
			store = new Storage<TraceState>(Storage.DataStructure.queue);
			break;
		default:
			printUsage();
			return;
		}
		try {
			//init the circuit board
			board = new CircuitBoard(args[2]);
		}
		catch (Exception e) {
			System.err.println("Error pasing file, error thrown was: " + e.toString());
			return;
		}
		best = new ArrayList<TraceState>();
		//run the search algorithm
		findPath();
		switch (args[1]) {
		case "-c":
			//print output to the command line
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
			//create the window and set it to visible
			initWindow();
			frame.setVisible(true);
			break;
		default:
			printUsage();
			return;
		}
	}

	private void initWindow() {
		frame = new JFrame("Results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Help");
		JMenuItem m1i1 = new JMenuItem("Quit");
		m1i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		m1.add(m1i1);
		JMenuItem m2i1 = new JMenuItem("About...");
		m2i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Circuit Tracing Search Program\\nWritten by Noah John(GitHub.com/GiGodN/)");
			}
		});
		m2.add(m2i1);
		mb.add(m1);
		mb.add(m2);
		mb.setPreferredSize(new Dimension(100, 25));
		frame.add(BorderLayout.NORTH, mb);
		JPanel subWindow = new JPanel();
		subWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT-25));
		initInternalWindow(subWindow);
		frame.add(BorderLayout.CENTER, subWindow);
	}
	
	private void initInternalWindow(JPanel frame) {
		frame.setLayout(new GridLayout(1, 3));
		boardContainer = new JPanel();
		initBoardContainer(-1);
		boardContainer.setVisible(true);
		GridBagConstraints boardC = new GridBagConstraints();
		boardC.gridwidth = GridBagConstraints.RELATIVE;
		frame.add(boardContainer, boardC);
		JPanel listPanel = initListPanel();
		frame.add(listPanel);
	}
	
	private void initBoardContainer(int index) {
		boardContainer.removeAll();
		boardContainer.setLayout(new GridLayout(this.board.numRows(), this.board.numCols()));
		if(index == -1) {
			CircuitBoard temp = new CircuitBoard(this.board);
			Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
			for(int y = 0; y < this.board.numRows(); y++) {
				for(int x = 0; x < this.board.numCols(); x++) {
					JPanel p = new JPanel();
					JLabel l = new JLabel(temp.charAt(y, x) + "");
					l.setFont(f);
					if(temp.charAt(y, x) == 'T') l.setForeground(Color.RED);
					p.add(l);
					p.setVisible(true);
					boardContainer.add(p);
				}
			}
			return;
		}
		CircuitBoard temp = new CircuitBoard(this.board);
		for (Point p : best.get(index).getPath()) {
			temp.makeTrace((int) p.getX(), (int) p.getY());
		}
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
		for(int y = 0; y < this.board.numRows(); y++) {
			for(int x = 0; x < this.board.numCols(); x++) {
				JPanel p = new JPanel();
				JLabel l = new JLabel(temp.charAt(y, x) + "");
				l.setFont(f);
				if(temp.charAt(y, x) == 'T') l.setForeground(Color.RED);
				p.add(l);
				p.setVisible(true);
				boardContainer.add(p);
			}
		}
	}
	
	private JPanel initListPanel() {
		JPanel frame = new JPanel();
		ArrayList<String> names = new ArrayList<String>();
		names.add("Initial");
		for(int i = 1; i <= best.size(); i++) {
			names.add("Solution " + i);
		}
		JList<String> list = new JList<String>(names.toArray(new String[names.size()]));
		list.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				initBoardContainer(list.getSelectedIndex()-1);
				boardContainer.validate();
				boardContainer.repaint();
			}
		});
		frame.add(list);
		return frame;
	}
	
	/**
	 * Finds the path from start to finish
	 */
	private void findPath() {
		//find the starting point
		double startX = board.getStartingPoint().getX(), startY = board.getStartingPoint().getY();
		//create the starting traces in non-occupied spaces
		if (board.isOpen((int) startX, (int) startY - 1))
			store.store(new TraceState(board, (int) startX, (int) startY - 1));
		if (board.isOpen((int) startX, (int) startY + 1))
			store.store(new TraceState(board, (int) startX, (int) startY + 1));
		if (board.isOpen((int) startX - 1, (int) startY))
			store.store(new TraceState(board, (int) startX - 1, (int) startY));
		if (board.isOpen((int) startX + 1, (int) startY))
			store.store(new TraceState(board, (int) startX + 1, (int) startY));
		//while path is not found
		while (!store.isEmpty()) {
			TraceState state = store.retrieve();
			//get current x and y
			double y = state.getCol(), x = state.getRow();
			//if the path is a valid path add it to the best list
			if (state.isComplete()) {
				if (best.isEmpty() || best.get(0).pathLength() == state.pathLength())
					best.add(state);
				else if (best.get(0).pathLength() > state.pathLength()) {
					best.clear();
					best.add(state);
				}
				continue;
			}
			//if the path goes longer then the best path don't add new traces
			if (best.isEmpty() || best.get(0).pathLength() > state.pathLength()) {
				double lastX, lastY;
				//find the position of the previous trace in the path if the path is not empty
				if(state.getPath().size() >=1) {
					lastX = state.getPath().get(state.pathLength() - 1).getX();
					lastY = state.getPath().get(state.pathLength() - 1).getY();
				}
				//if the path is empty instantiate the last position to garbage
				else {
					lastX = -1;
					lastY = -1;
				}
				//create the next trace states needed
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
