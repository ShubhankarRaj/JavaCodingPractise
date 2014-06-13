package SCJPTestSat;

public class Example {

	int j=10;
	int k=10;

	public void Example(int j, int k) {
		System.out.println(" "+this.j+this.k);

	}

	public static void main(String[] args) {
		Example exm = new Example();
		exm.Example(1, 2);
		System.out.println(exm.j);
		System.out.println(exm.k);


		Object myObj = new String[]{"one", "two", "three"};

		for (String s : (String[])myObj) System.out.print(s + ".");

	}
}


