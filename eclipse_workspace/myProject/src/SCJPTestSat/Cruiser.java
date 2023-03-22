package SCJPTestSat;

public class Cruiser {
    private int a = 0;
    
    public void foo() {
        Runnable r = new LittleCruiser();
        new Thread(r).start();
        new Thread(r).start();
    }
    
    public static void main(String arg[]) {
        int b = 5;
    	Cruiser c = new Cruiser();
        c.foo();
//        for (int i = 5; i < 10; i++) {
//            int current = b;
//            System.out.print(current + ", ");
//            b = current + 2;
//        }
    }
    
    public class LittleCruiser implements Runnable {
        public void run() {
        	System.out.println("Value of a is: "+a);
        	int current = 0;
//        	System.out.println("Value of current is: "+current);
            for (int i = 0; i < 4; i++) {
                current = a;
                System.out.print(current + ", ");
                a = current + 2;
            }
        }
    }
}