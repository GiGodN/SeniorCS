package stacks;

public class SyntaxCheckRunner {
	
	public static void main(String[] args) {
		SyntaxChecker s = new SyntaxChecker("(abc(*def)");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("[{}]");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("[");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("[{<()>}]");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("{<html[value=4]*(12)>{$x}}");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("[one]<two>{three}(four)");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("car(cdr(a)(b)))");
		System.out.println(s + ", " + s.checkExpression());
		s.setExpression("car(cdr(a)(b))");
		System.out.println(s + ", " + s.checkExpression());
	}
}