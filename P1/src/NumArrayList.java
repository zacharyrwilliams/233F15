/**
 * @author Zack Williams
 *
 */

public class NumArrayList implements NumList {
 private double[] list;
 private int totalElements = 0; 

 public NumArrayList(int capacity) {
   list = new double[capacity];  
   }
  
 public NumArrayList() {
  this(0);
 }
 
 public int capacity() {
   return list.length;
 }
 
 @Override
 public int size() {
  return totalElements;
 }

 private void enlargenArray() {
	 if(list.length == 0) {
		 double[] temp = new double[1];
		 list = temp; //Don't need to copy any data since array is empty
	 }
	 else {
		 double[] temp = new double[2*list.length];
		 for(int i = 0; i < list.length; i++) { //Copy over data from list
			 temp[i] = list[i];
		 }
		 list = temp;
	 }
 }
 
 @Override
 public void add(double value) {
  if(totalElements < list.length) {
   list[totalElements] = value;
   totalElements++;
  }
  else {
	  enlargenArray();
	  add(value);
  }
 }

 @Override
 public void insert(int i, double value) {
  if(i >= totalElements) {
   add(value);
  }
  else if(i < 0) {
   throw new IndexOutOfBoundsException("Negative indices are not valid");
  }
  else {
	  insert(i+1, list[i]);
	  list[i] = value;
  }
 }

 @Override
 public void remove(int i) {
  if(i > totalElements) {
	  throw new IndexOutOfBoundsException("There isn't an " + Integer.toString(i) + "-th element");
  }
  else if(i < 0) {
	  throw new IndexOutOfBoundsException("Negative indices are not valid");
  }
  else {
	  for(int k = i; k < totalElements-1; k++) {
		  list[k] = list[k+1];
	  }
	  list[totalElements-1] = 0.0;
	  totalElements--;
  }
 }

 @Override
 public boolean contains(double value) {
  for(int i = 0; i < totalElements; i++) {
   if(list[i] == value) {
  return true;
   }
  }
  return false;
 }

 @Override
 public double lookup(int i) {
  if(i < 0) {
   throw new IndexOutOfBoundsException("Negative Indices are not valid");
  }
  else if(i >= totalElements) {
   throw new IndexOutOfBoundsException("There isn't a " + Integer.toString(i) + "th element");
  }
  else {
   return list[i];
  }
 }

 @Override
 public boolean equals(NumList otherList) {
   
   if(size() != otherList.size()) {
     return false;
  }
  
  for(int i = 0; i < size(); i++) {
   if(lookup(i) != otherList.lookup(i)) {
  return false;
   }
  }
  return true;
 }

 @Override
 public void removeDuplicates() {
  NumArrayList temp = new NumArrayList();
  
  for(int i = 0; i < size(); i++) {
   double val = lookup(i);
   if(!temp.contains(val)) {
  temp.add(val);
   }
  }
  
  while(size() > 0) {
   remove(size()-1);
  }
  
  for(int i = 0; i < temp.size(); i++) {
   add(temp.lookup(i));
  }
 }
 
 @Override
 public String toString() {
  if(size() == 0) {
   return "";
  }
  
  StringBuilder builder = new StringBuilder();
  for(int i = 0; i < size()-1; i++) {
   builder.append(list[i] + " ");
  }
  builder.append(list[size()-1]);
  return builder.toString();
 }
 
 public double[] toArray() {
  double[] array = new double[size()];
  for(int i = 0; i < size(); i++) {
   array[i] = lookup(i);
  }
  return array;
 }
}