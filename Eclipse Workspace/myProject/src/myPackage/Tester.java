package myPackage;

public class Tester {
	//	static void test(float x) {
	//		System.out.print("float");
	//	}
	//
	//	static void test(double x) {
	//		System.out.print("double");
	//	}
	//	
	//	static void test(int x){
	//		System.out.print("Integer");
	//	}
	//	
	//	public static void main(String[] args) {
	//		test(Math.pow(2,3));
	//	}
	//	public static void main(String[] args) {

	//		String stmt = "JavaChamp is here to help you";
	//
	//		for (String token : stmt.split("//s")) {
	//
	//			System.out.println(token);
	//			System.out.println(" ");
	//
	//		}

	//		System.out.print(13+2 +"" + 7 + 2 + " ");
	//		System.out.print(7 + 2 + " ");


	static void call(Long x, Long y) {

		System.out.print("Long x, Long y");

	}

	static void call(int... x) {

		System.out.print("int... x");

	}

	public static void main(String[] args) {

		int val = 343453543;
		call(val, val, val, val);

	}

}	
