package com.ibeer.account.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args) throws ParseException {
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date().getTime()/1000);
		
		
		
		
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		 String format = sdf.format(new Date());
		 Date parse = sdf.parse(format);
		 System.out.println(parse.getTime()/1000);
		       
		
	}
    public long setDate(Date date) throws ParseException {
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		 String format = sdf.format(new Date());
		 Date parse = sdf.parse(format);
		long result = parse.getTime()/1000;
    	return result;
    }
    
    
}
