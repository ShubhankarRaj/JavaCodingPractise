package dumpsExerc;

public class Threads2 implements Runnable{
	
	public void run(){
		System.out.println("Running");
		throw new RuntimeException("Problem");
	}
	
	public static void main(String[] args){
		Thread t = new Thread(new Threads2());
		t.start();
		System.out.println("End Of Method");
	}
}
