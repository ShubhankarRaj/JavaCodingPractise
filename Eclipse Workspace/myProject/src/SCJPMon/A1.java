package SCJPMon;

class A1{
	     protected static Number take(){return 4;}
	   }
	   
	   class B1 extends A1{
		   protected Integer take(){return 0;}
		   // Insert Here
//		   System.out.println(B1.take());
		   System.out.println(A1.take());
	   }
