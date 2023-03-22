package VogelAlgorithms;

import java.util.Stack;

public class StackTest {
	public static void main(String []args){
		Stack st = new Stack();
		System.out.println("The STACK: "+st);
		showPush(st,1);
		showPush(st,100);
		showPush(st,1000);
		showPush(st,-56);
		showPush(st,56);
		showPop(st);
		showPop(st);
		showPop(st);
		showPop(st);		
	}
	
	static void showPush(Stack stk, int ind){
		stk.push(new Integer(ind));
		System.out.println("Push ("+ind+")");
		System.out.println("Stack: "+stk);
	}
	
	static void showPop(Stack stk){
		System.out.println("POP");
		int a= (Integer)stk.pop();
		System.out.println(a+" removed. Now Stack: "+stk);
	}
}