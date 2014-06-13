package ExamB;

public class Batman {
	int squares = 81;
	public static void main(String[] args){
		new Batman().go();
	}
	void go(){
		incr(++squares);
		System.out.println(squares);
	}
	void incr(int squares){
		squares = squares+10;
		System.out.println("The value inside INCR is :"+squares);
		String[] elements = { "for", "tea", "too" };
		String first = (elements.length > 4) ? elements[0] : null;
		System.out.println("The value of elements[0] is : "+elements[0]);
		System.out.println("The value of elements[0] is : "+first);
	}
}
