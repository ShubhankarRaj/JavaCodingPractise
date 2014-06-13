package VogelAlgorithms;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.*;

public class MergeSortTest {
	private int[] numbers;
	private final static int SIZE = 10;
	private final static int MAX = 50;
	
	@Before
	public void SetUp(){
		numbers = new int[SIZE];
		Random generator = new Random();
		for(int i = 0; i<numbers.length; i++){
			numbers[i] = generator.nextInt(MAX);
		}
		System.out.println(Arrays.toString(numbers));
	}
	@Test
	public void testMergeSort(){
		MergeSort sorter = new MergeSort();
		sorter.Sort(numbers);
		System.out.println(Arrays.toString(numbers));
		for(int i = 0; i<numbers.length-1; i++){
			if(numbers[i]>numbers[i+1])
					fail("The sorting is not done correctly");
		}
		assertTrue(true);
	}	
}

class MergeSort{
	private int[] nmbrs;
	private int[] helper;
	private int nmbrsLen;
	public void Sort(int[] values){
		this.nmbrs=values;
		nmbrsLen = nmbrs.length;
		this.helper = new int[nmbrsLen];
		mergeSort(0, nmbrsLen-1);
	}
	
	private void mergeSort(int low, int high){
		if(low<high){
			int middle = low + (high-low)/2;
			mergeSort(low,middle);
			mergeSort(middle+1, high);
			merge(low,middle,high);
		}		
	}
	
	private void merge(int low, int middle, int high){
		for(int i = low; i <= high; i++){
			helper[i] = nmbrs[i];
		}
		
		int i = low, j = middle+1, k = low;
		
		while(i<=middle && j<=high){
			if(helper[i]<=helper[j]){
				nmbrs[k] = helper[i];
				i++;
			}else{
				nmbrs[k] = helper[j];
				j++;
			}k++;
		}
	
		while( i<= middle){
			nmbrs[k] = helper[i];
			k++; i++;
		}
	}
}
