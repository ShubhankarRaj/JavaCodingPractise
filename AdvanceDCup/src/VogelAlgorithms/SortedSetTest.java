package VogelAlgorithms;

import java.util.*;

public class SortedSetTest {
	public static void main(String args[]){
	SortedSet set1 = new TreeSet();
	set1.add("b");
	set1.add("z");
	set1.add("r");
	set1.add("abcd");
	set1.add("adcd");
	
	Iterator it = set1.iterator();
	
	while(it.hasNext()){
		Object element = it.next();
		System.out.println(element.toString());
		
	}
	}
}
