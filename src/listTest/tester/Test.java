package listTest.tester;

import java.util.ListIterator;

import listTest.doubleLinkedList.IUDoubleLinkedList;

public class Test {
	
	public static void main(String[] args) {
		IUDoubleLinkedList<Integer> list = new IUDoubleLinkedList<Integer>();
		ListIterator<Integer> lI = list.listIterator();
		System.out.println(list);
		lI.hasPrevious();
		System.out.println(lI.previous());
	}

}
