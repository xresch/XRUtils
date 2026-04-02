package com.xresch.xrutils.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.xresch.xrutils.base.XR;

/**************************************************************************************************************
 * A Value class that allows to bypass type safety that like to get into the way of programmers.
 * Provides various useful methods that can check
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 * 
 **************************************************************************************************************/
public class XRValue implements Comparable<XRValue> {
	
	private XRValueType type;
	private Object value = null;
	
		
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public enum XRValueType{
		  NUMBER
		, STRING
		, BOOLEAN
		, JSON
		, NULL
	}
	
	/******************************************************************************************************
	 * Creates a clone of the QueryPart.
	 * 
	 ******************************************************************************************************/
	@Override
	public XRValue clone() {
		return this.getAsClone();
	}
			
	/******************************************************************************************************
	 * Private Constructor to enforce correct types.
	 * 
	 ******************************************************************************************************/
	private XRValue(XRValueType type, Object value) {
		super();
		this.value = value;
		if(value != null) {
			this.type = type;
		}else {
			this.type = XRValueType.NULL;
		}
	}
		
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	// Overload method to improve performance of getAsBigDecimal()
	public static XRValue newNumber(Integer value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, new BigDecimal(value));
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	// Overload method to improve performance of getAsBigDecimal()
	public static XRValue newNumber(Long value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, new BigDecimal(value));
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	// Overload method to improve performance of getAsBigDecimal()
	public static XRValue newNumber(Short value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, new BigDecimal(value));
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	// Overload method to improve performance of getAsBigDecimal()
	public static XRValue newNumber(Float value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, new BigDecimal(value));
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	// Overload method to improve performance of getAsBigDecimal()
	public static XRValue newNumber(Double value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, new BigDecimal(value));
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public static XRValue newNumber(Number value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.NUMBER, value);
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public static XRValue newString(String value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.STRING,value);
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public static XRValue newNull(){
		return new XRValue(XRValueType.NULL, null);
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public static XRValue newBoolean(Boolean value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.BOOLEAN, value);
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public static XRValue newJson(JsonElement value){
		if(value == null) { return newNull(); }
		return new XRValue(XRValueType.JSON, value);
	}
	
	/******************************************************************************************************
	 * Creates a new value based on the type of the JsonElement
	 ******************************************************************************************************/
	public static XRValue newFromJsonElement(JsonElement value){
		
		if(value == null || value.isJsonNull()) {
			return newNull(); 
		}
		
		if(value.isJsonPrimitive()) {
			JsonPrimitive primitive = value.getAsJsonPrimitive();
			
			if(primitive.isNumber()) {		return newNumber(value.getAsBigDecimal()); }
			if(primitive.isString()) {		return newString(value.getAsString()); }
			if(primitive.isBoolean()) {		return newBoolean(value.getAsBoolean()); }
		}
		
		return newJson(value);	
		
	}
	
	/******************************************************************************************************
	 * Creates a new value from a string while automatically detecting the data type from the string.
	 * Data types that will be detected are null, number, boolean, string, JsonObject and JsonArray.
	 * 
	 * @param string to convert
	 * @return XRValue for string
	 ******************************************************************************************************/
	public static XRValue newFromString(String string) {
	
		if(string == null) {	return newNull(); }
		
		if(string.isBlank()) {	return newString(string); }
		
		if( NumberUtils.isParsable(string) ) { return newNumber( new BigDecimal(string) ); }
		
		if( checkIsStringABoolean(string) ) { return newBoolean( Boolean.parseBoolean(string) ); }
		
		if(string.trim().startsWith("{")
		|| string.trim().startsWith("[")) {
			try {
				JsonElement element = XR.JSON.fromJson(string);
				return newJson( element);
			} catch (Exception e) {
				return newString(string);
			}
		}
		
		return newString(string);
		
	}
		
	/******************************************************************************************************
	 * Creates a new value from a string based on the type.
	 * Enforces correct types by throwing nasty exceptions.
	 ******************************************************************************************************/
	public static XRValue newFromString(XRValueType type, String string) throws IllegalStateException {
		
		//-----------------------------
		// Handle Nulls
		if(string == null) {
			return XRValue.newNull();
		}
		
		
		//-----------------------------
		// Handle Other Values
		
		switch (type) {
			
			case STRING: 
				return new XRValue(XRValueType.STRING, string.trim() ); 
			
			case BOOLEAN: 
				if( XRValue.checkIsStringABoolean(string) ) { 
					return new XRValue(XRValueType.BOOLEAN, Boolean.parseBoolean(string.trim()));
				}else {
					throw new IllegalStateException("The provided string was not a boolean: "+string); 
				} 
			//break;
			
			case NUMBER: 
				if( NumberUtils.isCreatable(string) ) { 
					return new XRValue(XRValueType.NUMBER, new BigDecimal(string.trim()));
				} else {
					throw new IllegalStateException("The provided string was not a number: "+string); 
				}
			//break;
			

			case JSON: 
				if( XRValue.checkIsJsonParsable(string) ) { 
					return new XRValue(XRValueType.JSON, XR.JSON.fromJson(string) );
				}else {
					throw new IllegalStateException("The provided string was not a JSON string: "+string); 
				}
			//break;
				
			case NULL: /* Do nothing */ break;
			default: /* Do nothing */ break;

		}
		
		return XRValue.newNull();
		
	}
	
	/******************************************************************************************************
	 * Creates a new QueryPart based on the type of the JsonElement
	 ******************************************************************************************************/
	public static XRValue newFromStringArray(ArrayList<String> array){
		
		JsonArray newArray = new JsonArray();
		
		for(String current : array) {
			newArray.add(current);
		}
		
		return newJson(newArray);	
		
	}


	/******************************************************************************************************
	 * Creates a new QueryPart based on the type of the JsonElement
	 ******************************************************************************************************/
	public void addToJsonObject(String propertyName, JsonObject object){
		
		if(object == null) {
			return;
		}
		
		switch(type) {
			case STRING: 	object.addProperty(propertyName, (String)value);
							break;
							
			case NUMBER:	object.addProperty(propertyName, (Number)value);
							break;
							
			case BOOLEAN:	object.addProperty(propertyName, (Boolean)value);
							break;
							
			case JSON:		object.add(propertyName, (JsonElement)value);
							break;
							
			case NULL:		object.add(propertyName, JsonNull.INSTANCE);
							break;

		default:
			break;
	
		}
		

		
	}

	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public XRValueType type() {
		return type;
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public boolean isOfType(XRValueType type) {
		return this.type == type;
	}
	
	
	/******************************************************************************************************
	 * If the value is null, change it to a number of value zero.
	 ******************************************************************************************************/
	public void nullToZero() {
		if(value == null) {
			this.value = 0;
			this.type = XRValueType.NUMBER;
		}
	}
	
	/******************************************************************************************************
	 * Check if the value is null
	 ******************************************************************************************************/
	public boolean isNull() {
		return value == null;
	}
	
	/******************************************************************************************************
	 * Check if the value is null
	 ******************************************************************************************************/
	public boolean isNullOrEmptyString() {
		return 
			value == null 
			|| (this.type == XRValueType.STRING 
				&& Strings.isNullOrEmpty((String)value) 
			);
	}
	
	/******************************************************************************************************
	 * Check if the value is of type boolean
	 ******************************************************************************************************/
	public boolean isBoolean() {
		return this.type == XRValueType.BOOLEAN;
	}
	
	/******************************************************************************************************
	 * Check if the value is a string representation of a boolean. 
	 ******************************************************************************************************/
	public boolean isBooleanString() {
		
		if(this.type == XRValueType.STRING) {
			return  checkIsStringABoolean(this.getAsString());
		}
		
		return false;
	}
	
	/******************************************************************************************************
	 * Check if the value is a string representation of a boolean. 
	 ******************************************************************************************************/
	public static boolean checkIsStringABoolean(String string) {
		
		if(string == null) { return false; } 

		String value = string.trim().toLowerCase();
		return  value.equals("true") || value.equals("false")   ;

	}
	
	/******************************************************************************************************
	 * Check if the value is string representation of a boolean. 
	 ******************************************************************************************************/
	public boolean isBoolOrBoolString() {
				
		return this.isBoolean() || this.isBooleanString();
	}
	
	/******************************************************************************************************
	 * Check if the value is a number
	 ******************************************************************************************************/
	public boolean isNumber() {
		return this.type == XRValueType.NUMBER;
	}
	
	
	/******************************************************************************************************
	 * Check if the value is a number
	 ******************************************************************************************************/
	public boolean isNumberString() {
		if(this.type == XRValueType.STRING) {
			String value = this.getAsString().trim();
			
			return NumberUtils.isParsable(value);

		}
		
		return false;

	}
	
	/******************************************************************************************************
	 * Check if the value is a number or a number string
	 ******************************************************************************************************/
	public boolean isNumberOrNumberString() {
				
		return this.isNumber() || this.isNumberString();
	}
	
	
	/******************************************************************************************************
	 * Check if the value is an Integer
	 ******************************************************************************************************/
	public boolean isInteger() {
		
		boolean isItReally = false;
		
		switch(type) {
			case NUMBER:	
				Number number = this.getAsNumber();
				Double doubleValue = number.doubleValue();
				
				if(Math.floor(doubleValue) == doubleValue) {
					
					isItReally = true;
				};
				break;
		
			case STRING:	
				try{
					Integer.parseInt((String)value);
					isItReally = true;
				}catch(Exception e) {
					isItReally = false;
				}
				break;
				
			default:		isItReally = false;

		}
		return isItReally;
	}
	
	/******************************************************************************************************
	 * Check if the value is of type String
	 ******************************************************************************************************/
	public boolean isString() {
		return this.type == XRValueType.STRING;
	}
	
	/******************************************************************************************************
	 * Check if the value can be converted into a JsonElement.
	 ******************************************************************************************************/
	public static boolean checkIsJsonParsable(String string) {
		
		String trimmed = string.trim();
		
		if(trimmed.startsWith("{")
		|| trimmed.startsWith("[")) {
			try {
				JsonElement element = XR.JSON.fromJson(string);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		return false;
				
	}
	
	/******************************************************************************************************
	 * Check if the value is of type JSON
	 ******************************************************************************************************/
	public boolean isJson() {
		return this.type == XRValueType.JSON;
	}
	
	/******************************************************************************************************
	 * Check if the value is a JSON Array
	 ******************************************************************************************************/
	public boolean isJsonArray() {
		return this.type == XRValueType.JSON && ((JsonElement)this.value).isJsonArray();
	}
	
	/******************************************************************************************************
	 * Check if the value is a JSON Object
	 ******************************************************************************************************/
	public boolean isJsonObject() {
		return this.type == XRValueType.JSON && ((JsonElement)this.value).isJsonObject();
	}
	
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	@SuppressWarnings("unchecked")
	public Object getValue() {
		return value;
	}
	
	
	
	/******************************************************************************************************
	 * Returns this value as a number.
	 * Booleans will be returned as 0 for false and 1 for true.
	 * Strings will be parsed to BigDecimal. If it is not parsable, it will return null.
	 * for JsonElements getBigDecimal will be called.
	 * This function might throw runtime exceptions for parsing or if json element is not a number.
	 * It is highly recommended to always call one of the isNumber()-methods before calling this method.
	 ******************************************************************************************************/
	public Number getAsNumber() {
		
		switch(type) {
			case NUMBER:	return ((Number)value);
	
			case BOOLEAN: 	return ((Boolean)value)  ? 1 : 0; 
			
			case STRING:	if(isNumberString()) {
								return new BigDecimal((String)value);
							}else {
								return null;
							}
			
			case JSON:		return ((JsonElement)value).getAsBigDecimal();
				
			default:		return null;

		}

	}
	
	/******************************************************************************************************
	 * It is recommended to use isInteger() first to make sure number value is really a Integer.
	 ******************************************************************************************************/
	public Integer getAsInteger() {		
		return getAsInt();

	}
	
	/******************************************************************************************************
	 * It is recommended to use isInteger() first to make sure number value is really a Integer.
	 ******************************************************************************************************/
	public Integer getAsInt() {
			
		Number number = this.getAsNumber();
		
		if(number == null) return null;
		
		return number.intValue();

	}
	
	
	/******************************************************************************************************
	 * It is recommended to use isNumber()/isNumberString() first to make 
	 * sure number value is really a Number.
	 ******************************************************************************************************/
	public Float getAsFloat() {
			
		Number number = this.getAsNumber();
		
		if(number == null) return null;
		
		return number.floatValue();

	}
	
	/******************************************************************************************************
	 * It is recommended to use isNumber()/isNumberString() first to make 
	 * sure number value is really a Number.
	 ******************************************************************************************************/
	public Long getAsLong() {
			
		Number number = this.getAsNumber();
		
		if(number == null) return null;
		
		return number.longValue();

	}
	
	/******************************************************************************************************
	 * It is recommended to use isNumber()/isNumberString() first to make 
	 * sure number value is really a Number.
	 ******************************************************************************************************/
	public Double getAsDouble() {
			
		Number number = this.getAsNumber();
		
		if(number == null) return null;
		
		return number.doubleValue();

	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public BigDecimal getAsBigDecimal() {
		
		//--------------------------------
		// Most numbers are be stored as BigDecimal
		// to improve performance of this method
		if(value instanceof BigDecimal) {
			return (BigDecimal)value;
		}
		
		//--------------------------------
		// Handle Number Strings
		if(this.isNumberString()) {
			return new BigDecimal(this.getAsString());
		}
		
		//--------------------------------
		// Any Other Value
		Number number = this.getAsNumber();
		if(number == null) return null;
		
		// this check is needed for proper conversion to string values
		if(number instanceof BigDecimal){
			return (BigDecimal) number;
		}else if(  number instanceof Integer 
				|| number instanceof Long
				|| number instanceof Short
				) {
			new BigDecimal(number.longValue());
		}
		
		return new BigDecimal(number.doubleValue());

	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public Boolean getAsBoolean() {
		
		switch(type) {
		
			case BOOLEAN: 	return ((Boolean)value); 
			
			case NUMBER:	return ( ((Number)value).floatValue() == 0) ? false : true;

			case STRING:	return Boolean.parseBoolean((String)value);
			
			case NULL:		return false;
			
			case JSON:
				JsonElement element = (JsonElement)value;
				if(element.isJsonPrimitive()) {
					if(element.getAsJsonPrimitive().isBoolean()) {
						return element.getAsBoolean();
					}else {
						return false;
					}
				}else {
					return false;
				}
				
			default:		return false;

		}

	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public JsonElement getAsJsonElement() {
		
		switch(type) {
			case JSON:		return ((JsonElement)value);
			
			case NUMBER:	return new JsonPrimitive((Number)value);

			case BOOLEAN: 	return new JsonPrimitive((Boolean)value);
			
			case STRING:	return new JsonPrimitive((String)value);
			
			case NULL:	return JsonNull.INSTANCE;
			
			default:	return JsonNull.INSTANCE;

		}
	}
	
	/******************************************************************************************************
	 * Converts the value into a JsonObject if not already a json object.
	 * If value is converted, resulting object contains two fields: type and value.
	 * Use getAsJsonElement for not having conversion.
	 ******************************************************************************************************/
	public JsonObject getAsJsonObject() {
		JsonObject object = new JsonObject();
		switch(type) {
			case JSON:		JsonElement element = ((JsonElement)value);
							if(element.isJsonObject()) {
								object = element.getAsJsonObject();
							}else if(element.isJsonArray()) {
								
								object.addProperty("type", "array");
								object.add("value", element);
							}else {
								//this code should never be reached
								object.add("type", JsonNull.INSTANCE);
								object.add("value", element);
							}
							break;
			
			case NUMBER:	object.addProperty("type", "number");
							object.addProperty("value", (Number)value);
							break; 
							
			case BOOLEAN:	object.addProperty("type", "boolean");
							object.addProperty("value", (Boolean)value);
							break; 
							
			case STRING:	object.addProperty("type", "string");
							object.addProperty("value", (String)value);
							break; 
						
			case NULL:		object.addProperty("type", "null");
							object.add("value", JsonNull.INSTANCE);
							break;
							
			default:		object.addProperty("type", "null");
							object.add("value", JsonNull.INSTANCE);
							break;
		}
		
		return object;
	}
	
	/******************************************************************************************************
	 * Takes the value of this XRValue and wraps it into an array.
	 * @return the same object if it is already an array, new array if it is of another type
	 ******************************************************************************************************/
	public XRValue convertToArray() {
		
		if(this.isJsonArray()) {
			return this;
		}
		
		return XRValue.newFromJsonElement(
				this.getAsJsonArray()
			);
	}
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public JsonArray getAsJsonArray() {
		JsonArray array;
		
		switch(type) {
			case JSON:		if(this.isJsonArray()) {
								return ((JsonElement)value).getAsJsonArray();
							}else {
								array = new JsonArray();
				 				array.add((JsonElement)value);
								return array;
							}
			
			case NUMBER:	array = new JsonArray();
			 				array.add((Number)value);
							return array;

			case BOOLEAN: 	array = new JsonArray();
							array.add((Boolean)value);
							return array;
			
			case STRING:	array = new JsonArray();
							array.add((String)value);
							return array;

			default:		return new JsonArray();

		}

	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	public ArrayList<String> getAsStringArray() {
		 return XR.JSON.jsonToStringArrayList(
				 getAsJsonArray() 
			);
	}
	
	/******************************************************************************************************
	 * Creates a clone of this XRValue.
	 ******************************************************************************************************/
	public XRValue getAsClone() {
		
		switch(type) {
			case NULL:		return XRValue.newNull();
			
			case JSON:		return XRValue.newJson( ((JsonElement)value).deepCopy());
							
			case NUMBER:	return XRValue.newNumber((Number)value);

			case BOOLEAN: 	boolean boolClone = (Boolean)value;
							return XRValue.newBoolean(boolClone);
			
			case STRING:	String stringClone = ((String)value)+"";
							return XRValue.newString(stringClone);

			default:		return XRValue.newNull();

		}

	}
	
	/******************************************************************************************************
	 * Returns a string representation of this value, except if the value is null.
	 ******************************************************************************************************/
	public String getAsString() {
		if(value == null) return null;
		
		// convert numbers to string without scientific notation
		if(type == XRValueType.NUMBER) {
			return this.getAsBigDecimal().stripTrailingZeros().toPlainString();
		}
		
		return value.toString();
	}
	
	/***********************************************************************************************
	 * Checks if the JsonElement is equals to this value.
	 * Ignores type safety as follows:
	 * <ul>
	 * 		<li>If this is of type boolean, compare to strings while ignoring case. Compare to numbers while 0 is false and everything else is true.</li>
	 * 		<li>If this is string, compare to the string representations of booleans(case insensitive) and numbers </li>
	 * 		<li>if this is a number, compare to strings by converting the number to a string. Compare to booleans with 0 as false and true everything else. </li>
	 * 		<li>If this is null, compare to strings and only return true if string is exactly "null" </li>
	 * </ul>
	 ***********************************************************************************************/
	public boolean equalsElement(JsonElement element) {
		
		switch(this.type()) {
			case BOOLEAN:
				if(element.isJsonPrimitive()) {
					JsonPrimitive primitive = element.getAsJsonPrimitive();
					if(primitive.isBoolean()) {
						if(primitive.getAsBoolean() == this.getAsBoolean()) {
							return true;
						}
					}else if(primitive.isString() || primitive.isNumber()) {
						if(primitive.getAsString().equalsIgnoreCase(this.getAsString()) ) {
							return true;
						}
					}
				}
				break;
														
			case NUMBER:
				if(element.isJsonPrimitive()) {
					JsonPrimitive primitive = element.getAsJsonPrimitive();
					if(primitive.isNumber()) {
						if(primitive.getAsNumber().doubleValue() == this.getAsNumber().doubleValue()) {
							return true;
						}
					}else if(primitive.isString() || primitive.isBoolean()) {
						if(primitive.getAsString().equals(this.getAsString()) ) {
							return true;
						}
					}
				}
				break;
				
				
			case STRING:
				if(element.isJsonPrimitive()) {
					JsonPrimitive primitive = element.getAsJsonPrimitive();
					if(primitive.isString()) {
						if(primitive.getAsString().equals(this.getAsString()) ) {
							return true;
						}
					}else if(primitive.isBoolean()) {
						if(this.getAsString().equalsIgnoreCase(primitive.getAsBoolean()+"") ) {
							return true;
						}
					}else if(primitive.isNumber()) {
						if(this.getAsString().equals(primitive.getAsNumber()+"") ) {
							return true;
						}
					}
				}else if(element.isJsonNull()) {
					return this.getAsString().equals("null");
				}
				break;
			
				
			case NULL:
				if(element.isJsonNull()
				|| ( element.isJsonPrimitive() && element.getAsString().equals("null") )  
				) {
					return true;
				}
				break;
				
				
			case JSON: // Not supported
				break;
				
			default: 
				return false;
		
		}
		
		return false;
	}
	
	/***********************************************************************************************
	 * Checks if the this value is contained in the given JsonElement.
	 * Ignores type safety as all values are converted and evaluated as strings.
	 ***********************************************************************************************/
	public boolean containedInElement(JsonElement element) {
		
		if(element.isJsonNull()) {
			if(this.isNull()) return true;
			
			return "null".contains(this.getAsString());
		}
		
		switch(this.type()) {
			case NUMBER:	
			case STRING:	
				if(element.isJsonPrimitive()) {
					
					String primitiveString = element.getAsString();
					String thisString = this.getAsString();
					
					// compare booleans case insensitive
					if(element.getAsJsonPrimitive().isBoolean()) {
						thisString = thisString.toLowerCase();
					}
					
					return primitiveString.contains(thisString);
				}
				break;
			
			case BOOLEAN:
				if(element.isJsonPrimitive()) {
					String primitiveString = element.getAsString();
					return primitiveString.toLowerCase().contains(this.getAsString().toLowerCase());
				}
				break;
				
			case NULL:
				if(element.isJsonPrimitive()) {
					String primitiveString = element.getAsString();
					return primitiveString.contains("null");
				}				 
				
			case JSON: // Not supported
				break;
				
			default: 
				return false;
		
		}
		
		return false;
	}
	
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	@Override
	public String toString() {
		return this.getAsString();
	}
	/******************************************************************************************************
	 * 
	 ******************************************************************************************************/
	@Override
	public int hashCode() {
		return Objects.hash(type, value);
	}

	/******************************************************************************************************
	 * Returns true if both values are the same, ignoring type
	 ******************************************************************************************************/
	public boolean equalsIgnoreType(Object obj) {
				
		if (this == obj)				 	{ return true;  }
		if (obj == null) 					{ return false; }
		if (getClass() != obj.getClass()) 	{ return false; }
		
		
		XRValue other = (XRValue) obj;
		if(type == other.type) {
			return Objects.equals(value, other.value);
		}else if(type == XRValueType.NULL 
		|| other.type == XRValueType.NULL   
		){
			return false;
		}else {
			this.getAsString()
				.equals(
						other.getAsString()
					);
		}
		
		return false;
	}
	
	/******************************************************************************************************
	 * Returns true if both values are of the same type and have the same value
	 ******************************************************************************************************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)				 	{ return true;  }
		if (obj == null) 					{ return false; }
		if (getClass() != obj.getClass()) 	{ return false; }
		
		XRValue other = (XRValue) obj;
		return type == other.type && Objects.equals(value, other.value);
	}

	/******************************************************************************************************
	 * Will compare as string.
	 ******************************************************************************************************/
	@Override
	public int compareTo(XRValue o) {
		
		if(o == null) { return -1; }
		
		return XR.Text.compareStringsAlphanum(this.getAsString(), o.getAsString());
	}

}
