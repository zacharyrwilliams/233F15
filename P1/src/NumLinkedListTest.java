import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
/**
 * 
 * @author Zack Williams
 *
 */

public class NumLinkedListTest {
	@Test
	public void stressTest() {
		Random r = new Random();
		NumLinkedList list = new NumLinkedList();
		NumLinkedList otherList = new NumLinkedList();
		
		double randomValue;
		double rangeMin = 0.0;
		double rangeMax = 50.0;
		for(int i = 0; i < 1000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			list.add(randomValue);
		}
		
		for(int i = 0; i < 1000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			otherList.add(randomValue);
		}
		list.equals(otherList);
		
		list.remove(456);
		list.remove(355);
		list.remove(793);
		list.removeDuplicates();
		list.size();
		
		otherList.toString();
	}
	
	@Test
	public void testSize() {
		NumLinkedList list = new NumLinkedList();
		assertEquals("List initialized with nothing should" +
				"have a size of 0.",list.size(), 0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		assertEquals("size() does not correctly count the "
				+"number of elements", list.size(), 3);
	}

	@Test
	public void testAdd() {
		NumLinkedList list = new NumLinkedList();
		list.add(5.0);
		assertEquals("After first element is added, size " +
		"should be one",list.size(), 1);
		
		list.add(5.0);
		list.add(0.0);
		list.add(4.534);
		
		assertEquals("list should count both repeating values as well as 0 values",
				list.size(),4);
	}

	@Test
	public void testInsert() {

		NumLinkedList list = new NumLinkedList();
		double epsilon = .000001;
		
		list.add(0.0);
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		
		//Did you know JUnit will no longer allow you to exactly compare doubles?
		assertEquals("If this fails then there is a problem with add or lookup", 
				list.lookup(3), 3.0, epsilon);
		
		list.insert(4, 4.0); //Should behave the same as add
		assertEquals("When an item is inserted at the end, item should be added" + "" +
				" to the end",list.lookup(4),4.0, epsilon);
		
		list.insert(2, 2048.0);
		assertEquals("Inserting into the middle should shift the rest of the "+
				"list over", list.lookup(2), 2048.0, epsilon);
		assertEquals("Inserting into the middle should shift the rest of the"+
				"list over", list.lookup(3), 2.0, epsilon);
	}

	@Test
	public void testRemove() {
		NumLinkedList list = new NumLinkedList();
		double epsilon = .000001;
		
		list.add(0.0);
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		
		list.remove(2);
		
		assertEquals("Remove should be shifting the next elements back", 
				list.lookup(2), 2.0, epsilon);
	}

	@Test
	public void testContains() {
		NumLinkedList list = new NumLinkedList();
		assertFalse("Empty list should not say it contains 0", list.contains(0.0));
		
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		
		assertFalse("Empty list should not contain 0 even if there is extra"
				+ " capacity in the array", list.contains(0.0));
		assertTrue("contains() should find an element that is there"
				, list.contains(2.0));
		assertFalse("contains() should not find an element that is not there",
				list.contains(3.14159));
	}

	@Test
	public void testLookup() {
		NumLinkedList list = new NumLinkedList();
		double epsilon = .000001;
		
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		
		assertEquals("lookup() failed to find the 0th element",
				list.lookup(0), 1.0, epsilon);
		assertEquals("lookup() failed to find a middle element", list.lookup(3), 4.0, epsilon);
		assertEquals("lookup() failed to find the last element",
				list.lookup(4), 5.0, epsilon);
	}

	@Test
	public void testEqualsNumList() {
		NumLinkedList list = new NumLinkedList();
		NumLinkedList list2 = new NumLinkedList();
		
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		
		list2.add(1.0);
		list2.add(2.0);
		list2.add(3.0);
		list2.add(4.0);
		list2.add(5.0);
		
		assertTrue("Lists with the same elements in the same order"+
		"should return equals() as true", list.equals(list2));
		assertTrue("It shouldn't matter which list calls equals()", list2.equals(list));
		
		list2.add(0.0);
		
		assertFalse("Adding an extra entry- even if 0.0 "+
		"should make lists unequal", list.equals(list2));
		
		list.add(0.0);
		list.add(15.4);
		list.add(35.0);
		
		list2.add(35.0);
		list2.add(15.4);
		
		assertFalse("Order matters for NumLinkedList equality", list.equals(list2));
		
	}
	
	@Test
	public void testRemoveDuplicates() {
		NumLinkedList list = new NumLinkedList();
		
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		list.add(6.0);
		list.add(7.0);
		list.add(8.0);
		list.add(2.0);
		
		list.removeDuplicates();
		
		assertEquals("size() should be adjusted correctly down when"+
		"duplicates are removed", list.size(), 8);
		assertTrue("One copy of an elements whose duplicates"+
		" are removed should still be in the array", list.contains(2.0));
	}
	
	@Test
	public void testToString() {
		NumLinkedList list = new NumLinkedList();		
		assertEquals("Empty list should yield the empty string", list.toString(), "");
		
		list.add(0.0);		
		assertEquals("List with only 0 element should return as such", list.toString(), "0.0");
		
		list.add(0.0);		
		assertEquals("String should contain duplicates", list.toString(), "0.0 0.0");
		assertFalse("There should not be trailing spaces", list.toString().equals("0.0 0.0 "));
	}

}
