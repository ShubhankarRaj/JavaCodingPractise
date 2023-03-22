package ExamB;

import java.text.SimpleDateFormat;

public class foo {
	static void bar( int... x ) {
		for( int z : x ) System.out.println(z);
		System.out.println(x);
	}
	public static void main(String args[])
	{
		foo.bar(3,3,3,0);
		foo.bar(3,3,3,8);
		SimpleDateFormat df = new SimpleDateFormat(); 
//		System.out.printf("Math.E=%b",Math.E);	/* This if un-commented would print TRUE as O/P*/
		System.out.printf("Math.E=%b");
	}
}

