package dumpsExerc;

public class Starter extends Thread{
	private int x = 2;
	public static void main(String args[]) throws Exception{
		new Starter().makeItSo();
	}
	
	public Starter(){
		System.out.println("This is getting Executed first");
		x=5;
		start();
	}
	public void makeItSo() throws Exception{
//		join();
		x = x-1;
		System.out.println(x);
	}
	public void run(){ x*=2;
		System.out.println("Executing RUN. Value of x : "+x);
	}
}
