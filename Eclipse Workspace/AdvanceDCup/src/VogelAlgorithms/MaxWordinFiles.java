package VogelAlgorithms;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MaxWordinFiles{
//	public static String wordCountReq = "doc";
	public static void main(String[] args) throws FileNotFoundException{
		FileInputStream fis = new FileInputStream("D:\\TestDoc.txt");
		Scanner scn = new Scanner(fis);
//		String regex = "[\\s.:-]";
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		do {
			String words[] = scn.nextLine().split("\\s");
			for (int i = 0; i < words.length; i++){
				String s = words[i];
//				if(s.contains(wordCountReq)){
					Integer v = wordCountMap.get(s);
					if (v==null){
						wordCountMap.put(s, 1);
					}else{
						wordCountMap.put(s, v+1);
					}
					
//				}
			}						
		}while((scn.hasNextLine()));
		
		/*TO GET THE MAXIMUM VALUE IN A HASH MAP*/
		Map.Entry<String, Integer> maxEntry = null;
		for(Map.Entry<String, Integer> entry : wordCountMap.entrySet()){
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue())>0){
				maxEntry = entry;
			}
		}
		System.out.println(wordCountMap);
		System.out.println(maxEntry);
	}
}
