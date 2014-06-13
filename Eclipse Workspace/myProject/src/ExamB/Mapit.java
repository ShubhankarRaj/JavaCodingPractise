package ExamB;
import java.util.*;

public class Mapit {
	public static void main(String [] args){
		Set<Integer> set = new HashSet<Integer>();
		Integer i1 = 45;
		Integer i2 = 46;
		set.add(i1);
		set.add(i1);
		set.add(i2);
		System.out.println(set.size() + " ");
		set.remove(i1);
		System.out.println(set.size() + " ");
		i2 = 47;
		i2 = 48;
		set.remove(i2);
		
		System.out.println(set.size() + " ");
		System.out.println(set.hashCode() + " ");
	}
}
