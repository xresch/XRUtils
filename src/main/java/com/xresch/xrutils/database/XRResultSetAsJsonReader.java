package com.xresch.xrutils.database;

import java.sql.Array;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;

/**************************************************************************************************************
 * Reads records from a Result set and converts them into Json Objects.
 * 
 **************************************************************************************************************/
public class XRResultSetAsJsonReader {
	
	private ResultSet resultSet = null;
	private ResultSetMetaData metadata;
	private int columnCount;
	/****************************************************************
	 * 
	 ****************************************************************/
	public XRResultSetAsJsonReader(ResultSet resultSet) {
		this.resultSet = resultSet;
		try {
			this.metadata = resultSet.getMetaData();
			this.columnCount = metadata.getColumnCount();
		}catch (SQLException e) {
				XRResultSetUtils.logger.error("Error while initializing ResultSetAsJsonReader:"+e.getMessage(), e);
		}
		
	}
	
	/****************************************************************
	 * Returns the next JsonObject or null if the end of the result set was reached.
	 ****************************************************************/
	public JsonObject next() {

		if(this.resultSet == null) {
			return null;
		}
		
		try {
			
			if(resultSet.next()) {
				JsonObject record = new JsonObject();
				for(int i = 1 ; i <= columnCount; i++) {
					String name = metadata.getColumnLabel(i);
					
					if(name.toUpperCase().startsWith("JSON")) {
						JsonElement asElement = XR.JSON.stringToJsonElement(resultSet.getString(i));
						record.add(name, asElement);
					}else {
						
						Object value = resultSet.getObject(i);
						if(value instanceof Clob) {			XR.JSON.addObject(record, name, resultSet.getString(i)); }
						else if(value instanceof Array) {	XR.JSON.addObject(record, name, ((Array)value).getArray()); } 
						else {									
							XR.JSON.addObject(record, name, value);
						}
					}
				}
				return record;
			}else {
				//-------------------------
				// end of results
				XRResultSetUtils.close(resultSet);
				return null;
			}
		} catch (SQLException e) {
			XRResultSetUtils.close(resultSet);
			XRResultSetUtils.logger.error("Error while reading SQL results:"+e.getMessage(), e);
		}
		
		//return null in case of error;
		return null;
	}
	
}