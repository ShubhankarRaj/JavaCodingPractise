package VogelAlgorithms;

import java.lang.Long;
import java.util.ArrayList;
import java.util.Collections;
public class pushZerotoEnd {
	public static void main(String []args){
		long i = 12390880002300L, temp = i;
		ArrayList<Long> arr = new ArrayList<Long>();
		do{
			arr.add(temp%10);
			temp = temp/10;
		}while(temp > 0);
		
		Collections.reverse(arr);
		System.out.println(arr);
		
		for(int j = 0; j<arr.size(); j++){
	
			/*Approach 1*/
//			if(arr.get(j)!= 0){
//				continue;
//			}
//			else{
//				int a = j, b = j;
//				while(a<(arr.size()-1)){
//					arr.set(a, arr.get(a+1));
//					a++;
//				}
//				arr.set((arr.size()-1), 0L);
//				System.out.println(a);
//				System.out.println(arr);
//				if(arr.get(j) == 0){
//					while(b<(arr.size()-1)){
//						arr.set(b, arr.get(b+1));
//						b++;
//					}
//					arr.set((arr.size()-1), 0L);
//				}
//			}			
		}
		
		/*Approach 2*/
		int l = 0;
		int r = arr.size()-1;
		
		while(l<r){
			if(arr.get(l)!=0)
				l++;
			else{
				arr.set(l, arr.get(r));
				arr.set(r, 0L);
				r--;
			}
		}
		System.out.println(arr);
	}
	
}
