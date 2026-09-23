package com.xresch.xrutils.database;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XRResultSet {
	
	private static Logger logger = LoggerFactory.getLogger(XRResultSet.class.getName());
	
	private XRDBInterface dbInterface;
	
	private String sqlString;
	private Object[] values;
	
	private int updateCount = -999;
	private Connection connection;
	private PreparedStatement prepared;
	private ResultSet result;
	
	
	private boolean isSuccess = false;
	private boolean isResultSet = false;
	private boolean executionResult = false;
	
	private boolean isSilent = false;
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet(XRDBInterface dbInterface, boolean isSuccess) {
		this.dbInterface = dbInterface;
		this.isSuccess = isSuccess;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public boolean isSuccess() {
		return this.isSuccess;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet connection(Connection value) {
		this.connection = value;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public Connection connection() {
		return this.connection;
	}
	
	/***********************************************************************************
	 * Close the connection.
	 ***********************************************************************************/
	public void close() {
		dbInterface.close(connection);
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet isResultSet(boolean value) {
		this.isResultSet = value;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public boolean isResultSet() {
		return this.isResultSet;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet resultSet(ResultSet result) {
		this.isResultSet = true;
		this.result = result;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public ResultSet getResultSet() {
		if(prepared == null || !isResultSet) {
			return null;
		}
		
		
		try {
			if(result == null) {
				result  = prepared.getResultSet();
			}
			
			return result;
			
		} catch (SQLException e) {

			logger.error("Issue executing prepared statement: "+e.getLocalizedMessage(), e);
			try {
				if(connection != null && dbInterface.transactionIsStarted() ) { 
					dbInterface.removeOpenConnection(connection);
					connection.close(); 
				}
				if(prepared != null) { prepared.close(); }
			} catch (SQLException e2) {
				logger.error("Issue closing resources.", e2);
			}
		} 
		
		return null;
	}
	
	/***********************************************************************************
	 * Streams bytes from a column to the defined output stream.
	 * @param columnName name of the column
	 * @param out the output stream
	 * @return true if successful or when result was empty, false on errors
	 ***********************************************************************************/
	public boolean streamBytes(Object columnName, OutputStream out) {
		
		if(prepared == null || !isResultSet) {
			return true;
		}
		
		try {
			
			ResultSet resultSet = prepared.getResultSet();
			
			 while (resultSet.next()) {
                InputStream input = resultSet.getBinaryStream(columnName.toString());
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
            }
	           return true;
			
		} catch (Exception e) {

			logger.error("Issue streaming bytes to output stream: "+e.getLocalizedMessage(), e);
			try {
				if(connection != null && dbInterface.transactionIsStarted() ) { 
					dbInterface.removeOpenConnection(connection);
					connection.close(); 
				}
				if(prepared != null) { prepared.close(); }
			} catch (SQLException e2) {
				logger.error("Issue closing resources.", e2);
			}
		} 
		
		return false;
	}
	
	/***********************************************************************************
	 * Streams bytes from a column to the defined output stream.
	 * @param columnName name of the column
	 * @param out the output stream
	 * @return InputStream for the first result, or null if there was no result or on error
	 ***********************************************************************************/
	public InputStream getBytesStream(String columnName) {
		
		if(prepared == null || !isResultSet) {
			return null;
		}
		
		try {
			
			ResultSet resultSet = prepared.getResultSet();
			
			if (resultSet.next()) {
                return resultSet.getBinaryStream(columnName.toString());
            }
			
		} catch (Exception e) {

			logger.error("Issue creating InputStream: "+e.getLocalizedMessage(), e);
			try {
				if(connection != null && dbInterface.transactionIsStarted() ) { 
					dbInterface.removeOpenConnection(connection);
					connection.close(); 
				}
				if(prepared != null) { prepared.close(); }
			} catch (SQLException e2) {
				logger.error("Issue closing resources.", e2);
			}
		} 
		
		return null;
	}
	
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSetAsJsonReader toJSONReader() {
		return XRResultSetUtils.toJSONReader(getResultSet());
	}
	
	/***********************************************************************************
	 * INTERNAL USE ONLY
	 * Set isSilent, if true, write errors to log but do not propagate them to client.
	 ***********************************************************************************/
	public XRResultSet isSilent(boolean value) {
		this.isSilent = value;
		return this;
	}
	
	/***********************************************************************************
	 * Retrieve isSilent, if true, write errors to log but do not propagate them to client.
	 ***********************************************************************************/
	public boolean isSilent() {
		return this.isSilent;
	}

	/***********************************************************************************
	 * INTERNAL USE ONLY
	 * Set the update count for update queries.
	 ***********************************************************************************/
	public XRResultSet updateCount(int value) {
		this.updateCount = value;
		return this;
	}
	
	/***********************************************************************************
	 * 
	 * @return number of rows updated, -999 if count was not set
	 ***********************************************************************************/
	public int updateCount() {
		return this.updateCount;
	}

	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet sqlString(String value) {
		this.sqlString = value;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public String sqlString() {
		return this.sqlString;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet values(Object[] values) {
		this.values = values;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public Object[] values() {
		return this.values;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet executionResult(boolean value) {
		this.executionResult = value;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public boolean executionResult() {
		return this.executionResult;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public XRResultSet preparedStatement(PreparedStatement value) {
		this.prepared = value;
		return this;
	}
	
	/***********************************************************************************
	 *
	 ***********************************************************************************/
	public PreparedStatement preparedStatement() {
		return this.prepared;
	}


	
}
