package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day6.txt");
		Scanner s = new Scanner(f);
		ArrayList<String> tl = new ArrayList<String>();
		int sum = 0;
		while(s.hasNext()) {
			String temp = s.nextLine();
			int[] amt = new int[26];
			if(!temp.equals("")) {
				tl.add(temp);
			}
			else {
				for(int i = 0; i < 26; i++) {
					boolean value = true;
					for(String a : tl) {
						if(!a.contains(Character.toString(i+97))) {
							value = false;
							break;
						}
					}
					if(value) {
						amt[i] = 1;
					}
				}
				for(int i = 0; i < 26; i++) {
					sum += amt[i];
				}
				tl.clear();
			}
		}
		System.out.println(sum);
		s.close();
	}
	
}
