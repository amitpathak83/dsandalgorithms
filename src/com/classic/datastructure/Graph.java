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
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author amitp
 *
 */
public class Graph {

	List<ArrayDeque<Vertex>> vertices;
	ArrayDeque<Vertex> topoSort = new ArrayDeque<>();

	boolean weighted = false;

	boolean isDirected = false;

	int time = 0;

	public Graph(int totalVertices) {
		this.vertices = new ArrayList<ArrayDeque<Vertex>>();
		for (int i = 0; i < totalVertices; i++) {
			ArrayDeque<Vertex> v = new ArrayDeque<Vertex>();
			Vertex v1 = new Vertex();
			v1.value = i;
			v.add(v1);
			vertices.add(v);
		}
	}

	public void addEdge(int from, int to, int weight) {
		Vertex v = new Vertex();
		v.value = to;
		v.weight = weight;
		if (!vertices.get(from).contains(v))
			vertices.get(from).add(v);
		// Need to add in other vertex also
		if (!isDirected) {
			Vertex v2 = new Vertex();
			v2.value = from;
			v2.weight = weight;
			if (!vertices.get(to).contains(v2))
				vertices.get(to).add(v2);
		}
	}
	
	public void dijksatra(int from) {
		ArrayDeque<Vertex>  vertexs = vertices.get(from);
	}

	public void addEdge(int from, int to) {
		addEdge(from, to, 0);
	}

	public static class Vertex {
		public int value;
		public int father;
		public int level;
		public String color = "white";
		public int weight = 0;
		public int d;
		public int f;
		public int distance=Integer.MAX_VALUE;

		@Override
		public String toString() {
			return "Vertex [value=" + value + ", father=" + father + ", level=" + level + ", color=" + color
					+ ", weight=" + weight + ", d=" + d + ", f=" + f + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
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
			if (value != other.value)
				return false;
			return true;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph g = new Graph(6);
		/*
		 * g.addEdge(2, 1); g.addEdge(2, 3); g.addEdge(1, 0); g.addEdge(3, 4);
		 * g.addEdge(3, 5); g.addEdge(4, 5); g.addEdge(4, 6); g.addEdge(4, 7);
		 * g.addEdge(5, 6); g.addEdge(6, 7);
		 */

		// g.doBFS(2);
		g.isDirected=true;
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(0,3);
		g.addEdge(4,5);
		g.addEdge(4,2);
		g.doDFS(0);
		//Arrays.sort(g.topoSort.toArray(new Vertex[0]),(a,b)->a.f-b.f);
		System.out.println(g.topoSort.stream().sorted((a,b)->b.f-a.f).mapToInt(a->a.value).boxed().collect(Collectors.toList()));
	}

	public void doBFS(int startVertex) {
		Queue<Vertex> visitQeueue = new ArrayDeque<Graph.Vertex>();
		HashSet<Integer> set = new HashSet<>();
		Vertex s = vertices.get(startVertex).getFirst();
		s.level = 0;
		s.color = "grey";
		s.father = -1;
		visitQeueue.add(s);
		set.add(s.value);
		while (!visitQeueue.isEmpty()) {
			Vertex curr = visitQeueue.poll();
			int index = 0;
			for (Vertex child : this.vertices.get(curr.value)) {
				if (index > 0 && !set.contains(child.value)) {
					if (child.color.equals("white")) {
						child.level = curr.level + 1;
						child.color = "grey";
						child.father = curr.value;
						visitQeueue.add(child);
						set.add(child.value);
					}
				}
				index++;

			}
			curr.color = "black";

			System.out.println(curr);
		}
	}

	public void doDFS(int startVertex) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < vertices.size(); i++) {
			Vertex s = vertices.get(i).getFirst();

			if (!set.contains(s.value)) {
				set.add(s.value);
				doDFS(s, set);
			}

		}
	}

	public void doDFS(Vertex s, HashSet<Integer> set) {

		time = time + 1;
		int index = 0;
		s.color = "grey";
		s.d = time;
		for (Vertex child : this.vertices.get(s.value)) {
			if (index > 0 && !set.contains(child.value)) {
				if (child.color.equals("white")) {
					child.father = s.value;
					set.add(child.value);
					doDFS(child, set);

				}
			}
			index++;
		}
		time = time + 1;
		s.f = time;
		s.color = "black";
		topoSort.add(s);
		System.out.println(s);
	}
}
