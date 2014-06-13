package SCJPMon;

public class Tahiti {
	Tahiti t;
	int global=0;
	
	public static void main(String []a){
		int local;
		Tahiti t = new Tahiti();
		Tahiti t2 = t.go(t);
		t2 = null;
//		Runtime.getRuntime().gc();
		System.out.println(String.valueOf(t));
		System.out.println(String.valueOf(t2));
	}
//	System.out.print(local);
	Tahiti go(Tahiti t){
		Tahiti t1 = new Tahiti();
		System.out.print(global);
		Tahiti t2 = new Tahiti();
//		t1.t = t2;
//		t2.t = t1;
		t.t = t2;
		return t1;
	}
}
