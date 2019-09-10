	
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class BufferedIOStream {
	
	private String inFileStr = "Sample.txt";
    private String outFileStr = "Sampleout.txt";
    private long startTime, elapsedTime;  // for speed benchmarking
    private int bufferSizeKB = 1;
    private int bufferSize = bufferSizeKB * 1024;
    
    public BufferedIOStream(int bufferSize) {
    	this.bufferSize = bufferSize;
    }
    
    public BufferedIOStream() {
    	
    }
    
    public void ReadBufferedIOStream(String KeywordName) {
    	// Using Buffered Stream I/O
    	System.out.println("Using Buffered Stream");
    	try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
    			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))){
    		
    		startTime = System.nanoTime();
    		int bytesCount;
    		
    		byte[] contents = new byte[120];
    		
    		Data datload = new Data();
    		//the buffer is reading into a byte array to read into a file which makes the transaction much cheaper 
    		while ((bytesCount = in.read(contents)) != -1) {
    			
    			if(datload.Compare(contents, KeywordName)) {
    				out.write(contents, 0, bytesCount);
    				out.write("\n".getBytes());
    			}
    			
    		}
    		
    		datload.stop(KeywordName);
    		
    		Queue slod = datload.getFile(KeywordName);
    		Iterator<String> stackiterator = slod.iterator();
    		while(stackiterator.hasNext()) {
    			System.out.println(slod.remove());
    		}
    		elapsedTime = System.nanoTime() - startTime;
    		
    		System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
}
