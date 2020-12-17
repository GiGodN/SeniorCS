package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day11.txt");
		Scanner s = new Scanner(f);
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		int line = 0;
		while(s.hasNext()) {
			String temp = s.nextLine();
			map.add(new ArrayList<String>());
			for(int i = 0; i < temp.length(); i++) {
				map.get(line).add(Character.toString(temp.charAt(i)));
			}
			line++;
		}
		s.close();
		ArrayList<ArrayList<String>> mapC = part1(map);
		while(!map.equals(mapC)) {
			map = mapC;
			mapC = part1(map);
		}
		int count = 0;
		for(ArrayList<String> a : map) {
			for(String b : a) {
				if(b.equals("#")) count++;
			}
		}
		System.out.println(count);
	}
	
	public static ArrayList<ArrayList<String>> part1(ArrayList<ArrayList<String>> map) {
		ArrayList<ArrayList<String>> mapC = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < map.size(); i++) {
			mapC.add((ArrayList<String>) map.get(i).clone());
		}
		for(int y = 0; y < map.size(); y++) {
			for(int x = 0; x < map.get(y).size(); x++) {
				switch(map.get(y).get(x)) {
				case "L" :
					if((y == 0) && (x == 0)) {
						boolean change = true;
						for(int i = 0; i < 2; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if((y == map.size()-1) && (x == map.get(y).size()-1)) {
						boolean change = true;
						for(int i = -1; i < 1; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if((y == 0) && (x == map.get(y).size()-1)) {
						boolean change = true;
						for(int i = 0; i < 2; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if((y == map.size()-1) && (x == 0)) {
						boolean change = true;
						for(int i = -1; i < 1; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if(y == 0) {
						boolean change = true;
						for(int i = 0; i < 2; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if(x == 0) {
						boolean change = true;
						for(int i = -1; i < 2; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if(y == map.size()-1) {
						boolean change = true;
						for(int i = -1; i < 1; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else if(x == map.get(y).size()-1) {
						boolean change = true;
						for(int i = -1; i < 2; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
					else {
						boolean change = true;
						for(int i = -1; i < 2; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) change = false;
								}
							}
						}
						if(change) mapC.get(y).set(x, "#");
					}
				case "#":
					if((y == 0) && (x == 0)) {
						int count = 0;
						for(int i = 0; i < 2; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if((y == map.size()-1) && (x == map.get(y).size()-1)) {
						int count = 0;
						for(int i = -1; i < 1; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if((y == 0) && (x == map.get(y).size()-1)) {
						int count = 0;
						for(int i = 0; i < 2; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if((y == map.size()-1) && (x == 0)) {
						int count = 0;
						for(int i = -1; i < 1; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if(y == 0) {
						int count = 0;
						for(int i = 0; i < 2; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if(x == 0) {
						int count = 0;
						for(int i = -1; i < 2; i++) {
							for(int j = 0; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if(y == map.size()-1) {
						int count = 0;
						for(int i = -1; i < 1; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else if(x == map.get(y).size()-1) {
						int count = 0;
						for(int i = -1; i < 2; i++) {
							for(int j = -1; j < 1; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
					else {
						int count = 0;
						for(int i = -1; i < 2; i++) {
							for(int j = -1; j < 2; j++) {
								if(i == 0 && j == 0) continue;
								else {
									if(map.get(i+y).get(j+x).equals("#")) count++;
								}
							}
						}
						if(count >= 4) mapC.get(y).set(x, "L");
					}
				}
			}
		}
		return mapC;
	}

}
