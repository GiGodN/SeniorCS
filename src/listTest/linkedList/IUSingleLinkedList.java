package listTest.linkedList;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import listTest.IndexedUnsortedList;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private Node<T> head, tail;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		add(0, element);
		
	}

	@Override
	public void addToRear(T element) {
		add(element);
		
	}

	@Override
	public void add(T element) {
		add(size, element);
	}

	@Override
	public void addAfter(T element, T target) {
		int i = indexOf(target);
		if(i == -1) throw new NoSuchElementException();
		add(i+1, element);
	}

	@Override
	public void add(int index, T element) {
		if(!(-1 < index && index < size+1)) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			head = new Node<T>(element);
			tail = head;
			size++;
			modCount++;
			return;
		}
		Node<T> node = head;
		for(int i = 0; i < index-1; i++) {
			node = node.getNext();
		}
		Node<T> nextNode = node.getNext();
		Node<T> newNode = new Node<T>(element);
		if (nextNode == tail) {
			tail = newNode;
		}
		newNode.setNext(nextNode);
		if(index == 0) {
			Node<T> oldHead = head;
			head = newNode;
			head.setNext(oldHead);
		}
		else {
			node.setNext(newNode);
		}
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(size == 0) throw new NoSuchElementException();
		T ret = head.getElement();
		head = head.getNext();
		size--;
		return ret;
	}

	@Override
	public T removeLast() {
		if(isEmpty()) throw new NoSuchElementException();
		return remove(size-1);
	}

	@Override
	public T remove(T element) {
		int i = indexOf(element);
		if(i == -1) throw new NoSuchElementException();
		return remove(i);
	}

	@Override
	public T remove(int index) {
		if(!(-1 < index && index < size)) {
			throw new IndexOutOfBoundsException();
		}
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<T> previous = null;
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			previous = current;
			current = current.getNext();
		}
		if (size() == 1) { //only node
			head = tail = null;
		} else if (current == head) { //first node
			head = current.getNext();
		} else if (current == tail) { //last node
			tail = previous;
			tail.setNext(null);
		} else { //somewhere in the middle
			previous.setNext(current.getNext());
		}
		size--;
		modCount++;
		return current.getElement();
	}

	@Override
	public void set(int index, T element) {
		if(index > size || index < -1 || size == 0) throw new IndexOutOfBoundsException();
		Node<T> node = head;
		for(int i = 0; i < index-1; i++) {
			node = node.getNext();
		}
		node.setElement(element);
	}

	@Override
	public T get(int index) {
		if(size < index || -1 < index) throw new IndexOutOfBoundsException();
		if(isEmpty()) throw new IndexOutOfBoundsException();
		Node<T> node = head;
		for(int i = 0; i < index; i++) {
			node = node.getNext();
		}
		return node.getElement();
	}

	@Override
	public int indexOf(T element) {
		int index = 0;
		boolean found = false;
		Node<T> node = head;
		while(node != null && !found) {
			if(node.getElement().equals(element)) {
				found = true;
				break;
			}
			node = node.getNext();
			index++;
		}
		if(!found) index = -1;
		return index;
	}

	@Override
	public T first() {
		if(isEmpty()) throw new NoSuchElementException();
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty()) throw new NoSuchElementException();
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		int index = indexOf(target);
		return index != -1;
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
        Node<T> current = head;
        while(current != null){
            result += current.getElement();
            if(current.getNext() != null){
                 result += ", ";
            }
            current = current.getNext();
        }
        result += "]";
        return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private Node<T> nextNode;
		private int iterModCount;
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			checkForComodification();
			try {
				return nextNode.getNext() != null;
			}
			catch(NullPointerException e) {
				return nextNode != null;
			}
		}

		@Override
		public T next() {
			checkForComodification();
			if(!hasNext()) throw new NoSuchElementException();
			T retVal = nextNode.getElement();
			nextNode = nextNode.getNext();
			return retVal;
		}
		
		@Override
		public void remove() {
			checkForComodification();
			if(nextNode == null) throw new IllegalStateException();
			nextNode.setNext(nextNode.getNext().getNext());
		}
		
		final void checkForComodification() {
            if (modCount != iterModCount)
                throw new ConcurrentModificationException();
        }
	}
}
