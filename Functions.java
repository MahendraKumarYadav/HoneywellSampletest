package com.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Functions {

	public static String time()
	{
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		Date now = new Date();
		String strTime = sdfTime.format(now);
		return strTime;
	}
	public static Object date()
	{
		Date dNow = new Date( );
		SimpleDateFormat ft =  new SimpleDateFormat ("d MMMM YYY");
		Object Cdate=ft.format(dNow);
		//System.out.println("Current Date: " +Cdate);
		return Cdate;
	}
	public int getRandom(int max, int min)
    {
        Random random= new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}



