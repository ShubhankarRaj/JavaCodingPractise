package VogelAlgorithms;

import java.util.*;

public class SetDemo {
	public static void main(String args[]){
		int count[] = {34, 22, 20, 30, 60, 22, 10};
		Set<Integer> set = new HashSet<Integer>();
		
		try{
			for(int i = 0; i < count.length; i++){
				set.add(count[i]);
			}
			System.out.println(set);
			
			TreeSet sortedSet = new TreeSet<Integer>(set);
			System.out.println("The new sorted Set is :"+sortedSet);
			
			System.out.println("First Element of Sorted Set is :"+(Integer)sortedSet.first());
			System.out.println("Last Element of Sorted Set is :"+(Integer)sortedSet.last());
		}
		catch(Exception e){ }
	}
}
