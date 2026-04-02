package com.xresch.xrutils.test.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.xresch.xrutils.data.XRValue;
import com.xresch.xrutils.data.XRValue.XRValueType;

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

public class TestXRValue {

		
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	public void test_fromString() throws InterruptedException {
		
		//----------------------------------
		// Null
		XRValue value = XRValue.newFromString(null);
		Assertions.assertEquals(value.type(), XRValueType.NULL);
		
		//----------------------------------
		// String
		value = XRValue.newFromString("");
		Assertions.assertEquals(value.type(), XRValueType.STRING);
		
		value = XRValue.newFromString("    ");
		Assertions.assertEquals(value.type(), XRValueType.STRING);
		
		value = XRValue.newFromString("some string");
		Assertions.assertEquals(value.type(), XRValueType.STRING);
		
		//----------------------------------
		// Number
		value = XRValue.newFromString("1");
		Assertions.assertEquals(value.type(), XRValueType.NUMBER);
		
		value = XRValue.newFromString("1.2");
		Assertions.assertEquals(value.type(), XRValueType.NUMBER);
		
		//----------------------------------
		// Boolean
		value = XRValue.newFromString("true");
		Assertions.assertEquals(value.type(), XRValueType.BOOLEAN);
		
		value = XRValue.newFromString("FALSE");
		Assertions.assertEquals(value.type(), XRValueType.BOOLEAN);
		
		value = XRValue.newFromString(" true  ");
		Assertions.assertEquals(value.type(), XRValueType.BOOLEAN);
		
		//----------------------------------
		// Json
		value = XRValue.newFromString("{\"key\": \"value\" }");
		Assertions.assertEquals(value.type(), XRValueType.JSON);
		
		value = XRValue.newFromString("[\"valueA\", \"valueB\" ]");
		Assertions.assertEquals(value.type(), XRValueType.JSON);
	}
	
}