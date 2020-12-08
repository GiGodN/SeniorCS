package exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author      Noah John <noahmjohn@gmail.com>
 * @version     1.0
 */

public class FormatChecker {

	/**
	 * Main method, handles arguments and FileNotFoundException, if found will print stack trace to err
	 * 
	 * @param args: file1 [file2 ... fileN]
	 */
	public static void main(String[] args) {
		//if args are not passed err and exit the program
		if(args.length < 1) {
			System.err.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
			return;
		}
		//loop over args and run the validate function for each
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			try {
				validate(args[i]);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("INVALID");
			}
		}
	}
	
	/**
	 * validates the formating of a file passed as a parameter <p> catches any errors that may
	 * occur due to errors in file formating and will print the stack trace to err
	 * 
	 * @return prints to console if the selected file is valid or not
	 * @param filename, name of file in same folder as class
	 * @throws FileNotFoundException
	 */
	public static void validate(String filename) throws FileNotFoundException {
		File f = new File(/*"src/exception/" + */filename);
		Scanner s = new Scanner(f);
		Scanner b = new Scanner(s.nextLine());
		double[][] arr;
		try {
			arr = new double[b.nextInt()][b.nextInt()];
		}
		catch(InputMismatchException e) {
			e.printStackTrace();
			System.err.println("INVALID");
			b.close();
			s.close();
			return;
		}
		if(b.hasNext()) {
			System.err.println("invalid file format \nINVALID");
			b.close();
			s.close();
			return;
		}
		b.close();
		for(double[] ar : arr) {
			Scanner a = new Scanner(s.nextLine());
			for(int x = 0; x < ar.length; x++) {
				try {
					ar[x] = a.nextDouble();
				}
				catch(InputMismatchException e) {
					e.printStackTrace();
					System.err.println("INVALID");
					a.close();
					s.close();
					return;
				}
				catch(NoSuchElementException e) {
					e.printStackTrace();
					System.err.println("INVALID");
					a.close();
					s.close();
					return;
				}
			}
			if(a.hasNext()) {
				System.err.println("invalid file format \nINVALID");
				a.close();
				s.close();
				return;
			}
			a.close();
		}
		if(s.hasNext()) {
			System.err.println("invalid file format \nINVALID");
			s.close();
			return;
		}
		System.out.println("VALID");
		s.close();
	}

}
