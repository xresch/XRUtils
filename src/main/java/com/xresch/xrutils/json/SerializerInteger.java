package com.xresch.xrutils.json;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 201
 * @license MIT-License
 **************************************************************************************************************/
public class SerializerInteger implements JsonSerializer<Integer> {

	public SerializerInteger() {

	}
	
	@Override
	public JsonElement serialize(Integer object, Type type, JsonSerializationContext context) {
			
		return new JsonPrimitive(object);
	}

}
