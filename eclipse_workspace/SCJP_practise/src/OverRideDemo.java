
public class OverRideDemo {

	public static void main(String args[]){
		double average1 = getAverage(1,4);
		double average2 = getAverage(2,5,10);
		char average3 = getAverage('a', 'z');
		
		System.out.println(" "+average1+" "+average2+" "+average3);
		
	}
	
	public static double getAverage(double first, double second){
		return (first+second)/2;
	}
	public static double getAverage(double first, double second, double third){
		return (first+second+third)/3;
	}
	public static char getAverage(char first, char second){
		return (char) (((int)first + (int)second)/2);
	}
}
