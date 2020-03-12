import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic sorted double-linked list class
 * @author Montague Nagel
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

	//------------------+-FIELDS-+----------------------------
	
	// Inherited from BasicDoubleLinkedList:
	// int size
	// Node<T> firstNode, lastNode
	
	/**
	 * Comparator to be used in comparisons
	 */
	Comparator<T> comp;
	
	//------------------+-CONSTRUCTORS-+----------------------
	
	/**
	 * Initializes a list with the specified comparator
	 * @param sComp
	 */
	public SortedDoubleLinkedList(Comparator<T> sComp) {
			comp = sComp;
			size = 0;
			firstNode = null;
			lastNode = null;
	}
	
	//------------------+-METHODS-+---------------------------
	
	/**
	 * Adds the element at the correct position in the sorted list
	 * @param data
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node<T> newNode = null;
		
		if (comp == null) {
			throw new UnsupportedOperationException("The comparator has not been initialized.");
		}
		
		if (isEmpty()) {												// Is list empty?
			firstNode = lastNode = new Node<T>(data);
			size++;
			return this;
		}
		
		
		if (comp.compare(data, firstNode.element) <= 0) {				// Is it the firstNode?
			firstNode.previous = new Node<T>(null, data, firstNode);
			firstNode = firstNode.previous;
			size++;
			return this;
		}
		
		if (comp.compare(data, lastNode.element) >= 0) {				// Is it the lastNode?
			lastNode.next = new Node<T>(lastNode, data, null);
			lastNode = lastNode.next;
			size++;
			return this;
		}
		
		Node<T> searchNode = firstNode;
		while (comp.compare(data,searchNode.element) >= 0) {				// Look through rest of Nodes to find the spot
				searchNode = searchNode.next;
		}
		
		newNode = new Node<T>(searchNode.previous,data,searchNode);
		searchNode.previous.next = newNode;
		searchNode.previous = newNode;
		
		size++;
		return this;
		
	}
	
	/**
	 * Inherited from BasicDoubleLinkedList.java 
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException,
	 										NoSuchElementException {
		return super.iterator();
	}
	
	
	/**
	 * Inherited from BasicDoubleLinkedList.java
	 */
	public SortedDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(targetData, comparator);
	}
	
	/**
	 * This operation is not supported by this type of list
	 * @throws UnsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	/**
	 * This operation is not supported by this type of list
	 * @throws UnsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	
	
	//------------------+-INNER CLASSES-+---------------------
	
	// Inherited from BasicDoubleLinkedList:
	// Node<T>
	// DoubleLinkedListIterator
	
}
