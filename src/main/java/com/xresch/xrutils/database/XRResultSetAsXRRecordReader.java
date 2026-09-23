package com.xresch.xrutils.database;

import java.sql.Array;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;
import com.xresch.xrutils.data.XRRecord;
import com.xresch.xrutils.data.XRValue;

/**************************************************************************************************************
 * Reads records from a Result set and converts them into Json Objects.
 * 
 **************************************************************************************************************/
public class XRResultSetAsXRRecordReader {
	
	private ResultSet resultSet = null;
	private ResultSetMetaData metadata;
	private int columnCount;
	/****************************************************************
	 * 
	 ****************************************************************/
	public XRResultSetAsXRRecordReader(ResultSet resultSet) {
		this.resultSet = resultSet;
		try {
			this.metadata = resultSet.getMetaData();
			this.columnCount = metadata.getColumnCount();
		}catch (SQLException e) {
				XRResultSetUtils.logger.error("Error while initializing ResultSetAsJsonReader:"+e.getMessage(), e);
		}
		
	}
	
	/****************************************************************
	 * Returns the next XRRecord or null if the end of the result set was reached.
	 ****************************************************************/
	public XRRecord next() {

		if(this.resultSet == null) {
			return null;
		}
		
		try {
			
			if(resultSet.next()) {
				XRRecord record = new XRRecord();
				for(int i = 1 ; i <= columnCount; i++) {
					String name = metadata.getColumnLabel(i);
					
					if(name.toUpperCase().startsWith("JSON")) {
						JsonElement asElement = XR.JSON.stringToJsonElement(resultSet.getString(i));
						record.add(name, asElement);
						
					}else {
						
						Object value = resultSet.getObject(i);
						
						JsonElement element = null;
						if(value instanceof Clob) {			element = XR.JSON.valueToJson( resultSet.getString(i) ); }
						else if(value instanceof Array) {	element = XR.JSON.valueToJson( ((Array)value).getArray() ); } 
						else {									
							element = XR.JSON.valueToJson(value);
						}
						
						record.add(name, XRValue.newFromJsonElement(element));
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