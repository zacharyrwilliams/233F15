import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ByteCounter extends LinkedList<HuffmanNode>{
	/**
	 * Java yelled at me until I added a serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Order order = Order.byByte;

	public ByteCounter(byte[] input) {
		buildList(input);
	}
	
	public ByteCounter(String fileName) throws FileNotFoundException {
		Path filePath = Paths.get(fileName);
		
		try {
			byte[] input = Files.readAllBytes(filePath);
			buildList(input);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ByteCounter(byte[] input, int[] counts) {
		if(input.length != counts.length) {
			throw new IllegalArgumentException("Input arrays are not same length");
		}
		
		for(byte item : input) { //First way I could think of to check for duplicates
			if(this.contains(item)) {
				throw new IllegalArgumentException("Byte input has duplicates"); //Input byte array should not have duplicates
			}
		}
		for(int count : counts) { //Check that counts are non-negative
			if(count < 0) {
				throw new IllegalArgumentException("Counts input has negative values");
			}
		}
		for(int i=0; i < input.length; i++) {
			this.add(new HuffmanNode(input[i], counts[i]));
		}
	}

	public int getCount(byte b) {
		for(HuffmanNode node : this) {
			if(node.b == b) {
				return node.count;
			}			
		}
		return 0;
	}

	public int[] getCount(byte[] b) {
		int[] counts = new int[this.size()];

		for(int i=0; i < counts.length; i++) {
			counts[i] = getCount(b[i]);
		}
		return counts;
	}

	public byte[] getElements() {
		HuffmanNode[] huff = this.toArray(new HuffmanNode[this.size()]);
		
		byte[] array = new byte[huff.length];
		for(int i=0; i < array.length; i++) {
			array[i] = huff[i].b;
		}

		return array;
	}


	public void setOrder(String order) {
		switch(order) {
		case "byte":
			this.order = Order.byByte;
			break;

		case "countInc":
			this.order = Order.countInc;
			break;

		case "countDec":
			this.order = Order.countDec;
			break;

		default:
			throw new IllegalArgumentException();
		}		
		sort();
	}

	public String toString() {//Returns in format of byte:count
		StringBuilder builder = new StringBuilder();
		for(HuffmanNode item : this) {
			builder.append(item.b + ":" + item.count + " ");
		}

		return builder.toString().trim();
	}

	public String toString(String format) {
		if(format == "byte" ) {
			return this.toString();
		}
		else if(format != "char") {
			throw new IllegalArgumentException();
		}
		StringBuilder builder = new StringBuilder();
		for(HuffmanNode item : this) {
			builder.append((char) item.b + ":" + item.count + " ");
		}

		return builder.toString().trim();
		
		
	}	
	
	private int findIndex(byte b) {
		//Finds index of the node with byte b
		//Assumes byte is actually in list, otherwise outputs -1
		int index = -1;
		for(HuffmanNode node : this) {
			if(node.b == b) {
				index = this.indexOf(node);
			}
		}
		
		return index;
	}
	
	private boolean hasByte(byte b) {
		for(HuffmanNode node : this) {
			if(node.b == b) {
				return true;
			}
		}
		
		return false;
	}

	private void buildList(byte[] input) {
		
		for(byte b : input) {
			if(!hasByte(b)) {
				this.add(new HuffmanNode(b, 1));
			}
			
			else {
				int index = this.findIndex(b);
				HuffmanNode node = this.get(index);
				node.count = node.count+1;
			}
		}
	}
	
	protected void sort() {
		//I'm lazy and don't want to type Collections.sort. more than once
		Collections.sort(this, new HuffmanNodeComparator(this.order));
	}
}