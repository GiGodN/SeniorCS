package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
		
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/map.txt");
		Scanner s = new Scanner(f);
		String[][] m = new String[323][31];
		for(int i = 0; i < m.length; i++) {
			String temp = s.nextLine();
			for(int i1 = 0; i1 < m[0].length; i1++) {
				m[i][i1] = Character.toString(temp.charAt(i1));
			}
		}
		int x = 0;
		int trees = 0;
		for(int y = 0; y < m.length; y++) {
			if(m[y][x].charAt(0) == '#') trees++;
			x = (x+3)%31;
		}
		System.out.println(trees);
		x = 0;
		int trees1 = 0;
		for(int y = 0; y < m.length; y++) {
			if(m[y][x].charAt(0) == '#') trees1++;
			x = (x+1)%31;
		}
		System.out.println(trees1);
		x=0;
		int trees2 = 0;
		for(int y = 0; y < m.length; y++) {
			if(m[y][x].charAt(0) == '#') trees2++;
			x = (x+5)%31;
		}
		System.out.println(trees2);
		x=0;
		int trees3 = 0;
		for(int y = 0; y < m.length; y++) {
			if(m[y][x].charAt(0) == '#') trees3++;
			x = (x+7)%31;
		}
		System.out.println(trees3);
		x=0;
		int trees4 = 0;
		for(int y = 0; y < m.length; y+=2) {
			if(m[y][x].charAt(0) == '#') trees4++;
			x = (x+1)%31;
		}
		System.out.println(trees4);
		System.out.println((long)trees * trees1 * trees2 * trees3 * trees4);
		s.close();
	}
	
}
