/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sony
 */
public class BankTransactions {

    private int account_number;
    private int account_balance;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void showData(){
    
    }
    
    public void Deposit(int a){
    
    if (a<0)
        //show Error Message
        System.out.println("The Amount entered is not proper");
    
    else
        account_balance=account_balance-a;
    }
    
    public void WithDraw(int b){
    }
    
}

