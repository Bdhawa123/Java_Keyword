
	/**
	 * The main class for execution
	 * 
	 * @author Binay Dhawa
	 */
public class main{

	/**
	 * Main class; specific rules in this case
	 * There needs to be 3 arguments namely Method, Buffersize and Fileinput
	 * The fourth argument isn't mandatory, it defines the keyword to be searched by default
	 * By Default the Keyword is set to continent inorder to change it a fourth argument is required
	 * 
	 * @param args returns the arguments assigned 
	 */
	public static void main(String[] args) {

		String methodName = args[0];
		Integer bufferSize = Integer.parseInt(args[1]);			//changed into integer as the buffersize is of type int
		String FileName = args[2];
		String Keyword = "Continent";

		if (args.length== 4){									//to change the keyword
			Keyword = args[3];
		}

		// System.out.println("Method: "+args[0]);
		// System.out.println("BufferSize: "+args[1]);
		// System.out.println("File to read : "+args[2]);
        // System.out.println("Keyword: "+Keyword);
        
		// Used to change which class is chosen for search
		switch(methodName){
			case "BufferedIOStream":
					BufferedIOStream BfIOS = new BufferedIOStream(bufferSize,FileName);
                    BfIOS.ReadBufferedIOStream(Keyword);
                    break;

			case "DirectBuffer":
					DirectBuffer DirB = new DirectBuffer(bufferSize,FileName);
                    DirB.ReadDirectBuffer(Keyword);
                    break;

			case "IndirectBuffer":
					IndirectBuffer IndBuff = new IndirectBuffer(bufferSize,FileName);
                    IndBuff.ReadIndirectBuffer(Keyword);
                    break;

			case "ProgrammerManaged":
					ProgrammerManaged PrgMan = new ProgrammerManaged(bufferSize,FileName);
                    PrgMan.ReadProgrammerManaged(Keyword);
                    break;
			
            default:
                    System.out.println("Given method isn't listed in the options");
                    break;
		}
		
		
	}
}
