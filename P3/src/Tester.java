public class Tester {
	/*
	 * This class gives several examples of attempts to solve the puzzle. There is no
	 * JUnit because I felt it was easiest to visualize what was happening via
	 * command line output, and because there would be no homogeneity for the graders
	 * to be able to apply their own tests on my code.
	 * 
	 * If you want to add examples to see if my code works on test cases you don't
	 * feel are adequately covered, here is how:
	 * 
	 * If you have a particular grid in mind, provide three arguments to Puzzle():
	 * The int sideLength of the grid, the int[] array, and the index of the blank
	 * 
	 * If you just want it to run on randomized grids, provide the int sideLength and
	 * the int numberofMoves you want it to make.
	 * 
	 * You may find it useful to comment out my personal tests when running your own
	 * You'll actually need to comment out some of them to see the outputs of earlier
	 * tests.
	 * 
	 * Additionally, I have console output heavy code- none of it is important to the
	 * functionality of my solver, so if you want to ignore it, they can be commented
	 * out as well
	 */

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
	}

	public static void test1() {	
		//Solve the trivial case of a 3x3 with no modifications
		System.out.println("Test 1 (a)");
		BFSsolver.solve(new Puzzle(0));

		System.out.println("Test 1 (b)");
		DFSsolver.solve(new Puzzle(0));
	}

	public static void test2() {
		//Solve the still trivial of 4x4 with no modifications
		System.out.println("Test 2 (a)");
		BFSsolver.solve(new Puzzle(4, 0));

		System.out.println("Test 2 (b)");
		DFSsolver.solve(new Puzzle(4, 0));
	}

	public static void test3() {
		//Solve a 3x3 which only requires 1 move
		System.out.println("Test 3 (a)");
		int[] arr = {3, 1, 2, 0, 4, 5, 6, 7, 8};
		BFSsolver.solve(new Puzzle(3, arr, 3));

		System.out.println("Test 3(b)");
		DFSsolver.solve(new Puzzle(3, arr, 3));
	}

	public static void test4() {
		//Solve a 3x3 which only *requires* 2 moves
		System.out.println("Test 4 (a)");
		int[] arr2 = {3, 1, 2, 4, 0, 5, 6, 7, 8};
		BFSsolver.solve(new Puzzle(3, arr2, 4));

		System.out.println("Test 4(b)");
		DFSsolver.solve(new Puzzle(3, arr2, 4));
	}

	public static void test5() {
		//Solve a 3x3 which has been randomized with 5 moves
		System.out.println("Test 5 (a)");
		BFSsolver.solve(new Puzzle(3, 5));

		System.out.println("Test 5 (b)");
		DFSsolver.solve(new Puzzle(3, 5));
	}

	public static void test6() {
		//Solve a 3x3 with many randomized moves
		System.out.println("Test 6 (a)");
		BFSsolver.solve(new Puzzle(3, 500));

		System.out.println("Test 6 (b)");
		DFSsolver.solve(new Puzzle(3, 500));
	}

	public static void test7() {
		//Solve a 4x4 which has been randomized with 10 moves
		System.out.println("Test 7 (a)");
		BFSsolver.solve(new Puzzle(4, 10));

		//This very likely won't finish running
		//I am surprised that it can't find a solution on a nearly solved grid
		//while it still takes over 2 million individual searches
		//I have it stop there because at around 2.5mil I get an OutOfMemoryError
		System.out.println("Test 7 (b)");
		DFSsolver.solve(new Puzzle(4, 10));
	}

	public static void test8() {
		//Try BFS on a 4x4 with many randomized moves
		System.out.println("Test 8");
		BFSsolver.solve(new Puzzle(4,300));
	}
}