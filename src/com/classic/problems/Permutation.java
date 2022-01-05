/**
 * 
 */
package com.classic.problems;

/**
 * @author amitp
 *
 */
public class Permutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		permutation("Amit");

	}

	private static void permutation(String string) {
		permutation("", "Amit");

	}

	private static void permutation(String prefix, String suffix) {
		int length = suffix.length();
		if (length == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < length; i++) {
				permutation(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1, length));

			}
		}

	}

}
