import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
/**
 * 
 * @author Zack Williams
 *
 */

public class NumArrayListTest {
	@Test
	public void stressTest() {
		Random r = new Random();
		NumArrayList list = new NumArrayList();
		NumArrayList otherList = new NumArrayList();
		
		double randomValue;
		double rangeMin = 0.0;
		double rangeMax = 50.0;
		for(int i = 0; i < 10000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			list.add(randomValue);
		}
		
		for(int i = 0; i < 10000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			otherList.add(randomValue);
		}
		list.equals(otherList);
		
		list.remove(456);
		list.remove(355);
		list.remove(793);
		list.removeDuplicates();
		list.size();
		list.capacity();
		
		otherList.toString();
	}
	
	
	@Test
	public void testCapacity() {
		NumArrayList list = new NumArrayList();
		assertEquals("List initialized with nothing should" + " " +
				"have a capacity of 0.",list.capacity(), 0);
		
		NumArrayList list2 = new NumArrayList(17);
		assertEquals("List initialized to have a nonzero capacity should" +
		" have a nonzero size", list2.capacity(),17);
		
	}

	@Test
	public void testSize() {
		NumArrayList list = new NumArrayList();
		assertEquals("List initialized with nothing should" + " " +
				"have a size of 0.",list.size(), 0);
		
		NumArrayList list2 = new NumArrayList(17);
		assertEquals("List initialized to have a nonzero capacity but" +
		" no elements should return 0", list2.size(), 0);
	}

	@Test
	public void testAdd() {
		NumArrayList list = new NumArrayList();
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
		NumArrayList list = new NumArrayList();
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
		
		list.insert(0, 33.0);
		
		assertTrue("insert() not properly placing a 0th element", 
				"33.0 0.0 1.0 2048.0 2.0 3.0 4.0".equals(list.toString())); 
	}

	@Test
	public void testRemove() {
		NumArrayList list = new NumArrayList();
		double epsilon = .000001;
		
		list.add(0.0);
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		
		list.remove(2);
		
		assertEquals("Remove should be shifting the next elements back", 
				list.lookup(2), 3.0, epsilon);
		
		NumArrayList otherList = new NumArrayList();
		otherList.add(5.0);
		otherList.add(6.0);
		otherList.add(7.0);
		otherList.add(8.0);
		
		otherList.remove(0);
		assertTrue("Useful error message goes here","6.0 7.0 8.0".equals(otherList.toString()));
	}

	@Test
	public void testContains() {
		NumArrayList list = new NumArrayList();
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
		NumArrayList list = new NumArrayList();
		double epsilon = .000001;
		
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		
		assertEquals("lookup() failed to find the 0th element",
				list.lookup(0), 1.0, epsilon);
		assertEquals("lookup() failed to find the last element",
				list.lookup(4), 5.0, epsilon);
	}

	@Test
	public void testEqualsNumList() {
		NumArrayList list = new NumArrayList();
		NumArrayList list2 = new NumArrayList();
		NumArrayList list3 = new NumArrayList(50);
		
		assertTrue("Lists with different capacities but otherwise equal"+
		" should still return equal", list.equals(list3));
		
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
		
		assertFalse("Order matters for NumArrayList equality", list.equals(list2));
		
	}

	@Test
	public void testRemoveDuplicates() {
		NumArrayList list = new NumArrayList();
		
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
		assertEquals("The capacity should not be reduced down when "
				+"duplicates are removed", list.capacity(), 16);
		assertTrue("One copy of an elements whose duplicates"+
		" are removed should still be in the array", list.contains(2.0));
	}

	@Test
	public void testToString() {
		NumArrayList list = new NumArrayList();
		
		assertEquals("Empty list should yield the empty string", list.toString(), "");
		
		list.add(0.0);
		
		assertEquals("List with only 0 element should return as such", list.toString(), "0.0");
		
		list.add(0.0);
		
		assertEquals("String should contain duplicates", list.toString(), "0.0 0.0");
		
		assertFalse("There should not be trailing spaces", list.toString().equals("0.0 0.0 "));
	}
}
