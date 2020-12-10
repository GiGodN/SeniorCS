package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/day5.txt");
		Scanner s = new Scanner(f);
		int[] val = new int[908];
		for(int x = 0; x < 908; x++) {
			String line = s.nextLine();
			String row = line.substring(0,7);
			String col = line.substring(7);
			int l, r;
			l = 0; r = 127;
			int rowI = 0;
			for(int i = 0; i < 7; i++) {
				rowI = l + (r - l) / 2;
				switch(row.charAt(i)){
				case 'F':
					if(i == 6) {
						rowI = l;
						break;
					}
					r = rowI;
					break;
				case 'B':
					if(i == 6) {
						rowI = r;
						break;
					}
					l = rowI + 1;
					break;
				}
			}
			l = 0; r = 7;
			int colI = 0;
			for(int i = 0; i < 3; i++) {
				colI = Math.round(l + (r - l) / (float)2);
				switch(col.charAt(i)) {
				case 'R':
					if(i == 2) {
						colI = r;
						break;
					}
					l = colI;
					break;
				case 'L':
					if(i == 2) {
						colI = l;
						break;
					}
					r = colI-1;
					break;
				}
			}
			val[x] = rowI * 8 + colI;
		}
		Arrays.sort(val);
		int l = 0, r = 907, rval = 0;
		while(l <= r ) {
			int mid = (l+r)/2;
			if(val[mid] != mid+36 && val[mid-1] == mid+35) {
				rval = mid+36;
				break;
			}
			if (val[mid] == mid+36) {
				l = mid-1;
			}
			else {
				r = mid+1;
			}
		}
		System.out.println(rval);
		s.close();
	}

}
