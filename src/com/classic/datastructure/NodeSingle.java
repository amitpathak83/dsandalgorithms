/**
 * 
 */
package com.classic.datastructure;

/**
 * @author amitp
 *
 */
public class NodeSingle<E> {
	
	private E element;
	
	private NodeSingle<E> next;

	public NodeSingle(E element, NodeSingle<E> next) {
		super();
		this.element = element;
		this.next = next;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public NodeSingle<E> getNext() {
		return next;
	}

	public void setNext(NodeSingle<E> next) {
		this.next = next;
	}

}
