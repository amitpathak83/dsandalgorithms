/**
 * 
 */
package com.classic.algorithms;

import java.util.Arrays;

/**
 * @author amitp
 *
 */
public class Sorts {
	public static void insertionSort(int[] nums) {
		if (nums != null) {
			int length = nums.length;
			for (int i = 1; i < length; i++) {
				int curr = nums[i];
				for (int j = i - 1; j >= 0; j--) {
					if (nums[j] > curr) {
						// swap
						nums[j + 1] = nums[j];
						nums[j] = curr;
					} else {
						break;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 1, 7, 6, 5, 4, 9 };
		Sorts.insertionSort(nums);
		System.out.println(Arrays.toString(nums));

	}

}
