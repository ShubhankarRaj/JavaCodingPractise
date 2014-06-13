package SCJPMon;
import java.util.regex.*;

public class countRegularExp {
  
    public static void main(String args[]){
      Pattern pt=Pattern.compile("r");
      Matcher mt=pt.matcher("Sun Certified Java Programmer");
      mt.find();
      System.out.print(mt.group());
    }
  }


