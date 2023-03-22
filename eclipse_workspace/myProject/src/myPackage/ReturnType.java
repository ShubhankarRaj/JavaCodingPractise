package myPackage;
class Ret{
	public long tryIt(){
		long num = 25;
		return num; // $1
	}
}
class ReturnType extends Ret{
	public long tryIt(){
//		char ch = 'a';
		long num = 50;
//		return ch; // $2
		return num;
	}
	public static void main(String []args){
		ReturnType r = new ReturnType(); // $3
		System.out.println(r.tryIt()); // $4
	}
}