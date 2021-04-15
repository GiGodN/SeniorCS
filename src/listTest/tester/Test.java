package listTest.tester;

import listTest.linkedList.IUSingleLinkedList;

public class Test {
	
	public static void main(String[] args) {
		IUSingleLinkedList<String> list = new IUSingleLinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
//		list.last();
		System.out.println(list.get(3));
	}

}
