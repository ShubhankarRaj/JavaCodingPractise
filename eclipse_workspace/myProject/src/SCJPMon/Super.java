package SCJPMon;

 class Aaa {
	void foo () throws Exception{
		System.out.println("Swapnil");
		throw new Exception();
	}
}
class Bbb extends Aaa{
	void foo ()
	{
		System.out.println("Raj");
		
	}
}
class Super{
	public static void main(String ag[]) throws Exception
	{
		Aaa ob = new Bbb();
		ob.foo();
	}
}