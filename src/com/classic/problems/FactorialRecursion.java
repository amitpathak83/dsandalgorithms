/**
 * 
 */
package com.classic.problems;

/**
 * @author amitp
 *
 */
public class FactorialRecursion {
	
	public static int factrecursion(int n) {
		
		if(n<=1) {
			return 1;
		}else {
			return n * factrecursion(n-1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(factrecursion(5));
		System.out.println(factrecursion(0));
		System.out.println(factrecursion(6));

	}

}
