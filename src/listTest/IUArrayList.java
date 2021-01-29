package listTest;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
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
		add(rear, element);
	}

	@Override
	public void addAfter(T element, T target) {
		int index = indexOf(target);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		add(index+1, element);
	}

	@Override
	public void add(int index, T element) {
		modCount++;
		if(index == array.length) expandCapacity();
		if(index == rear) {
			array[index] = element;
		}
		else if (index > rear) throw new IndexOutOfBoundsException();
		else {
			for (int i = index; i < rear; i++) {
				array[i+1] = array[i];
			}
			array[index] = element;
		}
		rear++;
	}

	@Override
	public T removeFirst() {
		if(rear == 0) throw new NoSuchElementException();
		return remove(0);
	}

	@Override
	public T removeLast() {
		if(rear == 0) throw new NoSuchElementException();
		return remove(rear-1);
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) { 
		modCount++;
		T val = array[index];
		if(index >= rear) throw new IndexOutOfBoundsException();
		rear--;
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear+1] = null;
		return val;
	}

	@Override
	public void set(int index, T element) {
		if(index >= rear || rear==0) throw new IndexOutOfBoundsException();
		array[index] = element;
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index >= rear) throw new IndexOutOfBoundsException();
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public T first() {
		if(rear == 0) throw new NoSuchElementException();
		return array[0];
	}

	@Override
	public T last() {
		if(rear == 0) throw new NoSuchElementException();
		return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		return rear == 0;
	}

	@Override
	public int size() {
		return rear;
	}

	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		Iterator<T> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            T e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private int lastRet = -1;
		
		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
		}
		
		public boolean hasNext() {
            return nextIndex != size();
        }

        public T next() {
            checkForComodification();
            try {
                int i = nextIndex;
                T next = get(i);
                lastRet = i;
                nextIndex = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException(e);
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
            	IUArrayList.this.remove(lastRet);
                if (lastRet < nextIndex)
                    nextIndex--;
                lastRet = -1;
                iterModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != iterModCount)
                throw new ConcurrentModificationException();
        }
        
	}
}
