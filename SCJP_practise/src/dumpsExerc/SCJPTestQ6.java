package dumpsExerc;

public class SCJPTestQ6 {
    private int a = 0;
    
    public void foo() {
        Runnable r = new LittleCruiser();
        new Thread(r).start();
        new Thread(r).start();
    }
    
    public static void main(String arg[]) {
    	SCJPTestQ6 c = new SCJPTestQ6();
        c.foo();
    }
    
    public class LittleCruiser implements Runnable {
        public void run() {
            int current = 0;
            for (int i = 0; i < 4; i++) {
                current = a;
                System.out.print(current + ", ");
                a = current + 2;
            }
        }
    }
}