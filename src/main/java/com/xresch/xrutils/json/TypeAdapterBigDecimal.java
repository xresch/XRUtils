package com.xresch.xrutils.json;

import java.io.IOException;
import java.math.BigDecimal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**************************************************************************************************************
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT-License
 **************************************************************************************************************/
public class TypeAdapterBigDecimal extends TypeAdapter<BigDecimal> {

    @Override
    public void write(JsonWriter out, BigDecimal value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        
        // this workaround is done for avoiding:
        //   - that output number is quoted
        //   - that number is represented in scientific notation
        // 
        // Why is this needed:
        // - when out.value(Number) is used, BigDecimal.toString() might return scientific notation
        // - when out.value(String) is used, the output is quoted
        // - out.jsonValue() cannot be used because it throws UnsupportedOperationException
        Number n = new Number() {
        	
            @Override public long longValue() {		return 0; }
            @Override public int intValue() { 		return 0; }
            @Override public float floatValue() {	return 0; }
            @Override public double doubleValue() { return 0; }
            @Override public String toString() { 	return value.stripTrailingZeros().toPlainString(); }

        };

        out.value(n);
    }

    @Override
    public BigDecimal read(JsonReader in) throws IOException {
        return new BigDecimal(in.nextString());
    }
}

