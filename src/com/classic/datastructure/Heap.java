/**
 * 
 */
package com.classic.datastructure;

import java.util.Arrays;

/**
 * @author amitp
 *
 */
public class Heap {

	int arr[] = null;

	int size = 0;

	public Heap(int[] arr) {
		super();
		this.arr = arr;
		size = arr.length;
	}

	public int parent(int i) {
		return i % 2 == 0 ? (i - 1) / 2 : i / 2;
	}

	public int left(int i) {
		return (2 * i) + 1;
	}

	public int right(int i) {
		return (2 * i) + 2;
	}

	public void maxHeapify(int i) {
		int largest = i;
		int left = left(i);
		int right = right(i);
		if (left < size && arr[left] > arr[i]) {
			largest = left;
		}
		if (right < size && arr[right] > arr[largest]) {
			largest = right;
		}
		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			maxHeapify(largest);
		}
	}

	public void buildHeap() {
		for (int i = (size / 2) - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	public void heapSort() {
		this.buildHeap();
		for (int i = arr.length - 1; i >= 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			this.size = this.size - 1;
			maxHeapify(0);
		}
	}

	public int heapMax() {
		return arr[0];
	}

	public int extractHeapMax() {
		int max = arr[0];
		arr[0] = arr[this.size - 1];
		this.size = this.size - 1;
		maxHeapify(0);
		return max;
	}

	public void increaseKey(int i, int key) {
		if (key > arr[i]) {
			arr[i] = key;
			while (i > 0 && arr[parent(i)] < arr[i]) {
				int temp = arr[i];
				arr[i] = arr[parent(i)];
				arr[parent(i)] = temp;
				i = parent(i);
			}
		}
	}

	public void insertKey(int key) {
		size = size + 1;
		if (size > arr.length) {
			int newarr[] = new int[size];
			System.arraycopy(arr, 0, newarr, 0, arr.length);
			arr = newarr;
		}
		arr[size - 1] = Integer.MIN_VALUE;
		increaseKey(size-1, key);
	}

	public static void main(String[] args) {
		int arr[] = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		Heap h = new Heap(arr);
		h.buildHeap();
		// h.heapSort();
		System.out.println(Arrays.toString(h.arr));
		System.out.println(h.extractHeapMax());
		System.out.println(h.extractHeapMax());
		System.out.println(Arrays.toString(h.arr));
		h.insertKey(17);
		System.out.println(h.extractHeapMax());
		h.insertKey(17);
		h.insertKey(18);
		h.insertKey(16);
		h.insertKey(19);
		h.insertKey(20);
		System.out.println(h.extractHeapMax());
	}
}
