/**
 * 
 */
package com.classic.datastructure;

/**
 * @author amitp
 *
 */
public class MyQueue<E> {

	private NodeSingle<E> head = null;

	/** The last node of the list */
	private NodeSingle<E> tail = null;

	private int size = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueue<String> queue = new MyQueue<>();
		queue.enqueue("Amit");
		queue.enqueue("Abhigyan");
		queue.enqueue("rakhi");
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);

	}

	public int size() {

		return size;
	}

	public boolean isEmpty() {

		return size == 0;
	}

	public void enqueue(E e) {
		NodeSingle<E> element = new NodeSingle<E>(e, null);
		// first element
		if (head == null) {

			head = element;
			tail = element;
		} else {
			tail.setNext(element);
			tail = element;
		}
		size++;
	}

	public E first() {

		return head != null ? head.getElement() : null;
	}

	public E dequeue() {
		E element = null;
		if (size > 0) {
			size--;
			element = head.getElement();
			head = head.getNext();
		}
		return element;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		NodeSingle<E> curr = head;
		while (curr != null) {
			sb.append(curr.getElement() + ";");
			curr = curr.getNext();
		}
		return sb.toString();
	}

}
