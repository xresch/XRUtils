package com.xresch.xrutils.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT-License
 **************************************************************************************************************/
public class TypeAdapterSLF4JLogger extends TypeAdapter<Logger> {

    @Override
    public void write(JsonWriter out, Logger value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        

        out.value(value.getName());
    }

    @Override
    public Logger read(JsonReader in) throws IOException {
    	
    	String loggerName = in.nextString();

    	if(loggerName != null) {
    		return LoggerFactory.getLogger(loggerName);
    	}
    	
    	return null;
    }
}

