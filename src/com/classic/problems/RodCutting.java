/**
 * 
 */
package com.classic.problems;

import java.util.Arrays;

/**
 * @author amitp
 *
 */
public class RodCutting {

	static int price[] = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sol = new int[price.length];
		for (int i = 0; i < price.length; i++) {
			for (int j = 0; j <= i; j++) {

				sol[i] = Math.max(sol[i], price[j] + sol[i - j]);
			}
		}

		System.out.println(Arrays.toString(sol));

	}

}
