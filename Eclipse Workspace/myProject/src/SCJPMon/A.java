package SCJPMon;

public class A{

	  private String runNow(){
	    return "High";
	  }

	  static class B extends A{
	    public String runNow(){
	      return "Low";
	    }
	  }

	  public static void main(String args[]){
	    A[] a=new B[]{new B(),new C()};
	    A newA=new C();
	    System.out.println(newA.runNow());
	    A newB=new B();
	    System.out.println(newB.runNow());
//	    for(A aa:a)
//	      System.out.print(aa.runNow()+" ");
	  }

	}

	class C extends A.B{

	  public String runNow(){
	    return "Out";
	  }

	}