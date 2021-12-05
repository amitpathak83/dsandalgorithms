package com.classic.problems;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

	private LinkedHashMap<Integer, Integer> cache = null;

	public LRUCache(int capacity) {
		cache = new LinkedHashMap<Integer, Integer>(capacity, .75f, true){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > capacity;
			}
		};
	}

	public int get(int key) {
		return cache.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		cache.put(key, value);
	}
}