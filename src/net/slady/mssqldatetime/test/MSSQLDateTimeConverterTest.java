package net.slady.mssqldatetime.test;

import junit.framework.Assert;

import net.slady.mssqldatetime.MSSQLDateTimeConverter;

import org.junit.Test;

public class MSSQLDateTimeConverterTest {

	@Test
	public void convertDateTime() {
		Assert.assertEquals("2014-04-22 00:00:00.0", MSSQLDateTimeConverter.convertDateTime("0000A31500000000"));
		Assert.assertEquals("2011-02-09 18:52:34.286", MSSQLDateTimeConverter.convertDateTime("00009E85013711EE"));
		// actually the exact time information should be "2011-02-09 18:52:34.286667"
		// the missing part "667" is due to missing nanosecond precision in the Calendar of Java,
		// but I guess the sub-second precision is all right for the insert,
		// just to know the limitations
	}

	@Test
	public void convertDate() {
		Assert.assertEquals("1993-12-16", MSSQLDateTimeConverter.convertDate("681B0B00"));
		Assert.assertEquals("2017-11-30", MSSQLDateTimeConverter.convertDate("963D0B00"));
		Assert.assertEquals("1995-05-31", MSSQLDateTimeConverter.convertDate("7B1D0B00"));
		Assert.assertEquals("1993-11-17", MSSQLDateTimeConverter.convertDate("4B1B0B00"));
	}

}
