/**
 * 
 */
package com.classic.datastructure;

/**
 * @author amitp
 *
 */
public class Edge implements Comparable<Edge>{
	public int fromVertex;
	public int weight;
	public int toVertex;
	boolean isDirecetd;
	// for flow only
	public int capacity; /* capacity of edge */
	public int residual; /* residual capacity of edge */
	public int flow;/* for weigth only*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromVertex;
		result = prime * result + toVertex;
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if ((fromVertex != other.fromVertex) && (!isDirecetd && (fromVertex != other.toVertex)))
			return false;
		if (toVertex != other.toVertex &&(!isDirecetd && (toVertex != other.fromVertex)))
			return false;
		if (weight != other.weight)
			return false;
		
		return true;
	}
	@Override
	public int compareTo(Edge o) {
		
		return Integer.compare(weight, o.weight);
	}
	@Override
	public String toString() {
		return "Edge [fromVertex=" + fromVertex + ", weight=" + weight + ", toVertex=" + toVertex + ", isDirecetd="
				+ isDirecetd + ", capacity=" + capacity + ", residual=" + residual + ", flow=" + flow + "]\n";
	}
	
	
	

}
