package VogelAlgorithms;

import java.util.Arrays;

public class MergeArrays {
	public static void main(String []args){
		int[] arr1 = {4, 12, 20, 32, 0, 0, 0};
		int[] arr2 = {1, 5, 15};
		System.out.println("Merging both the arrays: "+Arrays.toString(mergeArray(arr1, arr2, arr1.length, arr2.length)));
	}
	
	static int[] mergeArray(int[] bigArray, int[] smallArray, int bigArraySize, int smallArraySize){
		int[] newArray = new int[bigArraySize];
		int newArraybigArrayEnd = bigArraySize - smallArraySize -1;
		int bigArrayEnd = bigArraySize -1;
		int smallArrayEnd = smallArraySize -1;
		
		while(newArraybigArrayEnd >= 0 && bigArrayEnd >= 0 && smallArrayEnd >= 0){
			if(bigArray[newArraybigArrayEnd]>smallArray[smallArrayEnd]){
				newArray[bigArrayEnd] = bigArray[newArraybigArrayEnd];
				newArraybigArrayEnd--;
			}else{
				newArray[bigArrayEnd] = smallArray[smallArrayEnd];
				smallArrayEnd--;
			}
			bigArrayEnd--;
			
			if(newArraybigArrayEnd<0){
				while(bigArrayEnd >= 0 && smallArrayEnd >= 0){
					newArray[bigArrayEnd] = smallArray[smallArrayEnd];
					bigArrayEnd--;
					smallArrayEnd--;
				}
			}
			else if(smallArrayEnd < 0){
				while(newArraybigArrayEnd >= 0 && bigArrayEnd >=0){
					newArray[newArraybigArrayEnd] = bigArray[bigArrayEnd];
					bigArrayEnd--;
					newArraybigArrayEnd--;
				}
			}
		}
		return newArray;
	}
}
