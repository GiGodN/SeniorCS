package mergeSort;

import java.util.Comparator;
import java.util.ListIterator;

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
		if (list.size() <= 1)
			return;
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		ListIterator<T> iter = list.listIterator();
		while (iter.hasNext()) {
			T val = iter.next();
			if (iter.nextIndex() - 2 < list.size() / 2)
				left.add(val);
			else
				right.add(val);
		}
		mergesort(left);
		mergesort(right);
		merge(left, right, list);
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
		if (list.size() <= 1)
			return;
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		ListIterator<T> iter = list.listIterator();
		while (iter.hasNext()) {
			T val = iter.next();
			if (iter.nextIndex() - 2 < list.size() / 2)
				left.add(val);
			else
				right.add(val);
		}
		mergesort(left, c);
		mergesort(right, c);
		merge(left, right, list, c);
	}

	private static <T extends Comparable<T>> void merge(IndexedUnsortedList<T> left, IndexedUnsortedList<T> right,
			IndexedUnsortedList<T> list) {
		ListIterator<T> lIter = left.listIterator(), rIter = right.listIterator(), mIter = list.listIterator();
		T l = null, r = null;
		mIter.next();
		while (lIter.hasNext() && rIter.hasNext()) {
			l = lIter.next();
			r = rIter.next();
			if (l.compareTo(r) <= 0) {
				mIter.set(l);
				rIter.previous();
			}
			else {
				mIter.set(r);
				lIter.previous();
			}
			mIter.next();
		}
		while (lIter.hasNext()) {
			mIter.set(lIter.next());
			if (mIter.hasNext())
				mIter.next();
		}
		while (rIter.hasNext()) {
			mIter.set(rIter.next());
			if (mIter.hasNext())
				mIter.next();
		}
	}

	private static <T> void merge(IndexedUnsortedList<T> left, IndexedUnsortedList<T> right,
			IndexedUnsortedList<T> list, Comparator<T> comp) {
		ListIterator<T> lIter = left.listIterator(), rIter = right.listIterator(), mIter = list.listIterator();
		T l = null, r = null;
		mIter.next();
		while (lIter.hasNext() && rIter.hasNext()) {
			l = lIter.next();
			r = rIter.next();
			if (comp.compare(l, r) <= 0) {
				mIter.set(l);
				rIter.previous();
			}
			else {
				mIter.set(r);
				lIter.previous();
			}
			mIter.next();
		}
		while (lIter.hasNext()) {
			mIter.set(lIter.next());
			if (mIter.hasNext())
				mIter.next();
		}
		while (rIter.hasNext()) {
			mIter.set(rIter.next());
			if (mIter.hasNext())
				mIter.next();
		}
	}

}
