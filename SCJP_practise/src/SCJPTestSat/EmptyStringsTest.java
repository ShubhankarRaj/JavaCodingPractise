package SCJPTestSat;

public class EmptyStringsTest {
    public static boolean isEmpty(String s) {
    	if(s==null)
    		System.out.println("S Is NULL");
    	System.out.println(s);
    	return (s == null || s.length() == 0);
    }

    public static void main(String args[]) {
        if (isEmpty(null)) {
            System.out.print("empty ");
        } else {
            System.out.print("not_empty ");
        }
    }
}
