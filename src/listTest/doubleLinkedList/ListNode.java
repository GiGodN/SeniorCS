package listTest.doubleLinkedList;

public class ListNode<T> {
	
	private ListNode<T> next, prev;
	private T elem;
	
	public ListNode(T elem) {
		next = prev = null;
		this.elem = elem;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}

	public ListNode<T> getPrev() {
		return prev;
	}

	public void setPrev(ListNode<T> prev) {
		this.prev = prev;
	}

	public T getElem() {
		return elem;
	}

	public void setElem(T elem) {
		this.elem = elem;
	}

	@Override
	public String toString() {
		return "ListNode [next=" + next + ", prev=" + prev + ", elem=" + elem + "]";
	}

}
