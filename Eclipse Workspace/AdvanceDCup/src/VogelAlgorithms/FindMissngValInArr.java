package VogelAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindMissngValInArr {
	public static void FindMissinVal(int []arr){
		int totalNoElem = arr.length;
		//Since one element is missing, hence totalNoElem would be one more than the length
		int expTotalNoElem = totalNoElem+1;
		int sumOfExpTotalElem = expTotalNoElem*(expTotalNoElem+1)/2;
		int actualSum = 0;
		for(int i = 0; i < arr.length; i++){
			actualSum += arr[i];
		}
		int missingElem = sumOfExpTotalElem - actualSum;
		
		System.out.println("The MISSING element in the Array is :"+missingElem);
	}
	public static void main(String []args){
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the elements of the consecutive yet random Array(starting from 1) separated by a space:");
		String arrayContent = scn.next();
//		List<Integer> list = new ArrayList<Integer>();
//		for(String s : arrayContent.split("\\s")){
//			list.add(Integer.parseInt(s));
//		}
		String[] split = arrayContent.split("\\s");
		int[] desArr = new int[split.length];
		int i = 0;
		for(String string:split){
			desArr[i++] = Integer.parseInt(string);
		}
		FindMissinVal(desArr);
		
	}
}
