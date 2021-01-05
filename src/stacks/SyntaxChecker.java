package stacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SyntaxChecker {
	private String exp;
	private Stack<Character> symbols;
	private final ArrayList<Character> checkO = new ArrayList<Character>(Arrays.asList('{', '(', '<', '['));
	private final ArrayList<Character> checkC = new ArrayList<Character>(Arrays.asList('}', ')', '>', ']'));

	public SyntaxChecker() {
		this("");
	}

	public SyntaxChecker(String s) {
		exp = s;
		symbols = new Stack<Character>();
	}

	public void setExpression(String s) {
		exp = s;
		symbols = new Stack<Character>();
	}

	public boolean checkExpression() {
		boolean ret = true;
		for(int i = 0; i < exp.length(); i++) {
			char a = exp.charAt(i);
			if(checkO.contains(a)) {
				symbols.push(a);
			}
			else if(checkC.contains(a)) {
				if (!symbols.isEmpty()) {
					char b = symbols.pop();
					int j = checkO.indexOf(b);
					int jj = checkC.indexOf(a);
					if(j != jj) {
						ret = false;
						break;
					}
				}
				else {
					ret = false;
					break;
				}
			}
		}
		if(!symbols.isEmpty()) ret = false;
		return ret;
	}

	public String toString() {
		return exp;
	}
}