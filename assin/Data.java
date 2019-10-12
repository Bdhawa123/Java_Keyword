import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * This class is mainly used for computing whether the keyword is contained within the string, 
 * the class does not use any high computing algorithm to find keywords, as the purpose of this application is not to design 
 * a complex algorithm but the use of Java NIO to understand more about buffers
 *
 * 
 * @author Binay Dhawa
 */
public class Data {	
    //HashMap is used as a dictionary to store Keyword and the combined snippets containing the keyword.
    private  HashMap<String, Queue> mapclass =new HashMap<String,Queue>();
    //Queue is used to save all the snippets 
	private Queue<String> stack = new PriorityQueue<String>();
	
    /**
      Returns whether the array is within the keyword
      
	  @param bytarray  bytearray to compare for the keyword
      @param Keyword The snippet to search 
	  @return boolean whether the array contains the given keyword
	 */
	public boolean Compare(byte[] bytarray, String Keyword) {
		
		String CompareString = new String(bytarray);
		
		if(CompareString.contains(Keyword)){
			stack.add(CompareString);
			return true;
		}else
		return false;
	}
    
    
    /**
     * Map the existing queue to a keyword and push into a queue.
     * 
     * @param Keyword The Keyword that is to be pushed into the Hashmap
     * @return void It returns nothing
     */
	public void stop(String Keyword) {
		mapclass.put(Keyword,stack);
	}
	/**
     * This is to get the Queue pointing to the Keyword. On multiple entries of the keyword. Specific snippets adhering to id can be fetched.
     * 
     * @param Keyword The key value the stack has stored
     * @return Queue<String> Returns a value of type Queue<String>
     */
	public Queue<String> getFile (String Keyword) {
		return mapclass.get(Keyword);
	}
	
}
