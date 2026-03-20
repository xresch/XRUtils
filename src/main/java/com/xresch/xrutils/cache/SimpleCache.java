package com.xresch.xrutils.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**************************************************************************************************************
 * A simple Least Recently Used(LRU) Cache based on LinkedHashMap.
 * 
 * Copyright Reto Scheiwiller, 2026
 * @license MIT License
 * 
 **************************************************************************************************************/
public class SimpleCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;
	
	private final int maxEntries;

    public SimpleCache(int maxEntries) {
        super(maxEntries + 1, 0.75f, true); // access-order = true → LRU
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }
}
