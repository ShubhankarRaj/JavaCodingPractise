package ExamG;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Maker {
	public static void main(String[] args){
//		File dir = new File("Swapnildir");
//		dir.mkdir();
//		File RajDir = new File("RajRenamedir");
//		dir.renameTo(RajDir);
//		
//		File f1 = new File(dir, "test2.txt");
		
//		try{
//			f1.createNewFile();
//		}catch(IOException e){}
//		
//		File newDir = new File("newDir");
//		dir.renameTo(newDir);
		
		String csv = "Sue,5,true,3";
		Scanner scn = new Scanner(csv);
		System.out.println(csv);
		scn.useDelimiter(",");
		int age = scn.nextInt();
		System.out.println(age);
		
//		String x = scn.next();
//		System.out.println(x);
//		String x1 = scn.next();
//		System.out.println(x1);
//		int i=0;
//		while(scn.hasNext()){
//			System.out.println(" "+scn.next());
//		}
		
	}
}
