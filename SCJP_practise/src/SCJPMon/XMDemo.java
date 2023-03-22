package SCJPMon;

class XMethod{

	  static{
	    System.out.print("XMethod ");
	  }

	  public static XMethod getMe(){
	    System.out.print(1);
	    return new XMethod();
	  }

	  public int doThis(){
	    System.out.print(2);
	    return 3;
	  }	  
	  static{
		  System.out.println("This is the last print statement");
	  }

	}

	public class XMDemo{	

	  public static void main(String args[]){
	    System.out.print(XMethod.getMe().doThis());
	    int i=(byte)4;
	      byte b=(byte)i;
	      short s=b;
	      char c=(char)s;
	      java.lang.Character c2=(Byte)(new java.lang.Byte("4"));
	      char c3=new Character('A').charValue();
	      byte b2=new java.lang.Byte(4);
	      java.lang.Float ft=Float.valueOf("45T").floatValue();
	      java.lang.Float ft2=Float.parseFloat("45T").floatValue("32F");
	  }
	}
