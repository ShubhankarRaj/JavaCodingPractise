package SCJPTestFri1;

public class Class14 implements Runnable {
    
    public void run() {
        System.out.print("go");
    }
    
    public static void main(String arg[]) {
        Thread t = new Thread(new Class14());
        t.run();
        t.run();
        t.start();
    }
}
