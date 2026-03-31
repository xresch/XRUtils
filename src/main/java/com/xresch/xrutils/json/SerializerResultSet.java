package com.xresch.xrutils.json;

import java.lang.reflect.Type;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xresch.xrutils.database.XRResultSetAsJsonReader;
import com.xresch.xrutils.database.XRResultSetUtils;

/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 2019 
 * @license MIT-License
 **************************************************************************************************************/
public class SerializerResultSet implements JsonSerializer<ResultSet> {
	

	@Override
	public JsonElement serialize(ResultSet resultSet, Type type, JsonSerializationContext context) {
		
		JsonArray result = new JsonArray();
		
		XRResultSetAsJsonReader reader = XRResultSetUtils.toJSONReader(resultSet);
		
		JsonObject object;
		while( (object = reader.next()) != null) {
			result.add(object);
		}

		return result;
	}

}
