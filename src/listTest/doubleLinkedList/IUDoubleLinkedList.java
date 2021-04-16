package listTest.doubleLinkedList;

import java.util.ConcurrentModificationException;
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
		ListNode<T> newNode = new ListNode<T>(element);
		if(target == null) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			modCount++;
			size++;
			return;
		}
		if(index == 0) {
			head.setPrev(newNode);
			newNode.setNext(head);
			head = newNode;
			modCount++;
			size++;
			return;
		}
		ListNode<T> prev = target.getPrev();
		prev.setNext(newNode);
		target.setPrev(newNode);
		newNode.setNext(target);
		newNode.setPrev(prev);
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
		if(i == -1)
			throw new NoSuchElementException();
		return remove(i);
	}

	@Override
	public T remove(int index) {
		if(0 > index || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		ListNode<T> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		if(size == 1) {
			head = tail = null;
		}
		else if(index == 0) {
			head = curr.getNext();
			head.setPrev(null);
		}
		else if(curr == tail) {
			tail = curr.getPrev();
			tail.setNext(null);
		}
		else {
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
		}
		size--;
		modCount++;
		return curr.getElem();
	}

	@Override
	public void set(int index, T element) {
		if(index > size-1 || index < 0 || size == 0)
			throw new IndexOutOfBoundsException();
		ListNode<T> target = head;
		for(int i = 0; i < index-1; i++) {
			target = target.getNext();
		}
		target.setElem(element);
	}

	@Override
	public T get(int index) {
		if(size-1 < index || -1 >= index || isEmpty())
			throw new IndexOutOfBoundsException();
		ListNode<T> target = head;
		for(int i = 0; i < index-1; i++) {
			target = target.getNext();
		}
		return target.getElem();
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
		return indexOf(target) != -1;
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
		
		private int index, iterModCount;
		private ListNode<T> lastRet, curr;

		public DLLListIterator() {
			index = 0;
			lastRet = null;
			curr = head;
			iterModCount = modCount;
		}

		public DLLListIterator(int startingIndex) {
			index = startingIndex;
			lastRet = null;
			curr = head;
			for(int i = 0; i < startingIndex-1; i++) {
				curr = curr.getNext();
			}
		}

		@Override
		public void add(T arg0) {
			
		}

		@Override
		public boolean hasNext() {
			checkForComodification();
			return curr != tail;
		}

		@Override
		public boolean hasPrevious() {
			checkForComodification();
			return curr != head;
		}

		@Override
		public T next() {
			checkForComodification();
			try {
				index++;
				curr = curr.getNext();
				return curr.getPrev().getElem();
			}
			catch(NullPointerException e) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public int nextIndex() {
			checkForComodification();
			return index+1;
		}

		@Override
		public T previous() {
			checkForComodification();
			index--;
			curr = curr.getPrev();
			return curr.getElem();
		}

		@Override
		public int previousIndex() {
			checkForComodification();
			return index-1;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(T element) {
			// TODO Auto-generated method stub

		}
		
		final void checkForComodification() {
			if(modCount != iterModCount)
				throw new ConcurrentModificationException();
		}

	}

}
