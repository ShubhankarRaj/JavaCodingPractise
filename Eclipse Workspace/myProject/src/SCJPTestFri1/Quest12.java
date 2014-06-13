package SCJPTestFri1;

public class Quest12 {
    private Integer value = 1;
    
    public Integer getValue() {
        return value;
    }
    
    public void changeVal(Integer value) {
        value = new Integer(3);
    }

    public static void main(String args[]) {
        Integer a = new Integer(2);
        Quest12 c = new Quest12();
        c.changeVal(a);
        System.out.print(a);
    }
}
