package queue;

public class PalinListRunner {
	
	public static void main(String[] args) {
		PalinList p = new PalinList();
		p.setList("one two three two one");
		System.out.println(p + ", " + p.isPalin());
		p.setList("1 2 3 4 5 one two three four five");
		System.out.println(p + ", " + p.isPalin());
		p.setList("a b c d e f g x y z g f h");
		System.out.println(p + ", " + p.isPalin());
		p.setList("racecar is racecar");
		System.out.println(p + ", " + p.isPalin());
		p.setList("1 2 3 a b c c b a 3 2 1");
		System.out.println(p + ", " + p.isPalin());
		p.setList("chicken is a chicken");
		System.out.println(p + ", " + p.isPalin());
	}
}