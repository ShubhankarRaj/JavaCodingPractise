package SCJPTestSat;

public class Split_example {
	
		   public static void main(String args[]){
		      String Str = new String("Welcome-to-Tutorialspoint.com");

//		      System.out.println("Return Value - :" );
//		      for (String retval: Str.split("-", 4)){
//		         System.out.println(retval);
//		         System.out.println(retval.length());
//		      }
//		      System.out.println("");
//		      System.out.println("Return Value t :" );
//		      for (String retval: Str.split("t", 4)){
//		         System.out.println(retval);
//		         System.out.println(retval.length());
//		      }
//		      System.out.println("");
//		      System.out.println("Return Value \\b :" );
//		      for (String retval: Str.split("\\b")){
//		         System.out.println(retval);
//		         System.out.println(retval.length());
//		      }
//		      String tokens[]=Str.split("\\b");
//		      System.out.println("The number of splits done are: "+tokens.length);
		      
//		      System.out.println("");
////		      System.out.println("Return Value - :" );
//		      for (String retval: Str.split("-")){
//		         System.out.println(retval);
//		         System.out.println(retval.length());
//		      }
		      System.out.println("");
		      System.out.println("Return Value \\s:" );
		      for (String retval: Str.split("\\s")){
		         System.out.println(retval);
		         System.out.println(retval.length());
		      }
		   }
	}