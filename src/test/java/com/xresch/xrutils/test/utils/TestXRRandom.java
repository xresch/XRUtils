package com.xresch.xrutils.test.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;
import com.xresch.xrutils.utils.XRRandom;
import com.xresch.xrutils.utils.XRRandom.Person;
import com.xresch.xrutils.utils.XRRandom.RandomSeriesGenerator;
import com.xresch.xrutils.utils.XRTimeUnit;

/***************************************************************************
 * 
 ***************************************************************************/

public class TestXRRandom {

		
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	public void testExecuteMethods() throws InterruptedException {
		
		//--------------------------------------------
		// Person Data
		int id = 1234;
		Person p = XRRandom.randomPerson(id);
		
		//--------------------------------------------
		// Various Predefined String Data
		String firstname 		= XRRandom.firstnameOfGod();
		String lastname 		= XRRandom.lastnameSweden();
		String location 		= XRRandom.mythicalLocation();
		String company 			= XRRandom.companyTitle();
		String job 				= XRRandom.jobTitle();
		String street 			= XRRandom.street();
		String capitalCity 		= XRRandom.capitalCity();
		String country 			= XRRandom.country();
		String countryCode 		= XRRandom.countryCode();
		String continent 		= XRRandom.continent();
		String phoneNumber 		= XRRandom.phoneNumber();
		String colorName		= XRRandom.colorName();
		String colorSL 			= XRRandom.colorSL(200, 20, 80, 20, 80);
		String colorHSL 		= XRRandom.colorHSL(20, 80, 20, 80);
		String fruit 			= XRRandom.fruitName();
		String italianDessert 	= XRRandom.italianDessert();
		String adjective 		= XRRandom.exaggaratingAdjective();
		String statisticTitle 	= XRRandom.statisticsTitle();
		String methodName 		= XRRandom.methodName() ;
		String service 			= XRRandom.ultimateServiceName();
		

		//--------------------------------------------
		// Boolean
		int nullPercent = 10; //with 10% null values
		Boolean bool 			= XRRandom.bool();
		Boolean boolWithNulls 	= XRRandom.bool(nullPercent); // with 10% null values
		
		//--------------------------------------------
		// Strings
		String alphaLowerUppercase 	= XRRandom.string(32);
		String alphanum 			= XRRandom.stringAlphaNum(32);
		String alphanumSpecial 		= XRRandom.stringAlphaNumSpecial(32);
		String loremIpsum 			= XRRandom.loremIpsum(2048);
		
		//--------------------------------------------
		// The from method allowing any data type
		int fromWithInts 		= XRRandom.from(1, 3, 9, 27);
		long fromWithLong 		= XRRandom.from(1L, 4L, 16L, 64L);
		float fromWithFloat 	= XRRandom.from(1.1f, 2.2f, 3.3f);
		double fromWithDouble 	= XRRandom.from(1.2d, 2.2d, 3.3d);
		BigDecimal fromWithBig 	= XRRandom.from(new BigDecimal(4), new BigDecimal(424), new BigDecimal(4242.42));
		
		char fromWithChars 		= XRRandom.from('a', 'b', 'c');
		String fromWithString 	= XRRandom.from("AA", "BB", "CC");
		
		String fromStrings 		= XRRandom.fromStrings(new String[]{"AA", "BB", "CC"});
		
		//--------------------------------------------
		// Specific From Methods
		XR.Random.fromStrings("Tiramisu", "Panna Cotta", "Amaretti");
		XR.Random.fromChars('A', 'B', 'C');
		XR.Random.fromInts(1, 2, 3);
		XR.Random.fromLongs(11L, 22L, 33L);
		XR.Random.fromFloats(11.1f, 22.2f, 33.3f);
		XR.Random.fromDoubles(11.11d, 22.22d, 33.33d);
		XR.Random.fromBigDecimals(new BigDecimal("111.111"), new BigDecimal("123.456"), new BigDecimal("987.654"));
		
		XR.Random.fromDates(
				  new Date(Instant.now().toEpochMilli())
				, new Date(Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli())
				, new Date(Instant.now().minus(14, ChronoUnit.DAYS).toEpochMilli())
			);
		
		XR.Random.fromTimestamps(
				  new Timestamp(Instant.now().toEpochMilli())
				, new Timestamp(Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli())
				, new Timestamp(Instant.now().minus(14, ChronoUnit.DAYS).toEpochMilli())
			);

		XR.Random.fromInstants(
				  Instant.now()
				, Instant.now().minus(7, ChronoUnit.DAYS)
				, Instant.now().minus(14, ChronoUnit.DAYS)
				
		);
		
		//--------------------------------------------
		// from Collections, Arrays ...
		String fromArray = XRRandom.fromArray(new String[]{"AA", "BB", "CC"});
		// XRRandom.fromArray(T[] array) supports any other kind of array
		
		ArrayList<String> list = new ArrayList<>(); list.add("value"); list.add("value2"); list.add("value3");
		String fromArrayList = XRRandom.fromArray(list);
		// XRRandom.fromArray(ArrayList<T> array) supports any other kind of arrayList
		
		Map<String, String> map = new HashMap<>(); map.put("key", "value"); map.put("key2", "value2"); map.put("key3", "value3");
		Entry<String, String> fromMap = XRRandom.fromMap(map);
		
		Set<String> set = new HashSet<>(); set.add("value"); set.add("value2"); set.add("value3");
		String fromSet = XRRandom.fromSet(set);
		
		//--------------------------------------------
		// Random Number in Range
		XR.Random.fromZeroToInteger(100);
		XR.Random.integer(42, 88);
		XR.Random.integer(42, 88, nullPercent);
		XR.Random.doubleInRange(1000, 1000_000);
		XR.Random.floatInRange(1.0f, 1000.0f);
		XR.Random.longInRange(1000_000L, 1000_000_000L);
		XR.Random.bigDecimal(1000_000L, 1000_000_000L);
		
		//--------------------------------------------
		// Random Time in Range
		long latest = XRTimeUnit.d.offset(null, 0);
		long earliest = XRTimeUnit.d.offset(null, -1);
		
		long timeLong 			= XR.Random.epoch(earliest, latest);
		Date timeDate 			= XR.Random.date(earliest, latest);
		String timeString 		= XR.Random.dateString(earliest, latest, "YYYY-MM-dd");
		Timestamp timestamp 	= XR.Random.timestamp(earliest, latest);
		Instant timeInstant 	= XR.Random.instant(earliest, latest);
		ZonedDateTime timeZoned = XR.Random.zonedDateTime(earliest, latest, ZoneOffset.UTC);
		
		
		//--------------------------------------------
		// ArrayLists
		int count = 50;
		XRRandom.arrayListOfIntegers(count, 0, 1000);
		XRRandom.arrayListOfSwedishLastnames(count);
		XRRandom.randomPersonArray(count);
		
		//--------------------------------------------
		// JsonArrays containing JsonObjects
		int seriesCount = 3;
		JsonArray mightyPeople	= XRRandom.jsonArrayOfMightyPeople(count, 5, earliest, latest);
		JsonArray numbers		= XRRandom.jsonArrayOfNumberData(count, 0, earliest, latest);
		JsonArray arrays		= XRRandom.jsonArrayOfArrayData(count, 0, earliest, latest);
		JsonArray series		= XRRandom.jsonArrayOfSeriesData(seriesCount, count, earliest, latest);
		JsonArray stats			= XRRandom.jsonArrayOfStatisticalSeriesData(seriesCount, count, earliest, latest);
		JsonArray trading		= XRRandom.jsonArrayOfTradingData(seriesCount, count, earliest, latest);
		JsonArray tickets 		= XRRandom.jsonArrayOfSupportTickets(count);
		JsonArray countries 	= XRRandom.jsonArrayOfCountryData(); // not random, but returns data ^^'
		JsonArray batchjobs		= XRRandom.jsonArrayOfBatchCalls(seriesCount, count, earliest, latest, 7);
		JsonArray various		= XRRandom.jsonArrayOfVariousData(count, 0, earliest, latest);
		
		//--------------------------------------------
		// JsonArrays containing Other Types
		JsonArray arrayIntegers			= XRRandom.jsonArrayOfIntegers(count, 0,100);
		JsonArray arrayCharacters		= XRRandom.jsonArrayOfCharacters(count);
		JsonArray arrayStrings			= XRRandom.jsonArrayOfRandomStrings(count, 16);
		JsonArray mixedTypes			= XRRandom.jsonArrayOfMixedTypes(0,100);
		
		//--------------------------------------------
		// Json Objects
		JsonObject countryData = XRRandom.countryData();
		
		//--------------------------------------
		// Create Number Series
		int totalValues = 100;
		RandomSeriesGenerator generator = XRRandom.createRandomSeriesGenerator(totalValues);
		
		ArrayList<Integer> values = new ArrayList<>();
		for(int j = 0; j < totalValues; j++) {			
			values.add( generator.getValue(j) );
		}

	}
	
	/*****************************************************
	 * This is a manual check, no assertions are done.
	 *****************************************************/
	@Test
	public void testCFWRandom() {
		
		//------------------------------------
		// Test Data
		//------------------------------------
		Set<String> stringSet = new HashSet<>();
		stringSet.add("Filament");
		stringSet.add("Fiber");
		stringSet.add("Floss");
		stringSet.add("Thread");
		stringSet.add("Yarn");
		stringSet.add("Twine");
		stringSet.add("String");
		stringSet.add("Cord");
		stringSet.add("Rope");
		stringSet.add("Towline");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("hello", "world");
		map.put("world", "hello");
		map.put("foo", "bar");
		map.put("bar", "efoot");
		map.put("italian", "desserts");
		
		long latest = XRTimeUnit.d.offset(null, 0);
		long earliest = XRTimeUnit.d.offset(null, -1);
		
		//------------------------------------
		// Generate Data
		//------------------------------------
		Integer[] nullPercentArray = new Integer[] {100, 50, 0};
		
		for(Integer nullPercent : nullPercentArray) {
			
			System.out.println("================== Nulls: "+nullPercent+"% ==================");
			System.out.println("from(): " + XR.Random.from("One", true, "Three", null) );  // well it works 😅 
			System.out.println("fromStrings(): " + XR.Random.fromStrings("Tiramisu", "Panna Cotta", "Amaretti") );
			System.out.println("fromChars(): " + XR.Random.fromChars('A', 'B', 'C') );
			System.out.println("fromInts(): " + XR.Random.fromInts(1, 2, 3) );
			System.out.println("fromLongs(): " + XR.Random.fromLongs(11L, 22L, 33L) );
			System.out.println("fromFloats(): " + XR.Random.fromFloats(11.1f, 22.2f, 33.3f) );
			System.out.println("fromDoubles(): " + XR.Random.fromDoubles(11.11d, 22.22d, 33.33d) );
			System.out.println("fromBigDecimals(): " + XR.Random.fromBigDecimals(new BigDecimal("111.111"), new BigDecimal("123.456"), new BigDecimal("987.654")) );
			System.out.println("fromDates(): " 
									+ XR.Random.fromDates(
											  new Date(Instant.now().toEpochMilli())
											, new Date(Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli())
											, new Date(Instant.now().minus(14, ChronoUnit.DAYS).toEpochMilli())
										) 
									);
			
			System.out.println("fromTimestamps(): " 
									+ XR.Random.fromTimestamps(
											  new Timestamp(Instant.now().toEpochMilli())
											, new Timestamp(Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli())
											, new Timestamp(Instant.now().minus(14, ChronoUnit.DAYS).toEpochMilli())
										) 
									);
			
			System.out.println("fromInstants(): " 
									+ XR.Random.fromInstants(
												  Instant.now()
												, Instant.now().minus(7, ChronoUnit.DAYS)
												, Instant.now().minus(14, ChronoUnit.DAYS)
											) 
									);
			
			
			System.out.println(" ");
			System.out.println("fromArray(String[]): " + XR.Random.fromArray(new String[]{"StringA", "StringB", "StringC"}) );
			System.out.println("fromArray(Integer[]): " + XR.Random.fromArray(new Integer[]{11, 22, 33, 44, 55}) );
			System.out.println("fromArray(nullPercent, Integer[]): " + XR.Random.fromArray(nullPercent, new Integer[]{11, 22, 33, 44, 55}) );
			System.out.println("fromSet(nullPercent, stringSet): " + XR.Random.fromSet(stringSet, nullPercent) );
			System.out.println("fromMap(nullPercent, map): " + XR.Random.fromMap(map, nullPercent) );
			
			System.out.println(" ");
			System.out.println("string(16): " + XR.Random.string(16) );
			System.out.println("stringAlphaNum(32): " + XR.Random.stringAlphaNum(32) );
			System.out.println("stringAlphaNumSpecial(64): " + XR.Random.stringAlphaNumSpecial(64) );
			
			System.out.println(" ");
			System.out.println("bool(nullPercent): " + XR.Random.bool(nullPercent) );
			System.out.println("fromZeroToInteger(100): " + XR.Random.fromZeroToInteger(100) );
			System.out.println("integer(42, 88): " + XR.Random.integer(42, 88) );
			System.out.println("integer(42, 88, nullPercent): " + XR.Random.integer(42, 88, nullPercent) );
			System.out.println("doubleInRange(1000, 1000_000): " + XR.Random.doubleInRange(1000, 1000_000) );
			System.out.println("floatInRange(1.0f, 1000.0f): " + XR.Random.floatInRange(1.0f, 1000.0f) );
			System.out.println("longInRange(1000_000L, 1000_000_000L): " + XR.Random.longInRange(1000_000L, 1000_000_000L) );
			System.out.println("bigDecimal(1000_000L, 1000_000_000L): " + XR.Random.bigDecimal(1000_000L, 1000_000_000L) );
			
			System.out.println(" ");
			System.out.println("epoch(earliest, latest): " + XR.Random.epoch(earliest, latest) );
			System.out.println("date(earliest, latest): " + XR.Random.date(earliest, latest).toString() );
			System.out.println("dateString(earliest, latest): " + XR.Random.dateString(earliest, latest, "YYYY-MM-dd"));
			System.out.println("timestamp(earliest, latest): " + XR.Random.timestamp(earliest, latest).toString() );
			System.out.println("instant(earliest, latest): " + XR.Random.instant(earliest, latest).toString() );
			System.out.println("zonedDateTime(earliest, latest): " + XR.Random.zonedDateTime(earliest, latest, ZoneOffset.UTC).toString() );

			System.out.println(" ");
			System.out.println("firstnameOfGod(nullPercent): " + XR.Random.firstnameOfGod(nullPercent) );
			System.out.println("lastnameSweden(nullPercent): " + XR.Random.lastnameSweden(nullPercent) );
			System.out.println("companyTitle(nullPercent): " + XR.Random.companyTitle(nullPercent) );
			System.out.println("jobTitle(nullPercent): " + XR.Random.jobTitle(nullPercent) );
			System.out.println("street(nullPercent): " + XR.Random.street(nullPercent) );
			System.out.println("capital(nullPercent): " + XR.Random.capitalCity(nullPercent) );
			System.out.println("country(nullPercent): " + XR.Random.country(nullPercent) );
			System.out.println("countryCode(nullPercent): " + XR.Random.countryCode(nullPercent) );
			System.out.println("continent(nullPercent): " + XR.Random.continent(nullPercent) );
			System.out.println("countryData(nullPercent): " + XR.JSON.toJSON(XR.Random.countryData()) );
			System.out.println("phoneNumber(nullPercent): " + XR.Random.phoneNumber(nullPercent) );
			
			System.out.println(" ");
			System.out.println("mythicalLocation(nullPercent): " + XR.Random.mythicalLocation(nullPercent) );
			System.out.println("colorName(nullPercent): " + XR.Random.colorName(nullPercent) );
			System.out.println("colorSL(nullPercent): " + XR.Random.colorSL(200, 20, 80, 20, 80) );
			System.out.println("exaggaratingAdjective(nullPercent): " + XR.Random.exaggaratingAdjective(nullPercent) );
			System.out.println("fruitName(nullPercent): " + XR.Random.fruitName(nullPercent) );
			System.out.println("issueResolvedMessage(nullPercent): " + XR.Random.issueResolvedMessage(nullPercent) );
			System.out.println("italianDessert(nullPercent): " + XR.Random.italianDessert(nullPercent) );
			System.out.println("messageOfObedience(nullPercent): " + XR.Random.messageOfObedience(nullPercent) );
			System.out.println("methodName(nullPercent): " + XR.Random.methodName(nullPercent) );
			System.out.println("statisticsTitle(nullPercent): " + XR.Random.statisticsTitle(nullPercent) );
			System.out.println("ultimateServiceName(nullPercent): " + XR.Random.ultimateServiceName(nullPercent) );
			
			System.out.println(" ");
			System.out.println("loremIpsum(128): '" + XR.Random.loremIpsum(128)+"'" );
			System.out.println("loremIpsum(256): '" + XR.Random.loremIpsum(256)+"'" );
			System.out.println("loremIpsum(512): '" + XR.Random.loremIpsum(512)+"'" );
			System.out.println("loremIpsum(1024): '" + XR.Random.loremIpsum(1024)+"'" );
			System.out.println("loremIpsum(2049): '" + XR.Random.loremIpsum(2049)+"'" );
		}
		
	}
	
}