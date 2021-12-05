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
		if (low<=high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] > key) {
				index = binarySearchRecursively(arr, low, mid - 1, key);
			} else if (arr[mid] < key) {
				index = binarySearchRecursively(arr, mid + 1, high, key);
			}
		}
		return index;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 2, 5, 6, 1, 9, 11, 12 };
		Arrays.sort(arr);
		System.out.println(binarySearchRecursively(arr, 0, arr.length, 1));
		System.out.println(binarySearchRecursively(arr, 0, arr.length, 11));
		System.out.println(binarySearchRecursively(arr, 0, arr.length, 12));
		System.out.println(binarySearchRecursively(arr, 0, arr.length, 4));
	}

}
