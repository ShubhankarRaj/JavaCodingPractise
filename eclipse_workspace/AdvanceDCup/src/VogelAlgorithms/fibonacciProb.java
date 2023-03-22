package VogelAlgorithms;

public class fibonacciProb {
	public static void main(String[] args){
		Double i = 10.00;
		for(double m = i; m >= 1; m--){
		System.out.println("The fibonacci element at "+i+"th position is :"+fibonacci(m-1));
		}
	}
	
	static int fibonacci(double a){
		if(a<3) return 1;
		return fibonacci(a-1)+fibonacci(a-2);
	}
}
