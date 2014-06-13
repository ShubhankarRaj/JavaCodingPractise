package VogelAlgorithms;

public class maxObtainableProduct {
	public static void main(String[] args){
		int i = 12;
		System.out.println("The maximum obtainable product of "+i+" is : "+maxProd(i));
	}

	static int maxProd(int noToSplit){
		if ((noToSplit == 0)||(noToSplit == 1))
			return 0;
		int maxVal =0;
		for(int a = 1; a < noToSplit; a++){
			maxVal = max(maxVal, a*(noToSplit-a), maxProd(noToSplit-a)*a);
		}
		return maxVal;
	}

	static int max(int x, int y){
		return x>y ?x:y;
	}
	static int max(int x, int y, int z){
		return max(x, max(y,z));
	}
}
