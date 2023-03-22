package VogelAlgorithms;


import java.util.HashMap;
import java.util.Map;

public class AnagramTestHashMap {
	public static void main(String[] args){
		System.out.println(isAnagram("AbcDlkjl","lkjlDAbc"));
	}
	/*In this function, we are going to search for a character in the left string and add it to the 
	 * Hash-Map with a value of 0 first(if the key(character) is not found) and incremented to 1. In 
	 * the right string, we would be searching for a character and add it to the same Hash-map with a
	 * value of -1. So when the left string comes across a char that is already being added by the right
	 * string, it will increment the value of the key from -1 to 0, and the same would be done by right 
	 * string.. just that here it will decrement the value from 1 to 0. 
	 * In the end we would check the key-value pair for all the entries in the hash map and the value for
	 * all the keys would be 0, if the strings are anagrams of each other.*/
	
	
	public static boolean isAnagram(String leftString, String rightString) {
		if((leftString==null||rightString==null)||(leftString.length()!=rightString.length()))
			return false;
		Map<Character,Integer> matchfndMap = new HashMap<Character, Integer>();

		for(int i=0; i<leftString.length(); i++){
			char charFromLeft = leftString.charAt(i);
			int nrOfCharsinLeft = matchfndMap.containsKey(charFromLeft)? matchfndMap.get(charFromLeft):0;
			matchfndMap.put(charFromLeft,++nrOfCharsinLeft);

			char charFromRight = rightString.charAt(i);
			int nrOfCharsinRight = matchfndMap.containsKey(charFromRight)?matchfndMap.get(charFromRight):0;
			matchfndMap.put(charFromRight, --nrOfCharsinRight);
		}
		for(int nrOfChars : matchfndMap.values()){
			if(nrOfChars != 0){
				return false;
			}
		}

		return true;
	}
}
