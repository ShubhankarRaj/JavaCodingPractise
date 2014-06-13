package SCJPMon;

public class Hotel {
    
    public static void book(short a) {
        System.out.print("short ");
    }
    
    public static void book(Short a) {
        System.out.print("SHORT ");
    }
    
    public static void book(Long a) {
        System.out.print("LONG ");
    }
    
    public static void main(String[] args) {
        short shortRoom = 1;
//        Long intRoom = new Long(2);
        Short intRoom = new Short((short) 20);
        
        book(shortRoom);
        book(intRoom);
    }
}
