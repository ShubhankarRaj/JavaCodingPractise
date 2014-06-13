package SCJPMon;

public class MaClass{
	  {
	    System.out.print("SCEA, ");
	  }
	  static
	  {
	    System.out.print("SCJP, ");
	  }
	  
	  static String s2="Web Componet Developer, ";
	  static String s1=getVal();

	  public static String getVal(){
	    System.out.print("Mobile Application Developer, ");
	    return s2;
	  }

	  public static void main(String args[]){
	    System.out.print(s1);
	  }
	}
