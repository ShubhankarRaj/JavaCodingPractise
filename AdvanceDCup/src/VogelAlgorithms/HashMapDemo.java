package VogelAlgorithms;

import java.util.HashMap;
import java.util.Set;
import java.util.*;

public class HashMapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		hm.put("Raj", new Integer(20));
		hm.put("Raja", new Integer(200));
		hm.put("Raju", new Integer(2));
		hm.put("SiRaj", new Integer(20000));
		hm.put("Asha", new Integer(10));
		
		//Get a set of the entries made
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.println("The KEY values are as follows: "+me.getKey());
			System.out.println("The values are as follows: "+me.getValue());
		}
		
		int balance = (Integer)hm.get("Raju");
		System.out.println(balance);
		hm.put("Raju", new Integer(balance+19998));
		System.out.println(hm.get("Raju"));
	}

}
