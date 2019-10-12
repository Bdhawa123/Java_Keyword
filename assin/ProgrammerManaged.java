
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Uses Programmer Managed Buffer 
 * 
 * @author Binay Dhawa 
*/
public class ProgrammerManaged {
	
	private String inFileStr = "Sample.txt";										//default declaration of file 
    private String outFileStr = "Sampleout.txt";									//output for the found snippet
    private String SpeedSave = "Bufferspeedsave.txt";								//Record for speed			
    private long startTime, elapsedTime;  											// for speed benchmarking
    private int bufferSizeKB = 1;
    private int bufferSize = bufferSizeKB * 1024;									//buffer size
    
	
	/**
	 * Constructor with two parameters given
	 * 
	 * @param bufferSize The buffersize set for this execution
	 * @param FileName The FileName to be referenced from 
	 */
    public ProgrammerManaged(int BufferSize, String FileName) {
    	this.bufferSize = BufferSize;   
    	this.inFileStr = FileName;
    }
    
    
	/**
	 * Logic of the code 
	 * 
	 * @param KeywordName The Keyword Name to execute the whole operation
	 */
	public void ReadProgrammerManaged(String Keyword) {
		// Using a programmer-managed byte-array 
		System.out.println("Using a programmer-managed byte-array of " + bufferSize);
		try (FileInputStream in = new FileInputStream(inFileStr);							//input stream defined
				FileOutputStream out = new FileOutputStream(outFileStr)) {					//output stream defined
			startTime = System.nanoTime();													//Start system time
			byte[] byteArray = new byte[bufferSize];   										//allocate Direct Buffer
			int bytesCount;
			Data datload = new Data();
			while ((bytesCount = in.read(byteArray)) != -1) {								// Read data from file into ByteBuffer
				
				if(datload.Compare(byteArray, Keyword)) {									//whether the keyword exists within the file
    				out.write(byteArray, 0, bytesCount);									//write the contents into the Keyword named stack 
    				out.write("\n".getBytes());
    			}
			}
		 	datload.stop(Keyword+"4");														//reference to this ProgrammerManaged Buffer
			elapsedTime = System.nanoTime() - startTime;
			
				//output into a different file for the TIME references
				try (FileOutputStream ol = new FileOutputStream(SpeedSave)){
					ol.write("Programmer Managed: ".getBytes());
					ol.write(Long.toString(elapsedTime).getBytes());
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
