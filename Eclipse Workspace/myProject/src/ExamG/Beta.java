package ExamG;

interface Foo{}
class Alpha implements Foo{}
class Delta extends Alpha{
	public void go(){
		String o = "    ";
		z:
			for(int x = 0;x<3; x++){
				for(int y=0;y<2;y++){
					if(x==1)break;
					if(x==2 && y==1) break z;
					o = o + x + y;
					System.out.println(o);
				}
			}
		
	}
}
public class Beta extends Delta{
	public static void main(String []args){
		Beta x = new Beta();
//		Alpha a = x;
//		Foo f = (Beta)x;
		Foo f = (Alpha)x;
		Delta d = new Delta();
		d.go();
	}
}
