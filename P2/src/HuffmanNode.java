public class HuffmanNode {
	public byte b;
	public int count;
	public boolean[] code;
	public HuffmanNode left;
	public HuffmanNode right;

	public HuffmanNode(byte b, int c) {
		count = c;
		this.b = b;
		left = null;
		right = null;
	}
}