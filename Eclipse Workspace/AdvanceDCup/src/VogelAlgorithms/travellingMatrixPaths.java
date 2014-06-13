package VogelAlgorithms;

import java.util.Scanner;

public class travellingMatrixPaths {
	public static void main(String[] args){
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number of rows of the matrix:");
		int row = scn.nextInt();
		System.out.println("Enter the number of columns of the matrix:");
		int col = scn.nextInt();
		System.out.println("Total number of Paths are : "+totalPaths(row, col));
	}
	
	static int totalPaths(int matrixRows, int matrixColumns){
		if((matrixRows == 1)||(matrixColumns == 1))
			return 1;
		return totalPaths(matrixRows, matrixColumns-1) + totalPaths(matrixRows-1, matrixColumns); 
	}
}
