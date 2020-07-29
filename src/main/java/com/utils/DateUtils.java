package com.utils;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getTimeStamp() {
		
		StringBuffer stringBuffer = new StringBuffer();
		Date now = new Date();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
		return simpleDateFormat.format(now, stringBuffer, new FieldPosition(0)).toString();
		
		
		/*
		 * String pattern = "yyMMddHHmmssZ"; SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat(pattern); return simpleDateFormat.toString();
		 */
		
		
	}
}
