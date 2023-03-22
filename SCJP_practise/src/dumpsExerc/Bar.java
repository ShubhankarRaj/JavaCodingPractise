package dumpsExerc;

import java.util.Arrays.*;
public class Bar {
	
	static void foo( int... x ) {
//		for( int z : x ) System.out.println(z);
		System.out.println(x);
	}
	static void foo2( float... x ) {
//		for( int z : x ) System.out.println(z);
		for (int i = 0; i<x.length; i++)
		{
		System.out.println(x[i]);
		}
	}
	
	static int x;
	public static void main(String args[])
	{
	Bar o = new Bar();
	Bar o1 = new Bar();
	Bar.foo(3,3,3,0);
	Bar.foo2(3,3,3,1,23,12,1212,32323);
	Bar.foo(0);
	o.x = 20;
	o1.x = 30;
	
	System.out.println(o.x);
	System.out.println(o1.x);
	
	}
}

