package dumpsExerc;

public class SCJPMockTest {

	private static void doStuff(String str) {
        int var = 4;
        if (var == str.length()) {
            System.out.print(str.charAt(--var) + " ");
        }
        else {
            System.out.print(str.charAt(0) + " ");
        }
	}
	public static void main(String args[]){
		doStuff("abcd");
        doStuff("efg");
        doStuff("hi");
        System.out.printf("12%1$saddsfsfdsdf","","14%1$s","15%1$s","16%1$s","17%1$s");
	}
}
