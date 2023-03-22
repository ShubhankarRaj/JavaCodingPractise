package myPackage;
import java.util.Scanner;

class Hello {
    static class Thing {
        public int size;

        Thing() {
            size = 100;
            System.out.println(""+size);
        }
    }

    public static void main(String[] args) {
     //   Hello hello1 = new Hello();
    	int i1;
    	String s1;
    	Float f1;
    	Thing thing = new Thing();
        System.out.println("Hello, Raj and Swapnil!");
        Scanner keyboard = new Scanner(System.in);
        
        int amount = 5;
		amount = amount++;
		System.out.println(" "+amount);
		
		int n = 3;
		int m = 4;
		
		int result = m * (n++);
		System.out.println(""+result);
		System.out.println(""+n);
        
		String a = "RbJ";
		String b = "raj";
		String c = "swapnil";
		System.out.println(""+b.compareTo(a));
    }
}
