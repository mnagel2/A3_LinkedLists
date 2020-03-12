
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedDoubleLinkedList_STUDENT_Test {
	
	SortedDoubleLinkedList<String> names;
	SortedDoubleLinkedList<String> words;
	SortedDoubleLinkedList<Double> nums;
	SortedDoubleLinkedList<Double> nums2;
	
	StringComparator sComp;
	DoubleComparator dComp;
	
	String s1,s2,s3,s4,s5,s6;
	Double d1,d2,d3,d4,d5,d6;
	Double d7,d8,d9,d10,d11,d12;
	
	@Before
	public void setUp() {
		
		sComp = new StringComparator();
		dComp = new DoubleComparator();
		
		nums = new SortedDoubleLinkedList<Double>(dComp);
		nums2 = new SortedDoubleLinkedList<Double>(dComp);
		names = new SortedDoubleLinkedList<String>(sComp);
		words = new SortedDoubleLinkedList<String>(sComp);
		
		// s2, s1, s5, s3, s4, s6
		s1 = "Guerpo";
		s2 = "Bolgus";
		s3 = "Klunkle";
		s4 = "Sitpaklus";
		s5 = "Hink";
		s6 = "Victor";
		
		// d1, d2, d3, d4, d5, d6
		d1 = 1.89;
		d2 = 2.52;
		d3 = 3.14;
		d4 = 4.25;
		d5 = 5.37;
		d6 = 6.21;
		
		// d7, d8, d9, d10, d11, d12
		d7  = 2.89;
		d8  = 3.52;
		d9  = 4.14;
		d10 = 5.25;
		d11 = 6.37;
		d12 = 7.21;
		
		// d1, d2, d7, d3, d8, d9, d4, d10, d5, d6, d11, d12
	}
	
	@After
	public void tearDown() {
		nums = nums2 = null;
		names = null;
		
		
	}
	
	@Test
	public void testAddNames() {
		System.out.println(".testAddNames()");
		names.add(s1);
		System.out.println(names.toString());
		names.add(s2);
		System.out.println(names.toString());
		names.add(s3);
		System.out.println(names.toString());
		names.add(s4);
		System.out.println(names.toString());
		names.add(s5);
		System.out.println(names.toString());
		names.add(s6);
		System.out.println(names.toString());
		System.out.println("");
		
		assertEquals(s2, names.firstNode.element);
		assertEquals(s6, names.lastNode.element);
	}
	
	@Test
	public void testAddNumberToEnd() {
		System.out.println(".testAddNumberToEnd()");
		nums.add(d1);
		System.out.println(nums.toString());
		nums.add(d2);
		System.out.println(nums.toString());
		nums.add(d3);
		System.out.println(nums.toString());
		nums.add(d4);
		System.out.println(nums.toString());
		nums.add(d5);
		System.out.println(nums.toString());
		nums.add(d6);
		System.out.println(nums.toString());
		System.out.println("");
		
		assertEquals(d1, nums.firstNode.element);
		assertEquals(d6, nums.lastNode.element);
	}
	
	@Test
	public void testAddWords() {

		System.out.println(".testAddWordsOne()");
		words.add("toki");
		System.out.println(words.toString());
		words.add("pona");
		System.out.println(words.toString());
		words.add("li");
		System.out.println(words.toString());
		words.add("pona");
		System.out.println(words.toString());
		words.add("ala");
		System.out.println(words.toString());
		words.add("pona");
		System.out.println(words.toString());
		System.out.println("");
		
		assertEquals("toki",words.firstNode.next.next.next.next.next.element);
		assertEquals("toki",words.lastNode.element);
	}
	
	@Test
	public void testAddNumberToFront() {
		System.out.println(".testAddNumberToFront()");
		nums.add(d6);
		System.out.println(nums.toString());
		nums.add(d5);
		System.out.println(nums.toString());
		nums.add(d4);
		System.out.println(nums.toString());
		nums.add(d3);
		System.out.println(nums.toString());
		nums.add(d2);
		System.out.println(nums.toString());
		nums.add(d1);
		System.out.println(nums.toString());
		System.out.println("");
		
		assertEquals(d1, nums.firstNode.element);
		assertEquals(d6, nums.lastNode.element);
	}
	
	@Test
	public void testAddNumberFrontAndEndEquivalency() {
		System.out.println(".testAddNumberFrontAndEndEquivalency()");
		System.out.println(" Filling nums...");
		nums.add(d6);
		System.out.println(nums.toString());
		nums.add(d5);
		System.out.println(nums.toString());
		nums.add(d4);
		System.out.println(nums.toString());
		nums.add(d3);
		System.out.println(nums.toString());
		nums.add(d2);
		System.out.println(nums.toString());
		nums.add(d1);
		System.out.println(nums.toString());
		
		System.out.println(" Filling nums2...");
		nums2.add(d1);
		System.out.println(nums2.toString());
		nums2.add(d2);
		System.out.println(nums2.toString());
		nums2.add(d3);
		System.out.println(nums2.toString());
		nums2.add(d4);
		System.out.println(nums2.toString());
		nums2.add(d5);
		System.out.println(nums2.toString());
		nums2.add(d6);
		System.out.println(nums2.toString());
		
		System.out.println("");
		
		assertEquals(nums.firstNode.element, nums2.firstNode.element);
		assertEquals(nums.lastNode.element, nums2.lastNode.element);
		
		assertEquals(nums.lastNode.previous.previous.element, nums2.lastNode.previous.previous.element);
	}
	
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
}
