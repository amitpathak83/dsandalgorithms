/**
 * 
 */
package com.classic.algorithms;

import java.util.Arrays;

/**
 * @author amitp
 *
 */
public class BinarySearchRec {

	public static int binarySearchRecursively(int arr[], int low, int high, int key) {
		int index = -1;
		if (low <= high) {
			int mid = low + (high - low) / 2;
			if (mid < arr.length && arr[mid] == key) {
				return mid;
			} else if (mid < arr.length && arr[mid] > key) {
				index = binarySearchRecursively(arr, low, mid - 1, key);
			} else if (mid < arr.length && arr[mid] < key) {
				index = binarySearchRecursively(arr, mid + 1, high, key);
			}
		}
		return index;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int arr[] = { -1, 0, 3, 5, 9, 12 };
		Arrays.sort(arr);
		System.out.println(binarySearchRecursively(arr, 0, arr.length, 13));
		// System.out.println(binarySearchRecursively(arr, 0, arr.length, 11));
		// System.out.println(binarySearchRecursively(arr, 0, arr.length, 12));
		// System.out.println(binarySearchRecursively(arr, 0, arr.length, 4));
	}

}
