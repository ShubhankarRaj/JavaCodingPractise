package DynamoProg;

import java.util.Scanner;

public class ConvertTo1 {
	static int newNum, count=0;
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		System.out.println("Kindly Enter the digit which you want to convert to 1: ");
		int input = scn.nextInt();
		converter(input);
	}

	public static void converter(int num){

		if(num%2 == 0){
			newNum = num/2;
			count++;
			System.out.println("Divided by 2: "+newNum);
			converter(newNum);

		}
		else if(num%3 == 0){
			newNum = num/3;
			count++;
			System.out.println("Divided by 3: "+newNum);
			converter(newNum);

		}
		else if((num%2 != 0)&&(num%3 != 0)&&(num > 1)){
			num = num-1;
			count++;
			System.out.println("Reduced 1 from the value: "+newNum);
			converter(num);
		}
		else if(num == 1){
			 System.out.println("The total number of Steps in which the Digit can be converted to 1 is "+count);
		}
	}
}
