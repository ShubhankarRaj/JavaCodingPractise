package VogelAlgorithms;


import java.util.*;

public class NonRepeatingChar {
	public static void main(String []args){
		String str = "saaasdddddfdfdffdwfsdf";
		System.out.println("The First Non- Repeatable Element is : "+firstNonRepeatingFirstChar(str));		
	}
	private static Character firstNonRepeatingFirstChar(String a){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		List<Character> list = new ArrayList<Character>();
		char[] strArr = a.toCharArray();
		for(int i = 0; i < strArr.length; i++){
			char ch = strArr[i];
			Integer freq = map.get(ch);

			if (freq==null){
				map.put(ch, 1);
				list.add(ch);
			}else{
				map.put(ch, freq+1);
				
//				Iterator<Character> it = list.iterator();
//				while(it.hasNext()){
//					Character elem = it.next();
//					if(elem == ch){
//						list.remove(elem);
//					}
//				}
				
				for(int j=0; j<list.size();j++){
					char elem = list.get(j);
					if(elem==ch){
						list.remove(j);
						break;
					}
				}
			}
		}

		if(list.size() == 0){
			return null;
		}
		else {
			return list.get(0);
		}
	}

}


