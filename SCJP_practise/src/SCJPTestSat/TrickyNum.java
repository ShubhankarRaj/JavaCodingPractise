package SCJPTestSat;

public class TrickyNum<X extends Object> {

//	private X x;
//
//	//    System.out.println(X);
//	public TrickyNum(X x) {
//		this.x = x;
//	}
//
//	private X getDouble() {
//		return x.doubleValue();
//	}
//
//	public static void main(String args[]) {
//		TrickyNum<Integer> a = new TrickyNum<Integer>(new Integer(5));
//		System.out.println(a.getDouble());
//
//		int i = 10;
//		while (++i <= 10) {
//			System.out.println(i);
//			++i;
//			System.out.println(i);
//		}
//		System.out.println(i);
//	}
	
	
	    
	    private X x;
	    
	    public TrickyNum(X x) {
	        this.x = x;
	    }
	    
	    private double getDouble() {
	        return ((Integer) x).doubleValue();
	    }
	    
	    public static void main(String args[]) {
	        TrickyNum<Integer> a = new TrickyNum<Integer>(new Integer(1));
	        
	        System.out.print(a.getDouble());
	    }
	}
	

