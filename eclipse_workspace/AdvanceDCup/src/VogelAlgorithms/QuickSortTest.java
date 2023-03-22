package VogelAlgorithms;

public class QuickSortTest {
	private int[] numbers;
	public static void main(String []args){
		Quicksort sorter = new Quicksort();
		int[] test = {4,5,23,89,23,45,12,98,34,23,10};
		sorter.sort(test);
		if(!validate(test)){
			System.out.println("Should not happen");
		}
		System.out.println(test);
	}


public static boolean validate(int[] numbers){
	for(int i = 0; i < numbers.length - 1; i++){
		if (numbers[i]>numbers[i+1]){
			return false;
		}
	}
	return true;
}

public class Quicksort(){
	
	public void sort(int[] values){
		
	}
}
