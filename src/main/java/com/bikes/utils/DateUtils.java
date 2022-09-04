package com.bikes.utils;

import java.util.Date;

public class DateUtils {
	
	/**********************************************************************************
	 * Following method is used to print 'Date' on the extend report
	 **********************************************************************************/
	
	public static String getTimeStamp() {
		Date date =new Date();
		return ( date.toString().replaceAll(" ", "_").replaceAll(":", "_"));
	}

}
