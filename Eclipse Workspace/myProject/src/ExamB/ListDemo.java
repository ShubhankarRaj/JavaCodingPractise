package ExamB;

interface A{void x();}
class B implements A{public void x(){};
public void y(){}}
class C extends B{public void x(){}}
public class ListDemo {
	public static void main(String args[]){
		java.util.List<A> list = new java.util.ArrayList<A>();
		list.add(new B());
		list.add(new C());
		list.add(new B());
		for (A a : list){
			a.x();
//			b.x();
			
			System.out.println(a);
		}
	}

}
