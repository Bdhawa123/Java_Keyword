
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Queue;


/**
 * Uses Direct Buffer 
 * 
 * @author Binay Dhawa 
*/
public class IndirectBuffer {
	
	 private String inFileStr = "Sample.txt";
     private String outFileStr = "Sampleout.txt";
     private String SpeedSave = "Bufferspeedsave.txt";
     private long startTime, elapsedTime;  // for speed benchmarking
     private int bufferSizeKB = 1;
     private int bufferSize = bufferSizeKB * 1024;
	 
	 /**
	 * An Empty Constructor 
	*/
     public IndirectBuffer() {}
	 
	 /**
	 * Constructor with two parameters given
	 * 
	 * @param bufferSize The buffersize set for this execution
	 * @param FileName The FileName to be referenced from 
	 */
     public IndirectBuffer (int BufSize, String FileName) {
    	 this.inFileStr = FileName;
    	 this.bufferSize = BufSize;
     }
	 
	 /**
	 * Logic of the code 
	 * 
	 * @param KeywordName The Keyword Name to execute the whole operation
	 */
     public void ReadIndirectBuffer(String Keyword) {
	
		// Using FileChannel with indirect ByteBuffer
    	System.out.println("Using FileChannel with an indirect ByteBuffer of " + bufferSize);
    	try (FileChannel in = new FileInputStream(inFileStr).getChannel();						//input stream defined
    		FileChannel out = new FileOutputStream(outFileStr).getChannel()) {					//output stream defined
    	// Allocate an indirect ByteBuffer
    	ByteBuffer bytebuf = ByteBuffer.allocate(bufferSize);									//allocate buffer indirect

		startTime = System.nanoTime();															//start time
       	int bytesCount;
    	Data datload = new Data();
		
	while ((bytesCount = in.read(bytebuf)) > 0) {											 	// Read data from file into ByteBuffer
    	   	
	bytebuf.flip();																				// flip the buffer which set the limit to current position, and position to 0.
    	   	String newstring = StandardCharsets.UTF_8.decode(bytebuf).toString();
			byte[] newbit = newstring.getBytes();
			
			  if(datload.Compare(newbit, Keyword)) {											//write the contents into the Keyword named stack 
				bytebuf.flip();
			out.write(bytebuf); 																// Write data from ByteBuffer to file
			}
          	bytebuf.clear();     																// For the next read
       	}
       	datload.stop(Keyword+"2");																// reference to the IndirectBuffer		
       	elapsedTime = System.nanoTime() - startTime;
		

		   //output into a different file for the TIME reference	
			try (FileOutputStream ol = new FileOutputStream(SpeedSave)){
				ol.write("Indirect Buffer: ".getBytes());
				ol.write(Long.toString(elapsedTime).getBytes());
				
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
