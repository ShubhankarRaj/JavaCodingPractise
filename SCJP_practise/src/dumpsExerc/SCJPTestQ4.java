package dumpsExerc;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SCJPTestQ4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2009);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String formattedDate = df.format(c.getTime());
		System.out.println(formattedDate);

	}

}
