package VogelAlgorithms;

public class travellingMatrixPaths2 {
	public static void main(String[] args){
		int rows = 3, columns = 3;
		System.out.println("The total number of paths from 1st cell to last is : "+pathCalculation(rows, columns));
	}
	
	
	static int pathCalculation(int matrixRows, int matrixColumns){
	
		int count[][] = new int[matrixRows][matrixColumns];
		
		for(int i=0; i<matrixRows; i++){
			count[i][0] = 1;
		}
		for(int j=0; j<matrixColumns; j++){
			count[0][j] = 1;
		}
		
		for(int i=1; i<matrixRows; i++){
			for(int j=1; j<matrixColumns; j++){
				count[i][j] = count[i-1][j]+count[i][j-1];
			}
		}
		return count[matrixRows-1][matrixColumns-1];
	}
}
