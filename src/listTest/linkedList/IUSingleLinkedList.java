package listTest.linkedList;


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
		try{
			add(indexOf(target)+1, element);
		}
		catch(IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
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
		T ret = head.getElement();
		head = head.getNext();
		size--;
		return ret;
	}

	@Override
	public T removeLast() {
		return remove(size-1);
	}

	@Override
	public T remove(T element) {
		return remove(indexOf(element));
	}

	@Override
	public T remove(int index) {
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
		if(index > size) throw new IndexOutOfBoundsException();
		Node<T> node = head;
		for(int i = 0; i < index-1; i++) {
			node = node.getNext();
		}
		node.setElement(element);
	}

	@Override
	public T get(int index) {
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
		return head.getElement();
	}

	@Override
	public T last() {
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
			// TODO 
			return false;
		}

		@Override
		public T next() {
			// TODO 
			return null;
		}
		
		@Override
		public void remove() {
			// TODO
		}
	}
}
