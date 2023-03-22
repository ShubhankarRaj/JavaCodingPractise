package SCJPTestSat;

public class TryMe1 {
    
    public static void printB(String str) {
         System.out.print(Boolean.valueOf(str) ? "false" : "true");
//    	System.out.print(Boolean.valueOf(str));
    }
    
    public static void main(String args[]) {
        printB("TRue");
        printB("false");
    }
}
