package ExamG;

//import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;

public class AddDateClass {
	public static void main(String[] args){
		Date d = new Date();
		String ds = "December 16, 2013";
		DateFormat df = DateFormat.getDateInstance();
		d.setTime((60*60*24*1000)+d.getTime());
		try{
		d = df.parse(ds);
//		System.out.println(d.toString());
		}
		catch(ParseException e){
			System.out.println(d.toString());
		}
		
//		System.out.println(d.toString());
	}
}
