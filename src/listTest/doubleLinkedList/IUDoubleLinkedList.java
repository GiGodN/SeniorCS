package listTest.doubleLinkedList;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import listTest.IndexedUnsortedList;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	
	private ListNode<T> head, tail;
	private int size, modCount;

	public IUDoubleLinkedList() {
		head = tail = null;
		size = modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		
	}

	@Override
	public void addToRear(T element) {
		
	}

	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAfter(T element, T target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int index, T element) {
		
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T first() {
		if(isEmpty()) throw new NoSuchElementException();
		return head.getElem();
	}

	@Override
	public T last() {
		if(isEmpty()) throw new NoSuchElementException();
		return tail.getElem();
	}

	@Override
	public boolean contains(T target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new DLLListIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLListIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLListIterator(startingIndex);
	}
	
	private class DLLListIterator implements ListIterator<T> {
		
		public DLLListIterator() {
			
		}
		
		public DLLListIterator(int startingIndex) {
			
		}

		@Override
		public void add(T arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(T arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
