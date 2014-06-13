package dumpsExerc;

public class Cruiser implements Runnable {
    
    public void run() {
        System.out.print("go");
    }
    
    public static void main(String arg[]) {
        Thread t = new Thread(new Cruiser());
        t.run();
        System.out.println(t.getId());
        t.run();
        System.out.println(t.getId());
        t.start();
        System.out.println(t.getId());
    }
}
