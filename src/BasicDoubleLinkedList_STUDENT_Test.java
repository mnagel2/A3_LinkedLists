
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {

	public BasicDoubleLinkedList<String> frames;
	public BasicDoubleLinkedList<Integer> grades;
	public ListIterator<String> sIt;
	public StringComparator comp;
	
	String w1, w2, w3, w4, w5;
	Integer g1, g2, g3, g4, g5;
	
	@Before
	public void setUp() {
		frames = new BasicDoubleLinkedList<String>();
		w1 = "walking1.png";
		w2 = "walking2.png";
		w3 = "walking3.png";
		w4 = "walking4.png";
		w5 = "walking5.png";
		
		grades = new BasicDoubleLinkedList<Integer>();
		g1 = 79;
		g2 = 84;
		g3 = 88;
		g4 = 94;
		g5 = 80;
		
		
		
		System.out.println("Setting up frames list...");
		System.out.println(frames.addToFront(w5));
		System.out.println(frames.addToFront(w4));
		System.out.println(frames.addToFront(w3));
		System.out.println(frames.addToFront(w2));
		System.out.println(frames.addToFront(w1));
	}
	
	@After
	public void tearDown() {
		frames = null;
		grades = null;
		
		w1=w2=w3=w4=w5=null;
		g1=g2=g3=g4=g5=null;
	}
	
	@Test
	public void testAddToFront() {
		//System.out.println("Setting up test for addToFront with frames list...");
		System.out.println(frames.addToFront(w5));
		System.out.println(frames.addToFront(w4));
		System.out.println(frames.addToFront(w3));
		System.out.println(frames.addToFront(w2));
		System.out.println(frames.addToFront(w1));
		
		
	}
	
	@Test
	public void testAddToEnd() {
		//System.out.println("Setting up test for addToEnd with frames list...");
		System.out.println(frames.addToEnd(w1));
		System.out.println(frames.addToEnd(w2));
		System.out.println(frames.addToEnd(w3));
		System.out.println(frames.addToEnd(w4));
		System.out.println(frames.addToEnd(w5));
		
		
	}
	
	@Test
	public void testRetrieveFirstElement() {
		System.out.println(".testRetrieveFirstElement()");
		System.out.println("Retrieved: " + frames.retrieveFirstElement());
		assertEquals(4,frames.getSize());
		
		assertEquals(0,grades.getSize());
		System.out.println(grades.addToFront(g1));
		assertEquals(g1,grades.firstNode.element);
		assertEquals(g1,grades.retrieveFirstElement());
	}
	
	@Test
	public void testRetrieveLastElement() {
		assertEquals("walking5.png",frames.retrieveLastElement());
	}
	
	@Test
	public void testIterator() {
		System.out.println(".testIterator()");
		
		sIt = frames.iterator();
		
		assertEquals(true, sIt.hasNext());
		
		assertEquals(w1, sIt.next());
		
		System.out.println("Right Frame? Should be the second one: " + sIt.next());
		
		assertEquals(w2, sIt.previous());
		
		assertEquals(true, sIt.hasPrevious());
		
		System.out.println("");
	}

	private class StringComparator implements Comparator<String>
	{
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}	
	}
	
}
