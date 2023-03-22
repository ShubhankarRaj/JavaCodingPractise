package VogelAlgorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FibonacciHashMap {
	public static Map<Integer, Integer> fiboMap = new HashMap<Integer, Integer>();
	public static void main(String[] args){
		Scanner scn = new Scanner(System.in);
		int input = scn.nextInt();
		
		fiboMap.put(1, 1);
		System.out.println("1st element of Fibonacci is :"+fiboMap.get(1));
		fiboMap.put(2, 1);
		System.out.println("2nd element of Fibonacci is :"+fiboMap.get(2));
		for(int i = 3; i <= input; i++){
			int fib = fiboNacci(i-1) + fiboNacci(i-2);
			System.out.println(i+"th element of Fibonacci is :"+fib);
			fiboMap.put(i, fib);
		}		
	}
	
	static int fiboNacci(int a){
		if(fiboMap.containsKey(a))
			return fiboMap.get(a);
		return 1;
	}

}
