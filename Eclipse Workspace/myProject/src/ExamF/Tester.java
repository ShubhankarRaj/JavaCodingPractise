package ExamF;

class A
{
	void foo()throws Exception
	{
		throw new Exception();
	}
	{
		System.out.println("BBB");
	}
	{
		System.out.println("AAA");
	}
}
class Sub2 extends A
{
	void foo()
	{
		try
		{
			System.out.println("B");
		}
		catch(Exception e)
		{
		}	
	}
}
public  class Tester 
{
	public static void main(String args[]) throws Exception
	{
		A a = new Sub2();
		a.foo();
	}
}