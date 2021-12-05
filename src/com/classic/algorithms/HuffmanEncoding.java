package com.classic.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

class Node {

	int data;
	String c;

	Node left;
	Node right;
}

class TreeComparator implements Comparator<Node> {
	public int compare(Node x, Node y) {

		return x.data - y.data;
	}
}

public class HuffmanEncoding {

	public static void printHuffManTree(Node root, String s) {

		if (root.left == null && root.right == null && !root.c.equals("-")) {

			System.out.println(root.c + "->" + s);

			return;
		}

		printHuffManTree(root.left, s + "0");
		printHuffManTree(root.right, s + "1");
	}

	// main function
	public static void main(String[] args) {

		int n = 6;
		String[] chars = { "a", "b", "c", "d", "e", "f" };
		int[] freqs = { 4, 4, 12, 13, 16, 44 };

		PriorityQueue<Node> q = new PriorityQueue<Node>(n, new TreeComparator());

		for (int i = 0; i < n; i++) {
			Node currNode = new Node();
			currNode.c = chars[i];
			currNode.data = freqs[i];
			currNode.left = null;
			currNode.right = null;
			q.add(currNode);
		}

		Node root = null;

		while (q.size() > 1) {
			Node x = q.poll();
			Node y = q.poll();
			Node f = new Node();
			f.data = x.data + y.data;
			f.c = "-";
			f.left = x;
			f.right = y;
			root = f;
			q.add(f);
		}

		printHuffManTree(root, "");
	}
}
