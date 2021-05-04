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

	/**
	 * Adds the specified value to the head of the list
	 * 
	 * @param element value to add to the head
	 */
	@Override
	public void addToFront(T element) {
		add(0, element);
	}

	/**
	 * Adds the specified value to the tail of the list
	 * 
	 * @param element value to add to the tail of the list
	 */
	@Override
	public void addToRear(T element) {
		add(size, element);
	}

	/**
	 * Adds the specified value to the end of the list
	 * 
	 * @param element value to add to list
	 */
	@Override
	public void add(T element) {
		add(size, element);
	}

	/**
	 * Adds the specific element after the target value
	 * 
	 * @param element value to add to the list
	 * @param target target value
	 * @throws NoSuchElementException when target does not exist in the list
	 */
	@Override
	public void addAfter(T element, T target) {
		int i = indexOf(target);
		if(i == -1)
			throw new NoSuchElementException();
		add(i + 1, element);
	}

	/**
	 * Adds the element at the target index
	 * 
	 * @param index the target index to insert the element at
	 * @param element the element to be added to the list
	 * @throws IndexOutOFBoundsException when the index does not fall in the domain of the list
	 */
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

	/**
	 * removes the first element in the list
	 */
	@Override
	public T removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(0);
	}

	/**
	 * Removes the last element in the list
	 */
	@Override
	public T removeLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(size-1);
	}

	/**
	 * remove the target element
	 * 
	 * @param value to be removed
	 * @throws NoSuchElementException when the vaulue is not in the list
	 */
	@Override
	public T remove(T element) {
		int i = indexOf(element);
		if(i == -1)
			throw new NoSuchElementException();
		return remove(i);
	}

	/**
	 * removes the value at the index
	 * 
	 * @param index index of the target to be removed
	 */
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

	/**
	 * sets the value of the element at the target index
	 * 
	 * @param index target index to modify
	 * @param element value to set at index
	 */
	@Override
	public void set(int index, T element) {
		if(index > size-1 || index < 0 || size == 0)
			throw new IndexOutOfBoundsException();
		ListNode<T> target = head;
		for(int i = 0; i < index; i++) {
			target = target.getNext();
		}
		target.setElem(element);
		modCount++;
	}

	/**
	 * returns the value at the target index
	 * 
	 * @param index the index of the target element to return
	 */
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

	/**
	 * returns the index of the target element
	 * 
	 * @param element the element to find the index of
	 */
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

	/**
	 * returns the first element in the list
	 */
	@Override
	public T first() {
		if(isEmpty())
			throw new NoSuchElementException();
		return head.getElem();
	}

	/**
	 * returns the last element in the list
	 */
	@Override
	public T last() {
		if(isEmpty())
			throw new NoSuchElementException();
		return tail.getElem();
	}

	/**
	 * returns true if the target is contained in the list
	 * 
	 * @param target the value to find in the list
	 */
	@Override
	public boolean contains(T target) {
		return indexOf(target) != -1;
	}

	/**
	 * returns true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * returns the string representation of the list
	 * formated in [elem1, elem2 ... elemN]
	 */
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

	/**
	 * returns a Iterator for the list
	 */
	@Override
	public Iterator<T> iterator() {
		return new DLLListIterator();
	}

	/**
	 * returns a ListIterator for the list
	 */
	@Override
	public ListIterator<T> listIterator() {
		return new DLLListIterator();
	}

	/**
	 * returns a ListIterator staring at the target index for the list
	 * 
	 * @param startingIndex index to start the list iterator at
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLListIterator(startingIndex);
	}

	private class DLLListIterator implements ListIterator<T> {
		
		private int index, lastIndex, iterModCount;
		private ListNode<T> lastRet, curr;

		/**
		 * Constructor with a starting index of zero
		 */
		public DLLListIterator() {
			index = 0;
			lastIndex = 0;
			lastRet = null;
			curr = head;
			iterModCount = modCount;
		}
		
		/**
		 * Constructor with starts the iterator at target index
		 * 
		 * @param startingIndex index to start at
		 */

		public DLLListIterator(int startingIndex) {
			try {
				index = startingIndex;
				lastIndex = 0;
				lastRet = null;
				curr = head;
				iterModCount = modCount;
				for(int i = 0; i < startingIndex; i++) {
					curr = curr.getNext();
				}
				if(curr == null) throw new IndexOutOfBoundsException();
			}
			catch(NullPointerException e) {
				throw new IndexOutOfBoundsException();
			}
		}

		/**
		 * Adds an element to the list behind the current index.
		 * Will not affect the next call to next
		 * 
		 * 
		 * @param element to be added
		 */
		@Override
		public void add(T elem) {
			checkForComodification();
			lastRet = null;
			lastIndex = index;
			index++;
			if(curr != null) {
				ListNode<T> last = curr.getPrev();
				ListNode<T> newNode = new ListNode<T>(elem);
				curr.setPrev(newNode);
				if(last != null) last.setNext(newNode);
				newNode.setPrev(last);
				newNode.setNext(curr);
			}
			else {
				curr = new ListNode<T>(elem);
			}
			size++;
			modCount++;
			iterModCount++;
		}

		/**
		 * Returns true if the list has another element available for a call to next
		 */
		@Override
		public boolean hasNext() {
			checkForComodification();
			return index != size;
		}

		/**
		 * Returns true if the list has another element available for a call to previous
		 */
		@Override
		public boolean hasPrevious() {
			checkForComodification();
			return index != 0;
		}

		/**
		 * Returns the next element in the list and increments the index
		 */
		@Override
		public T next() {
			checkForComodification();
			try {
				lastIndex = index;
				index++;
				lastRet = curr;
				curr = curr.getNext();
				return lastRet.getElem();
			}
			catch(NullPointerException e) {
				throw new NoSuchElementException();
			}
		}

		/**
		 * Returns the index of the next element to be returned by the iterator
		 */
		@Override
		public int nextIndex() {
			checkForComodification();
			return index+1;
		}

		/**
		 * Returns the previous element in the list and decrements the index
		 */
		@Override
		public T previous() {
			checkForComodification();
			try {
				lastIndex = index;
				index--;
				lastRet = curr;
				curr = curr.getPrev();
				return curr.getElem();
			}
			catch(NullPointerException e) {
				throw new NoSuchElementException();
			}
		}

		/**
		 * Returns the index of the previous element to be returned by the iterator
		 */
		@Override
		public int previousIndex() {
			checkForComodification();
			return index-1;
		}

		/**
		 * Removes the element last returned by the iterator
		 */
		@Override
		public void remove() {
			checkForComodification();
			if(lastRet == null) throw new IllegalStateException();
			IUDoubleLinkedList.this.remove(lastIndex);
			lastRet = null;
			index--;
			iterModCount++;
		}

		/**
		 * Sets the value of the node last returned by the iterator
		 */
		@Override
		public void set(T element) {
			checkForComodification();
			if(lastRet == null) throw new IllegalStateException();
			IUDoubleLinkedList.this.set(lastIndex, element);
			lastRet = null;
			iterModCount++;
		}
		
		private final void checkForComodification() {
			if(modCount != iterModCount)
				throw new ConcurrentModificationException();
		}

	}

}
