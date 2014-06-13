package SCJPMon;

public class Yippee {
	public static void main(String args[]){
		for(int x = 1; x < args.length; x++)
			System.out.print(args[x]+ " ");
		
		
	        try {
	            String arr[] = new String[10];
	            arr = null;
	            arr[0] = "one";
	            System.out.print(arr[0]);
	        } catch(NullPointerException nex) { 
	            System.out.print("null pointer exception"); 
	        } catch(Exception ex) {
	            System.out.print("exception");
	        }
	    
	}
	
}
