package SCJPTestFri1;

class Hotel {
    public void book() throws Exception {
        throw new Exception();
    }
}

public class Quest15 extends Hotel  {
    public void book() {
        System.out.print("booked");
    }
    
    public static void main(String args[]) {
        Quest15 h = new Quest15();
        h.book();
    }   
}
