public class Thread2 {

void waitForSignal () throws InterruptedException{
	Object obj = new Object();
	synchronized (Thread.currentThread())
	{
		obj.wait();
		obj.notify();
	}
}
}

