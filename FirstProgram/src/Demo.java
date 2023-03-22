/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony dfdsdfdsf 
 */
public class Demo{
    public static void main(String args[]){
    byte x;
    int a=270;
    double b =128.128;
    System.out.println("int converted to byte");
    x=(byte) a;
    System.out.println("a and x "+ a +" "+x);
    System.out.println("double converted to int");
    a=(int) b;
    System.out.println("b and a "+ b +" "+a);
    System.out.println("n double converted to byte");
    x=(byte)b;
    System.out.println("b and x "+b +" "+x);
        //int intArray[] = new int[10];
    int intArray[] = new int[10];
    intArray[0] = 0;
    intArray[1] = 200;
    intArray[2] = 250;
    intArray[3] = 230;
    intArray[4] = 240;
    intArray[5] = 160;
                  
    int count;
    for(count = 0; count<=5; count++){
        System.out.println(intArray[count]);
    }
    
    }
}

class ArrayDemo{
     public static void main(String args[]){
        int array[] = new int[7];
        for (int count=0;count<7;count++){
           array[count]=count+1;
           System.out.println("array["+count+"] = "+array[count]);
        }
        
        String []b = {"Apple","Mango","Orange","Blue-Berry"};
        System.out.println("Before Function Call... "+b[0]);
        ArrayDemo.passByRef(b);
        System.out.println("After Function Call... "+b[0]);
      }
     
     public static void passByRef(String a[]){
         a[0]="Changed Value";
     }
}


    