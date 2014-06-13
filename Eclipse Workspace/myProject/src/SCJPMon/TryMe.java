package SCJPMon;

public class TryMe {
    
    public static void printB(String str) {
         System.out.print(Boolean.valueOf(str) ? "This is done when True is passed" : "This is done when False is passed"); 
    }
    
    public static void main(String args[]) {
        printB("False");
        System.out.println();
        printB("False");
    }
}
