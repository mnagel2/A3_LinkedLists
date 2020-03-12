import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic double-linked list class
 * @author Montague Nagel
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{

	//------------------+-FIELDS-+----------------------------
	/**
	 * The size of the list
	 */
	protected int size;
	
	/**
	 * The first and last node that can be accessed
	 */
	protected Node<T> firstNode, lastNode;
	
	//------------------+-CONSTRUCTORS-+----------------------
	
	/**
	 * A new double linked list with no elements inside
	 */
	public BasicDoubleLinkedList () {
		size = 0;
		firstNode = null;
		lastNode = null;
	}
	
	//------------------+-METHODS-+---------------------------
	
	/**
	 * Adds an element to the front of the list
	 * @param data
	 * @return the amended list
	 */
	public BasicDoubleLinkedList<T> addToFront(T data){
		if(isEmpty()) {
			firstNode = new Node<T>(data);
			lastNode = firstNode;
		} else {
			firstNode.previous = new Node<T>(null, data, firstNode);
			firstNode = firstNode.previous;
		}
		size++;
		return this;
	}
	
	/**
	 * Adds an element to the end of the list
	 * @param data
	 * @return the amended list
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data){
		if(isEmpty()) {
			lastNode = new Node<T>(data);
			firstNode = lastNode;
		} else {
			lastNode.next = new Node<T>(lastNode, data, null);
			lastNode = lastNode.next;
		}
		size++;
		return this;
	}
	
	/**
	 * Removes and returns the first element in the list
	 * @return the removed element
	 */
	public T retrieveFirstElement() {
		Node<T> tempNode;
		if (isEmpty()) {
			return null;
		} else if (size == 1) {
			size--;
			tempNode = new Node<T>(firstNode);
			firstNode = null;
			lastNode = null;
			return tempNode.element;
		} else {
			size--;
			tempNode = new Node<T>(firstNode);
			firstNode = firstNode.next;
			firstNode.previous = null;
			return tempNode.element;
		}
	}
	
	/**
	 * Removes and returns the last element in the list
	 * @return the removed element
	 */
	public T retrieveLastElement() {
		Node<T> tempNode;
		if (isEmpty()) {
			return null;
		} else if (size == 1) {
			return retrieveFirstElement();
		} else {
			size--;
			tempNode = new Node<T>(lastNode);
			lastNode = lastNode.previous;
			lastNode.next = null;
			return tempNode.element;
		}
	}
	
	/**
	 * Gets the first element in the list without removing it
	 * @return
	 */
	public T getFirst() {
		if (size > 0) {
			return firstNode.element;
		}
		
		return null;
	}
	
	/**
	 * Gets the last element in the list without removing it
	 * @return
	 */
	public T getLast() {
		if (size > 0) {
			return lastNode.element;
		}
		return null;
	}
	
	/**
	 * Gets the size of the list
	 * @return size of list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if list is empty
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Resets the BasicDoubleLinkedList, clearing it of all nodes
	 */
	public void clear() {
		firstNode = null;
		lastNode = null;
		size = 0;
	}
	
	/**
	 * Gets the ArrayList of the elements from head to tail
	 * @return the ArrayList of elements
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> arrayListElements = new ArrayList<T>();
		if (isEmpty()) {
			return arrayListElements;
		}
		Node<T> searchNode = firstNode;
		for(int i = 0; i < size; i++) {
			arrayListElements.add(searchNode.element);
			if(i+1 < size) {
				searchNode = searchNode.next;
			}
		}
		return arrayListElements;
	}
	
	/**
	 * 
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException,
											 NoSuchElementException         {
		ListIterator<T> listIterator = new DoubleLinkedListIterator<T>();
		return listIterator;
	}
	
	/**
	 * Removes the first instance of the targeted data in the list
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		if (size == 0) {																// List is empty
			return this;
		}
		if (comparator.compare(targetData, firstNode.element) == 0) {					// First Element Matches
			retrieveFirstElement();
			return this;
		}
		if (comparator.compare(targetData,lastNode.element) == 0) {						// Last Element Matches
			retrieveLastElement();
			return this;
		}
		Node<T> searchNode = firstNode;
		boolean isFound = false;
		for (int i = 0; i < size; i++) {												// A Middle Element Matches
			if (comparator.compare(targetData, searchNode.element) == 0) {
				isFound = true;
				try {
					searchNode.previous.next = searchNode.next;
				} catch (NullPointerException e) {
					System.out.println("Failure!");
				}
				try {
					searchNode.next.previous = searchNode.previous;
				} catch (NullPointerException e) {
					System.out.println("Failure!");
				}
				size--;
				return this;
			}
			searchNode = searchNode.next;
		}
		return this;
	}
	
	public String toString() {
		String rep = "Size of list: " + size + ".";
		Node<T> tNode = new Node<T>(firstNode);
		if (size == 0) {
			rep = "Empty List.";
		} else {
			rep = rep + " ";
			for (int i = 0; i < size; i++) {
				rep += tNode + " ";
				try {
					tNode = tNode.next;
				}
				catch (NullPointerException e) {
					
				}
			}
		}
		return rep;
	}
	
	
	//------------------+-INNER CLASSES-+---------------------
	
	/**
	 * Nodes contain a reference to their own data element, and a reference to the next node in the list.
	 * Node is a generic private class of the generic double singly-linked circular list class "BasicDoubleLinkedList.java".
	 * @author Montague Nagel
	 *
	 * @param <E>
	 */
	protected class Node<E> {
		protected E element;
		protected Node<E> previous, next;
		
		/**
		 * Constructor for initial nodes
		 * @param data
		 */
		Node (E data){
			element = data;
		}
		
		/**
		 * Constructor for duplicating nodes
		 * @param node
		 */
		Node (Node<E> node){
			this.previous = node.previous;
			this.element = node.element;
			this.next = node.next;
		}
		
		/**
		 * Constructor for most cases
		 * @param previousNode
		 * @param currentNode
		 * @param nextNode
		 */
		Node (Node<E> previousNode, E currentNode, Node<E> nextNode){
			previous = previousNode;
			element = currentNode;
			next = nextNode;
		}
		
		/**
		 * Method for cloning Nodes
		 */
		public Node<E> clone(){
			Node<E> newNode = new Node<E>(previous,element,next);
			return newNode;
		}
		
		/**
		 * Checks if next points to another node
		 * @return true if there is a next Node
		 */
		public boolean hasNext() {
			try {
				@SuppressWarnings("unused")
				E nextData = next.element;
				return true;
			} catch (NullPointerException e) {
				return false;
			}
		}
		
		/**
		 * Checks if previous points to another node
		 * @return true if there is a previous Node
		 */
		public boolean hasPrevious() {
			try {
				@SuppressWarnings("unused")
				E previousData = previous.element;
				return true;
			} catch (NullPointerException e) {
				return false;
			}
		}
		
		/**
		 * Returns the Node's data element as a String
		 */
		public String toString() {
			return this.element.toString();
		}
			/*
			String rep = "";
			if (hasPrevious()) {
				rep += "<-- "; 
			} else {
				rep += "X-- ";
			}
			
			rep += "(" + this.element + ")";
			if (hasNext()) {
				rep += " -->";
			} else {
				rep += " --X";
			}
			return rep;
		}
		*/
	}
	
	/**
	 * DoubleLinkedListIterator only supports the methods hasNext(), hasPrevious(), next(), and previous() which allow the class to iterate across the linked list
	 * @author Montague Nagel
	 *
	 * @param <N>
	 */
	protected class DoubleLinkedListIterator<N>  implements ListIterator<N> {

		/**
		 * "Cursor" location
		 */
		int index;
		/**
		 * The Nodes adjacent to the cursor
		 */
		Node<T> previousNode, nextNode;
		
		/**
		 * List Iterator Constructor
		 */
		public DoubleLinkedListIterator(){
			index = 0;
			nextNode = new Node<T>(firstNode);
			previousNode = new Node<T>(null, null, nextNode);
			nextNode.previous = previousNode;
			//System.out.println("Starting iterator before: " + nextNode);
		}
		
		/**
		 * Checks if there is another node in the forward direction
		 */
		public boolean hasNext() {
			//System.out.println("Next is: " + nextNode);
			return previousNode.hasNext();
			
		}
		
		/**
		 * Checks if there is another node in the backward direction
		 */
		public boolean hasPrevious() {
			return !(index == 0);
		}
		
		/**
		 * Advances the "cursor" of the iterator by one and returns the data in the next node
		 */
		@SuppressWarnings("unchecked")
		public N next() {
			if (index < size) {
				nextNode = nextNode.next;
				previousNode = previousNode.next;
				index++;
				//System.out.println("The new next is: " + nextNode);
				return (N) previousNode.element;
			} else {
				throw new NoSuchElementException();
			}	
		}
		
		/**
		 * Retreat the "cursor" of the iterator by one and returns the data in the previous node
		 */
		public N previous() {
			if (index > 0) {
				nextNode = previousNode;
				previousNode = previousNode.previous;
				index--;
				//System.out.println("The new previous is: " + previousNode);
				return (N) nextNode.element;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		/**
		 * Gets the location of the "cursor"
		 * @return cursor's location
		 */
		public int getIndex() {
			return index;
		}
		
		/**
		 * This operation is not supported by this list iterator
		 * @throws UnsupportedOperationException
		 */
		public void add(Object e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		/**
		 * This operation is not supported by this list iterator
		 * @throws UnsupportedOperationException
		 */
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
		 * This operation is not supported by this list iterator
		 * @throws UnsupportedOperationException
		 */
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
		 * This operation is not supported by this list iterator
		 * @throws UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
		 * This operation is not supported by this list iterator
		 * @throws UnsupportedOperationException
		 */
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}
}
