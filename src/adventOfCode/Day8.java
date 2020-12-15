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
		part1(instrc, (ArrayList<Integer>)visits.clone());
		System.out.println(acc);
		part2(instrc, visits);
		s.close();
	}
	
	public static boolean part1(ArrayList<String> instrc, ArrayList<Integer> visits) {
		acc = 0; ptr = 0;
		boolean running = true;
		while(running) {
			if(ptr == 641) {
				running = false;
				return true;
			}
			if(visits.get(ptr).equals(1)) {
				running = false;
				return false;
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
		return false;
	}
	
	public static void part2(ArrayList<String> instrc, ArrayList<Integer> visits) {
		for(int i = 0; i < instrc.size(); i++) {
			ArrayList<String> instrcC = (ArrayList<String>)instrc.clone();
			ArrayList<Integer> visitsC = (ArrayList<Integer>)visits.clone();
			String a = instrc.get(i);
			if(a.substring(0,3).equals("nop")) {
				instrcC.set(i, "jmp" + a.substring(3));
				if(part1(instrcC, visitsC)) {
					System.out.println(acc);
					return;
				}
			}
			else if(a.substring(0,3).equals("jmp")) {
				instrcC.set(i, "nop" + a.substring(3));
				if(part1(instrcC, visitsC)) {
					System.out.println(acc);
					return;
				}
			}
		}
	}

}
