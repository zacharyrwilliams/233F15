import java.util.Comparator;

public class HuffmanNodeComparator implements Comparator<HuffmanNode> {
	private Order order;

	public HuffmanNodeComparator(Order order) {
		this.order = order;
	}

	@Override
	public int compare(HuffmanNode node1, HuffmanNode node2) {
		int result = 0;
		
		switch(order) {
		case byByte:
			result = node1.b - node2.b;
			break;

		case countInc:
			if(node1.count != node2.count) {
				result = node1.count - node2.count;
			}
			else if(node1.b != node2.b) {
				result = node1.b - node2.b;
			}
			else {
				result = 0;
			}
			break;

		case countDec:
			if(node1.count != node2.count) {
				result = node2.count - node1.count;
			}
			else if(node1.b != node2.b) {
				result = node1.b - node2.b;
			}
			else {
				result = 0;
			}
			break;

		}
		return result;
	}
}
