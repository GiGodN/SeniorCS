package listTest.tester;

import listTest.doubleLinkedList.IUDoubleLinkedList;

public class Test {
	
	public static void main(String[] args) {
		IUDoubleLinkedList<Integer> list = new IUDoubleLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(1);
		System.out.println(list.last());
	}

}
