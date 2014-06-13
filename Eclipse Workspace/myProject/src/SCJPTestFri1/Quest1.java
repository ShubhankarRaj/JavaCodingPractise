package SCJPTestFri1;

class Small {
	public Small() {
		super();
		System.out.print("a ");
		
	}
}

class Small2 extends Small {
	public Small2() {
		super();
		System.out.print("b ");		
	}
}

class Small3 extends Small2 {
	public Small3() {
		super();
		System.out.print("c ");		
	}
}

public class Quest1 {
	public static void main(String args[]) {
		new Small3();
	}
}
