package com.xresch.xrutils.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;
import com.xresch.xrutils.data.XRRecord;

/**************************************************************************************************************
 * Utility class used to convert a result set.
 * 
 * @author Reto Scheiwiller, (c) Copyright 2025
 * @license EPL-License
 **************************************************************************************************************/
public class XRResultSetConverter {
	
	private static Logger logger = LoggerFactory.getLogger(XRResultSetConverter.class.getName());

	private XRDBInterface db = null;
	private ResultSet result = null;
	
	/****************************************************************
	 * 
	 ****************************************************************/
	public XRResultSetConverter(XRDBInterface db, ResultSet result) {
		this.db = db;
		this.result = result;
		
	}
	
	/****************************************************************
	 * Executes the query and returns the first value of the first
	 * column as an integer.
	 * Useful for getting counts, averages, maximum etc...
	 * @return integer value, 0 if no rows are selected, null in case of errors
	 ****************************************************************/
	public Integer getFirstAsCount() {
		
		try {
			if(result.next()) {
				return result.getInt(1);
			}else {
				return 0;
			}
		}catch (SQLException e) {
			logger.error("Error reading integer from database.", e);
			
		}finally {
			db.close(result);
		}
		
		return null;
		
	}
	
	/****************************************************************
	 * Executes the query and returns the first value of the first
	 * column as integer.
	 * Useful for getting counts, averages, maximum etc...
	 * @return integer value, null if no rows are selected or in case of errors
	 ****************************************************************/
	public Integer getFirstAsInteger() {
		
		try {
			if(result.next()) {
				return result.getInt(1);
			}else {
				return null;
			}
		}catch (SQLException e) {
			logger.error("Error reading integer from database.", e);
			
		}finally {
			db.close(result);
		}
		
		return null;
		
	}
	
	/****************************************************************
	 * Executes the query and returns the first value of the first
	 * column as long.
	 * 
	 * @return long value, null if no rows are selected or in case of errors
	 ****************************************************************/
	public Long getFirstAsLong() {
		
		try {
			if(result.next()) {
				return result.getLong(1);
			}else {
				return null;
			}
		}catch (SQLException e) {
			logger.error("Error reading integer from database.", e);
			
		}finally {
			db.close(result);
		}
		
		return null;
		
	}
	
	/****************************************************************
	 * Executes the query and returns the first result as XRRecord.
	 * 
	 * @return XRRecord or null if no rows are selected or in case of errors
	 ****************************************************************/
	public XRRecord getFirstAsXRRecord() {
		
		XRResultSetAsXRRecordReader reader = toXRRecordReader();
		
		return reader.next();
		
	}

	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return map of objects, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public HashMap<Object, Object> toKeyValueMap(String keyColumnName, String valueColumnName) {
		
		HashMap<Object, Object> keyValueMap = new HashMap<>();
		
		if(result == null) {
			return keyValueMap;
		}
		
		try {
			while(result.next()) {
				Object key = result.getObject(keyColumnName);
				Object value = result.getObject(valueColumnName);
				keyValueMap.put(key, value);
			}
		} catch (SQLException e) {
			logger
				.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return map of objects, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public LinkedHashMap<String, String> toKeyValueMapString(String keyColumnName, String valueColumnName) {
		
		LinkedHashMap<String, String> keyValueMap = new LinkedHashMap<>();
		
		if(result == null) {
			return keyValueMap;
		}
		
		try {
			while(result.next()) {
				String key = result.getString(keyColumnName);
				String value = result.getString(valueColumnName);
				keyValueMap.put(key, value);
			}
		} catch (SQLException e) {
			logger
				.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public LinkedHashMap<Integer, Object> toIDValueMap(Object idColumnName, Object valueColumnName) {
		
		LinkedHashMap<Integer, Object> keyValueMap = new LinkedHashMap<>();
		
		if(result == null) {
			return keyValueMap;
		}
		
		try {
			while(result.next()) {
				Integer key = result.getInt(idColumnName.toString());
				Object value = result.getObject(valueColumnName.toString());
				keyValueMap.put(key, value);
			}
		} catch (SQLException e) {
			logger
				.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a list of maps with key/values.
	 * @return list of maps holding key(column name) with values
	 ***************************************************************************/
	public ArrayList<LinkedHashMap<String, Object>> toListOfKeyValueMaps() {
		
		ArrayList<LinkedHashMap<String, Object>> resultList =  new ArrayList<>();
		
		if(result == null) {
			return resultList;
		}
		
		try {
			ResultSetMetaData meta = result.getMetaData();
			int columnCount = meta.getColumnCount();
			
			while(result.next()) {
				LinkedHashMap<String, Object> keyValueMap = new LinkedHashMap<>();
				
				for(int i = 1; i <= columnCount; i++) {
					String key = meta.getColumnLabel(i);
					Object value = result.getObject(key);
					keyValueMap.put(key, value);
				}
				resultList.add(keyValueMap);
				
			}
		} catch (SQLException e) {
			logger
			.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
		
		return resultList;
	}
	
	/***************************************************************
	 * Execute the Query and gets the result as a string array.
	 ***************************************************************/
	public String[] toStringArray(String columnName) {
		return toStringArrayList(columnName).toArray(new String[] {});
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public ArrayList<String> toStringArrayList(String columnName) {
		
		ArrayList<String> stringArray = new ArrayList<String>();
		
		if(result == null) {
			return stringArray;
		}
		
		try {
			while(result.next()) {
				Object value = result.getObject(columnName.toString());
				stringArray.add(value.toString());
			}
		} catch (SQLException e) {
			logger
			.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
			
		return stringArray;
	}
	
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public ArrayList<Integer> toIntegerArrayList(String columnName) {
		
		ArrayList<Integer> stringArray = new ArrayList<Integer>();
		
		if(result == null) {
			return stringArray;
		}
		
		try {
			while(result.next()) {
				int value = result.getInt(columnName.toString());
				stringArray.add(value);
			}
		} catch (SQLException e) {
			logger
			.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
			
		return stringArray;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public LinkedHashMap<Object, Object> toLinkedHashMap(Object keyColumnName, Object valueColumnName) {
		
		LinkedHashMap<Object, Object>  resultMap = new LinkedHashMap<Object, Object>();
		
		if(result == null) {
			return resultMap;
		}
		
		try {
			while(result.next()) {
				Object key = result.getObject(keyColumnName.toString());
				Object value = result.getObject(valueColumnName.toString());
				resultMap.put(key, value);
			}
		} catch (SQLException e) {
			logger
			.error("Error reading object from database.", e);
			
		}finally {
			db.close(result);
		}
					
		return resultMap;
	}
	
	
	/********************************************************************************************
	 * Returns a ResultSetAsJsonReader to convert SQL records to json objects one by one. 
	 * 
	 ********************************************************************************************/
	public XRResultSetAsJsonReader toJSONReader() {
		return new XRResultSetAsJsonReader(result);
	}
	
	/********************************************************************************************
	 * Returns a ResultSetAsJsonReader to convert SQL records to json objects one by one. 
	 * 
	 ********************************************************************************************/
	public XRResultSetAsXRRecordReader toXRRecordReader() {
		return new XRResultSetAsXRRecordReader(result);
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a JsonArray.
	 * @return list of maps holding key(column name) with values
	 ***************************************************************************/
	public JsonArray toJSONArray() {
		
		JsonArray resultArray = new JsonArray();
		if(result == null) {
			return resultArray;
		}
		XRResultSetAsJsonReader reader = toJSONReader();
		
		JsonObject object;
		while( (object = reader.next()) != null) {
			resultArray.add(object);
		}

		return resultArray;
		
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a JsonArray.
	 * @return list of maps holding key(column name) with values
	 ***************************************************************************/
	public ArrayList<XRRecord> toXRRecordList() {
		
		ArrayList<XRRecord> result = new ArrayList<XRRecord>();
				
		XRResultSetAsXRRecordReader reader = toXRRecordReader();
		
		XRRecord record;
		while( (record = reader.next()) != null) {
			result.add(record);
		}

		return result;
		
	}
	
	

	/********************************************************************************************
	 * Converts the ResultSet into a CSV string.
	 * 
	 ********************************************************************************************/
	public String toCSV(String delimiter) {
		StringBuilder csv = new StringBuilder();
		
		try {
			
			if(result == null) {
				return "";
			}
			
			//--------------------------------------
			// Check has results
			/* Excluded as MSSQL might throw errors			
			resultSet.beforeFirst();
			if(!resultSet.isBeforeFirst()) {
				return "";
			} */
			
			//--------------------------------------
			// Iterate results
			ResultSetMetaData metadata = result.getMetaData();
			int columnCount = metadata.getColumnCount();
			
			for(int i = 1 ; i <= columnCount; i++) {
				csv.append("\"")
				   .append(metadata.getColumnLabel(i))
				   .append("\"")
				   .append(delimiter);
			}
			csv.deleteCharAt(csv.length()-1); //remove last comma
			csv.append("\r\n");
			while(result.next()) {
				for(int i = 1 ; i <= columnCount; i++) {
					
					String value = result.getString(i);
					csv.append("\"")
					   .append(XR.JSON.escapeString(value))
					   .append("\"")
					   .append(delimiter);
				}
				csv.deleteCharAt(csv.length()-1); //remove last comma
				csv.append("\r\n");
			}
			csv.deleteCharAt(csv.length()-1); //remove last comma
	
			
		} catch (SQLException e) {
				logger.error("Exception occured while converting ResultSet to CSV.", e);
				
				return "";
		}
	
		return csv.toString();
	}

	/********************************************************************************************
	 * Returns an XML string with an array containing a record for each row.
	 * 
	 ********************************************************************************************/
	public String toXML() {
		StringBuilder xml = new StringBuilder();
		
		try {
			
			if(result == null) {
				return "<data></data>";
			}
			//--------------------------------------
			// Check has results
			/* Excluded as MSSQL might throw errors			
			resultSet.beforeFirst();
			if(!resultSet.isBeforeFirst()) {
				return "<data></data>";			
			}*/
			
			//--------------------------------------
			// Iterate results
			ResultSetMetaData metadata = result.getMetaData();
			int columnCount = metadata.getColumnCount();
	
			xml.append("<data>\n");
			while(result.next()) {
				xml.append("\t<record>\n");
				for(int i = 1 ; i <= columnCount; i++) {
					String column = metadata.getColumnLabel(i);
					xml.append("\t\t<").append(column).append(">");
					
					String value = result.getString(i);
					xml.append(value);
					xml.append("</").append(column).append(">\n");
				}
				xml.append("\t</record>\n");
			}
			xml.append("</data>");
			
		} catch (SQLException e) {
				logger.error("Exception occured while converting ResultSet to XML.", e);
				
				return "<data></data>";
		}
	
		return xml.toString();
	}
		
}
