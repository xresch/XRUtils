package com.xresch.xrutils.test.data;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;
import com.xresch.xrutils.data.XRRecord;
import com.xresch.xrutils.data.XRValue;

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

public class TestXRRecord {

	private XRRecord record = 
			new XRRecord()
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
	
	public enum TestFields {
		ID, INDEX, LIKES_TIRAMISU, USER, FIRSTNAME, LASTNAME, LOCATION, EMAIL, VALUE, SEARCH_FOR, TAGS, OBJECT, ARRAY
	}
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testAddAndRetrieveUsingStrings() throws InterruptedException {
		
		XRRecord testRecord = 
				new XRRecord()
					.add("STRING", "aca90d7d-e59e-4c91-b17")
					.add("BOOLEAN", false)
					.add("INTEGER", 42)
					.add("FLOAT", 42.42f)
					.add("DOUBLE", 55.55d)
					.add("BIGDECIMAL", new BigDecimal("123.456"))
					.add("TAGS", new String[] {"tag1", "tag2", "tag3"})
					.add("OBJECT", new JsonObject())
					.add("ARRAY", new JsonArray())
					.add("ELEMENT", (JsonElement)new JsonObject())
					.add(null, "canDoNull")
				;
		
		Assertions.assertEquals(11, testRecord.size(), "has 11 values");
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", testRecord.getString("STRING"));
		Assertions.assertEquals(false, testRecord.getBoolean("BOOLEAN"));
		Assertions.assertEquals(42, testRecord.getInteger("INTEGER"));
		Assertions.assertEquals(true, XR.Math.equals(42.42f, testRecord.getFloat("FLOAT"), 0.001f) );
		Assertions.assertEquals(true, XR.Math.equals(55.55d, testRecord.getDouble("DOUBLE"), 0.001d) );

		Assertions.assertEquals("[\"tag1\",\"tag2\",\"tag3\"]", testRecord.getString("TAGS"));
		Assertions.assertEquals("{}", testRecord.getString("OBJECT"));
		Assertions.assertEquals("[]", testRecord.getString("ARRAY"));
		Assertions.assertEquals("{}", testRecord.getString("ELEMENT"));
		
		Assertions.assertEquals("canDoNull", testRecord.getString(null));
	}
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRetrieveWithDefaultValues() throws InterruptedException {
		
		JsonObject object = new JsonObject();
		object.addProperty("abc", "def");
		
		JsonArray array = new JsonArray();
		array.add("a");
		array.add("b");
		array.add("c");
		
		XRRecord testRecord = 
				new XRRecord()
					.add("STRING", "aca90d7d-e59e-4c91-b17")
					.add("BOOLEAN", false)
					.add("INTEGER", 42)
					.add("FLOAT", 42.42f)
					.add("DOUBLE", 55.55d)
					.add("BIGDECIMAL", new BigDecimal("123.456"))
					.add("TAGS", new String[] {"tag1", "tag2", "tag3"})
					.add("OBJECT", object)
					.add("ARRAY", array)
					.add("ELEMENT", (JsonElement)object)
					.add(null, "canDoNull")
				;
		
		Assertions.assertEquals(11, testRecord.size(), "has 11 values");
		
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", testRecord.getString("STRING", "default"));
		Assertions.assertEquals("default"				, testRecord.getString("STRINGX", "default"));
		
		Assertions.assertEquals(false, testRecord.getBoolean("BOOLEAN" , true));
		Assertions.assertEquals(true, testRecord.getBoolean("BOOLEANX", true));
		
		Assertions.assertEquals(42, testRecord.getInteger("INTEGER", 88));
		Assertions.assertEquals(88, testRecord.getInteger("INTEGERX", 88));
		
		Assertions.assertEquals(true, XR.Math.equals(42.42f, testRecord.getFloat("FLOAT", 33.33f), 0.001f) );
		Assertions.assertEquals(true, XR.Math.equals(33.33f, testRecord.getFloat("FLOATX", 33.33f), 0.001f) );
		
		Assertions.assertEquals(true, XR.Math.equals(55.55d, testRecord.getDouble("DOUBLE", 77.77d), 0.001d) );
		Assertions.assertEquals(true, XR.Math.equals(77.77, testRecord.getDouble("DOUBLEX", 77.77d), 0.001d) );

		Assertions.assertEquals("[\"tag1\",\"tag2\",\"tag3\"]", XR.JSON.toJSON( testRecord.getJsonArray("TAGS", new JsonArray()) ) );
		Assertions.assertEquals("[]", XR.JSON.toJSON( testRecord.getJsonArray("TAGSX", new JsonArray()) ) );
		
		Assertions.assertEquals("{\"abc\":\"def\"}", XR.JSON.toJSON( testRecord.getJsonObject("OBJECT", new JsonObject()) ) );
		Assertions.assertEquals("{}", XR.JSON.toJSON( testRecord.getJsonObject("OBJECTX", new JsonObject()) ) );

		Assertions.assertEquals("[\"a\",\"b\",\"c\"]", XR.JSON.toJSON( testRecord.getJsonArray("ARRAY", new JsonArray()) ) );
		Assertions.assertEquals("[]", XR.JSON.toJSON( testRecord.getJsonArray("ARRAYX", new JsonArray()) ) );
		
		Assertions.assertEquals("{\"abc\":\"def\"}", XR.JSON.toJSON( testRecord.getJsonElement("ELEMENT", new JsonObject()) ) );
		Assertions.assertEquals("{}", XR.JSON.toJSON( testRecord.getJsonElement("ELEMENTX", new JsonObject()) ) );
		
		Assertions.assertEquals("canDoNull", testRecord.getString(null, "ABC"));
	}
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testAddAndRetrieveUsingEnums() throws InterruptedException {
		
		XRRecord enumRecord = 
				new XRRecord()
					.add(TestFields.ID, "aca90d7d-e59e-4c91-b17")
					.add(TestFields.INDEX, 0)
					.add(TestFields.LIKES_TIRAMISU, false)
					.add(TestFields.USER, "u.bjoerk")
					.add(TestFields.FIRSTNAME, "Uranus")
					.add(TestFields.LASTNAME, "Bjoerk")
					.add(TestFields.LOCATION, "Aztlan")
					.add(TestFields.EMAIL, "u.bjoerk@aztlan.com")
					.add(TestFields.VALUE, 66)
					.add(TestFields.SEARCH_FOR, "Gianduiotto")
					.add(TestFields.TAGS, new String[] {"tag1", "tag2", "tag3"})
					.add(TestFields.OBJECT, new JsonObject())
					.add(TestFields.ARRAY, new JsonArray())
					.add(null, "canDoNull")
					;
		
		Assertions.assertEquals(14, enumRecord.size(), "has 14 values");
		Assertions.assertEquals(0, enumRecord.getInteger(TestFields.INDEX));
		Assertions.assertEquals("aca90d7d-e59e-4c91-b17", enumRecord.getString(TestFields.ID));
		Assertions.assertEquals("u.bjoerk", enumRecord.getString(TestFields.USER));
		Assertions.assertEquals("Uranus", enumRecord.getString(TestFields.FIRSTNAME));
		Assertions.assertEquals("Bjoerk", enumRecord.getString(TestFields.LASTNAME));
		Assertions.assertEquals("Aztlan", enumRecord.getString(TestFields.LOCATION));
		Assertions.assertEquals("u.bjoerk@aztlan.com", enumRecord.getString(TestFields.EMAIL));
		Assertions.assertEquals(false, enumRecord.getBoolean(TestFields.LIKES_TIRAMISU));
		Assertions.assertEquals(66, enumRecord.getInt(TestFields.VALUE));
		Assertions.assertEquals("Gianduiotto", enumRecord.getString(TestFields.SEARCH_FOR));
		Assertions.assertEquals("[\"tag1\",\"tag2\",\"tag3\"]", enumRecord.getString(TestFields.TAGS));
		Assertions.assertEquals("{}", enumRecord.getString(TestFields.OBJECT));
		Assertions.assertEquals("[]", enumRecord.getString(TestFields.ARRAY));
		Assertions.assertEquals("canDoNull", enumRecord.getString(null));
		
		//-------------------------------------
		// Execute Test
		enumRecord = 
				new XRRecord()
					.add(TestFields.TAGS, "tag1", "tag2", "tag3")
					;
		
		Assertions.assertEquals(1, enumRecord.size(), "has 1 value");
		Assertions.assertEquals("[\"tag1\",\"tag2\",\"tag3\"]", enumRecord.getString(TestFields.TAGS));
	}
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	void testRecordClone() throws InterruptedException {
		
		//-------------------------------------
		// Execute Test
		XRRecord record = new XRRecord();
		record.add("valueA", "A");
		
		XRRecord clone = record.clone();
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
		
		LinkedHashMap<String, XRValue> map = record.toHashMap();
		
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