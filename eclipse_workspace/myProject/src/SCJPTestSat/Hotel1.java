package SCJPTestSat;

public class Hotel1 {
    
    public static void book(short a) {
        System.out.print("short ");
    }
    
    public static void book(Short a) {
        System.out.print("SHORT ");
    }
    
    public static void book(long a) {
        System.out.print("LONG ");
    }
    
    public static void main(String[] args) {
        short shortRoom = 1;
//        Integer intRoom = new Integer(2);
        int intRoom = 1;
        book(shortRoom);
        book(intRoom);
    }
}
