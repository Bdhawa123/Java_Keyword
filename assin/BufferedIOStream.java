	
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Uses Buffered IOStream 
 * 
 * @author Binay Dhawa 
*/
public class BufferedIOStream {
	
	private String inFileStr = "Sample.txt";					//default declaration of file 
    private String outFileStr = "Sampleout.txt";				//output for the found snippets 
    private String SpeedSave = "Bufferspeedsave.txt";			//Record for speed
    private long startTime, elapsedTime;  						// for speed benchmarking
    private int bufferSizeKB = 1;								
    private int bufferSize = bufferSizeKB * 1024;				//buffer size
	
	
	/**
	 * Constructor with two parameters given
	 * 
	 * @param bufferSize The buffersize set for this execution
	 * @param FileName The FileName to be referenced from 
	 */
    public BufferedIOStream(int bufferSize, String FileName) {
    	this.bufferSize = bufferSize;
    	this.inFileStr = FileName;
    }
	
	/**
	 * An Empty Constructor 
	*/
    public BufferedIOStream() {}
	
	/**
	 * Logic of the code 
	 * 
	 * @param KeywordName The Keyword Name to execute the whole operation
	 */
    public void ReadBufferedIOStream(String KeywordName) {
    	// Using Buffered Stream I/O
    	System.out.println("Using Buffered Stream");
    	
    	try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));						//input stream defined
    			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))){				//output stream defined
    		
    		startTime = System.nanoTime();																			//time declaration
    		int bytesCount;	
    		byte[] contents = new byte[bufferSize];		
			Data datload = new Data();  																			//the buffer is reading into a byte array to read into a file which makes the transaction much cheaper 
    		while ((bytesCount = in.read(contents)) != -1) {
    			
    			if(datload.Compare(contents, KeywordName)) {								//write the contents into the Keyword named stack 
    				out.write(contents, 0, bytesCount);
    				out.write("\n".getBytes());
    			}
    		}    		
    		datload.stop(KeywordName+"1");													//1 reference to the BufferedIOStream		
    		
    		elapsedTime = System.nanoTime() - startTime;
			
			
				//output into a different file for the TIME reference
	    		try (FileOutputStream ol = new FileOutputStream(SpeedSave)){
					ol.write("BufferedIOStream: ".getBytes());
					ol.write(Long.toString(elapsedTime/1000000).getBytes());
				}catch(IOException ex) {
					ex.printStackTrace();
				}
    		
    		System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
}
