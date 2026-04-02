package com.xresch.xrutils.test.data;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;
import com.xresch.xrutils.data.Unrecord;
import com.xresch.xrutils.data.Unvalue;

/***************************************************************************
 * This is an example on how to programmatically execute a test on the 
 * local machine using JUnit, without the need to execute the JAR file 
 * with command line arguments.
 * 
 * Copyright Owner: Performetriks GmbH, Switzerland
 * License: Eclipse Public License v2.0
 * 
 * @author Reto Scheiwiller
 * 
 ***************************************************************************/

public class TestUnrecord {

	private Unrecord record = 
			new Unrecord()
				.add("ID", "aca90d7d-e59e-4c91-b17")
				.add("INDEX", 0)
				.add("LIKES_TIRAMISU", false)
				.add("USER", "u.bjoerk")
				.add("FIRSTNAME", "Uranus")
				.add("LASTNAME", "Bjoerk")
				.add("LOCATION", "Aztlan")
				.add("EMAIL", "u.bjoerk@aztlan.com")
				.add("VALUE", 66)
				.add("SEARCH_FOR", "Gianduiotto")
				;
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordClone() throws InterruptedException {
		
		//-------------------------------------
		// Execute Test
		Unrecord record = new Unrecord();
		record.add("valueA", "A");
		
		Unrecord clone = record.clone();
		clone.add("valueB", "B");
		
		Assertions.assertEquals(record.size(), 1, "record has 1 entry");
		Assertions.assertEquals(clone.size(), 2, "clone has 2 entries");
		
		Assertions.assertEquals(record.containsKey("valueA"), true, "record has valueA");
		Assertions.assertEquals(record.containsKey("valueB"), false, "record has not valueB");
		
		Assertions.assertEquals(clone.containsKey("valueA"), true, "clone has valueA");
		Assertions.assertEquals(clone.containsKey("valueB"), true, "clone has valueB");
	}	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordInsert() throws InterruptedException {
		

		String replaced = record.insert("""
				{
					  "id": ${ID}
					, "index": "${INDEX}"
					, "likesTiramisu": ${LIKES_TIRAMISU}
				}
				""");
		
		System.out.println(replaced);
		
		Assertions.assertEquals("""
				{
					  "id": aca90d7d-e59e-4c91-b17
					, "index": "0"
					, "likesTiramisu": false
				}
				""".trim(), replaced.trim(), "all parameters replaced");
		
	}	
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordInsert_BeginAndEndString() throws InterruptedException {
		
		String replaced = record.insert("${ID}        ${INDEX}");
		
		System.out.println("###### "+replaced);
		
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17        0", replaced, "params replaced at begin/end of string");
		
	}	
		
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordInsert_SameParam() throws InterruptedException {
		
		String replaced = record.insert("${INDEX}, ${INDEX}, ${INDEX}, ${INDEX}");
		
		System.out.println("###### "+replaced);
		
		Assertions.assertEquals("0, 0, 0, 0", replaced, "params replaced at begin/end of string");
		
	}	
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordInsert_UnknownParam() throws InterruptedException {
		
		String replaced = record.insert("${INDEX}, ${noParam}, ${INDEX}");
		
		System.out.println("###### "+replaced);
		
		Assertions.assertEquals("0, ${noParam}, 0", replaced, "params replaced at begin/end of string");
		
	}	
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecord_toJsonObject() throws InterruptedException {
		

		JsonObject object = record.toJsonObject();
		
		System.out.println("###### "+object);

		Assertions.assertEquals(10, object.size(), "has 10 values");
		Assertions.assertEquals(0, object.get("INDEX").getAsInt());
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", object.get("ID").getAsString());
		Assertions.assertEquals("u.bjoerk", object.get("USER").getAsString());
		Assertions.assertEquals("Uranus", object.get("FIRSTNAME").getAsString());
		Assertions.assertEquals("Bjoerk", object.get("LASTNAME").getAsString());
		Assertions.assertEquals("Aztlan", object.get("LOCATION").getAsString());
		Assertions.assertEquals("u.bjoerk@aztlan.com", object.get("EMAIL").getAsString());
		Assertions.assertEquals(false, object.get("LIKES_TIRAMISU").getAsBoolean());
		Assertions.assertEquals(66, object.get("VALUE").getAsInt());
		Assertions.assertEquals("Gianduiotto", object.get("SEARCH_FOR").getAsString());
		
	}	
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecord_toHashMap() throws InterruptedException {
		
		LinkedHashMap<String, Unvalue> map = record.toHashMap();
		
		System.out.println("###### "+map);

		Assertions.assertEquals(10, map.size(), "has 10 values");
		Assertions.assertEquals(0, map.get("INDEX").getAsInt());
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", map.get("ID").getAsString());
		Assertions.assertEquals("u.bjoerk", map.get("USER").getAsString());
		Assertions.assertEquals("Uranus", map.get("FIRSTNAME").getAsString());
		Assertions.assertEquals("Bjoerk", map.get("LASTNAME").getAsString());
		Assertions.assertEquals("Aztlan", map.get("LOCATION").getAsString());
		Assertions.assertEquals("u.bjoerk@aztlan.com", map.get("EMAIL").getAsString());
		Assertions.assertEquals(false, map.get("LIKES_TIRAMISU").getAsBoolean());
		Assertions.assertEquals(66, map.get("VALUE").getAsInt());
		Assertions.assertEquals("Gianduiotto", map.get("SEARCH_FOR").getAsString());
		
	}	
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecord_toHashMapStrings() throws InterruptedException {
		
		LinkedHashMap<String, String> map = record.toHashMapStrings();
		
		System.out.println("###### "+map);

		Assertions.assertEquals(10, map.size(), "has 10 values");
		Assertions.assertEquals("0", map.get("INDEX"));
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", map.get("ID"));
		Assertions.assertEquals("u.bjoerk", map.get("USER"));
		Assertions.assertEquals("Uranus", map.get("FIRSTNAME"));
		Assertions.assertEquals("Bjoerk", map.get("LASTNAME"));
		Assertions.assertEquals("Aztlan", map.get("LOCATION"));
		Assertions.assertEquals("u.bjoerk@aztlan.com", map.get("EMAIL"));
		Assertions.assertEquals("false", map.get("LIKES_TIRAMISU"));
		Assertions.assertEquals("66", map.get("VALUE"));
		Assertions.assertEquals("Gianduiotto", map.get("SEARCH_FOR"));
		
	}
	
}