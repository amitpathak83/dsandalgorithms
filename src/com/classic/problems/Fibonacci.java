package com.classic.problems;

import java.util.HashMap;

public class Fibonacci {
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static {
		map.put(0, 0);
		map.put(1, 1);
	}

	public int fib(int n) {
		if (map.containsKey(n)) {
			return map.get(n);
		} else {
			int fib = fib(n - 1) + fib(n - 2);
			map.put(n, fib);
			return fib;
		}

	}

}