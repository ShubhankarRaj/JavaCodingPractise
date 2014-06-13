package myPackage;

public class NamedRunnable implements Runnable {
	private String name;
	public NamedRunnable(String name){
		this.name = name;
	}
	public void run(){
		System.out.println("Greetings from Runnable "+name+"!!");
	}
	public static void main(String[] args) {
		NamedRunnable greeting = new NamedRunnable("Shubhankar");
		Thread greetingThread = new Thread(greeting);
		greetingThread.start();
		System.out.println("Greetings from Main !!");
	}

}
