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
		add(0, element);
	}

	@Override
	public void addToRear(T element) {
		add(size, element);
	}

	@Override
	public void add(T element) {
		add(size, element);
	}

	@Override
	public void addAfter(T element, T target) {
		int i = indexOf(target);
		if(i == -1)
			throw new NoSuchElementException();
		add(i + 1, element);
	}

	@Override
	public void add(int index, T element) {
		if(!(-1 < index && index < size + 1)) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			head = new ListNode<T>(element);
			tail = head;
			size++;
			modCount++;
			return;
		}
		ListNode<T> target = head;
		for (int i = 0; i < index; i++) {
			target = target.getNext();
		}
		ListNode<T> next = new ListNode<T>(element);
		if(target == null) {
			tail.setNext(next);
			next.setPrev(tail);
			tail = next;
			modCount++;
			size++;
			return;
		}
		if(index == 0) {
			head.setPrev(next);
			next.setNext(head);
			head = next;
			modCount++;
			size++;
			return;
		}
		ListNode<T> prev = target.getPrev();
		prev.setNext(next);
		target.setPrev(next);
		next.setNext(target);
		next.setPrev(prev);
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(0);
	}

	@Override
	public T removeLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(size-1);
	}

	@Override
	public T remove(T element) {
		int i = indexOf(element);
		if(i < -1)
			throw new NoSuchElementException();
		return remove(i);
	}

	@Override
	public T remove(int index) {
		if(!(-1 < index && index < size)) {
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		ListNode<T> cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.getNext();
		}
		if(size == 1) {
			head = tail = null;
		}
		else if(index == 0) {
			head = cur.getNext();
			head.setPrev(null);
		}
		else if(cur == tail) {
			tail = cur.getPrev();
			tail.setNext(null);
		}
		else {
			cur.getPrev().setNext(cur.getNext());
			cur.getNext().setPrev(cur.getPrev());
		}
		size--;
		modCount++;
		return cur.getElem();
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
		int i = 0;
		boolean found = false;
		ListNode<T> node = head;
		while (node != null && !found) {
			if(node.getElem().equals(element)) {
				found = true;
				break;
			}
			node = node.getNext();
			i++;
		}
		if(!found)
			i = -1;
		return i;
	}

	@Override
	public T first() {
		if(isEmpty())
			throw new NoSuchElementException();
		return head.getElem();
	}

	@Override
	public T last() {
		if(isEmpty())
			throw new NoSuchElementException();
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
	public String toString() {
		String result = "[";
		ListNode<T> current = head;
		while (current != null) {
			result += current.getElem();
			if(current.getNext() != null) {
				result += ", ";
			}
			current = current.getNext();
		}
		result += "]";
		return result;
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
