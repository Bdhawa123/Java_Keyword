
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class IndirectBuffer {
	
	 private String inFileStr = "Sample.txt";
     private String outFileStr = "Sampleout.txt";
     private long startTime, elapsedTime;  // for speed benchmarking
     private int bufferSizeKB = 1;
     private int bufferSize = bufferSizeKB * 1024;
     
     public void ReadIndirectBuffer() {
    	 // Check file length
    	File fileIn = new File(inFileStr);
    	System.out.println("File size is " + fileIn.length() + " bytes");
    	System.out.println("Buffer size is " + bufferSize );
	
		// Using FileChannel with indirect ByteBuffer
    	System.out.println("Using FileChannel with an indirect ByteBuffer of " + bufferSize);
    	try (FileChannel in = new FileInputStream(inFileStr).getChannel();
    		FileChannel out = new FileOutputStream(outFileStr).getChannel()) {
    	// Allocate an indirect ByteBuffer
    	ByteBuffer bytebuf = ByteBuffer.allocate(bufferSize);

       	startTime = System.nanoTime();
       	int bytesCount;
       	while ((bytesCount = in.read(bytebuf)) > 0) { // Read data from file into ByteBuffer
    	   	// flip the buffer which set the limit to current position, and position to 0.
    	   	bytebuf.flip();
          	out.write(bytebuf);  // Write data from ByteBuffer to file
          	bytebuf.clear();     // For the next read
       	}
       	elapsedTime = System.nanoTime() - startTime;
       	System.out.println("Elapsed Time is "
    		   + (elapsedTime / 1000000.0) + " msec");
    	} catch (IOException ex) {
    	ex.printStackTrace();
    	}
    }

}
