package net.slady.mssqldatetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MSSQLDateTimeConverter {

	final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String convertDateTime(final String inputDateTimeString) {
		final long days = Long.valueOf(inputDateTimeString, 16);
		final Calendar cal = createNewDateTimeCalendar();
		cal.add(Calendar.DAY_OF_MONTH, (int) days);
		return dateFormat.format(cal.getTime()) + " 00:00:00.0";
	}
	
	public static String convertDate(final String inputDateString) {
		final long dateDays = convertHex(inputDateString);
		final Calendar dateCalendar = createNewDateCalendar();
		dateCalendar.add(Calendar.DAY_OF_MONTH, (int) dateDays);
		return dateFormat.format(dateCalendar.getTime());
	}

	private static Calendar createNewDateTimeCalendar() {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal;
	}
	
	private static Calendar createNewDateCalendar() {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		return cal;
	}
	
	private static long convertHex(final String input) {
		String s = "";
		for (int i = 0; i < 4; i++) {
			int beginIndex = i * 2;
			s = input.substring(beginIndex, beginIndex + 2) + s;
		}
		
		return (long) Long.valueOf(s, 16);
	}

}
