package net.slady.mssqldatetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MSSQLDateTimeFormatConverter {
	private static final String CAST_CONSTANT = "CAST(0x";
	private static final int CAST_CONSTANT_LENGTH = CAST_CONSTANT.length();
	private static final int AS_DATE_LENGTH = "12345678 AS Date)".length();
	private static final int AS_DATE_TIME_LENGTH = "1234567812345678 AS DateTime)".length();
	private static final String AS_DATE_REGEXP = "\\w{8} AS Date\\).*";
	private static final String AS_DATE_TIME_REGEXP = "\\w{16} AS DateTime\\).*";

	private static final int DATE_TIME_NUMBER_LENGTH = 16;
	private static final int DATE_NUMBER_LENGTH = 8;

	public static void main(final String[] args) throws IOException {
		final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			final String line = input.readLine();
			if (line == null) {
				break;
			}
			
			System.out.println(processLine(line));
		}
	}

	public static String processLine(final String line) {
		if (!line.contains(CAST_CONSTANT)) {
			return line;
		}
		
		final StringBuilder buf = new StringBuilder();
		int position = 0;
		
		while (true) {
			int found = line.indexOf(CAST_CONSTANT, position);
			if (found == -1) {
				break;
			}

			buf.append(line.subSequence(position, found));
			position = found + CAST_CONSTANT_LENGTH;
			final String theRestLine = line.substring(position);

			if (theRestLine.matches(AS_DATE_REGEXP)) {
				final String number = line.substring(position, position + DATE_NUMBER_LENGTH);
				buf.append('\'');
				buf.append(MSSQLDateTimeConverter.convertDate(number));
				buf.append('\'');
				position += AS_DATE_LENGTH;
			} else if (theRestLine.matches(AS_DATE_TIME_REGEXP)) {
				final String number = line.substring(position, position + DATE_TIME_NUMBER_LENGTH);
				buf.append('\'');
				buf.append(MSSQLDateTimeConverter.convertDateTime(number));
				buf.append('\'');
				position += AS_DATE_TIME_LENGTH;
			} else {
				buf.append(CAST_CONSTANT);
			}
		}
		
		buf.append(line.substring(position));

		return buf.toString();
	}

}
