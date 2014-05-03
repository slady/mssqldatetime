package net.slady.mssqldatetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MSSQLDateTimeFormatConverter {
	final static String CAST_CONSTANT = "CAST(0x";
	final static int CAST_CONSTANT_LENGTH = CAST_CONSTANT.length();
	final static int AS_DATE_LENGTH = " AS Date)".length();
	final static int AS_DATE_TIME_LENGTH = "00000000 AS DateTime)".length();

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
			buf.append('\'');
			position = found + CAST_CONSTANT_LENGTH;
			int nextPos = position + 8;
			final String number = line.substring(position, nextPos);
			char testChar = line.charAt(nextPos);
			if (testChar == ' ') {
				buf.append(MSSQLDateTimeConverter.convertDate(number));
				nextPos += AS_DATE_LENGTH;
			} else if (testChar == '0') {
				buf.append(MSSQLDateTimeConverter.convertDateTime(number));
				nextPos += AS_DATE_TIME_LENGTH;
			} else {
				throw new IllegalStateException("Position: " + nextPos + ", Character: " + testChar);
			}

			buf.append('\'');
			position = nextPos;
		}
		
		buf.append(line.substring(position));

		return buf.toString();
	}

}
