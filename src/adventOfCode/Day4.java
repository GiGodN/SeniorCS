package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Passport.txt");
		Scanner s = new Scanner(f);
		String t = "";
		int amt = 0;
		int amt2 = 0;
		while (s.hasNext()) {
			String temp = s.nextLine();
			t += " " + temp;
			t = t.trim();
			if (temp.equals("")) {
				boolean invalide = false;
				if (t.contains("byr") && t.contains("iyr") && t.contains("eyr") && t.contains("hgt")
						&& t.contains("hcl") && t.contains("ecl") && t.contains("pid")) {
					amt++;
				}
				else {
					invalide = true;
				}
				Scanner l = new Scanner(t);
				ArrayList<String> pass = new ArrayList<String>();
				while (l.hasNext())
					pass.add(l.next());
				l.close();
				for (String a : pass) {
					switch (a.substring(0, 3)) {
					case "byr":
						if (!((1920 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2002)))
							invalide = true;
						break;
					case "iyr":
						if (!((2010 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2020)))
							invalide = true;
						break;
					case "eyr":
						if (!((2020 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2030)))
							invalide = true;
						break;
					case "hgt":
						switch (a.substring(a.length() - 2)) {
						case "cm":
							if (!((150 <= Integer.parseInt(a.substring(4, a.length() - 2)))
									&& (Integer.parseInt(a.substring(4, a.length() - 2)) <= 193)))
								invalide = true;
							break;
						case "in":
							if (!((59 <= Integer.parseInt(a.substring(4, a.length() - 2)))
									&& (Integer.parseInt(a.substring(4, a.length() - 2)) <= 76)))
								invalide = true;
							break;
						}
						break;
					case "hcl":
						String aaa = a.substring(4);
						if (aaa.charAt(0) == '#') {
							for (int i = 0; i < 6; i++) {
								if (!(((48 <= aaa.charAt(i + 1)) && (aaa.charAt(i + 1) <= 57))
										|| ((97 <= aaa.charAt(i + 1)) && (aaa.charAt(i + 1) <= 102))))
									invalide = true;
							}
						}
						break;
					case "ecl":
						String aa = a.substring(4);
						if (!(aa.equals("amb") || aa.equals("blu") || aa.equals("brn") || aa.equals("gry")
								|| aa.equals("grn") || aa.equals("hzl") || aa.equals("oth")))
							invalide = true;
						break;
					case "pid":
						if (!(a.substring(4).length() == 9))
							invalide = false;
						break;
					}
					
				}
				if(!invalide) {
					amt2++;
				}
				t = "";
			}
		}
		boolean invalide = false;
		if (t.contains("byr") && t.contains("iyr") && t.contains("eyr") && t.contains("hgt")
				&& t.contains("hcl") && t.contains("ecl") && t.contains("pid")) {
			amt++;
		}
		else {
			invalide = true;
		}
		Scanner l = new Scanner(t);
		ArrayList<String> pass = new ArrayList<String>();
		while (l.hasNext())
			pass.add(l.next());
		l.close();
		for (String a : pass) {
			switch (a.substring(0, 3)) {
			case "byr":
				if (!((1920 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2002)))
					invalide = true;
				break;
			case "iyr":
				if (!((2010 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2020)))
					invalide = true;
				break;
			case "eyr":
				if (!((2020 <= Integer.parseInt(a.substring(4))) && (Integer.parseInt(a.substring(4)) <= 2030)))
					invalide = true;
				break;
			case "hgt":
				switch (a.substring(a.length() - 2)) {
				case "cm":
					if (!((150 <= Integer.parseInt(a.substring(4, a.length() - 2)))
							&& (Integer.parseInt(a.substring(4, a.length() - 2)) <= 193)))
						invalide = true;
					break;
				case "in":
					if (!((59 <= Integer.parseInt(a.substring(4, a.length() - 2)))
							&& (Integer.parseInt(a.substring(4, a.length() - 2)) <= 76)))
						invalide = true;
					break;
				}
				break;
			case "hcl":
				String aaa = a.substring(4);
				if (aaa.charAt(0) == '#') {
					for (int i = 0; i < 6; i++) {
						if (!(((48 <= aaa.charAt(i + 1)) && (aaa.charAt(i + 1) <= 57))
								|| ((97 <= aaa.charAt(i + 1)) && (aaa.charAt(i + 1) <= 102))))
							invalide = true;
					}
				}
				break;
			case "ecl":
				String aa = a.substring(4);
				if (!(aa.equals("amb") || aa.equals("blu") || aa.equals("brn") || aa.equals("gry")
						|| aa.equals("grn") || aa.equals("hzl") || aa.equals("oth")))
					invalide = true;
				break;
			case "pid":
				if (!(a.substring(4).length() == 9))
					invalide = false;
				break;
			}
			
		}
		if(!invalide) {
			amt2++;
		}
		System.out.println(amt);
		System.out.println(amt2);
		s.close();
	}

}
