import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;

public class HuffmanCode extends HuffmanList{
	
	//Java yells at me unless a let it have a UID
	private static final long serialVersionUID = 1L;
	private LinkedList<HuffmanNode> leafNodes; //This makes my life easier

	public HuffmanCode(byte[] input) {
		super(input);
		initialize();
	}
	
	
	public HuffmanCode(String fileName) throws FileNotFoundException {
		super(fileName);
		initialize(); 
	}

	public HuffmanCode(byte[] input, int[] counts) {
		super(input, counts);
		initialize();
	}
	
	//This is called from constructor, and builds the tree out of the original list
	//and then gives each leaf node a code
	private void initialize() {
		leafNodes = new LinkedList<HuffmanNode>();
		buildTree();
		makeCode();
	}

	private void makeCode() {
		HuffmanNode node = this.getFirst();
		boolean[] bool = new boolean[1];
		if(node.left != null) {
			bool[0] = false;
			setCodes(node.left, bool);
		}
		boolean[] otherbool = new boolean[1];
		if(node.right != null) {
			otherbool[0] = true;
			setCodes(node.right, otherbool);
		}
	}

	private void setCodes(HuffmanNode node, boolean[] bool) {
		if(!isLeaf(node)) {
			if(node.left != null) {
				setCodes(node.left, nextBool(bool, false));
			}
			if(node.right != null) {
				setCodes(node.right, nextBool(bool, true));
			}
		}
		else { //isLeaf(node)
			node.code = bool;
			for(boolean b : bool) {
			}
			leafNodes.add(node);
		}
	}
	
	private boolean[] nextBool(boolean[] bool, boolean val) {
		boolean[] nextbool = new boolean[bool.length+1];
		for(int i = 0; i < bool.length; i++) {
			nextbool[i] = bool[i];
		}
		nextbool[nextbool.length-1] = val;
		return nextbool;
	}

	private boolean isLeaf(HuffmanNode node) {
		if(node.left != null || node.right != null) {//If it has children, it is not a leaft
			return false;
		}
		return true;
	}
	
	//This turns my list of huffman nodes into a proper tree, merging as
	//the Huffman Code is supposed to 
	private void buildTree() {
		while(this.size() > 1) {
			HuffmanNode a = this.remove();
			HuffmanNode b = this.remove();
			this.add(merge(a,b));
			sort();
		}
	}

	private HuffmanNode merge(HuffmanNode a, HuffmanNode b) {
		HuffmanNode node = new HuffmanNode((byte) 0, a.count+b.count);
		node.left = a;
		node.right = b;
		return node;
	}

	public boolean[] code(byte b) {
		for(HuffmanNode node : leafNodes) {
			if(node.b == b)  {
				return node.code;
			}	
		}
		//If not already exited, it means the byte wasn't found in my list
		throw new IllegalArgumentException();
	}

	public String codeString(byte b) {
		boolean[] byteCode = code(b);
		StringBuilder builder = new StringBuilder();
		
		for(boolean bool : byteCode) {
			if(bool == false) {
				builder.append(0);
			}
			else if(bool == true) {
				builder.append(1);
			}
			else {
				throw new IllegalArgumentException("If this happens I cry");
			}
		}
		return builder.toString();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		Collections.sort(leafNodes, new HuffmanNodeComparator(Order.countDec));
		for(HuffmanNode node : leafNodes) {
			builder.append(node.b + ": " + codeString(node.b) +"\n");
		}
		return builder.toString().trim();
	}
}