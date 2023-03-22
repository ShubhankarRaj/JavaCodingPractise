package SCJPTestSat;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TryMe {
   public static void main(String args[]) throws InterruptedException{
        List list = new LinkedList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        
        Thread.sleep(1);
        Collections.reverse(list);
        Iterator iter = list.iterator();
        System.out.println("NO ISSUE");
        
        String test = "This is a asd-terst rstring";
    String[] tokens = test.split("\\s");
    
        System.out.println(tokens.length);
        for(int i=0;i<tokens.length;i++)
        System.out.println(tokens[i]);
//        for (List s : list) {
//            System.out.print(s + " ");
//        }
        
   
    }
}