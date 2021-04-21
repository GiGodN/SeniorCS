package listTest.tester;

import java.util.ListIterator;

import listTest.doubleLinkedList.IUDoubleLinkedList;

public class Test {
	
	public static void main(String[] args) {
		IUDoubleLinkedList<Integer> list = new IUDoubleLinkedList<Integer>();
		list.add(1);
		list.add(2);
		ListIterator<Integer> lI = list.listIterator(1);
		System.out.println(list);
		System.out.println(lI.previous());
		System.out.println(lI.next());
	}

}
