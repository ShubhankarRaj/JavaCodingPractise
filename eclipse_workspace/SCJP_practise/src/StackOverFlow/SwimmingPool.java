package StackOverFlow;

import java.math.*;


public class SwimmingPool{ 
	public static double SwimmingCount = 50;
  public static void countSpaces(String s){
    double count = 0.0;

    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == ' '){
        count++;
      }
    }
    
    System.out.println("Percent of spaces is " + count/s.length());
  }

  public static void main (String[] args){

    String s = "lefhg aljdfgh liauh h";
    countSpaces(s);
  }      
}
