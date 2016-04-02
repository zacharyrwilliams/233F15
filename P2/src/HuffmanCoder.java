import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HuffmanCoder {
	private String inputFile;
	private String outputFile;
	
	public HuffmanCoder(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public void compress() throws IOException {
		Path inPath = Paths.get(inputFile);
		
		HuffmanCode huffCode = new HuffmanCode(inputFile);
		BinaryWriter writer = new BinaryWriter(outputFile);
		byte[] input = Files.readAllBytes(inPath);
				
		for(byte b : input) {
			writer.writeBinaryArray(huffCode.code(b));
		}
		writer.close();
	}
	
}
