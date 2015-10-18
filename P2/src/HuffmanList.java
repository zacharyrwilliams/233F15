import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanList implements Iterable<HuffmanNode> {
	private LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
	private ByteCounter counter;
	
	public HuffmanList(byte[] input) {
		//Create ByteCounter, take in data from its structure
		counter = new ByteCounter(input); 
		byte[] data = counter.getElements();
		
		//Sort the data by counts
		byte x;  
		for(int i = 0; i < data.length; i++) {
			int j = i;			   
			while(j>0 && counter.getCount(data[j-1]) >= counter.getCount(data[j])) {				
				x = data[j];
				data[j] = data[j-1];
				data[j-1] = x;
				j--;
		    	}
			}
		
		//Create list
		for(int i = 0; i < data.length; i++) {
			list.add(new HuffmanNode(data[i], counter.getCount(data[i])));
		}
	}
	
	public HuffmanList(String inputFileName) {
		//Things
	}
	
	public HuffmanList(byte[] input, int[] counts) {
		//Stuff
	}

	@Override
	public Iterator<HuffmanNode> iterator() {
		return list.iterator();
	}
}