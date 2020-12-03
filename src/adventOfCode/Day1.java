package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
		
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/expences.txt");
		Scanner s = new Scanner(f);
		int[] exp = new int[200];
		for(int i = 0; i < exp.length; i++) {
			exp[i] = s.nextInt();
		}
		for(int a: exp) {
			for(int b: exp) {
				for(int c: exp) {
					if((a + b + c) == 2020) {
						System.out.println(a*b*c);
					}
				}
			}
		}
		s.close();
	}
	
}
