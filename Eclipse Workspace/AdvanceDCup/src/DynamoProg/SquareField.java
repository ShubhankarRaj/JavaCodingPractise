package DynamoProg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SquareField {
	static File outFile = null;
	
	public static void main(String args[]) {
		// This method is created to capture the total number of test scenarios
		// in 1st line
		// total number of test cases and another parameter in the second line,
		// and
		// all the test-data for the test cases from the 3rd line onwards
		captureInputNew();
	}
	
	private static int squareFieldCalculator(ArrayList<Integer[]> testDataArray, int noOfSquares){
		return 0;
	}
	
	public static void captureInputNew(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the path of the file: ");
		String filePath = scn.next();
		FileReader inputFile;
		BufferedReader bReader;
		System.out.println("Enter the path of the O/P file: ");
		
		String outFilePath = scn.next();
		outFile = new File(outFilePath);

		if (outFile.exists()) {
			outFile.delete();
			try {
				outFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			inputFile = new FileReader(filePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, true));
			LineNumberReader lineNo = new LineNumberReader(inputFile);
			String line;
			int countOfLine = 0, totalTestScenarios, totalTestCases = 0, totalSquares = 0, testCaseCounter = 0, testScenarioCounter = 0, lengthOfSquare;
			String[] testCaseDetails, testDataDetails;
			ArrayList<Integer[]> testDataArrayList = new ArrayList<Integer[]>();
			boolean thisRowIsTestDATA = false;
			// Reading the file LINE by LINE
			while ((line = lineNo.readLine()) != null) {
				countOfLine = lineNo.getLineNumber();
				
				//	Reserving the first line for total number of Test Scenarios
				if (countOfLine == 1) {
					System.out.println("Total count of TEST SCENARIOS in the input file is: " + line);
				}else{
					
					//	If the row found in the input is not for test data:
					if(!thisRowIsTestDATA){
						testScenarioCounter++;
						testCaseDetails = line.split(" ");
						//	First element of the array wud be reserved for total number of test cases
						totalTestCases = Integer.parseInt(testCaseDetails[0]);
						//	2nd element of the array wud be reserved for total number of squares for test-data
						totalSquares = Integer.parseInt(testCaseDetails[1]);
						
						//	Clearing the arraylist with all the test-data, so that new set of test-data can be added
						testDataArrayList.clear();
						
						//	Setting the flag as TRUE so that Test Data is captured 
						thisRowIsTestDATA = true;
						
					}else{
						if(testCaseCounter == totalTestCases){
							testCaseCounter = 0;
							
							//	After scanning through all the rows for the test data, the flag need to be toggled
							//	so that we are able to read the next test case details
							thisRowIsTestDATA = false;
							
							//	Send the ArrayList(testDataArrayList) to the method which is going to actually solve the problem
							lengthOfSquare = squareFieldCalculator(testDataArrayList, totalSquares);
							
							//	Add the lines for getting the length of the square which would be the solution
							//	and print them in the output file.
							writer.write("Case #" +testScenarioCounter+ ": "+lengthOfSquare);
							
						}else{
							testDataDetails = line.split(" ");
							Integer[] testDataArray = new Integer[testDataDetails.length];
							for(int i=0; i<testDataDetails.length; i++){
								testDataArray[i] = Integer.parseInt(testDataDetails[i]);
							}
							//	Adding the Integer array containing the test data to the ArrayList!!
							testDataArrayList.add(testDataArray);
							testCaseCounter++;
						}
					}
				}
			}
		}catch(Exception e){
			
		}
	
	}
}
