import java.io.FileNotFoundException;
import java.util.*;

public class ByteCounter {
	private ArrayList<Byte> list = new ArrayList<Byte>();
	private ArrayList<Integer> count = new ArrayList<Integer>();
	private int[] orders; //holds either the ordering for the list array or count array, depending on which is sorted
	private String outputOrdering;
	
	public ByteCounter(byte[] input) {
		for(byte value : input) {			
			if(list.contains(value)) {
				int index = list.indexOf(value);
				count.set(index, count.get(index)+1);
			}
			else {
				list.add(value);
			}			
		}
		
		orders = new int[list.size()];
	}
	
	public ByteCounter(String fileName) throws FileNotFoundException {
		
	}
	
	public int getCount(byte b) {
		if(list.contains(b)) {
			return count.get(list.indexOf(b));
		}
		
		return 0;
	}
	
	public int[] getCount(byte[] b) {
		int[] array = new int[count.size()];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = count.get(i);
		}
		
		return array;
	}
	
	public byte[] getElements() {
		byte[] array = new byte[list.size()];
	
		for(int i = 0; i < array.length; i++) {
		array[i] = list.get(i);
		}
	
	return array;
	}
	
	public void sort(String order) {
		if(order.equals("byte")) {
			byte x;  
			for(int i = 0; i < list.size(); i++) {
				int j = i;			   
				while(j>0 && list.get(j-1) > list.get(j)) {
					x = list.get(j);
					list.set(j,  list.get(j-1));
					list.set(j-1, x);
					j--;
			    	}
				orders[i] = j;
				}  
			}
		else if(order.equals("countInc")) {
			int x;  
			for(int i = 0; i < count.size(); i++) {
				int j = i;			   
				while(j>0 && count.get(j-1) > count.get(j)) {
					x = count.get(j);
					count.set(j,  count.get(j-1));
					count.set(j-1, x);
					j--;
			    	}
				orders[i] = j;
				}  
			}
			
		else if(order.equals("countDec")) {
			int x;  
			for(int i = 0; i < count.size(); i++) {
				int j = i;			   
				while(j>0 && count.get(j-1) < count.get(j)) {
					x = count.get(j);
					count.set(j,  count.get(j-1));
					count.set(j-1, x);
					j--;
			    }
				orders[i] = j;
			}
		}
	}
		
	public void setOrder(String order) {
		outputOrdering = order;
	}
	
	public String toString() {//Returns in format of byte:count
		StringBuilder builder = new StringBuilder();
		sort(outputOrdering);
		
		if(outputOrdering.equals("byte")) {
			for(int i = 0; i < list.size()-1; i++) {
				builder.append(list.get(i).toString() + ":" + count.get(orders[i]).toString() + " ");
			}
			builder.append(list.get(list.size()).toString() + ":" + count.get(orders[list.size()]).toString());
		}
		else if(outputOrdering.equals("countInc") || outputOrdering.equals("countDec")) {
			for(int i = 0; i < list.size()-1; i++) {
				builder.append(list.get(orders[i]).toString() + ":" + count.get(i).toString() + " ");
			}
			builder.append(list.get(orders[list.size()]).toString() + ":" + count.get(list.size()).toString());
		}
		else {
			return "Something went wrong either in .toString() or in .setOrder()";
		}
		
		return builder.toString();
	}
	
	public String toString(String format) {
		return null;
	}
}