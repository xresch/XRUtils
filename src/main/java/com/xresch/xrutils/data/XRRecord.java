package com.xresch.xrutils.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xresch.xrutils.database.XRResultSetUtils;


/***************************************************************************
 * A general record class that holds key-value pairs of XRValues.
 * Has some useful convertion methods.
 * 
 * Copyright Owner: Performetriks GmbH, Switzerland
 * License: Eclipse Public License v2.0
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 * 
 ***************************************************************************/
public class XRRecord {
	
	private static final Logger logger = LoggerFactory.getLogger(XRRecord.class);
	
	LinkedHashMap<String, XRValue> keyValues = new LinkedHashMap<>();
	
	public static final String LB = "${";
	public static final String RB = "}";

	public static final int LB_SIZE = LB.length();
	public static final int RB_SIZE = RB.length();
	
	/***********************************************************************
	 * 
	 ***********************************************************************/
	public XRRecord() {
	}
	
	/***********************************************************************
	 * Creates a new record based on the fields of a JsonObject.
	 ***********************************************************************/
	public XRRecord(JsonObject object) {
		
		for(Entry<String, JsonElement> entry : object.entrySet()) {
			String name = entry.getKey();
			XRValue value = XRValue.newFromJsonElement(object.get(name));
			
			this.add(name, value);
			
		}
		
	}
	
	/***********************************************************************
	 * Creates a new record based on the key-value pairs of a Map.
	 ***********************************************************************/
	public XRRecord(Map<String, String> map, boolean convertTypes) {
		
		for(Entry<String, String> entry : map.entrySet()) {
			String name = entry.getKey();
			
			if(!convertTypes) {
				XRValue value = XRValue.newString(map.get(name));
				this.add(name, value);
			}else {
				XRValue value = XRValue.newFromString(map.get(name));
				this.add(name, value);
			}
			
		}
		
	}
	
	/***********************************************************************
	 * Creates a new record using the values in the map.
	 * 
	 ***********************************************************************/
	public XRRecord(Map<String, XRValue> keyValues) {
		
		this.keyValues.putAll(keyValues);
	}
	
	/***********************************************************************
	 * Create a clone of the data record.
	 * @return clone of this instance
	 ***********************************************************************/
	public XRRecord clone() {
		return new XRRecord(keyValues);
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, XRValue value) {
		keyValues.put(key, value);
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, String value) {
		keyValues.put(key, XRValue.newString(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced. Automatically detects the data type 
	 * from the string. Data types that will be detected are null, number, 
	 * boolean, string, JsonObject and JsonArray.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord addFromString(String key, String value) {
		keyValues.put(key, XRValue.newFromString(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, JsonObject value) {
		keyValues.put(key, XRValue.newJson(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, JsonArray value) {
		keyValues.put(key, XRValue.newJson(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, ArrayList<String> value) {
		keyValues.put(key, XRValue.newFromStringArray(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, boolean value) {
		keyValues.put(key, XRValue.newBoolean(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, int value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, long value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, float value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, double value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, short value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, Number value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds a value for a key. If a value has already been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord add(String key, BigDecimal value) {
		keyValues.put(key, XRValue.newNumber(value));
		return this;
	}
	
	/***********************************************************************
	 * Adds all the key-value pairs of the record to this class.
	 * 
	 * @param record
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord addAll(XRRecord record) {
		keyValues.putAll(record.keyValues);
		return this;
	}
	
	/***********************************************************************
	 * Adds all the key-value pairs of the map to this class.
	 * 
	 * @param map
	 * 
	 * @return instance for chaining
	 ***********************************************************************/
	public XRRecord addAll(Map<String, XRValue> map) {
		keyValues.putAll(map);
		return this;
	}
	
	/***********************************************************************
	 * Overloaded method to simplify working with enumerations.
	 * This method will call the Object.toString() method and calls the 
	 * this.containsKey(String) method.
	 * 
	 * @param key
	 * @return true if the record contains a value for the given key
	 ***********************************************************************/
	public boolean containsKey(Object key) {
		if(key == null) { return containsKey(key); }
		return containsKey(key.toString());
	}
	
	/***********************************************************************
	 * 
	 * @param key
	 * @return true if the record contains a value for the given key
	 ***********************************************************************/
	public boolean containsKey(String key) {
		return keyValues.containsKey(key);
	}
	
	/***********************************************************************
	 * Removes a value for a key. If a value has alread been assigned 
	 * for that key it will be replaced.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return the previous value associated with key, or null if there was 
	 * no mapping for key.(A null return can also indicate that the map 
	 * previously associated null with key.)
	 ***********************************************************************/
	public XRValue remove(String key) {
		return keyValues.remove(key);
	}
	
	/***********************************************************************
	 * Overloaded method to simplify working with enumerations.
	 * This method will call the Object.toString() method and calls the 
	 * this.get(String) method.
	 * 
	 * @param key
	 * 
	 * @return the value to which the specified key is mapped, or a XRValue
	 * that is null if not available
	 ***********************************************************************/
	public XRValue get(Object key) {
		return get(key.toString());
	}
	
	/***********************************************************************
	 * Get the value for a specified key.
	 * 
	 * @param key
	 * 
	 * @return the value to which the specified key is mapped, or a XRValue
	 * that is null if not available
	 ***********************************************************************/
	public XRValue get(String key) {
		if(!keyValues.containsKey(key)) {
			return XRValue.newNull();
		}
		return keyValues.get(key);
	}
	
	/***********************************************************************
	 * Get the value for a specified key as a string value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return String value or null
	 ***********************************************************************/
	public String getString(Object key) {
		if(key == null) { return get(key).getAsString(); }
		return get(key.toString()).getAsString();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an integer value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Integer value or null
	 ***********************************************************************/
	public Integer getInteger(Object key) {
		if(key == null) { return get(key).getAsInteger(); }
		return get(key.toString()).getAsInteger();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an Long value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Long value or null
	 ***********************************************************************/
	public Long getLong(Object key) {
		if(key == null) { return get(key).getAsLong(); }
		return get(key.toString()).getAsLong();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an Double value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Double value or null
	 ***********************************************************************/
	public Double getDouble(Object key) {
		if(key == null) { return get(key).getAsDouble(); }
		return get(key.toString()).getAsDouble();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an Float value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Float value or null
	 ***********************************************************************/
	public Float getFloat(Object key) {
		if(key == null) { return get(key).getAsFloat(); }
		return get(key.toString()).getAsFloat();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an Number value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Number value or null
	 ***********************************************************************/
	public Number getNumber(Object key) {
		if(key == null) { return get(key).getAsNumber(); }
		return get(key.toString()).getAsNumber();
	}
	
	
	/***********************************************************************
	 * Get the value for a specified key as an BigDecimal value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return BigDecimal value or null
	 ***********************************************************************/
	public BigDecimal getBigDecimal(Object key) {
		if(key == null) { return get(key).getAsBigDecimal(); }
		return get(key.toString()).getAsBigDecimal();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as an boolean value.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return Boolean value 
	 ***********************************************************************/
	public Boolean getBoolean(Object key) {
		if(key == null) { return get(key).getAsBoolean(); }
		return get(key.toString()).getAsBoolean();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as a JsonArray.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return JsonArray value 
	 ***********************************************************************/
	public JsonArray getJsonArray(Object key) {
		if(key == null) { return get(key).getAsJsonArray(); }
		return get(key.toString()).getAsJsonArray();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as a JsonObject.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return JsonObject value 
	 ***********************************************************************/
	public JsonObject getJsonObject(Object key) {
		if(key == null) { return get(key).getAsJsonObject(); }
		return get(key.toString()).getAsJsonObject();
	}
	
	/***********************************************************************
	 * Get the value for a specified key as a JsonElement.
	 * 
	 * @param key name of value, key.toString() will be used to retrieve the value
	 * @return JsonElement value 
	 ***********************************************************************/
	public JsonElement getJsonElement(Object key) {
		if(key == null) { return get(key).getAsJsonElement(); }
		return get(key.toString()).getAsJsonElement();
	}
	
	/***********************************************************************
	 * Inserts the values of this data record into a string by replacing 
	 * placeholders. The placeholders syntax is "${fieldname}".
	 * This method is faster than using multiple String.replace() calls as  
	 * it only parses the string once from left to right.	 * 
	 * 
	 * @param insertHere
	 * @return string with replaced placeholders
	 ***********************************************************************/
	public String insert(String insertHere) {
		

		if(keyValues == null || keyValues.isEmpty()) {
			return insertHere;
		}

		StringBuilder sb = new StringBuilder(insertHere);
		
		int fromIndex = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		int length = sb.length();

		while (fromIndex < length && leftIndex < length) {

			leftIndex = sb.indexOf(LB, fromIndex);

			if (leftIndex != -1) {
				rightIndex = sb.indexOf(RB, leftIndex);

				if (rightIndex != -1 && (leftIndex + LB_SIZE) < rightIndex) {

					String propertyName = sb.substring(leftIndex + LB_SIZE, rightIndex);
					if(keyValues != null 
					&& keyValues.containsKey(propertyName)) {
						sb.replace(leftIndex, rightIndex + RB_SIZE,
								keyValues.get(propertyName).getAsString());
					}
					// start again from leftIndex
					fromIndex = leftIndex + 1;

				} else {
					logger.trace("Parameter was missing the right bound");

					break;
				}

			} else {
				// no more stuff found to replace
				break;
			}
		}

		return sb.toString();

	}
	
	
	/***********************************************************************
	 * Returns the number of key-value mappings in this data record.
	 * 
	 * @return size
	 ***********************************************************************/
	public int size() {
		return keyValues.size();
	}
	
	/******************************************************************************************************
	 * Converts the result set to a list of XRRecords.
	 * Converts data types on a best effort basis. Numbers will be converted as numbers, date and time will 
	 * be converted to epoch millis, everything else will be converted as a string. If the string can be 
	 * parsed to a JsonObject or JsonArray it will do so.
	 * 
	 * This method closes the result set.
	 * 
	 * @param result to convert
	 * @return true if successful, false otherwise
	 ******************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<XRRecord> resultSetToList(ResultSet result) {
		
		ResultSetMetaData metadata;
		boolean success = true;
		ArrayList<XRRecord> records = new ArrayList<>();
		
		try {
			
			if(result == null) {
				return records;
			}
			
			//--------------------------------------
			// Check has results

			metadata = result.getMetaData();

			while(result.next()) {
				
				XRRecord record = resultSetRowToRecord(result, metadata);
				
				records.add(record);
			}
		
		} catch (SQLException e) {
			success = false;
			logger.error("SQL Exception occured while trying to map ResultSet to fields. Check Cursor position.", e);
		}finally {
			XRResultSetUtils.close(result);
		}
		
		return records;
	}
	
	/******************************************************************************************************
	 * Converts the result set to a map of XRRecords.
	 * Converts data types on a best effort basis. Numbers will be converted as numbers, date and time will 
	 * be converted to epoch millis, everything else will be converted as a string. If the string can be 
	 * parsed to a JsonObject or JsonArray it will do so.
	 * 
	 * This method closes the result set.
	 * 
	 * @param result to convert
	 * @param keyColumnName the column that should be used as the maps key
	 * 
	 ******************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, XRRecord> resultSetToKeyValueMap(ResultSet result, String keyColumnName) {
		
		ResultSetMetaData metadata;

		LinkedHashMap<String, XRRecord> records = new LinkedHashMap<>();
		
		try {
			
			if(result == null) {
				return records;
			}
			
			//--------------------------------------
			// Check has results

			metadata = result.getMetaData();

			while(result.next()) {
				
				XRRecord record = resultSetRowToRecord(result, metadata);
				
				records.put(record.getString(keyColumnName), record);
			}
		
		} catch (SQLException e) {
			logger.error("SQL Exception occured while trying to map ResultSet to XRRecord.", e);
		}finally {
			XRResultSetUtils.close(result);
		}
		
		return records;
	}
	
	/******************************************************************************************************
	 * Converts the result set to a map of XRRecords.
	 * Converts data types on a best effort basis. Numbers will be converted as numbers, date and time will 
	 * be converted to epoch millis, everything else will be converted as a string. If the string can be 
	 * parsed to a JsonObject or JsonArray it will do so.
	 * 
	 * This method closes the result set.
	 * 
	 * @param result to convert
	 * @param keyColumnName the column that contains the ID should be used as the maps key
	 * 
	 ******************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<Integer, XRRecord> resultSetToIdValueMap(ResultSet result, String idColumnName) {
		
		ResultSetMetaData metadata;

		LinkedHashMap<Integer, XRRecord> records = new LinkedHashMap<>();
		
		try {
			
			if(result == null) {
				return records;
			}
			
			//--------------------------------------
			// Check has results

			metadata = result.getMetaData();

			while(result.next()) {
				
				XRRecord record = resultSetRowToRecord(result, metadata);
				
				records.put(record.getInteger(idColumnName), record);
			}
		
		} catch (SQLException e) {
			logger.error("SQL Exception occured while trying to map ResultSet to XRRecord.", e);
		}finally {
			XRResultSetUtils.close(result);
		}
		
		return records;
	}

	
	/******************************************************************************************************
	 * Takes a results set as input that is being read. Cursor must be on the row that should be converted
	 * to a XRRecord.
	 * 
	 * Converts data types on a best effort basis. 
	 * <ul>
	 * 	<li><b>Numbers:</b> will be converted as numbers.</li>
	 * 	<li><b>Boolean:</b> will be converted as boolean.</li>
	 * 	<li><b>Date/Time:</b> Date and time will be converted to epoch millis.</li>
	 * 	<li><b>Everything else:</b> will be converted as a string. If the string can be 
	 *        parsed to a JsonObject or JsonArray it will do so.</li>
	 * 
	 * @param result set to process
	 * @return metadata of the result set
	 ******************************************************************************************************/
	public static XRRecord resultSetRowToRecord(ResultSet result, ResultSetMetaData metadata)
			throws SQLException {
		
		XRRecord record = new XRRecord();

		for(int i=1; i <= metadata.getColumnCount(); i++) {
			
			int type = metadata.getColumnType(i);

			String propertyName = metadata.getColumnLabel(i);
			
			switch(type) {
			
				case Types.SMALLINT:
				case Types.INTEGER:
					record.add(propertyName, result.getInt(i));
					break;
				
				case Types.BIGINT:
					record.add(propertyName, result.getLong(i));
					break;
					
				case Types.DECIMAL:
					record.add(propertyName, result.getBigDecimal(i));
					break;	
					
				case Types.DOUBLE:
					record.add(propertyName, result.getDouble(i));
					break;	
				
				case Types.FLOAT:
					record.add(propertyName, result.getFloat(i));
					break;	
					
				case Types.BOOLEAN:
					record.add(propertyName, result.getBoolean(i));
					break;	
					
				case Types.DATE:
				case Types.TIME:
				case Types.TIME_WITH_TIMEZONE:
				case Types.TIMESTAMP:
				case Types.TIMESTAMP_WITH_TIMEZONE:
					record.add(propertyName, result.getTimestamp(i).getTime());
					break;	
				
				default: record.addFromString(propertyName, result.getString(i));
			}
			
		}
		return record;
	}
	
	/***********************************************************************
	 * Returns the entry set of key-value mappings in this data record.
	 * 
	 * @return entrySet
	 ***********************************************************************/
	public Set<Entry<String, XRValue>> entrySet() {
		return keyValues.entrySet();
	}
	
	/***********************************************************************
	 * Returns a clone of the HashMap that holds this record's key-value pairs.
	 * 
	 * @return LinkedHashMap<String, XRValue>
	 ***********************************************************************/
	public LinkedHashMap<String, XRValue> toHashMap() {
		return new LinkedHashMap<>(keyValues);
	}
	
	/***********************************************************************
	 * Converts the key-value pairs of this record to a HashMap that holds
	 * strings as values.
	 * 
	 * @return LinkedHashMap<String, String>
	 ***********************************************************************/
	public LinkedHashMap<String, String> toHashMapStrings() {
		LinkedHashMap<String, String> stringMap = new LinkedHashMap<>();
		
		for(Entry<String, XRValue> entry : keyValues.entrySet()) {
			stringMap.put(entry.getKey(), entry.getValue().getAsString());
		}
		
		return stringMap;
	}
	/***********************************************************************
	 * Converts the key-value pairs of this record to a JsonObject.
	 * 
	 * @return JsonObject
	 ***********************************************************************/
	public JsonObject toJsonObject() {
		JsonObject object = new JsonObject();
		
		for(Entry<String, XRValue> entry : keyValues.entrySet()) {
			object.add(entry.getKey(), entry.getValue().getAsJsonElement());
		}
		
		return object;
	}
	
	/***********************************************************************
	 * Returns the record as an array-like string oneliner.
	 * Useful for debugging.
	 * @return string
	 ***********************************************************************/
	public  String toString() {

		if(keyValues.size() == 0) { return ""; } 
		
		return new StringBuilder("[")
				.append( Joiner.on("\",    ").withKeyValueSeparator("=\"").join(keyValues) )
				.append("\"]")
				.toString()
				;
		
	}
	
	/***********************************************************************
	 * Returns the record as an array-like string with pretty print.
	 * Useful for debugging.
	 * 
	 * @return string
	 ***********************************************************************/
	public  String toStringPretty() {

		if(keyValues.size() == 0) { return ""; } 
		
		return new StringBuilder("[\r\n  ")
				.append( Joiner.on("\"\r\n, ").withKeyValueSeparator("=\"").join(keyValues) )
				.append("\"\r\n]")
				.toString()
				;
		
	}
	
	
	

}
