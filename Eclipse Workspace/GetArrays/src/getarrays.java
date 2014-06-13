/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
public class getarrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int array[] = new int[7];
        for (int count=0;count<7;count++){
           array[count]=count+1;
       }
       for (int count=0;count<7;count++){
           System.out.println("array["+count+"] = "+array[count]);
       }
      //System.out.println("Length of Array  =  "+array.length);
      // array[8] =10;
    }
}
