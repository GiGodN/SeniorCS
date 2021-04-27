package mergeSort;

public class Test {
	
	public static void main(String[] args) {
		IUDoubleLinkedList<Integer> list = new IUDoubleLinkedList<Integer>();
		list.add(4);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(3);
		System.out.println(list);
		Sort.sort(list);
		System.out.println(list);
	}
	
}
