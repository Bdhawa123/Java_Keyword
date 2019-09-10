
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DirectBuffer {

	private String inFileStr = "Sample.txt";
    private String outFileStr = "Sampleout.txt";
    private long startTime, elapsedTime;  // for speed benchmarking
    private int bufferSizeKB = 1;
    private int bufferSize = bufferSizeKB * 1024;

    public void ReadDirectBuffer() {

    	// Using FileChannel with direct ByteBuffer
    	System.out.println("Using FileChannel with a direct ByteBuffer of " + bufferSize );
    	try (FileChannel in = new FileInputStream(inFileStr).getChannel();
    			FileChannel out = new FileOutputStream(outFileStr).getChannel()) {
    		// Allocate a "direct" ByteBuffer
    		ByteBuffer bytebuf = ByteBuffer.allocateDirect(bufferSize);
    		CharBuffer charbuf = CharBuffer.allocate(bufferSize);

    		startTime = System.nanoTime();
    		int bytesCount;
    		while ((bytesCount = in.read(bytebuf)) > 0) { // Read data from file into ByteBuffer
    			// flip the buffer which set the limit to current position, and position to 0.

    			bytebuf.flip();
    			//System.out.print("This should be a new line\n");
    			//System.out.println(bytebuf.toString());

    			System.out.println(StandardCharsets.UTF_8.decode(bytebuf).toString());

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
