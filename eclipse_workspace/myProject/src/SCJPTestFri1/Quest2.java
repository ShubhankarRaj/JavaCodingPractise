package SCJPTestFri1;

public class Quest2 {

	int a = 10;

	public void doStuff(int a) {
		a += 1;
		System.out.println(a++);
	}
	public static void main(String args[]) {
		Quest2 t = new Quest2();
		t.doStuff(3);

	}
}
