package SCJPMon;

public class TWorks extends Thread{
	  public void run(){
	    {
	      System.out.print("-A-");
	      System.out.print("-B-");
	      System.out.print("-C-");
	    }
	  }
	  public static void main(String args[]){
	    TWorks tw=new TWorks();
	    Thread rb1=new Thread(tw);
	    Thread rb2=new Thread(tw);
	    rb1.start();
	    rb2.start();
	  }
	}
