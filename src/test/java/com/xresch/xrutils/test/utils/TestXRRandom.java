package com.xresch.xrutils.test.utils;

import org.junit.jupiter.api.Test;

import com.xresch.xrutils.utils.XRRandom;

/***************************************************************************
 * 
 ***************************************************************************/

public class TestXRRandom {

		
	/*****************************************************************
	 * 
	 *****************************************************************/
	@Test
	public void testExecuteMethods() throws InterruptedException {
		
		//--------------------------------------------
		// Boolean
		Boolean bool 			= XRRandom.bool();
		Boolean boolWithNulls 	= XRRandom.bool(10); // with 10% null values
		
		//--------------------------------------------
		// Strings
		String alphaLowerUppercase = XRRandom.string(32);
		String alphanum = XRRandom.stringAlphaNum(32);
		String alphanumSpecial = XRRandom.stringAlphaNumSpecial(32);
		String loremIpsum = XRRandom.loremIpsum(2048);
		
		
		//--------------------------------------------
		// from Collections, Arrays ...
		
		//--------------------------------------------
		// Various Predefined String Data
		String firstname = XRRandom.firstnameOfGod();
		String lastname = XRRandom.lastnameSweden();
		String location = XRRandom.mythicalLocation();
		String company = XRRandom.companyTitle();
		String service = XRRandom.ultimateServiceName();
		String color = XRRandom.colorName();
		String fruit = XRRandom.fruitName();
		String italianDessert = XRRandom.italianDessert();
		String adjective = XRRandom.exaggaratingAdjective();

	}
	
}