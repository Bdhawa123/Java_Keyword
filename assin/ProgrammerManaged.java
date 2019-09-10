
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProgrammerManaged {
	
	private String inFileStr = "Sample.txt";
    private String outFileStr = "Sampleout.txt";
    private long startTime, elapsedTime;  // for speed benchmarking
    private int bufferSizeKB = 1;
    private int bufferSize = bufferSizeKB * 1024;
    
	public void ReadProgrammerManaged() {
		// Using a programmer-managed byte-array 
		System.out.println("Using a programmer-managed byte-array of " + bufferSize);
		try (FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)) {
			startTime = System.nanoTime();
			byte[] byteArray = new byte[bufferSize];    // byte-array
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				System.out.print(in.read(byteArray));
				out.write(byteArray, 0, bytesCount);
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
