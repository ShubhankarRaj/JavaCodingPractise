package InheritanceChapter;

public class ShapeAreaPeri extends Rectangle{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Rectangle r1 = new Rectangle();
//		Rectangle r1 = new Rectangle();
		double constant1 = Rectangle.Constant;
		double constant2 = Rectangle.
		Circle c = new Circle(5.0);
		Circle disc = new Circle(5.0);
//		double circleArea = c.getArea();
		Measurable box = new Rectangle(5.0, 5.0);
		Measurable disc1 = new Circle(5.0);
		
		if (c==disc)
				System.out.println("THe objects are equal");
		else
			System.out.println("Objects not equal");
		
		if (c.equals(disc))
			System.out.println("THe values of the objects are same");
		else
			System.out.println("The values are also different");
		
		Double a= box.getArea();
		Double d = disc1.getArea();

	}

}
