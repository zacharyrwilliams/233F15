import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCoderFundTest {

	/* Other JUnit tests.
     *
     * Write your own JUnit tests below to check the correctness of your implementation.
     * Leave the above methods intact and add your own tests below this comment so it's
     * easier for the graders to see what you've added.
     *
     * When you turn in your drafts we will run our own test suite on your code and provide
     * you with the feedback to help you debug an issues.
     *
     * Your draft code needs to contain a complete set of methods as specified in the assignment.
     * Otherwise, it won't compile with our test suite.  To avoid this, you should first write
     * a complete set of "skeleton" methods, i.e. methods with the correct names and
     * the correct return and argument types.
     *
     * To help ensure your code will compile when you submit it for testing, below we have
	 * included a set of skeleton tests. You should replace these with more meaningful tests
	 * as you write your methods.
     */

	@Test
	public void testConstructor() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	assertTrue(
			"skeleton: The constructor makes a HuffmanCode using a 'file.txt'.",
			true);
	}

	@Test
	public void testCompressMethod() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	hc.compress();
    	assertTrue(
			"skelton: The compress method writes the compressed 'file.txt' to 'output.txt'.",
			true);
	}
	@Test
	public void foo() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("file3.txt", "output3.txt");
    	hc.compress();
    	assertTrue(
			"skelton: The compress method writes the compressed 'file.txt' to 'output.txt'.",
			true);
	}
	
	@Test
	public void test1() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("foo.txt", "output2.txt");
    	hc.compress();
    	assertTrue(
			"skelton: The compress method writes the compressed 'file.txt' to 'output.txt'.",
			true);
	}
}