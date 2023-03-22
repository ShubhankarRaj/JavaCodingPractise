class ThreadTest {
	synchronized void hit(long n) {
		for(int i = 1; i < 10; i++)
			System.out.println(n + "-" + i + " ");
	}
}
public class PingPong implements Runnable {
	static ThreadTest tt2 = new ThreadTest();
	public static void main(String[] args) {
		new Thread(new PingPong()).start();
		new Thread(new PingPong()).start();
	}
	public void run() { 
		tt2.hit(Thread.currentThread().getId());
		for(int j = 1; j<100; j++)
			System.out.println(j +"-"+Thread.currentThread().getId());
		}
}
