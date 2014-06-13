/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
class Account{
    int a,b;
    public void setData(int c,int d){
        a=c;
        b=d;
    }
    
    public void showData(){
        System.out.println("Vallue of a: "+a);
        System.out.println("Vallue of b: "+b);
    }
}
class ObjectArray {
    public static void main(String args[]){
    Account Obj[]= new Account[2];
    Obj[0] = new Account();
    Obj[1] = new Account();
    Obj[0].setData(1,2);
    Obj[1].setData(5,6);
    
    System.out.println("For Array Element 0");
    Obj[0].showData();
    System.out.println("For Array Element 1");
    Obj[1].showData();
    
    }
}