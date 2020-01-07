package com.ibeer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
public static final SimpleDateFormat DEFULAT_DATE_PATTERN = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	public static long setDatef(Date date) throws ParseException {
		 String format = DEFULAT_DATE_PATTERN.format(date);
		 Date parse = DEFULAT_DATE_PATTERN.parse(format);
		 long result = parse.getTime()/1000;
   	return result;
   }
	public static long setDate(Date date){		 
		 long result = date.getTime()/1000;
  	return result;
  }
}
