package dumpsExerc;


import myPackage.Terracarta;
public class Geodetics {
	public static final double DIAMETER = 12756.32; // kilometers
	static double radius = Terracarta.halfway();
	public static void main(String args[]){
		System.out.println("The value of radius is: " +radius);
	}
}

