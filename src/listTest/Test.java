package listTest;

public class Test {
	
	public static void main(String[] args) {
		IUArrayList<Integer> list = new IUArrayList<Integer>();
		list.add(1,5,3,4,7,5);
		IUArrayList<Integer> listF = IUArrayList.sort(list);
		IUArrayList<Integer> listB = IUArrayList.revSort(list);
		System.out.println(list);
		System.out.println(listF);
		System.out.println(listB);
		IUArrayList<Character> listC = new IUArrayList<Character>();
		listC.add('a','b','c','a','a','a','a','a','a','a','a','a','a','a','a','a','y','t','d','e','f');
		IUArrayList<Character> listFC = IUArrayList.sort(listC);
		IUArrayList<Character> listBC = IUArrayList.revSort(listC);
		System.out.println(listC);
		System.out.println(listFC);
		System.out.println(listBC);
	}

}
