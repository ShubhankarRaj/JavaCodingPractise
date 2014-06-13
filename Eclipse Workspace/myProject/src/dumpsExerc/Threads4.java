package dumpsExerc;

public class Threads4 {
	public static void main(String[] args){
		Threads4 arr[] = new Threads4[8];
		new Threads4().go();
	}
	
	synchronized public void go(){
		Runnable r = new Runnable(){
			public void run(){
				System.out.println("Foo");
			}
		};
		Thread t = new Thread(r);
		t.start();
		t.start();
	}
}
