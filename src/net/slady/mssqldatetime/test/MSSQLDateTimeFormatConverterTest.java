package net.slady.mssqldatetime.test;

import junit.framework.Assert;
import net.slady.mssqldatetime.MSSQLDateTimeFormatConverter;

import org.junit.Test;


public class MSSQLDateTimeFormatConverterTest {

	@Test
	public void processLine1() {
		final String input = "INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2)"
			+ " VALUES (CAST(0x681B0B00 AS Date), CAST(0x963D0B00 AS Date),"
			+ " CAST(0x0000A31500000000 AS DateTime), CAST(0x0000A31500000000 AS DateTime))";
		final String result = MSSQLDateTimeFormatConverter.processLine(input);
		final String expected = "INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2)"
			+ " VALUES ('1993-12-16', '2017-11-30', '2014-04-22 00:00:00.0', '2014-04-22 00:00:00.0')";
		Assert.assertEquals(expected , result);
	}
	
	@Test
	public void processLine2() {
		final String input = "INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2)"
			+ " VALUES (CAST(0x7B1D0B00 AS Date), CAST(0x963D0B00 AS Date),"
			+ " CAST(0x0000A32500000000 AS DateTime), CAST(0x0000A32500000000 AS DateTime))";
		final String result = MSSQLDateTimeFormatConverter.processLine(input);
		final String expected = "INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2)"
			+ " VALUES ('1995-05-31', '2017-11-30', '2014-05-08 00:00:00.0', '2014-05-08 00:00:00.0')";
		Assert.assertEquals(expected , result);
	}

}
