package VogelAlgorithms;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountWordinFiles{
	public static String wordCountReq = "doc";
	public static void main(String[] args) throws FileNotFoundException{
		FileInputStream fis = new FileInputStream("D:\\TestDoc.txt");
		Scanner scn = new Scanner(fis);
		String regex = "[\\s.:-]";
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		do {
			String words[] = scn.nextLine().split("\\s");
//			System.out.println(words.length);
			for (int i = 0; i < words.length; i++){
				String s = words[i];
				if(s.contains(wordCountReq)){
					Integer v = wordCountMap.get(wordCountReq);
					if (v==null){
						wordCountMap.put(wordCountReq, 1);
					}else{
						wordCountMap.put(wordCountReq, v+1);
					}
					
				}
			}						
		}while((scn.hasNextLine()));
		
		System.out.println(wordCountMap);
	}
}


