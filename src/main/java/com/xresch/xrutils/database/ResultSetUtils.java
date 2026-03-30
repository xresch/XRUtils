package com.xresch.xrutils.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xresch.xrutils.data.Unrecord;


/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 2022
 * @license MIT-License
 **************************************************************************************************************/
public class ResultSetUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ResultSetUtils.class.getName());
	
	private static final ResultSetUtils INSTANCE = new ResultSetUtils();
	
		
	/********************************************************************************************
	 * 
	 * @param resultSet which should be closed.
	 * @param doClose true if it should be closed, false if this method should do nothing
	 ********************************************************************************************/
	public static void close(ResultSet resultSet, boolean doClose){
		
		if( ! doClose) { return; }
		
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
	 * column as integer.
	 * Useful for getting counts, averages, maximum etc...
	 * @return integer value, 0 if no rows are selected, null in case of errors
	 ****************************************************************/
	public static Integer getFirstAsInteger(ResultSet result, boolean doClose) {
		
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
			close(result, doClose);
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
	 * @return list of object, empty if results set is null or an error occurs.
	 ***************************************************************************/
	public static LinkedHashMap<String, Unrecord> toKeyUnrecordMap(ResultSet result, String keyColumnName) {
		
		return Unrecord.resultSetToKeyValueMap(result, keyColumnName);

	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map of Keys and Unrecords.
	 * This method closes the result set.
	 * 
	 * @return list of object, empty if results set is null or an error occurs.
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
			close(result, true);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * This method closes the result set.
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
			close(result, true);
		}
			
		return keyValueMap;
	}
	
	/***************************************************************************
	 * Converts a ResultSet into a map with the key/values of the selected columns.
	 * @return list of object, empty if results set is null or an error occurs.
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
			close(result, true);
		}
			
		return keyValueMap;
	}
//	
//	/***************************************************************************
//	 * Converts a ResultSet into a list of maps with key/values.
//	 * @return list of maps holding key(column name) with values
//	 ***************************************************************************/
//	public static ArrayList<LinkedHashMap<String, Object>> toListOfKeyValueMaps(ResultSet result) {
//		
//		ArrayList<LinkedHashMap<String, Object>> resultList =  new ArrayList<>();
//		
//		if(result == null) {
//			return resultList;
//		}
//		
//		try {
//			ResultSetMetaData meta = result.getMetaData();
//			int columnCount = meta.getColumnCount();
//			
//			while(result.next()) {
//				LinkedHashMap<String, Object> keyValueMap = new LinkedHashMap<>();
//				
//				for(int i = 1; i <= columnCount; i++) {
//					String key = meta.getColumnLabel(i);
//					Object value = result.getObject(key);
//					keyValueMap.put(key, value);
//				}
//				resultList.add(keyValueMap);
//				
//			}
//		} catch (SQLException e) {
//			logger
//			.error("Error reading object from database.", e);
//			
//		}finally {
//			close(result, doClose);
//		}
//		
//		return resultList;
//	}
//	
//	/***************************************************************
//	 * Execute the Query and gets the result as a string array.
//	 ***************************************************************/
//	public static String[] toStringArray(ResultSet result, String columnName) {
//		return toStringArrayList(result, columnName).toArray(new String[] {});
//	}
//	
//	/***************************************************************************
//	 * Converts a ResultSet into a map with the key/values of the selected columns.
//	 * @return list of object, empty if results set is null or an error occurs.
//	 ***************************************************************************/
//	public static ArrayList<String> toStringArrayList(ResultSet result, String columnName) {
//		
//		ArrayList<String> stringArray = new ArrayList<String>();
//		
//		if(result == null) {
//			return stringArray;
//		}
//		
//		try {
//			while(result.next()) {
//				Object value = result.getObject(columnName.toString());
//				stringArray.add(value.toString());
//			}
//		} catch (SQLException e) {
//			logger
//			.error("Error reading object from database.", e);
//			
//		}finally {
//			close(result, doClose);
//		}
//			
//		return stringArray;
//	}
//	
//	
//	/***************************************************************************
//	 * Converts a ResultSet into a map with the key/values of the selected columns.
//	 * @return list of object, empty if results set is null or an error occurs.
//	 ***************************************************************************/
//	public static ArrayList<Integer> toIntegerArrayList(ResultSet result, String columnName) {
//		
//		ArrayList<Integer> stringArray = new ArrayList<Integer>();
//		
//		if(result == null) {
//			return stringArray;
//		}
//		
//		try {
//			while(result.next()) {
//				int value = result.getInt(columnName.toString());
//				stringArray.add(value);
//			}
//		} catch (SQLException e) {
//			logger
//			.error("Error reading object from database.", e);
//			
//		}finally {
//			close(result, doClose);
//		}
//			
//		return stringArray;
//	}
//	
//	/***************************************************************************
//	 * Converts a ResultSet into a map with the key/values of the selected columns.
//	 * @return list of object, empty if results set is null or an error occurs.
//	 ***************************************************************************/
//	public static LinkedHashMap<Object, Object> toLinkedHashMap(ResultSet result, Object keyColumnName, Object valueColumnName) {
//		
//		LinkedHashMap<Object, Object>  resultMap = new LinkedHashMap<Object, Object>();
//		
//		if(result == null) {
//			return resultMap;
//		}
//		
//		try {
//			while(result.next()) {
//				Object key = result.getObject(keyColumnName.toString());
//				Object value = result.getObject(valueColumnName.toString());
//				resultMap.put(key, value);
//			}
//		} catch (SQLException e) {
//			logger
//			.error("Error reading object from database.", e);
//			
//		}finally {
//			close(result, doClose);
//		}
//					
//		return resultMap;
//	}
//	
//	/********************************************************************************************
//	 * Returns a jsonString with an array containing a json object for each row.
//	 * Returns an empty array in case of error.
//	 * 
//	 ********************************************************************************************/
//	public static String toJSON(ResultSet resultSet) {
//		return XR.JSON.toJSON(resultSet);
//	}
//	
//	/********************************************************************************************
//	 * Returns a ResultSetAsJsonReader to convert SQL records to json objects one by one. 
//	 * 
//	 ********************************************************************************************/
//	public static ResultSetAsJsonReader toJSONReader(ResultSet resultSet) {
//		return INSTANCE.new ResultSetAsJsonReader(resultSet);
//	}
//	
//	/***************************************************************************
//	 * Converts a ResultSet into a JsonArray.
//	 * @return list of maps holding key(column name) with values
//	 ***************************************************************************/
//	public static JsonArray toJSONArray(ResultSet result) {
//		
//		JsonArray resultArray = new JsonArray();
//		if(result == null) {
//			return resultArray;
//		}
//		ResultSetAsJsonReader reader = ResultSetUtils.toJSONReader(result);
//		
//		JsonObject object;
//		while( (object = reader.next()) != null) {
//			resultArray.add(object);
//		}
//
//		return resultArray;
//		
////		JsonArray resultArray =  new JsonArray();
////		
////		if(result == null) {
////			return resultArray;
////		}
////		
////		try {
////			ResultSetMetaData meta = result.getMetaData();
////			int columnCount = meta.getColumnCount();
////			
////			while(result.next()) {
////				JsonObject currentObject = new JsonObject();
////				
////				for(int i = 1; i <= columnCount; i++) {
////					String key = meta.getColumnLabel(i);
////					int type = meta.getColumnType(i);
////					
////					
////					switch(type) {
////					
////						case Types.BOOLEAN:
////							currentObject.addProperty(key, result.getBoolean(i));
////							break;
////							
////						case Types.BIT:
////						case Types.TINYINT:
////						case Types.SMALLINT:
////						case Types.INTEGER:
////							currentObject.addProperty(key, result.getInt(i));
////							break;
////						
////						case Types.BIGINT:
////							currentObject.addProperty(key, result.getLong(i));
////							break;
////							
////						case Types.FLOAT:
////							currentObject.addProperty(key, result.getFloat(i));
////							break;	
////								
////						case Types.DOUBLE:
////							currentObject.addProperty(key, result.getDouble(i));
////							break;	
////						
////						case Types.NUMERIC:
////						case Types.DECIMAL:
////							currentObject.addProperty(key, result.getBigDecimal(i));
////							break;	
////						
////						case Types.TIME:
////							Time time = result.getTime(i);
////							if(time != null) {
////								currentObject.addProperty(key, time.getTime());
////							}else {
////								currentObject.add(key, JsonNull.INSTANCE);
////							}
////							break;	
////							
////						case Types.DATE:
////							Date date = result.getDate(i);
////							if(date != null) {
////								currentObject.addProperty(key, date.getTime());
////							}else {
////								currentObject.add(key, JsonNull.INSTANCE);
////							}
////							break;	
////							
////							
////						case Types.TIMESTAMP:
////						case Types.TIMESTAMP_WITH_TIMEZONE:
////							Timestamp timestamp = result.getTimestamp(i);
////							if(timestamp != null) {
////								currentObject.addProperty(key, timestamp.getTime());
////							}else {
////								currentObject.add(key, JsonNull.INSTANCE);
////							}
////							
////							break;	
////							
////						default: 
////							currentObject.addProperty(key, result.getString(i));
////					}
////					
////				}
////				resultArray.add(currentObject);
////				
////			}
////		} catch (SQLException e) {
////			logger
////			.error("Error reading object from database:"+e.getMessage(), e);
////			
////		}finally {
////			close(result, doClose);
////		}
////		
////		return resultArray;
//		
//	}
//	
//	
//
//	/********************************************************************************************
//	 * Converts the ResultSet into a CSV string.
//	 * 
//	 ********************************************************************************************/
//	public static String toCSV(ResultSet resultSet, String delimiter) {
//		StringBuilder csv = new StringBuilder();
//		
//		try {
//			
//			if(resultSet == null) {
//				return "";
//			}
//			
//			//--------------------------------------
//			// Check has results
//			/* Excluded as MSSQL might throw errors			
//			resultSet.beforeFirst();
//			if(!resultSet.isBeforeFirst()) {
//				return "";
//			} */
//			
//			//--------------------------------------
//			// Iterate results
//			ResultSetMetaData metadata = resultSet.getMetaData();
//			int columnCount = metadata.getColumnCount();
//			
//			for(int i = 1 ; i <= columnCount; i++) {
//				csv.append("\"")
//				   .append(metadata.getColumnLabel(i))
//				   .append("\"")
//				   .append(delimiter);
//			}
//			csv.deleteCharAt(csv.length()-1); //remove last comma
//			csv.append("\r\n");
//			while(resultSet.next()) {
//				for(int i = 1 ; i <= columnCount; i++) {
//					
//					String value = resultSet.getString(i);
//					csv.append("\"")
//					   .append(XR.JSON.escapeString(value))
//					   .append("\"")
//					   .append(delimiter);
//				}
//				csv.deleteCharAt(csv.length()-1); //remove last comma
//				csv.append("\r\n");
//			}
//			csv.deleteCharAt(csv.length()-1); //remove last comma
//	
//			
//		} catch (SQLException e) {
//				logger
//					.error("Exception occured while converting ResultSet to CSV.", e);
//				
//				return "";
//		}
//	
//		return csv.toString();
//	}
//
//	/********************************************************************************************
//	 * Returns an XML string with an array containing a record for each row.
//	 * 
//	 ********************************************************************************************/
//	public static String toXML(ResultSet resultSet) {
//		StringBuilder xml = new StringBuilder();
//		
//		try {
//			
//			if(resultSet == null) {
//				return "<data></data>";
//			}
//			//--------------------------------------
//			// Check has results
//			/* Excluded as MSSQL might throw errors			
//			resultSet.beforeFirst();
//			if(!resultSet.isBeforeFirst()) {
//				return "<data></data>";			
//			}*/
//			
//			//--------------------------------------
//			// Iterate results
//			ResultSetMetaData metadata = resultSet.getMetaData();
//			int columnCount = metadata.getColumnCount();
//	
//			xml.append("<data>\n");
//			while(resultSet.next()) {
//				xml.append("\t<record>\n");
//				for(int i = 1 ; i <= columnCount; i++) {
//					String column = metadata.getColumnLabel(i);
//					xml.append("\t\t<").append(column).append(">");
//					
//					String value = resultSet.getString(i);
//					xml.append(value);
//					xml.append("</").append(column).append(">\n");
//				}
//				xml.append("\t</record>\n");
//			}
//			xml.append("</data>");
//			
//		} catch (SQLException e) {
//				logger
//					.error("Exception occured while converting ResultSet to XML.", e);
//				
//				return "<data></data>";
//		}
//	
//		return xml.toString();
//	}
//	
//	
//	/**************************************************************************************************************
//	 * Reads records from a Result set and converts them into Json Objects.
//	 * 
//	 **************************************************************************************************************/
//	public class ResultSetAsJsonReader {
//		
//		private ResultSet resultSet = null;
//		private ResultSetMetaData metadata;
//		private int columnCount;
//		/****************************************************************
//		 * 
//		 ****************************************************************/
//		public ResultSetAsJsonReader(ResultSet resultSet) {
//			this.resultSet = resultSet;
//			try {
//				this.metadata = resultSet.getMetaData();
//				this.columnCount = metadata.getColumnCount();
//			}catch (SQLException e) {
//					logger.error("Error while initializing ResultSetAsJsonReader:"+e.getMessage(), e);
//			}
//			
//		}
//		
//		/****************************************************************
//		 * Returns the next JsonObject or null if the end of the result set was reached.
//		 ****************************************************************/
//		public JsonObject next() {
//
//			if(this.resultSet == null) {
//				return null;
//			}
//			
//			try {
//				
//				if(resultSet.next()) {
//					JsonObject record = new JsonObject();
//					for(int i = 1 ; i <= columnCount; i++) {
//						String name = metadata.getColumnLabel(i);
//						
//						if(name.toUpperCase().startsWith("JSON")) {
//							JsonElement asElement = XR.JSON.stringToJsonElement(resultSet.getString(i));
//							record.add(name, asElement);
//						}else {
//							
//							Object value = resultSet.getObject(i);
//							if(value instanceof Clob) {				XR.JSON.addObject(record, name, resultSet.getString(i)); }
//							else if(value instanceof JdbcArray) {	XR.JSON.addObject(record, name, ((JdbcArray)value).getArray()); } 
//							else {									
//								XR.JSON.addObject(record, name, value);
//							}
//						}
//					}
//					return record;
//				}else {
//					//-------------------------
//					// end of results
//					CFWDB.close(resultSet);
//					return null;
//				}
//			} catch (SQLException e) {
//				CFWDB.close(resultSet);
//				logger.error("Error while reading SQL results:"+e.getMessage(), e);
//			}
//			
//			//return null in case of error;
//			return null;
//		}
//		
//	}
		
}
