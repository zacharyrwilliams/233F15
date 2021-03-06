import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;


/*
 * This class implements BFS on the N-puzzle.
 * 
 * This class is not meant to actually be instantiated- you can call its solve method
 * without having created a solver object.
 * 
 * This class is also actually identical to the DFS implementation also included,
 * the only difference being how we pop objects out from the queue. Popping from the
 * front makes it a BF search, popping from the back makes it a DF search. There are
 * other ways to implement them, but this was conceptually the easiest- literally 
 * allowing me to just copy-paste code, changing a pollFirst() call to pollLast().
 */
public class BFSsolver {

	public static void solve(Puzzle root) {
		System.out.println("Running: Breadth-First Search");

		Deque<Puzzle> queue = new LinkedList<Puzzle>();
		HashSet<String> visited = new HashSet<String>();

		System.out.println("Input Puzzle: ");
		System.out.println(root.printBoard());

		queue.add(root);
		visited.add(root.toString());
		Puzzle current = root;	

		System.out.println("Searching...");
		int count = 0;
		while((current.isGoal() == false) && (queue.isEmpty() == false) && (count < 2000000)) {
			current = queue.pollFirst();
			count++;

			for(Puzzle neighbor : current.getNeighbors()) {
				if(!visited.contains(neighbor.toString())) {
					queue.add(neighbor);
					visited.add(neighbor.toString());
					neighbor.setParent(current);
				}
			}
			if(count % 10000 == 0) {
				System.out.println(count+ " nodes checked...");
			}
		}

		if(count >= 2000000) {
			System.out.println("I gave up because I will run out of memory soon");
		}

		System.out.println("Here is what I exited the search at:");
		System.out.println(current.printBoard());

		if(current.isGoal()) {
			System.out.println("Finished search! Building solution tree...");
			ArrayList<Puzzle> list = new ArrayList<Puzzle>();
			while(current.hasParent()) {
				list.add(0, current);
				current = current.getParent();
			}
			list.add(0,current);

			for(Puzzle state : list) {
				System.out.println("*******Next Move*******");
				System.out.println(state.printBoard());
			}
		}
	}
}