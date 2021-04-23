package mergeSort;

import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator. As
 * written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort {
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. As
	 * configured, uses WrappedDLL. Must be changed if using your own
	 * IUDoubleLinkedList class.
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() {
		return new IUDoubleLinkedList<T>(); // TODO: replace with your IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using
	 * compareTo() method defined by class of objects in list. DO NOT MODIFY THIS
	 * METHOD
	 * 
	 * @param <T>  The class of elements in the list, must extend Comparable
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @see IndexedUnsortedList
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) {
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using given
	 * Comparator. DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>  The class of elements in the list
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @param c    The Comparator used
	 * @see IndexedUnsortedList
	 */
	public static <T> void sort(IndexedUnsortedList<T> list, Comparator<T> c) {
		mergesort(list, c);
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using compareTo() method defined by class of
	 * objects in list. DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>  The class of elements in the list, must extend Comparable
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list) {
		aSort(list, 0, list.size()-1);
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using the given Comparator. DO NOT MODIFY THIS
	 * METHOD SIGNATURE
	 * 
	 * @param <T>  The class of elements in the list
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @param c    The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c) {
		// TODO: Implement recursive mergesort algorithm using Comparator

	}
	
	private static <T extends Comparable<T>> void aSort(IndexedUnsortedList<T> list, int left, int right) {
		if(left < right) {
			int middle = left + (right-left)/2;
			aSort(list, left, middle);
			aSort(list, middle+1, right);
			merge(list, left, middle, right);
		}
	}
	
	private static <T extends Comparable<T>> void merge(IndexedUnsortedList<T> list, int left, int middle, int right) {
		int leftSize = middle - left + 1;
		int rightSize = right = middle;
		
		IndexedUnsortedList<T> leftList = new IUDoubleLinkedList<T>();
		IndexedUnsortedList<T> rightList = new IUDoubleLinkedList<T>();
		
		for(int i = 0; i < leftSize; ++i) {
			leftList.add(i, list.get(left + i));
		}
		for(int i = 0; i < rightSize; ++i) {
			rightList.add(i, list.get(middle + 1 + i));
		}
		
		int l = 0, r = 0, i = left;
		while(l < leftSize && r < rightSize) {
			if(leftList.get(l).compareTo(rightList.get(r)) > 0) {
				list.set(i, leftList.get(l));
				l++;
			}
			else {
				list.set(i, rightList.get(r));
				r++;
			}
			i++;
		}
		
		while(l < leftSize) {
			list.set(i, leftList.get(l));
			l++;
			i++;
		}
		while(r < rightSize) {
			list.set(i, leftList.get(r));
			r++;
			i++;
		}
	}

}
