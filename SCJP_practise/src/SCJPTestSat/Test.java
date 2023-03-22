package SCJPTestSat;

//class A {
//    private B b;
//    public A() {
//        this.b = new B(this);
//    }
//    
//}
//
//class B {
//    private A a;
//    
//    public B(A a) {
//        this.a = a;
//    }
//}
//
public class Test { 
//    public static void main(String args[]) {
//        A aa=new A();
////        System.out.println(""+aa);
//        aa = null;
//        // some code goes here
//    }
//}

public static void main(String args[]) {
    try {
        String arr[] = new String[10];
        arr = null;
        arr[0] = "one";
        System.out.print(arr[0]);
    
    } catch(NullPointerException nex) { 
        System.out.print("null pointer exception"); 
   
}
}
}
