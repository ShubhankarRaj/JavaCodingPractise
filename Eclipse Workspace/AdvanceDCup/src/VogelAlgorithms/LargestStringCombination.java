package VogelAlgorithms;


import java.util.*;

public class LargestStringCombination 
{
	public static void main(String[] args) 
	{
		ArrayList<String>list = new ArrayList<String>();
		list.add( "rat");
		list.add( "cat");
		list.add( "abcxyz");        
		list.add( "abc");
		list.add( "xyz");
		list.add( "ratcatabc");
		list.add( "xyzcatratabc" );


		Map<String, Integer> map = new HashMap<String, Integer>();

		for(int i=0; i<list.size()-1; i++)
		{
			String s = list.get(i);         
			for(int j=1; j < list.size(); j++)
			{	if(i==j)
				{
					continue;
				}
				if(list.get(j).contains(s))
				{
					Integer v = map.get(list.get(j));
					if(v == null)
					{
						map.put(list.get(j), 1);
					}
					else
					{
						map.put(list.get(j), v+1);
					}
				}
			}   
		}
		System.out.println(map);
//		  ValueComparator bvc =  new ValueComparator(map);
//	        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
		
	}
}
