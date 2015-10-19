import java.util.Comparator;

public class HuffmanNodeComparator implements Comparator<HuffmanNode> {
	private Order order;
	
	public HuffmanNodeComparator(Order order) {
		this.order = order;
	}

	@Override
	public int compare(HuffmanNode node1, HuffmanNode node2) {
		switch(order) {
			case byByte:
				return node1.b - node2.b;
				
			case countInc:
				if(node1.count != node2.count) {
					return node1.count - node2.count;
				}
				else if(node1.b != node2.b) {
					return node1.b - node2.b;
				}
				return 0;
			
			case countDec:
				if(node1.count != node2.count) {
					return node2.count - node2.count;
				}
				else if(node1.b != node2.b) {
					return node1.b - node2.b;
				}
				return 0;
				
		}
		return 0;
	}
}
