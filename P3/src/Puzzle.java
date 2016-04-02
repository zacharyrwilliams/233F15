import java.util.ArrayList;
import java.util.Random;

public class Puzzle {
	private int[] state;
	private int len;
	private int blankPosition;
	private Puzzle parent;

	/*
	 * Basic constructor that just takes in an integer to tell how many 
	 * random moves to make on a 3x3 puzzle grid
	 */
	public Puzzle(int numMoves) {
		this(3, numMoves);
	}

	/*
	 * This constructor creates a sideLength x sideLength sized puzzle grid
	 * and makes random moves to it
	 */
	public Puzzle(int sideLength, int numMoves) {
		if(sideLength < 3) {
			throw new IllegalArgumentException("8-puzzle is the minimum size");
		}

		len = sideLength;
		state = new int[len*len];
		for(int i = 0; i < len*len; i++) {
			state[i] = i;
		}
		blankPosition = 0;
		randomize(numMoves);
	}
	
	/*
	 * This constructor is for when I want to input a specific puzzle grid
	 */
	public Puzzle(int sideLength ,int[] inputState, int inputBlankPos) {
		len = sideLength;
		blankPosition = inputBlankPos;

		state = new int[inputState.length];
		for(int i = 0; i < state.length; i++) {
			state[i] = inputState[i];
		}
	}

	public int[] getState() {
		return state;
	}
	
	public void setState(int[] newState) {
		state = newState.clone();
	}
	
	/*
	 * This method makes it easy for me to grab all possible neighbor states
	 */
	public ArrayList<Puzzle> getNeighbors() {
		ArrayList<Puzzle> lst = new ArrayList<Puzzle>();
		lst.add(right());
		lst.add(up());
		lst.add(left());
		lst.add(down());

		return lst;
	}
	
	/*
	 * Tells me if the puzzle is equal to the solution
	 */
	public boolean isGoal() {
		int[] goal = new int[len*len];
		for(int i = 0; i < goal.length; i++) {
			goal[i] = i;
			if(goal[i] != state[i]) {
				return false;
			}
		}		
		return true;
	}
	
	/*
	 * Updates the puzzle grid with random moves
	 * If it attempts an illegal move, nothing fails- simply nothing happens
	 */
	public void randomize(int numMoves) {
		Random r = new Random();
		Puzzle p = this;
		for(int i = 0; i < numMoves; i++) {
			switch (r.nextInt(4)) {
			case 0:
				p = p.up();
				break;
			case 1:
				p = p.down();
				break;
			case 2:
				p = p.left();
				break;
			case 3:
				p = p.right();
				break;
			default:
				//Should never happen
				throw new IllegalArgumentException("What happened?");	
			}
		}
		setState(p.getState()); //Update the board
		setBlankPos(p.getBlankPos()); //And location of the blank
	}

	//Swaps blocks- here to reduce code duplication
	private void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	//Moves blank to the left, if possible
	//If it is not, the method simply returns a puzzle as it was
	public Puzzle left() {
		if( (blankPosition) % len != 0) { //If blank is not on left side
			int[] copy = state.clone();
			swap(copy, blankPosition, blankPosition-1);
			return new Puzzle(len, copy, blankPosition-1);
		}
		return this;
	}

	//Moves blank to the right, if possible
	public Puzzle right() {
		if((blankPosition+1) % len != 0) { //If blank is not on right side..
			int[] copy = state.clone();
			swap(copy, blankPosition, blankPosition+1);
			return new Puzzle(len, copy, blankPosition+1);
		}	
		return this;
	}

	//Moves blank up, if possible
	public Puzzle up() {
		if(blankPosition >= len) { //If blank is not in top row
			int[] copy = state.clone();
			swap(copy, blankPosition, blankPosition-len);
			return new Puzzle(len, copy, blankPosition-len);
		}		
		return this;
	}

	//Moves blank down, if possible
	public Puzzle down() {
		if(blankPosition < (len*len - len)) { //If blank is not in bottom row
			int[] copy = state.clone();
			swap(copy, blankPosition, blankPosition+len);
			return new Puzzle(len, copy, blankPosition+len);
		}		
		return this;
	}

	public String printBoard() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < state.length; i++) {
			builder.append(state[i] + " ");
			if((i+1 ) % len == 0) {//If at the right side of the boardS
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	//Used for HashSet- not sure if this is even necessary anymore, but it works
	//so no point in changing it
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int n : state) {
			builder.append(n);
		}
		return builder.toString();	
	}

	public void setParent(Puzzle par) {
		parent = par;
	}

	public boolean hasParent() {
		if(parent != null) {
			return true;
		}

		return false;
	}

	public Puzzle getParent() {
		return parent;
	}
	
	public int getBlankPos() {
		return blankPosition;
	}
	
	public void setBlankPos(int pos) {
		blankPosition = pos;
	}
}