package InheritanceChapter;


public class Circle implements Measurable
{
	private double myRadius;
	public Circle(double radius)
	{
		myRadius = radius;
	}
	public double getPerimeter()
	{
		System.out.println(""+2 * 3.14 * myRadius);
		return 2 * Math.PI * myRadius;
	}
	public double getCircumference()
	{
		return getPerimeter();
	}
	public double getArea()
	{
		System.out.println(""+Math.PI * myRadius * myRadius);
		return Math.PI * myRadius * myRadius;
	}
}
