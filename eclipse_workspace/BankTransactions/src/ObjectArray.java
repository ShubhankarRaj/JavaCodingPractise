/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
public class ObjectArray {
    public static void main(String args[]){
        Account obj[]=new Account[2];
        obj[0]=new Account();
        obj[1]=new Account();
        obj[0].setData(1,2);
        obj[1].setData(3,4);
        System.out.println("For Array Element 0");
        obj[0].ShowData();
        System.out.println("For Array Element 1");
        obj[1].ShowData();
    }
}

class Account{
    int a,b;
    public void setData(int c, int d){
        a = c;
        b = d;
    }
    public void ShowData(){
        System.out.println("Value of a = "+a);
        System.out.println("Value of b = "+b);
    }
}
