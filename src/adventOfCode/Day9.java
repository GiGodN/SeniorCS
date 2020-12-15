package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/adventOfCode/Day9.txt");
		Scanner s = new Scanner(f);
		ArrayList<Long> message = new ArrayList<Long>();
		ArrayList<Long> message2 = new ArrayList<Long>();
		for(int i = 0; i < 25; i++) {
			long temp = s.nextLong();
			message.add(temp);
			message2.add(temp);
		}
		long rval = 0;
		while(s.hasNext()) {
			long val = s.nextLong();
			message2.add(val);
			boolean valid = false;
			for(int x = 0; x < 25; x++) {
				for(int y = 0; y < 25; y++) {
					if(x == y) {
						continue;
					}
					else {
						if(message.get(x) + message.get(y) == val) valid = true;
					}
				}
			}
			if(valid) {
				message.remove(0);
				message.add(val);
			}
			else {
				rval = val;
				System.out.println(rval);
				break;
			}
		}
		while(s.hasNext()) {
			message2.add(s.nextLong());
		}
		for(int i = 0; i < message2.size(); i++) {
			ArrayList<Long> list = new ArrayList<Long>();
			int l = 0;
			long sum = 0;
			while(sum < rval) {
				list.add(message2.get(i+l));
				l++;
				sum = 0;
				for(long a : list) sum += a;
			}
			if(sum == rval && list.size() >= 2) {
				long sml = list.get(0), lrg = list.get(0);
				for(long a : list) {
					if(a < sml) sml = a;
					if(a > lrg) lrg = a;
				}
				System.out.println(sml+lrg);
			}
		}
		s.close();
	}

}
