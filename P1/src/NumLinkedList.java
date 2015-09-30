/**
 * @author Zack Williams
 * 
 */

public class NumLinkedList implements NumList {
 private Node head;
 private Node current;
 private Node tail;
 private int size;
 private int currentIndex;
 
 private class Node{
  private Node next;
  private double value;
  
  public Node(double val, Node node) {
   setValue(val);
   setNextNode(node);
  }
  
  public void setNextNode(Node node) {
   next = node;
  }
  
  public void setValue(double val) {
   value = val;
  }
  
  public double getValue() {
   return value;
  }
  
  public Node getNext() {
   return next;
  }
 }
 
 public NumLinkedList() {
  size = 0;
 }
 @Override
 public int size() {
  return size;
 }

 @Override
 public void add(double value) {
  Node node = new Node(value, null);
  
  if(size == 0) {
   current = node;
   currentIndex = 0;
   head = node;
  }
  else {
   tail.setNextNode(node);
  }
  
  tail = node;
  size++;
 }

 private void traverse(int i) {
	 current = head;
	 int currentIndex = 0;
	 while(currentIndex < i) {
		 current = current.getNext();
		 currentIndex++;
	 }
 }
 
 @Override
 public void insert(int i, double value) {
  if(i < 0) {
   throw new IndexOutOfBoundsException("Negative indices are not valid");
  }
  else if(i == 0) {
	  Node node = new Node(value, head.getNext());
	  head = node;
  }
  else if(i >= size-1) {
   add(value);
  }
  else {//A->B->N->C->D->E
	  traverse(i-1);
	  current.setNextNode(new Node(value, current.getNext()));
	  size++;
  }
 }
 
 @Override
 public void remove(int i) {
	 if(i < 0) {
		 throw new IndexOutOfBoundsException("Negative indices are not valid");
	 }
	 else if(i >= size) {
		 throw new IndexOutOfBoundsException("Input must be within range of list");
	 }
	 else if(i == size-1) {
		 traverse(i);
		 current.setNextNode(null);
		 tail = current;
		 size--;
	 }
	 else {
		 traverse(i);
		 current.setNextNode(current.getNext().getNext());
		 size--;
	 }
 }
 
 @Override
 public boolean contains(double value) {
  for(int i = 0; i < size(); i++) {
   if(lookup(i) == value) {
	  return true;
   }
  }
  return false;
 }

 @Override
 public double lookup(int i) {
  if(i < 0) {
   throw new IndexOutOfBoundsException("Negative indices are not valid");
  }
  else if(i >= size) {
   throw new IndexOutOfBoundsException("There is no " + Integer.toString(i) + "th element");
  }
  else {
   traverse(i);
  }
  
  return current.getValue();
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
  NumLinkedList temp = new NumLinkedList();
  
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
  StringBuilder builder = new StringBuilder();
  
  if(size != 0) {
   current = head;
   currentIndex = 0;
   
   while(currentIndex < size-1) {
  builder.append(current.getValue() + " ");
  current = current.getNext();
  currentIndex++;
   }
   builder.append(current.getValue());
  }  
  
  return builder.toString();
 }
}