import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Data {	
	
	private  HashMap<String, Queue> mapclass =new HashMap<String,Queue>();
	
	private Queue<String> stack = new PriorityQueue<String>();
	
	
	public boolean Compare(byte[] bytarray, String Keyword) {
		
		String CompareString = new String(bytarray);
		
		if(CompareString.contains(Keyword)){
			stack.add(CompareString);
			return true;
		}else
		return false;
	}
	
	public void stop(String Keyword) {
		mapclass.put(Keyword,stack);
	}
	
	public Queue<String> getFile (String Keyword) {
		return mapclass.get(Keyword);
	}
	
}
