package dumpsExerc;

//public class Persons {
//	String name = "No name";
//	public Persons(){};
//	public Persons(String nm) { name = nm; }
//}

public class Persons {
	public final int a = 1234;
	private String name;
  public Persons(String name) { 
	  this.name = name;
	  System.out.println("inside Persons method: "+this.name);
  }
  public Persons(String name1, String name2){
	  
  }
  public boolean equals(Persons p) {
	  System.out.println("inside Equals function: "+p.name);
	  System.out.println("inside Equals function: "+this.name);
	  return p.name.equals(this.name);
  }
  
  public Persons(int i) {
	  i = a;
	  System.out.println(""+i);
	  i = 0;
//	  a = i;
	  System.out.println(""+a);
  }
  
  public String stringReturn(int i){
	  String a = String.valueOf(i); 
	  String b = Integer.toString(i);
	  System.out.println(" "+b);
	  return a;
  }
  
  public static void chkstatic(){
	  System.out.println("Inside Check static!!");
  }
  
  public static void main(String []args){
	Persons per = new Persons("Mike");
	System.out.println(per.name);
	Persons.chkstatic();
	System.out.println(" "+per.stringReturn(345));
	boolean result = per.equals(per);
	System.out.print(result);
	Persons perint = new Persons(123);
//	System.out.println(perint.a);
	
  }
}

