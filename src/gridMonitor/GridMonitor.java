package gridMonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author      Noah John <noahmjohn@gmail.com>
 * @version     1.0
 */

public class GridMonitor implements GridMonitorInterface {

	/**
	 * the private field that stores the grid to monitor <p> generated in the constructor
	 */
	private double[][] grid;
	
	/**
	 * The Constructor, initiates private value grid based of pass filename
	 * 
	 * @param filename the name of a file in the gridMonitor package
	 * @return null, a constructor
	 * @throws FileNotFoundException
	 */
	public GridMonitor(String filename) throws FileNotFoundException {
		File f = new File("src/gridMonitor/" + filename);
		Scanner scan = new Scanner(f);
		int r = scan.nextInt();
		int c = scan.nextInt();
		grid = new double[r][c];
		scan.nextLine();
		for(int y = 0; y < r; y++) {
			for(int x = 0; x < c; x++) {
				grid[y][x] = scan.nextDouble();
			}
		}
		scan.close();
	}
	
	/**
	 * getter for the unmodified grid imported from file
	 * 
	 * @return the value of the grid field
	 */
	@Override
	public double[][] getBaseGrid() {
		double[][] ret = new double[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++) {
			ret[i] = grid[i].clone();
		}
		return ret;
		
	}

	/**
	 * calculates the sum of the surrounding cells <p> if their is no cell on a side it will us the original cells value
	 * 
	 * @return the surrounding cells summed into the cell
	 */
	@Override
	public double[][] getSurroundingSumGrid() {
		double[][] rVal = new double[grid.length][grid[0].length];
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				int tempUpI, tempDownI, tempLeftI, tempRightI;
				if (y-1 < 0) tempUpI = 0; else tempUpI = y-1;
				if (y+1 > grid.length-1) tempDownI = grid.length-1; else tempDownI = y+1;
				if (x-1 < 0) tempLeftI = 0; else tempLeftI = x-1;
				if (x+1 > grid[0].length-1) tempRightI = grid[0].length-1; else tempRightI = x+1;
				double up = grid[tempUpI][x];
				double down = grid[tempDownI][x];
				double left = grid[y][tempLeftI];
				double right = grid[y][tempRightI];
				rVal[y][x] = up + down + left + right;
			}
		}
		return rVal;
	}

	/**
	 * uses the getSums method to calculate the average value of each cell
	 * 
	 * @return the grid of averages
	 */
	@Override
	public double[][] getSurroundingAvgGrid() {
		double[][] sum = this.getSurroundingSumGrid();
		double[][] rVal = new double[grid.length][grid[0].length];
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				rVal[y][x] = sum[y][x]/4;
			}
		}
		return rVal;
	}

	/**
	 * creates the delta grid based off of the getAvg method
	 * 
	 * @return the calculated delta grid
	 */
	@Override
	public double[][] getDeltaGrid() {
		double[][] avg = this.getSurroundingAvgGrid();
		double[][] rVal = new double[grid.length][grid[0].length];
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				rVal[y][x] = Math.abs(avg[y][x]/2);
			}
		}
		return rVal;
	}

	/**
	 * Calculates witch cells are in dangers
	 * 
	 * @return each cell returns true of false, a cell will return true if it is failing and false if it is working functionaly
	 */
	@Override
	public boolean[][] getDangerGrid() {
		boolean[][] rVal = new boolean[grid.length][grid[0].length];
		double[][] avg = this.getSurroundingAvgGrid();
		double[][] delta = this.getDeltaGrid();
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				rVal[y][x] = !((avg[y][x]-delta[y][x] < grid[y][x]) &&(grid[y][x] < avg[y][x]+delta[y][x]));
			}
		}
		return rVal;
	}

}
