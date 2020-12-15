package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10 {

	private static int num1 = 0, num3 = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day10.txt");
		Scanner s = new Scanner(f);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		while(s.hasNext()) {
			list.add(s.nextInt());
		}
		Collections.sort(list);
		list.add(list.get(list.size()-1)+3);
		part1(list);
		System.out.println(num1*num3);
		part2(list);
		s.close();
	}
	
	public static boolean part1(ArrayList<Integer> list) {
		num1 = 0; num3 = 0;
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i+1)-list.get(i) == 3) {
				num3++;
			}
			else if (list.get(i+1)-list.get(i) == 1) {
				num1++;
			}
			else return false;
		}
		return true;
	}
	
	public static void part2(ArrayList<Integer> list) {
		long sum = 0;
		ArrayList<Integer> listC = (ArrayList<Integer>)list.clone();
		listC.remove(0); listC.remove(listC.size()-1);
		for(int i = 0; i < listC.size(); i++) {
			listC = (ArrayList<Integer>)list.clone();
			for(int x = 0; x < i; x++) {
				listC = (ArrayList<Integer>)list.clone();
				for(int y = 0; y < x; y++) {
					listC.remove(y);
					if(part1(listC))sum++;
				}
			}
		}
		//System.out.println(sum);
	}
	
}
