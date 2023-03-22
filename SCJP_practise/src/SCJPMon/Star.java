package SCJPMon;

public class Star{

    static int a=0;
    volatile int b=1;

    public static void main(String args[]){
      new Star().a=2;
      Star st = new Star();
      st.a =10;
      System.out.println(st.a+""+"+"+new Star().b+"="+(new Star().a + new Star().b));
      new Star().b=3;
      System.out.println(new Star().a+""+"+"+new Star().b+"="+(new Star().a + new Star().b));
      new Star().a=4;
      System.out.println(new Star().a+""+"+"+new Star().b+"="+(new Star().a + new Star().b));
      new Star().b=5;
      System.out.println(new Star().a+""+"+"+new Star().b+"="+(new Star().a + new Star().b));
    }
  }
