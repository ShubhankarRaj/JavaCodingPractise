package SCJPTestSat;
import java.util.*;

public class StringToInteger {
    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
    
    public void add(String a, Integer b) {
            hm.put(a, b);
    }
    
    public void add(Integer a, String b) {
        hm1.put(a, b);
}
    
    public Set<?> getKeys() {
            return hm1.keySet();           
    }
    
//    public Set<Integer> getKeys1() {
//        return hm1.keySet();
//}
    
    public Collection<?> getValues() {
            return hm1.values();
    }
    
//    public Collection<Integer> getValues1() {
//        return hm.values();
//}
    
    public static void main(String args[]) {
            StringToInteger si = new StringToInteger();
            si.add("One", 1);
            si.add("Two", 2);
            si.add(100, "One Hundred");
            si.add(200, "Two Hundred");
            
            System.out.println(si.getKeys());
        //    System.out.println(si.getKeys1());
            System.out.print(si.getValues());
//            System.out.println(si.getValues1());
    }
}
