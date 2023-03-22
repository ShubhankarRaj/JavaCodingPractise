/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
public class UsingThis {
int a,b;

public void SetData(int c, int d){
    a = c;
    b = d;
}

public void ShowData(){
    System.out.println("Valus of a :"+a);
    System.out.println("Valus of b :"+b);
}
public static void main(String args[]){
    UsingThis Obj1 = new UsingThis();
    Obj1.SetData(2,3);
    //Obj1.ShowData();
    UsingThis Obj2 = new UsingThis();
    Obj2.SetData(66,77);
    Obj1.ShowData();
}
}
