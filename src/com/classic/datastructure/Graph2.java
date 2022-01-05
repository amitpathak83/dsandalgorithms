/**
 * 
 */
package com.classic.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author amitp with help form
 *         https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
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
		// System.out.println(graph2.kruskal());
		//System.out.println(graph2.prims());

		Graph2 graph3 = new Graph2(5, true);
		graph3.isdirected = true;
		graph3.addEdge(1, 2, 6);
		graph3.addEdge(1, 5, 7);
		graph3.addEdge(2, 3, 5);
		graph3.addEdge(2, 4, -4);
		graph3.addEdge(2, 5, 8);
		graph3.addEdge(3, 2, -2);
		graph3.addEdge(4, 3, 7);
		graph3.addEdge(4, 1, 2);
		graph3.addEdge(5, 4, 9);
		graph3.addEdge(5, 3, -3);
		//System.out.println(Arrays.toString(graph3.bellmanFord()));

		Graph2 graph4 = new Graph2(5, true);
		graph4.isdirected = true;
		graph4.addEdge(1, 2, 10);
		graph4.addEdge(1, 5, 5);
		graph4.addEdge(2, 3, 1);
		graph4.addEdge(2, 5, 2);
		graph4.addEdge(3, 4, 4);
		graph4.addEdge(4, 1, 7);
		graph4.addEdge(4, 3, 6);
		graph4.addEdge(5, 2, 3);
		graph4.addEdge(5, 3, 9);
		graph4.addEdge(5, 4, 2);
		System.out.println(graph4.dijkstra().toString());

	}

	public List<Vertex> prims() {
		List<Vertex> edgesMST = new ArrayList<>();
		// create a queue of vertices
		PriorityQueue<Vertex> vertices = new PriorityQueue<Graph2.Vertex>();
		Vertex[] verticesarr = new Vertex[this.rootVetices.length];
		// add all vertices to queue
		for (int i = 1; i < this.rootVetices.length; i++) {
			Vertex v = new Vertex();
			v.vertex = i;
			if (i == 1) {
				v.parent = -1;
				v.key = 0;
			}
			vertices.add(v);
			verticesarr[i] = v;
		}
		while (!vertices.isEmpty()) {
			Vertex v = vertices.poll();
			edgesMST.add(v);
			List<Edge> adj = this.rootVetices[v.vertex];
			for (Edge e : adj) {
				Vertex v2 = new Vertex();
				v2.vertex = e.toVertex;
				if (vertices.contains(v2) && e.weight < verticesarr[v2.vertex].key) {
					v2.parent = v.vertex;
					v2.key = e.weight;
					verticesarr[v2.vertex] = v2;
					vertices.remove(v2);
					vertices.add(v2);
				}
			}
		}

		return edgesMST;
	}

	public Vertex[] bellmanFord() {
		Vertex[] verticesarr = new Vertex[this.rootVetices.length];
		// add all vertices to queue
		for (int i = 1; i < this.rootVetices.length; i++) {
			Vertex v = new Vertex();
			v.vertex = i;
			if (i == 1) {
				v.parent = -1;
				v.key = 0;
			}
			verticesarr[i] = v;
		}
		Deque<Vertex> vertices = new ArrayDeque<Graph2.Vertex>();
		vertices.add(verticesarr[1]);
		while (!vertices.isEmpty()) {
			Vertex v = vertices.poll();
			List<Edge> edges = this.rootVetices[v.vertex];
			for (Edge edge : edges) {
				if (!edge.isProcessedInF) {
					vertices.add(verticesarr[edge.toVertex]);
					edge.isProcessedInF = true;
					int weight = edge.weight;
					if (verticesarr[edge.toVertex].key > verticesarr[edge.fromVertex].key + weight) {
						verticesarr[edge.toVertex].key = verticesarr[edge.fromVertex].key + weight;
						verticesarr[edge.toVertex].parent = v.vertex;
					}
				}
			}
		}

		return verticesarr;
	}

	public Set<Vertex> dijkstra() {
		Vertex[] verticesarr = new Vertex[this.rootVetices.length];
		Set<Vertex> edgesMST = new HashSet<>();
		// create a queue of vertices
		PriorityQueue<Vertex> vertices = new PriorityQueue<Graph2.Vertex>();
		// add all vertices to queue
		for (int i = 1; i < this.rootVetices.length; i++) {
			Vertex v = new Vertex();
			v.vertex = i;
			if (i == 1) {
				v.parent = -1;
				v.key = 0;
			}
			verticesarr[i] = v;

		}
		vertices.add(verticesarr[1]);
		while (!vertices.isEmpty()) {
			Vertex v = vertices.poll();
			edgesMST.add(v);
			List<Edge> edges = this.rootVetices[v.vertex];
			for (Edge edge : edges) {
				if (!edgesMST.contains(verticesarr[edge.toVertex])) {
					edge.isProcessedInF = true;
					int weight = edge.weight;
					if (verticesarr[edge.toVertex].key > verticesarr[edge.fromVertex].key + weight) {
						verticesarr[edge.toVertex].key = verticesarr[edge.fromVertex].key + weight;
						verticesarr[edge.toVertex].parent = v.vertex;
					}
					vertices.add(verticesarr[edge.toVertex]);
				}
			}
		}

		return edgesMST;
	}

	private static class Vertex implements Comparable<Vertex> {
		int vertex;
		/**
		 * max value means not yet discovered -1 is for root
		 */
		int parent = Integer.MAX_VALUE;
		int key = Integer.MAX_VALUE;

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(key, o.key);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + vertex;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (vertex != other.vertex)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Vertex [vertex=" + vertex + ", parent=" + parent + ", key=" + key + "]\n";
		}

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

		@Override
		public String toString() {
			return "Subset [parent=" + parent + ", rank=" + rank + "]";
		}

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
