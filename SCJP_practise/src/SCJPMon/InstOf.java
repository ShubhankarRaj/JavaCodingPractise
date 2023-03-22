package SCJPMon;

interface I{}
class newA{}
class B implements I{}
class newC extends newA{} 
class D{}
final class E{}

class InstOf{
  public static void main(String args[]){
    Boolean b;
 b = new D() instanceof I;
  }
}
