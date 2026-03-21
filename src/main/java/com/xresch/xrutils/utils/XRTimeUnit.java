package com.xresch.xrutils.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/********************************************************************************************
 * The mother of all time enumerations! :-P
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 ********************************************************************************************/
public enum XRTimeUnit {
	
	//  ns("nanosecond", 	TimeUnit.NANOSECONDS, 	ChronoUnit.NANOS, 	null)
	//, us("microsecond", TimeUnit.MICROSECONDS, 	ChronoUnit.MICROS, 	null)
	 ms("milliseconds", TimeUnit.MILLISECONDS, 	ChronoUnit.MILLIS, 	Calendar.MILLISECOND)
	, s("seconds", 		TimeUnit.SECONDS, 		ChronoUnit.SECONDS, Calendar.SECOND)
	, m("minutes", 		TimeUnit.MINUTES,		ChronoUnit.MINUTES, Calendar.MINUTE)
	, h("hours", 		TimeUnit.HOURS, 		ChronoUnit.HOURS, 	Calendar.HOUR_OF_DAY)
	, d("days", 		TimeUnit.DAYS, 			ChronoUnit.DAYS, 	Calendar.DAY_OF_YEAR)
	, M("months", 		null, 					ChronoUnit.MONTHS, 	Calendar.MONTH)
	, y("years", 		null, 					ChronoUnit.YEARS, 	Calendar.YEAR)
	;
	
	//==============================
	// Caches
	private static TreeSet<String> enumNames = null;
	private static String optionsString = null;
	private static String optionsHTMLList = null;
	
	//==============================
	// Fields
	private String longName;
	private TimeUnit timeUnit;
	private ChronoUnit chronoUnit;
	private Integer calendarUnit;
	
	private XRTimeUnit(String longName, TimeUnit timeUnit, ChronoUnit chronoUnit,  Integer calendarUnit) {
		this.longName = longName;
		this.timeUnit = timeUnit;
		this.chronoUnit = chronoUnit;
		this.calendarUnit = calendarUnit;
	}
			
	public String longName() { return this.longName; }
	public TimeUnit timeUnit() { return this.timeUnit; }
	public ChronoUnit chronoUnit() { return this.chronoUnit; }
	public Integer calendarUnit() { return this.calendarUnit; }
	
	public long toMillis() { return chronoUnit.getDuration().toMillis(); }
	public long toMillis(int amount) { return amount * chronoUnit.getDuration().toMillis(); }
	public Duration toDuration() { return chronoUnit.getDuration(); }
	
	/********************************************************************************************
	 * Returns the calendar unit lower than the specified unit.
	 * Returns Milliseconds if the unit is milliseconds.
	 * @param calendar which time should be truncated
	 * @return nothing
	 ********************************************************************************************/
	public XRTimeUnit calendarUnitLower() { 
		switch(this) {
			case y:	 return XRTimeUnit.M;
			case M:	 return XRTimeUnit.d;
			case d:	 return XRTimeUnit.h;
			case h:	 return XRTimeUnit.m;
			case m:	 return XRTimeUnit.s;
			case s:	 return XRTimeUnit.ms;
			case ms: return XRTimeUnit.ms;
			default: return XRTimeUnit.ms;
					
		}
	}
	/********************************************************************************************
	 * Returns a set with all names
	 ********************************************************************************************/
	public static TreeSet<String> getNames() {
		if(enumNames == null) {
			enumNames = new TreeSet<>();
			
			for(XRTimeUnit unit : XRTimeUnit.values()) {
				enumNames.add(unit.name());
			}
		}
		return enumNames;
	}
	
	/********************************************************************************************
	 * Returns a set with all names
	 ********************************************************************************************/
	public static String getOptionsString() {
		if(optionsString == null) {
			return String.join(" | ", getNames());
		}
		return optionsString;
	}
	
	/********************************************************************************************
	 * Returns a set with all names
	 ********************************************************************************************/
	public static String getOptionsHTMLList() {
		if(optionsHTMLList == null) {
			optionsHTMLList = "<ul>";
			for(XRTimeUnit unit : XRTimeUnit.values()) {
				optionsHTMLList += "<li><b>"+unit.name()+":&nbsp</b>"+unit.longName()+"</li>";
			}
			optionsHTMLList += "</ul>";
		}
		return optionsHTMLList;
	}
	/********************************************************************************************
	 * Return time with an offset starting from the given time.
	 * Use positive values to go to the future, use negative values to go to the past.
	 * @param epochMillis the time in milliseconds which should be offset.
	 * @param amount to offset for the selected time unit.
	 * @return offset time in epoch milliseconds
	 ********************************************************************************************/
	public static boolean has(String enumName) {
		return getNames().contains(enumName);
	}
	
	/********************************************************************************************
	 * Return time with an offset starting from the given time.
	 * Use positive values to go to the future, use negative values to go to the past.
	 * @param epochMillis the time in milliseconds which should be offset. If null takes current time.
	 * @param amount to offset for the selected time unit.
	 * @return offset time in epoch milliseconds
	 ********************************************************************************************/
	public long offset(Long epochMillis, int amount) { 
		
		if(epochMillis == null) {
			epochMillis = System.currentTimeMillis();
		}
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(epochMillis);
		calendar.add(this.calendarUnit, amount);
		
		return calendar.getTimeInMillis();
	}
	
	/********************************************************************************************
	 * Return time rounded to certain values.
	 * @param epochMillis the time in milliseconds which should be rounded.
	 * @param amount to round to.
	 * @return offset time in epoch milliseconds
	 ********************************************************************************************/
	public long round(long epochMillis, int amount) { 
					
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(epochMillis);
		
		int unit = this.calendarUnit;
		
		// don't round on days of month, will shift more values to 07th and reduce on 28th
//			if(unit == Calendar.DAY_OF_YEAR) {
//				unit = Calendar.DAY_OF_MONTH;
//			}
		int valueToRound = calendar.get(unit);
		
		
		int modulo = (valueToRound % amount);

		if(modulo != 0) {
			
			// decide to round up or down
			int diff = 0;
			if(modulo < ((float)amount / 2)) {
				diff = modulo*-1;
			}else {
				diff = amount - modulo;
			}
											
			calendar.add(this.calendarUnit, diff);

		}
		truncate(calendar);
		
		return calendar.getTimeInMillis();
	}
	
	/********************************************************************************************
	 * Truncates every time unit which is lower than this time unit.
	 * For Example, if time unit is minute, it will truncate seconds and below.
	 * @param epochMillis the time in milliseconds which should be truncated.
	 * @return truncated time in epoch milliseconds
	 ********************************************************************************************/
	public long truncate(long epochMillis) { 
					
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(epochMillis);
		truncate(calendar);
		
		return calendar.getTimeInMillis();
	}
	
	/********************************************************************************************
	 * Truncates every time unit which is lower than this time unit.
	 * For Example, if time unit is minute, it will truncate seconds and below.
	 * @param calendar which time should be truncated
	 * @return nothing
	 ********************************************************************************************/
	public void truncate(Calendar calendar) { 
		switch(this) {
			case y:	 calendar.set(Calendar.MONTH, 0);
			case M:	 calendar.set(Calendar.DAY_OF_MONTH, 0);
			case d:	 calendar.set(Calendar.HOUR_OF_DAY, 0);
			case h:	 calendar.set(Calendar.MINUTE, 0);
			case m:	 calendar.set(Calendar.SECOND, 0);
			case s:	 calendar.set(Calendar.MILLISECOND, 0);
			default: break;
					
		}
	}
	
	/********************************************************************************************
	 * Return the difference of two times.
	 * The returned time will be the difference of the two times in the selected time unit.
	 * @param earlier the time in milliseconds of the earlier time
	 * @param later the time in milliseconds of the later time
	 * @return truncated time in epoch milliseconds
	 ********************************************************************************************/
	public float difference(Calendar earlier, Calendar later) { 		
		return difference(earlier.getTimeInMillis(), later.getTimeInMillis());
	}
	
	
	/********************************************************************************************
	 * Return the difference of two times.
	 * The returned time will be the difference of the two times in the selected time unit.
	 * @param earlier the time in milliseconds of the earlier time
	 * @param later the time in milliseconds of the later time
	 * @return truncated time in epoch milliseconds
	 ********************************************************************************************/
	public float difference(long earlier, long later) { 
		
		if(earlier == later) {
			return 0;
		}
		
		long diff = later - earlier;

		return convertPrecise(diff);
	}
	
	/********************************************************************************************
	 * Converts the given milliseconds to a representation of the selected time unit.
	 * @param millis the time in milliseconds of the earlier time
	 * @return float value representing milliseconds
	 ********************************************************************************************/
	public float convertPrecise(long millis) { 
		
		return millis / ((float)this.toMillis());
	}
	
	/********************************************************************************************
	 * Converts the given milliseconds to a representation of the selected time unit.
	 * @param millis the time in milliseconds of the earlier time
	 * @return long value representing milliseconds, rounding towards zero will occur
	 ********************************************************************************************/
	public long convert(long millis) { 
		
		return millis / this.toMillis();
	}
	
	
}