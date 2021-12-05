/**
 * 
 */
package com.classic.algorithms;

import java.util.HashMap;

/**
 * @author amitp
 *
 */
public class BoyerMooreStringMatching {

	public static int boyerMoore(String text, String word) {

		int matchIndex = -1;

		// create last match
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			hashMap.put(chars[i], i);
		}
		int m = word.length();
		int n = text.length();
		int i = m - 1;
		int j = m - 1;
		while (i <= n - 1) {
			if (word.charAt(j) == text.charAt(i)) {
				if (j == 0) {
					return i;
				} else {
					i--;
					j--;
				}
			} else {
				i = i + m - Math.min(j, 1+hashMap.getOrDefault(text.charAt(i), -1));
				j = m - 1;
			}
		}
		return i;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(boyerMoore("helloamitpathak", "pathak"));

	}

}
