import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class HuffmanList extends ByteCounter {
	
	public HuffmanList(byte[] input) {
		super(input);
		setOrder("countInc");
	}

	public HuffmanList(String fileName) throws FileNotFoundException {
		super(fileName);
		setOrder("countInc");
	}

	public HuffmanList(byte[] input, int[] counts) {
		super(input, counts);
		setOrder("countInc");
	}
}