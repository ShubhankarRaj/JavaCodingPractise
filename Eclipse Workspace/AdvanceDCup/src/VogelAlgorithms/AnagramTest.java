package VogelAlgorithms;

import java.util.Arrays;

public class AnagramTest {
	public static void main(String[] args){
		System.out.println(areAnagrams("CALENDAR","RADLEACN"));
	}
	public static boolean areAnagrams(String first, String second){
		if(first.length()!= second.length())
			return false;
		char[] firstChars = first.toCharArray();
		char[] secondChars = second.toCharArray();
		
		Arrays.sort(firstChars);
		Arrays.sort(secondChars);
		
		String sortedFirst = new String(firstChars);
		String sortedSecond = new String(secondChars);
		
		return sortedFirst.equals(sortedSecond)?true:false;
	}
}


