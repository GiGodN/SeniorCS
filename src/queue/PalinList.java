package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PalinList {
	private Queue<String> queue;
	private Stack<String> stack;
	private String list;

	public PalinList() {
		setList("");
	}

	public PalinList(String list) {
		setList(list);
	}

	public void setList(String list) {
		this.list = list;
		queue = new LinkedList<String>();
		stack = new Stack<String>();
		Scanner s = new Scanner(list);
		while(s.hasNext()) {
			String temp = s.next();
			queue.add(temp);
			stack.add(temp);
		}
		s.close();
	}

	public boolean isPalin() {
		while(!queue.isEmpty() || !stack.isEmpty()) {
			if(!stack.pop().equals(queue.remove())) return false;
		}
		return true;
	}

	public String toString() {
		return list;
	}
}