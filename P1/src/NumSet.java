/**
 * @author Zack Williams
 *
 */

public class NumSet extends NumArrayList {
 
 public NumSet() {}
 
 public NumSet(double[] input) {
  for(int i = 0; i < input.length; i++) {
   add(input[i]);
  }
  removeDuplicates();
 }
 
 @Override
 public void add(double value) {
  if(!contains(value)) {
   super.add(value);
  }
 }
 
 @Override
 public void insert(int i, double value) {
  add(value);
 }
 
 public static NumSet union(NumSet set1, NumSet set2) {
  double[] array = new double[set1.size() + set2.size()];
  
  for(int i = 0; i < set1.size(); i++) {
   array[i] = set1.lookup(i);
  }
  for(int i = set1.size(); i < set1.size() + set2.size(); i++) {
   array[i] = set2.lookup(i-set1.size());
  }
  
  return new NumSet(array);
 }
 
 public static NumSet intersect(NumSet set1, NumSet set2) {
  NumArrayList list = new NumArrayList();
  
  for(int i = 0; i < set1.size(); i++) {
	  if(set2.contains(set1.lookup(i))) {
		  list.add(set1.lookup(i));
	  }
  }
  
  return new NumSet(list.toArray());
 }
 
 private static double[] sort(double[] array) {
  double x;  
  for(int i = 0; i < array.length; i++) {
   int j = i;
   
   while(j>0 && array[j-1] > array[j]) {
	   x = array[j];
	   array[j] = array[j-1];
	   array[j-1] = x;
	   j--;
   	}
  }  
  return array;
 }
 
 public static boolean equivalence(NumSet set1, NumSet set2) {
  if(set1.size() != set2.size()) {
   return false;
  }
  
  double[] array1 = NumSet.sort(set1.toArray());
  double[] array2 = NumSet.sort(set2.toArray());
  
  for(int i = 0; i < array1.length; i++) {
   if(array1[i] != array2[i]) {
  return false;
   }
  }
  
  return true;
 }
}