package InheritanceChapter;

public class Rectangle implements Measurable
{
	private double myWidth;
	private double myHeight;
	public static double Constant;
	public static double Constant2;
//	public Rectangle(){
//		Constant = 3.14;
//	}
	
	public Rectangle(double width, double height)
	{
		Constant2 = 3.145;
		myWidth = width;
		myHeight = height;
	}
	public double getPerimeter()
	{
		System.out.println(""+2 * (myWidth + myHeight));
		return 2 * (myWidth + myHeight);
	}
	public double getArea()
	{
		System.out.println(""+myWidth * myHeight);
		return myWidth * myHeight;
	}
}
