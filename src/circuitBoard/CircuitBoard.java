package circuitBoard;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 * 
 * @author mvail
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	// constants you may find useful
	private final int ROWS; // initialized in constructor
	private final int COLS; // initialized in constructor
	private final char OPEN = 'O'; // capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/**
	 * Construct a CircuitBoard from a given board input file, where the first line
	 * contains the number of rows and columns as ints and each subsequent line is
	 * one row of characters representing the contents of that position. Valid
	 * characters are as follows: 'O' an open position 'X' an occupied, unavailable
	 * position '1' first of two components needing to be connected '2' second of
	 * two components needing to be connected 'T' is not expected in input files -
	 * represents part of the trace connecting components 1 and 2 in the solution
	 * 
	 * @param filename file containing a grid of characters
	 * @throws FileNotFoundException      if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that
	 *                                    prevents reading a valid input file
	 */
	public CircuitBoard(String filename) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(filename));
		// sub scanner to find the size of the board
		Scanner line = new Scanner(fileScan.nextLine());
		try {
			ROWS = line.nextInt();
			COLS = line.nextInt();
			// if the first line has extra values
			if(line.hasNext()) {
				fileScan.close();
				line.close();
				throw new InvalidFileFormatException(filename);
			}
		}
		// if the first line has wrong or not enough values
		catch (InputMismatchException e) {
			line.close();
			throw new InvalidFileFormatException(filename);
		}
		line.close();
		// initiate the board
		board = new char[ROWS][COLS];
		try {
			int y = 0;
			// loop over all the rows
			while (y < ROWS) {
				// create the scanner for the current row
				Scanner nextLine = new Scanner(fileScan.nextLine());
				int x = 0;
				// loop over all the columns in the row
				while (x < COLS) {
					// find the character
					char temp = nextLine.next().charAt(0);
					// make sure its a value char
					if(ALLOWED_CHARS.indexOf(temp) == -1 && temp != 'T') {
						nextLine.close();
						fileScan.close();
						throw new InvalidFileFormatException(filename);
					}
					// add it to the board
					board[y][x] = temp;
					// if its the starting point, and it is null, instantiate the starting point
					if(temp == '1' && startingPoint == null)
						startingPoint = new Point(y, x);
					// if there already is a staring node and it finds another, throw a file format
					// exception
					else if(startingPoint != null && temp == '1') {
						nextLine.close();
						fileScan.close();
						throw new InvalidFileFormatException(filename);
					}
					// if it finds a ending point, and it is null, instantiate the ending point
					if(temp == '2' && endingPoint == null)
						endingPoint = new Point(y, x);
					// if there is already a ending node and it finds another ending node, throw a
					// file format exception
					else if(endingPoint != null && temp == '2') {
						nextLine.close();
						fileScan.close();
						throw new InvalidFileFormatException(filename);
					}
					// increment the column pointer
					x++;
				}
				// if there are more values the columns throw an error
				if(nextLine.hasNext()) {
					nextLine.close();
					fileScan.close();
					throw new InvalidFileFormatException(filename);
				}
				// increment the rows
				y++;
				nextLine.close();
			}
			// if there are more rows than expected or either of the Points are null throw
			// an exception
			if(fileScan.hasNext() || startingPoint == null || endingPoint == null) {
				fileScan.close();
				throw new InvalidFileFormatException(filename);
			}
			fileScan.close();
		}
		// if any of the rows or columns are to short catch the null pointer and throw a
		// invalid file format exception
		catch (NoSuchElementException e) {
			throw new InvalidFileFormatException(filename);
		}
	}

	/**
	 * Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/**
	 * utility method for copy constructor
	 * 
	 * @return copy of board array
	 */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}

	/**
	 * Return the char at board position x,y
	 * 
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}

	/**
	 * Return whether given board position is open
	 * 
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open
	 */
	public boolean isOpen(int row, int col) {
		if(row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}

	/**
	 * Set given position to be a 'T'
	 * 
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if(isOpen(row, col)) {
			board[row][col] = TRACE;
		}
		else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}

	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}

	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}

	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}

	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}

}// class CircuitBoard
