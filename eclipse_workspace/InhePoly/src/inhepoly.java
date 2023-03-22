/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */

class X{
    private int a;
    int b;
        public void m1(){
            System.out.println("This method is M1 of class X");
        }
    
}

class Y extends X{
    int c;
    
        public void m1(){
            System.out.println("This method is M1 of class Y");
        }
        public void m2(){
            super.m1();
            System.out.println("This method is M2 of Class Y");
        }
}
public class inhepoly {

    public static void main(String[] args) {
            X x=new X();
            Y y=new Y();
            y.m2();
            x.m1();
            y.m1();
            x=y;
            x.m1();
                    
    }
}
