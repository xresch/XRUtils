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
import com.xresch.xrutils.data.Unrecord;


/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 2022
 * @license MIT-License
 **************************************************************************************************************/
public class XRResultSetUtils {
	
	static Logger logger = LoggerFactory.getLogger(XRResultSetUtils.class.getName());
	
	private static final XRResultSetUtils INSTANCE = new XRResultSetUtils();
	
		
	/********************************************************************************************
	 * Closes the result set and it's connection.
	 * @param resultSet which should be closed.
	 ********************************************************************************************/
	public static void close(ResultSet resultSet){

		try {
			if(resultSet != null 
			&& resultSet.getStatement() != null 
			&& !resultSet.getStatement().isClosed()) {
				
				if(!resultSet.getStatement().getConnection().isClosed()) {
					resultSet.getStatement().getConnection().close();
					resultSet.close();
				}
			}
		} catch (SQLException e) {
			logger.error("Exception occured while closing ResultSet. ", e);
		}
	}
	
	/****************************************************************
	 * Executes the query and returns the first value of the first
	 * column as integer. Closes the result set.
	 * 
	 * Useful for getting counts, averages, maximum etc...
	 * @return integer value, 0 if no rows are selected, null in case of errors
	 ****************************************************************/
	public static Integer getFirstAsInteger(ResultSet result) {
		
		try {
			if(result.next()) {
				
				return result.getInt(1);
				
			}else {
				return 0;
			}
		}catch (SQLException e) {
			logger
			.error("Error reading integer from database.", e);
			
		}finally {
			close(result);
		}
		
		return null;
		
	}
	
	/***************************************************************************
	 * Converts a ResultSet into an array list of Unrecords.
	 * This method closes the result set.
	 * 
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static ArrayList<Unrecord> toUnrecordList(ResultSet result) {
		
		return Unrecord.resultSetToList(result);
		
	}
	
	
	/***************************************************************************
	 * Converts a ResultSet into a map of Keys and Unrecords.
	 * This method closes the result set.
	 * 
	 * @param result set
	 * @param keyColumnName name of the column that should be used as the key
	 * @return map of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static LinkedHashMap<String, Unrecord> toKeyUnrecordMap(ResultSet result, String keyColumnName) {
		
		return Unrecord.resultSetToKeyValueMap(result, keyColumnName);

	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map of Keys and Unrecords.
	 * This method closes the result set.
	 * 
	 * @param result set
	 * @param idColumnName name of the column that should be used as the id
	 * @return map of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static LinkedHashMap<Integer, Unrecord> toIdUnrecordMap(ResultSet result, String idColumnName) {
		
		return Unrecord.resultSetToIdValueMap(result, idColumnName);

	}
	
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * This method closes the result set.
	 * 
	 * @return map of objects, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static HashMap<Object, Object> toKeyValueMap(ResultSet result, String keyColumnName, String valueColumnName) {
		
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
			logger.error("Error reading object from database.", e);
			
		}finally {
			close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * This method closes the result set.
	 * 
	 * @return map of objects, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static LinkedHashMap<String, String> toKeyValueMapString(ResultSet result, String keyColumnName, String valueColumnName) {
		
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
			close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * This method closes the results set.
	 * 
	 * @param result set
	 * @param idColumnName the column containing the IDs 
	 * @param valueColumnName the column containing the values
	 * @return map of objects, empty if results set is null or an error occurs.
	 * 
	 ***************************************************************************/
	public static LinkedHashMap<Integer, Object> toIDValueMap(ResultSet result, Object idColumnName, Object valueColumnName) {
		
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
			close(result);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a list of maps with key/values.
	 * This method closes the results set.
	 * 
	 * @return list of maps holding key(column name) with values
	 ***************************************************************************/
	public static ArrayList<LinkedHashMap<String, Object>> toListOfKeyValueMaps(ResultSet result) {
		
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
			close(result);
		}
		
		return resultList;
	}
	
	/***************************************************************
	 * Execute the Query and gets the values of the specified 
	 * column as a string array.
	 * This method closes the results set.
	 * 
	 * @param result
	 * @param columnName
	 * @return string array
	 ***************************************************************/
	public static String[] toStringArray(ResultSet result, String columnName) {
		return toStringArrayList(result, columnName).toArray(new String[] {});
	}
	
	/***************************************************************************
	 * Execute the Query and gets the values of the specified 
	 * column as an ArrayList of strings.
	 * This method closes the results set.
	 * 
	 * @param result
	 * @param columnName
	 * @return ArrayList of strings
	 ***************************************************************************/
	public static ArrayList<String> toStringArrayList(ResultSet result, String columnName) {
		
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
			close(result);
		}
			
		return stringArray;
	}
	
	
	/***************************************************************************
	 * Execute the Query and gets the values of the specified 
	 * column as an ArrayList of integers.
	 * This method closes the results set.
	 * 
	 * @param result
	 * @param columnName
	 * @return ArrayList of integers
	 ***************************************************************************/
	public static ArrayList<Integer> toIntegerArrayList(ResultSet result, String columnName) {
		
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
			close(result);
		}
			
		return stringArray;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * This method closes the result set.
	 * 
	 * @param result set
	 * @param idColumnName the column containing the IDs 
	 * @param valueColumnName the column containing the values
	 * @return map of objects, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static LinkedHashMap<Object, Object> toLinkedHashMap(ResultSet result, Object keyColumnName, Object valueColumnName) {
		
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
			close(result);
		}
					
		return resultMap;
	}
	
	/********************************************************************************************
	 * Returns a jsonString with an array containing a json object for each row.
	 * Returns an empty array in case of error.
	 * 
	 ********************************************************************************************/
	public static String toJSON(ResultSet resultSet) {
		return XR.JSON.toJSON(resultSet);
	}
	
	/********************************************************************************************
	 * Returns a ResultSetAsJsonReader to convert SQL records to json objects one by one. 
	 * ResultSetAsJsonReader will close the result set when fully read to reader.next() == null.
	 * @param result set to read
	 * @return ResultSetAsJsonReader
	 ********************************************************************************************/
	public static ResultSetAsJsonReader toJSONReader(ResultSet result) {
		return new ResultSetAsJsonReader(result);
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a JsonArray.
	 * This method closes the result set.
	 * 
	 * @return list of maps holding key(column name) with values
	 ***************************************************************************/
	public static JsonArray toJSONArray(ResultSet result) {
		
		JsonArray resultArray = new JsonArray();
		if(result == null) {
			return resultArray;
		}
		ResultSetAsJsonReader reader = XRResultSetUtils.toJSONReader(result);
		
		JsonObject object;
		while( (object = reader.next()) != null) {
			resultArray.add(object);
		}

		return resultArray;
		
	}
	
	

	/********************************************************************************************
	 * Converts the ResultSet into a CSV string.
	 * This method closes the result set.
	 * 
	 ********************************************************************************************/
	public static String toCSV(ResultSet resultSet, String delimiter) {
		StringBuilder csv = new StringBuilder();
		
		try {
			
			if(resultSet == null) {
				return "";
			}
						
			//--------------------------------------
			// Iterate results
			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();
			
			for(int i = 1 ; i <= columnCount; i++) {
				csv.append("\"")
				   .append(metadata.getColumnLabel(i))
				   .append("\"")
				   .append(delimiter);
			}
			csv.deleteCharAt(csv.length()-1); //remove last comma
			csv.append("\r\n");
			while(resultSet.next()) {
				for(int i = 1 ; i <= columnCount; i++) {
					
					String value = resultSet.getString(i);
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
		}finally {
			close(resultSet);
		}
	
		return csv.toString();
	}

	/********************************************************************************************
	 * Returns an XML string with an array containing a record for each row.
	 * This method closes the result set.
	 * 
	 ********************************************************************************************/
	public static String toXML(ResultSet resultSet) {
		StringBuilder xml = new StringBuilder();
		
		try {
			
			if(resultSet == null) {
				return "<data></data>";
			}
			
			//--------------------------------------
			// Iterate results
			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();
	
			xml.append("<data>\n");
			while(resultSet.next()) {
				xml.append("\t<record>\n");
				for(int i = 1 ; i <= columnCount; i++) {
					String column = metadata.getColumnLabel(i);
					xml.append("\t\t<").append(column).append(">");
					
					String value = resultSet.getString(i);
					xml.append(value);
					xml.append("</").append(column).append(">\n");
				}
				xml.append("\t</record>\n");
			}
			xml.append("</data>");
			
		} catch (SQLException e) {
				logger
					.error("Exception occured while converting ResultSet to XML.", e);
				
				return "<data></data>";
		}finally {
			close(resultSet);
		}
	
		return xml.toString();
	}
		
}
