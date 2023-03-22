package VogelAlgorithms;

/* For example, for N = 4,S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 * Hence the program can be splitted into two parts:
 * For a sum of N, and integers(coins) available are S = {S1, S2, ..., Sm}
 * 
 * Then there would be two groups in which the solutions can be broken 
 * C(N,m) = C(N,m - 1) + C(N - Sm,m)
 * i.e., all the groups in one where they dont contain the maximum integer, and the other group where they have at least once the maximum
 * integer.
 * */

public class CoinChange {
	public static void main(String[] args){
		int i,j;
		int arr[] = {1,2,5};
		int m = arr.length;
		System.out.println("The number of Sets that are possible are: "+count(arr, m, 10));		
	}
	
	public static int count(int A[], int noOfCoins, int sum){
		if(sum == 0){
			return 1;
		}
		
		if(sum < 0){
			return 0;
		}
		
		//if noOfCoins is 0 and sum is greater than zero
		if(noOfCoins<=0 && sum > 0){
			return 0;
		}
		
		return (count(A, noOfCoins-1, sum) + count(A, noOfCoins, (sum-A[noOfCoins-1])));
	}
}
