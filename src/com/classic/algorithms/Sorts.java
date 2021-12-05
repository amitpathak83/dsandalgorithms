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

	public static void bubbleSort(int nums[]) {
		int n = nums.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
	}

	public static void selectionSort(int nums []) {
		for (int i = 0; i < nums.length - 1; i++) {
			int minindex = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[minindex]) {
					minindex = j;
				}
			}
			int min = nums[minindex];
			nums[minindex] = nums[i];
			nums[i] = min;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 1, 7, 6, 5, 4, 9 };
		// Sorts.insertionSort(nums);
		//Sorts.bubbleSort(nums);
		Sorts.selectionSort(nums);
		System.out.println(Arrays.toString(nums));

	}

}
