package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/passwords.txt");
		Scanner s = new Scanner(f);
		String[][] pass = new String[1000][2];
		for(int i = 0; i < pass.length; i++) {
			String temp = s.nextLine();
			pass[i][0] = temp.substring(0, temp.indexOf(':'));
			pass[i][1] = temp.substring(temp.indexOf(':')+1);
		}
		int countP = 0;
		for(String[] a:pass) {
			char r = a[0].charAt(a[0].length()-1);
			int low, high;
			low = Integer.parseInt(a[0].substring(0,a[0].indexOf('-')));
			high = Integer.parseInt(a[0].substring(a[0].indexOf('-')+1, a[0].indexOf(' ')));
			int countS = 0;
			for(int i = 0; i < a[1].length(); i++) {
				if(a[1].charAt(i) == r) {
					countS++;
				}
			}
			if((low <= countS) && (countS <= high)) {
				countP++;
			}
		}
		System.out.println(countP);
		int countP2 = 0;
		for(String[] a:pass) {
			char r = a[0].charAt(a[0].length()-1);
			int low, high;
			low = Integer.parseInt(a[0].substring(0,a[0].indexOf('-')));
			high = Integer.parseInt(a[0].substring(a[0].indexOf('-')+1, a[0].indexOf(' ')));
			if(((a[1].charAt(low) == r) && !(a[1].charAt(high) == r)) || ((a[1].charAt(high) == r) && !(a[1].charAt(low) == r) )) countP2++;
		}
		System.out.println(countP2);
		s.close();
	}
	
}
