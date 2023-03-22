/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
class MyException extends Exception{
    int a;
    MyException(int b){
        a = b;
    }
    public String toString(){
        return ("Exception number = "+a);
        
    }
}

class JavaException{
    public static void main(String args[]){
        try{
            throw new MyException(2);
            
        }
        catch (MyException e){
            System.out.println(e);
        }
    }
}
