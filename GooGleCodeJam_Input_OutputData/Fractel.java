package gcj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Fractiles {
	static File outFile = null;
	static ArrayList<String> charArrayList = new ArrayList<String>();

	public static void main(String[] args) {
		// This method is created to capture input where the first line of the
		// input is the number
		// of test cases, and all the following lines are test cases.
		captureInputAndSolveTestCase();
	}

	private static void solveTestCase(int lineCount, String lineContent)
			throws IOException {
		String[] testDataStrArr = lineContent.split(" ");
		int[] testDataArr = new int[testDataStrArr.length];
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(outFile, true));
		for (int i = 0; i < testDataStrArr.length; i++) {
			testDataArr[i] = Integer.parseInt(testDataStrArr[i]);
		}

		int k = testDataArr[0];
		int c = testDataArr[1];
		int s = testDataArr[2];
		System.out.print("Case #"+(lineCount -1)+": ");
		writer.write("Case #" + (lineCount - 1) + ": ");
		System.out.println(k+" "+c+" "+s);
		if(k-c<0) {
			c=k;
		}
		if(s<(k+1-c)) {
			System.out.println("IMPOSSIBLE");
			writer.write("IMPOSSIBLE");
			writer.newLine();
			writer.close();
			return;
		} else {
			s= k+1-c;
		}
		//TODO big
		BigInteger pos =BigInteger.ZERO,pow = BigInteger.ZERO,kb = BigInteger.valueOf(k);;
		System.out.println(k+" "+c+" "+s);
		
		for(int i=0; i<c; i++) {
			
			if(i==0){
				pow=BigInteger.ZERO;
			} else if(i==1) {
				pow=BigInteger.ZERO;
			} else if(i==2) {
				pow=kb;
			} else {
				pow=pow.multiply(kb);
			}
			
			pos= pos.add(pow);
			System.out.println(pow+" "+pos);
			
		}
		System.out.println();
		System.out.print(">\t:"+pos+'\t');
		pos=pos.add(BigInteger.valueOf((c-1)));
		System.out.println("new pos:\t"+pos+"\t");
		for(int j=1; j<= (k+1-c); j++) {
			System.out.print(pos.add(BigInteger.valueOf(j))+" ");
			writer.write(pos.add(BigInteger.valueOf(j))+" ");
			
		}
		System.out.println();
		writer.newLine();
		
		writer.close();
	}
	
	
	
	
	// This method is created to capture input where the first line of the input
	// is the number
	// of test cases, and all the following lines are test cases.
	public static void captureInputAndSolveTestCase() {
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

			LineNumberReader lineNo = new LineNumberReader(inputFile);
			String line;
			int countOfLine = 0;
			// Reading the file LINE by LINE
			while ((line = lineNo.readLine()) != null) {
				countOfLine = lineNo.getLineNumber();
				if (countOfLine == 1) {
					System.out
							.println("Total count of cases in the input file is: "
									+ line);
				} else {
					solveTestCase(countOfLine, line);
				}
			}
			lineNo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File is not found!!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

