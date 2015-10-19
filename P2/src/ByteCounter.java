import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;

public class ByteCounter {
	private ArrayList<HuffmanNode> list = new ArrayList<HuffmanNode>();
	private Order order = Order.byByte;
	
	public ByteCounter(byte[] input) {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		
		//Create list of present bytes, list of counts
		for(byte value : input) {	
			if(bytes.contains(value)) {
				int index = bytes.indexOf(value);
				counts.set(index, counts.get(index)+1);
			}
			else {
				bytes.add(value);
			}			
		}
		//Create the list I actually want to use- the list of HuffmanNodes
		for(int i=0; i < counts.size(); i++) {
			list.add(new HuffmanNode(bytes.get(i), counts.get(i)));
		}
	}
	
	public ByteCounter(String fileName) throws FileNotFoundException {
		
	}
	
	public int getCount(byte b) {
		for(HuffmanNode node : list) {
			if(node.b == b) {
				return node.count;
			}			
		}
		return 0;
	}
	
	public int[] getCount(byte[] b) {
		int[] counts = new int[list.size()];
		
		for(int i=0; i < counts.length; i++) {
			counts[i] = getCount(b[i]);
		}
		return counts;
	}
	
	public byte[] getElements() {
		byte[] array = new byte[list.size()];
		for(int i=0; i < array.length; i++) {
			array[i] = list.get(i).b;
		}
		
		return array;
	}
	
		
	public void setOrder(String order) {
		switch(order) {
			case "byte":
				this.order = Order.byByte;
				
			case "countInc":
				this.order = Order.countInc;
				
			case "countDec":
				this.order = Order.countDec;
				
			default:
				throw new IllegalArgumentException();
		}
	}
	
	public String toString() {//Returns in format of byte:count
		StringBuilder builder = new StringBuilder();
		Arrays.sort(list.toArray(new HuffmanNode[list.size()]), new HuffmanNodeComparator(order));
		
		//Need to check sign-age of bytes
		for(HuffmanNode item : list) {
			builder.append(item.b + ":" + item.count + " ");
		}
		
		return builder.toString().trim();
	}
	
	public String toString(String format) {
		setOrder(format);
		return toString();
	}
}