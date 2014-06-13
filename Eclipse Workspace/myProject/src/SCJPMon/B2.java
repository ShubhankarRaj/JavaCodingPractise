package SCJPMon;

class A2{

	  public static class GreenHouse{
	    int x=1;
	  }
	  public static GreenHouse gh;
	  A2(GreenHouse gh){
	    this.gh=gh;
	  }
	  public static GreenHouse getGreenHouse(){
	    return gh;
	  }
	}
	public class B2 {

	  public static class GreenHouse{
	    int x=2;
	  }

	  public static void main(String args[]){
	    A2 a=new A2(new A2.GreenHouse());
	    System.out.println(a.getGreenHouse().x);
	  }

	}

	 
