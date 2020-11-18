package gridMonitor;

import java.io.FileNotFoundException;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException {
		GridMonitor g = new GridMonitor("sample.txt");
		boolean[][] r = g.getDangerGrid();
		for (int x = 0; x<r.length; x++) {
			for (int y = 0; y<r[0].length; y++) {
				System.out.print(r[x][y] + " ");
			}
			System.out.println();
		}
		
	}

}
