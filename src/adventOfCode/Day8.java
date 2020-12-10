package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {

	public static int acc = 0, ptr = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day8.txt");
		Scanner s = new Scanner(f);
		
		ArrayList<String> instrc = new ArrayList<String>();
		ArrayList<Integer> visits = new ArrayList<Integer>();
		while(s.hasNext()) {
			instrc.add(s.nextLine());
			visits.add(0);
		}
		part1((ArrayList<String>)instrc.clone(), (ArrayList<Integer>)visits.clone());
		System.out.println(acc);
		s.close();
	}
	
	public static boolean part1(ArrayList<String> instrc, ArrayList<Integer> visits) {
		boolean running = true;
		while(running) {
			if(visits.get(ptr).equals(1)) {
				running = false;
				return false;
			}
			else if(ptr == 642) {
				running = false;
				return true;
			}
			switch(instrc.get(ptr).substring(0,3)) {
			case "acc":
				acc += Integer.parseInt(instrc.get(ptr).substring(4));
				visits.set(ptr, visits.get(ptr)+1);
				ptr++;
				break;
			case "jmp":
				visits.set(ptr, visits.get(ptr)+1);
				ptr += Integer.parseInt(instrc.get(ptr).substring(4));
				break;
			case "nop":
				visits.set(ptr, visits.get(ptr)+1);
				ptr++;
				break;
			}
		}
		return true;
	}
	
	public static boolean part2(ArrayList<String> instrc, ArrayList<Integer> visits) {
		for(String a : instrc) {
			if(a.substring(0,3).equals("nop") || a.substring(0,3).equals("jmp")) {
				
			}
		}
	}

}
