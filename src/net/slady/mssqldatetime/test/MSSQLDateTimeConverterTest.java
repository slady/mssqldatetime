package net.slady.mssqldatetime.test;

import junit.framework.Assert;

import net.slady.mssqldatetime.MSSQLDateTimeConverter;

import org.junit.Test;

public class MSSQLDateTimeConverterTest {

	@Test
	public void convertDateTime() {
		String dateTime = MSSQLDateTimeConverter.convertDateTime("0000A315");
		Assert.assertEquals("2014-04-22 00:00:00.0", dateTime);
	}

	@Test
	public void convertDate() {
		Assert.assertEquals("1993-12-16", MSSQLDateTimeConverter.convertDate("681B0B00"));
		Assert.assertEquals("2017-11-30", MSSQLDateTimeConverter.convertDate("963D0B00"));
		Assert.assertEquals("1995-05-31", MSSQLDateTimeConverter.convertDate("7B1D0B00"));
		Assert.assertEquals("1993-11-17", MSSQLDateTimeConverter.convertDate("4B1B0B00"));
	}

}
