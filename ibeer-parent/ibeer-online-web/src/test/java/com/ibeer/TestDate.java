package com.ibeer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
 public static void main(String[] args) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	
	String dl = "2039-28-25 16:28:01:281";
	 dl = "2099-28-25 16:28:01";
	Date parse = sdf.parse(dl);
	System.out.println(parse.getTime()+"--"+"2179556881000".length());
	String format = sdf.format(new Date(parse.getTime()));
	System.out.println(format);

	
	
	
}
}
