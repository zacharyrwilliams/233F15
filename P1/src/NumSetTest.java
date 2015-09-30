import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
/**
 * 
 * @author Zack Williams
 *
 */

public class NumSetTest {
		
	@Test
	public void stressTest() {
		Random r = new Random();
		NumSet set = new NumSet();
		NumSet otherSet = new NumSet();
		
		double randomValue;
		double rangeMin = 0.0;
		double rangeMax = 50.0;
		for(int i = 0; i < 10000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			set.add(randomValue);
		}
		
		for(int i = 0; i < 10000; i++) {
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			otherSet.add(randomValue);
		}
		NumSet.equivalence(set, otherSet);
		
		
		otherSet.toString();
	}
	
	@Test
	public void testSize() {
		double[] array = {1.0,3.0,5.0,7.0,9.0,11.0,11.0,11.0};
		NumSet set = new NumSet(array);
		assertTrue("size should not be counting repeats", set.size() == 6);
		
		NumSet emptySet = new NumSet();
		assertTrue("empty sets should have a size of 0", emptySet.size() == 0);
	}

	@Test
	public void testUnionNumSet() {
		double[] array = {1.0,3.0,5.0,7.0,9.0,11.0};
		double[] array2 = {0.0,2.0,4.0,6.0,8.0,10.0};
		NumSet set = new NumSet(array);
		NumSet set2 = new NumSet(array2);
		
		NumSet result = NumSet.union(set, set2);
		
		assertTrue("Union does not correctly add elements",
				result.toString().equals("1.0 3.0 5.0 7.0 9.0 11.0 " +
						"0.0 2.0 4.0 6.0 8.0 10.0"));
	}

	@Test
	public void testIntersectNumSet() {
		double[] array = {1.0,3.0,5.0,7.0,9.0,11.0};
		double[] array2 = {0.0,2.0,4.0,6.0,8.0,10.0};
		NumSet set = new NumSet(array);
		NumSet set2 = new NumSet(array2);
		
		NumSet result = NumSet.intersect(set, set2);
		
		assertTrue("Intersection of disjoint sets should be null",
				result.toString().equals(""));
		
		double[] primes = {2.0, 3.0, 5.0, 7.0, 11.0, 13.0, 15.0};
		
		NumSet set3 = NumSet.union(set, set2);
		NumSet set4 = new NumSet(primes);
		NumSet data = NumSet.intersect(set3, set4);
		assertTrue("Intersect should contain the primes from 0-11",
				data.toString().equals("3.0 5.0 7.0 11.0 2.0"));
		
	}

	@Test
	public void testEquivalenceNumSet() {
		double[] array = {2.0,4.0,6.0,8.0};
		double[] array2 = {4.0,6.0,8.0,2.0};
		
		NumSet set1 = new NumSet(array);
		NumSet set2 = new NumSet(array2);
		
		assertTrue("Order does not matter in sets",NumSet.equivalence(set1,set2));
		
		set1.add(8.0);
		assertTrue("Trying to add a repeat element shouldn't matter", 
				NumSet.equivalence(set1,set2));
	}
	
	@Test
	public void testContains() {
		double[] array = {1.0,3.0,5.0,7.0,9.0,11.0};
		double[] array2 = {0.0,2.0,4.0,6.0,8.0,10.0};
		NumSet set = new NumSet(array);
		NumSet set2 = new NumSet(array2);
		
		assertTrue("contains() can't find a present element", set.contains(11.0));
		assertFalse("contains() managed to find an element that wasn't there", set.contains(-3.14159));
		assertTrue("contains() doesn't work after union-ing",
				NumSet.union(set, set2).contains(0.0));
	}

	@Test
	public void testToString() {
		double[] array = {1.0,3.0,5.0,7.0,9.0,11.0};
		double[] array2 = {0.0,2.0,4.0,6.0,8.0,10.0};
		NumSet set = new NumSet(array);
		NumSet set2 = new NumSet(array2);
		
		NumSet result = NumSet.union(set, set2);
		
		assertFalse("toString() isn't placing the order correctly",
				result.toString().equals("0.0 1.0 2.0 3.0 4.0 5.0 " +
						"6.0 7.0 8.0 9.0 10.0 11.0"));
		NumSet set3 = new NumSet();
		
		assertTrue("Empty set should yield empty string", set3.toString().equals(""));
	}

}
