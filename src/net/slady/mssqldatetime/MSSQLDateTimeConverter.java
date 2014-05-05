package net.slady.mssqldatetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MSSQLDateTimeConverter {

	final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	private static final int DATE_TIME_NUMBER_LENGTH = 16;
	private static final int DATE_NUMBER_LENGTH = 8;
	private static final int HEX_RADIX_BASE = 16;

	public static String convertDateTime(final String inputDateTimeString) {
		if (inputDateTimeString == null || inputDateTimeString.length() != DATE_TIME_NUMBER_LENGTH) {
			throw new IllegalArgumentException("wrong exception: " + inputDateTimeString);
		}

		final String datePart = inputDateTimeString.substring(0, DATE_NUMBER_LENGTH);
		final long days = Long.valueOf(datePart, HEX_RADIX_BASE);
		final Calendar cal = createNewDateTimeCalendar();
		cal.add(Calendar.DAY_OF_MONTH, (int) days);

		final String timePart = inputDateTimeString.substring(DATE_NUMBER_LENGTH, DATE_TIME_NUMBER_LENGTH);
		final long tics = Long.valueOf(timePart, HEX_RADIX_BASE);
		final long millis = tics * 1000 / 300;
		cal.add(Calendar.MILLISECOND, (int) millis);

		return DATE_TIME_FORMAT.format(cal.getTime());
	}
	
	public static String convertDate(final String inputDateString) {
		if (inputDateString ==  null || inputDateString.length() != DATE_NUMBER_LENGTH) {
			throw new IllegalArgumentException("wrong date format: " + inputDateString);
		}

		final long dateDays = convertHex(inputDateString);
		final Calendar dateCalendar = createNewDateCalendar();
		dateCalendar.add(Calendar.DAY_OF_MONTH, (int) dateDays);
		return DATE_FORMAT.format(dateCalendar.getTime());
	}

	private static Calendar createNewDateTimeCalendar() {
		final Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal;
	}
	
	private static Calendar createNewDateCalendar() {
		final Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		return cal;
	}
	
	private static long convertHex(final String input) {
		String s = "";
		for (int i = 0; i < 4; i++) {
			final int beginIndex = i * 2;
			s = input.substring(beginIndex, beginIndex + 2) + s;
		}
		
		return (long) Long.valueOf(s, 16);
	}

}
