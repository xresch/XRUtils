package com.xresch.xrutils.test.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.xresch.xrutils.data.Unvalue;
import com.xresch.xrutils.data.Unvalue.UnvalueType;

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

public class TestUnvalue {

		
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	public void testUnvalue_fromString() throws InterruptedException {
		
		//----------------------------------
		// Null
		Unvalue unvalue = Unvalue.newFromString(null);
		Assertions.assertEquals(unvalue.type(), UnvalueType.NULL);
		
		//----------------------------------
		// String
		unvalue = Unvalue.newFromString("");
		Assertions.assertEquals(unvalue.type(), UnvalueType.STRING);
		
		unvalue = Unvalue.newFromString("    ");
		Assertions.assertEquals(unvalue.type(), UnvalueType.STRING);
		
		unvalue = Unvalue.newFromString("some string");
		Assertions.assertEquals(unvalue.type(), UnvalueType.STRING);
		
		//----------------------------------
		// Number
		unvalue = Unvalue.newFromString("1");
		Assertions.assertEquals(unvalue.type(), UnvalueType.NUMBER);
		
		unvalue = Unvalue.newFromString("1.2");
		Assertions.assertEquals(unvalue.type(), UnvalueType.NUMBER);
		
		//----------------------------------
		// Boolean
		unvalue = Unvalue.newFromString("true");
		Assertions.assertEquals(unvalue.type(), UnvalueType.BOOLEAN);
		
		unvalue = Unvalue.newFromString("FALSE");
		Assertions.assertEquals(unvalue.type(), UnvalueType.BOOLEAN);
		
		unvalue = Unvalue.newFromString(" true  ");
		Assertions.assertEquals(unvalue.type(), UnvalueType.BOOLEAN);
		
		//----------------------------------
		// Json
		unvalue = Unvalue.newFromString("{\"key\": \"value\" }");
		Assertions.assertEquals(unvalue.type(), UnvalueType.JSON);
		
		unvalue = Unvalue.newFromString("[\"valueA\", \"valueB\" ]");
		Assertions.assertEquals(unvalue.type(), UnvalueType.JSON);
	}
	
}