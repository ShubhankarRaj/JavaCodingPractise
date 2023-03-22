package dumpsExerc;

interface Foo {}
 class Alpha implements Foo {}
 class Beta extends Alpha {}
 class Delta extends Beta {
	public static void main( String[] args ) {
		Delta x = new Delta();
		Delta b = (Delta)(Delta)(Alpha)x;
		Foo f = (Alpha)x;
		Alpha a = x;
		System.out.println("This is a new program:");
		// insert code here
	}
}

