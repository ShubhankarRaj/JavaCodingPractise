package SCJPMon;

public class BlahhTest extends Thread {
	int a =1;
	static int b = 2;
	int c=3;
	int i=0;
	public void run()
	{
		System.out.println("i =" + i++);
		a = 10+i;
		System.out.println(a);
		b = 20+i;
		System.out.println(b);
		c = 30+i;
		System.out.println(c);
	}
	
	
public static void main(String Args[])
{
	BlahhTest obj = new BlahhTest();
	//System.out.println(obj.a);
	Thread t = new Thread(obj);
	t.start();
	Thread t2 = new Thread(obj);
	
	t2.start();
	Thread t3 = new Thread(obj);
	t3.start();
	System.out.println(new BlahhTest().a +" " + b +" " + obj.c);
	
	
}
}
