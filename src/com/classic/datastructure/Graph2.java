/**
 * 
 */
package com.classic.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * @author amitp
 *
 */
public class Graph2 {

	List<Edge>[] rootVetices = null;
	boolean isdirected = false;

	@SuppressWarnings("unchecked")
	public Graph2(int totalVertices, boolean isdirected) {
		super();
		this.rootVetices = new ArrayList[totalVertices + 1];
		for (int i = 0; i < rootVetices.length; i++) {
			if (i != 0) {
				rootVetices[i] = new ArrayList<>();
			}
		}
		this.isdirected = isdirected;
	}

	public void addEdge(int fromVertex, int toVertex, int weight) {
		Edge e = new Edge();
		e.fromVertex = fromVertex;
		e.toVertex = toVertex;
		e.weight = weight;
		e.isDirecetd = this.isdirected;
		rootVetices[fromVertex].add(e);
		if (!isdirected) {
			Edge e2 = new Edge();
			e2.toVertex = fromVertex;
			e2.fromVertex = toVertex;
			e2.weight = weight;
			e2.isDirecetd = this.isdirected;
			rootVetices[toVertex].add(e2);
		}
	}

	public void addEdge(int fromVertex, int toVertex) {
		addEdge(fromVertex, toVertex, 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graph2 graph2 = new Graph2(9, false);
		graph2.isdirected = false;
		graph2.addEdge(1, 2, 4);
		graph2.addEdge(2, 3, 8);
		graph2.addEdge(3, 4, 7);
		graph2.addEdge(4, 5, 9);
		graph2.addEdge(5, 6, 10);
		graph2.addEdge(6, 7, 2);
		graph2.addEdge(7, 8, 1);
		graph2.addEdge(8, 9, 7);
		graph2.addEdge(7, 9, 6);
		graph2.addEdge(8, 1, 8);
		graph2.addEdge(8, 2, 11);
		graph2.addEdge(3, 9, 2);
		graph2.addEdge(3, 6, 4);
		graph2.addEdge(4, 6, 14);
		System.out.println(graph2.kruskal());

	}

	public List<Edge> kruskal() {

		List<Edge> edgesMST = new ArrayList<>();
		List<Edge> allEdges = new ArrayList<Edge>();
		int i = 0;
		for (List<Edge> edges : this.rootVetices) {
			if (i > 0) {
				for (Edge edge : edges) {
					if (!allEdges.contains(edge)) {
						allEdges.add(edge);
					}

				}
			}
			i++;

		}
		Collections.sort(allEdges);
		// Allocate memory for creating V subsets
		Subset subsets[] = new Subset[rootVetices.length];
		for (i = 1; i < rootVetices.length; ++i)
			subsets[i] = new Subset();

		// Create V subsets with single elements
		for (int v = 1; v < rootVetices.length; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}

		for (Edge edge : allEdges) {
			int x = find(subsets, edge.fromVertex);
			int y = find(subsets, edge.toVertex);
			if (x != y) {
				edgesMST.add(edge);
				 Union(subsets, x, y);

			}
		}
		return edgesMST;

	}

	class Subset {
		int parent, rank;
	}

	int find(Subset subsets[], int i) {
		// find root and make root as parent of i
		// (path compression)
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}

	// A function that does union of two sets
	// of x and y (uses union by rank)
	void Union(Subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		// Attach smaller rank tree under root
		// of high rank tree (Union by Rank)
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;

		// If ranks are same, then make one as
		// root and increment its rank by one
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

}
