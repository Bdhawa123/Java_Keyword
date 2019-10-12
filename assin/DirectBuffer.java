
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Uses Direct Buffer 
 * 
 * @author Binay Dhawa 
*/
public class DirectBuffer {
	
	private String inFileStr = "Sample.txt";										//default declaration of file 
    private String outFileStr = "Sampleout.txt";									//output for the found snippet
    private String SpeedSave = "Bufferspeedsave.txt";								//Record for speed			
    private long startTime, elapsedTime;  											// for speed benchmarking
    private int bufferSizeKB = 1;
    private int bufferSize = bufferSizeKB * 1024;									//buffer size
    
	/**
	 * An Empty Constructor 
	*/
	public DirectBuffer() {}
	
	/**
	 * Constructor with two parameters given
	 * 
	 * @param bufferSize The buffersize set for this execution
	 * @param FileName The FileName to be referenced from 
	 */
    public DirectBuffer(int bufferSize, String FileName) {
    	this.bufferSize = bufferSize;
    	this.inFileStr = FileName;
    }

	/**
	 * Logic of the code 
	 * 
	 * @param KeywordName The Keyword Name to execute the whole operation
	 */
    public void ReadDirectBuffer(String KeywordName) {
    	
    	System.out.println("Using FileChannel with a direct ByteBuffer of " + bufferSize );
    	
    	try (FileChannel in = new FileInputStream(inFileStr).getChannel();						//input stream defined
    			FileChannel out = new FileOutputStream(outFileStr).getChannel()) {				//output stream defined
    	
    		
    		ByteBuffer bytebuf = ByteBuffer.allocateDirect(bufferSize);							//allocate Direct Buffer
	startTime = System.nanoTime();																//Start system time
    		Data datload = new Data();
    		
    		int bytesCount;
    		while ((bytesCount = in.read(bytebuf)) > 0) { 										// Read data from file into ByteBuffer
				bytebuf.flip();																	// flip the buffer which set the limit to current position, and position to 0		
    			
    		
    			String newstring = StandardCharsets.UTF_8.decode(bytebuf).toString();			//decode for reading string
    			byte[] newbit = newstring.getBytes();
    			
    				if (datload.Compare(newbit, KeywordName)){
						bytebuf.flip();															//reset the buffer for read
						out.write(bytebuf);
    					}    				
    			bytebuf.clear();     															// For the next read
    		}
    	 	datload.stop(KeywordName+"3");														//reference to this DirectBuffer for Keyword
    		elapsedTime = System.nanoTime() - startTime;
			
			
				//output into a different file for the TIME reference	
	    		try (FileOutputStream ol = new FileOutputStream(SpeedSave)){
					ol.write("Direct Buffer: ".getBytes());
					ol.write(Long.toString(elapsedTime / 1000000).getBytes());
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}
    		System.out.println("Elapsed Time is "
    				+ (elapsedTime / 1000000.0) + " msec");
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }

}
