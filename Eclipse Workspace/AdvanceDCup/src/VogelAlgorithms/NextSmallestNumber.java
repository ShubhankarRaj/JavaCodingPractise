package VogelAlgorithms;


public class NextSmallestNumber {
	public static void main(String[] args){
		int num = 76574321;
		//Converting number to String
		String strnum = Integer.toString(num);
		char[] numarr = strnum.toCharArray();
		
		int len = numarr.length;
//		System.out.print("Length:"+len);
		for(int i = len-2; i > -1 ; i--){
			int current = Integer.parseInt(Character.toString(numarr[i]));
			int right = Integer.parseInt(Character.toString(numarr[i+1]));
					
			if (current<right){
				int pivot = current;
				int temp = right;
				pivot = right;
				temp = current;
				String a = Integer.toString(pivot);
//				System.out.println(a);
				numarr[i] = a.charAt(0);
				String b = Integer.toString(temp);
				numarr[i+1] = b.charAt(0);
				
				do{
				for(int j=i+1; j<len-1; j++){
					if(numarr[j]>numarr[j+1]){
						char temp1 = numarr[j];
						numarr[j]=numarr[j+1];
						numarr[j+1]=temp1;
					}
				}
				}while(numarr[i+1]>numarr[i+2]);				
				System.out.println(numarr);
				break;
				}
			
		}
	}
}


