package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {

	public static HashMap<String, String> dict = new HashMap<String, String>();
	public static int gold = 0;
	public static long numG = 0;
	public static boolean counted = false;

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day7.txt");
		Scanner s = new Scanner(f);
		while (s.hasNext()) {
			String temp = s.nextLine();
			dict.put(temp.substring(0, temp.indexOf(" contain")),
					temp.substring(temp.indexOf("contain") + 8, temp.length() - 1));
		}
		dict.forEach((k, v) -> {
			counted = false;
			hasGold(k);
		});
		countGold("shiny gold bags", 1);
		System.out.println(gold);
		System.out.println(numG);
		s.close();
	}

	public static void hasGold(String key) {
		String value = dict.get(key);
		if (counted) {
			return;
		}
		if (value.contains("shiny gold bag")) {
			gold++;
			counted = true;
			return;
		} else if (value.equals("no other bags")) {
			return;
		} else {
			String[] p = value.split(", ");
			for (int i = 0; i < p.length; i++) {
				if (!p[i].substring(p[i].length() - 1).equals("s")) {
					p[i] += "s";
				}
				hasGold(p[i].substring(p[i].indexOf(' ') + 1));
			}
		}
	}

	public static void countGold(String key, int mult) {
		String value = dict.get(key);
		if (value.equals("no other bags")) {
			return;
		}
		String[] p = value.split(", ");
		for (int i = 0; i < p.length; i++) {
			if (!p[i].substring(p[i].length() - 1).equals("s")) {
				p[i] += "s";
			}
			numG += mult * Integer.parseInt(p[i].substring(0, p[i].indexOf(' ')));
			countGold(p[i].substring(p[i].indexOf(' ') + 1),
					mult * Integer.parseInt(p[i].substring(0, p[i].indexOf(' '))));
		}
	}

}
