package cs228hw4.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * An implementation of the DiGraph interface. Only used for testing Dijkstra algorithm, not meant to be fully 
 * functional or efficient implementation. 
 */
public class TestGraph<V> implements DiGraph<V>{
	
	private class Edge{
		private V firstVertex; 
		private V secondVertex; 
		private int cost; 
		
		private Edge (V inFirstVertex, V inSecondVertex) {
			firstVertex = inFirstVertex; 
			secondVertex = inSecondVertex; 
		}
	}
	
	private ArrayList<V> vertices = new ArrayList<V>(); 
	private ArrayList<Edge> edges = new ArrayList<Edge>(); 
	
	public TestGraph(ArrayList<V> inVertices, ArrayList<V[]> inUnformattedEdges, ArrayList<Integer> inCosts){
		vertices = inVertices; 
		edges = makeFormattedEdges(inUnformattedEdges, inCosts); 
	}
	

	public TestGraph() {
		vertices = new ArrayList<V>(); 
		edges = new ArrayList<Edge>(); 
	}
	
	public void addEdge(V[] inEdge) {
		if (inEdge.length > 2) throw new RuntimeException(); 
		Edge newEdge = new Edge(inEdge[0], inEdge[1]); 
		edges.add(newEdge); 
	}
	
	public boolean hasEdge(V first, V second) {
		for (Edge e : edges) {
			if (e.firstVertex == first && e.secondVertex == second) {
				return true; 
			}
		}
		return false; 
	}
	
	public boolean hasVertex(V vert) {
		if (vertices.contains(vert)) {
			return true; 
		}
		return false; 
	}
	
	public void printVertices() {
		System.out.println(vertices); 
	}
	
	public void printEdges() {
		for (Edge e : edges) {
			System.out.println("From " + e.firstVertex + " to " + e.secondVertex); 
		}
	}
	
	public void addVertex(V vertex) {
		vertices.add(vertex); 
		//System.out.println(vertices);
	}
	
	private ArrayList<Edge> makeFormattedEdges(ArrayList<V[]> inVerts, ArrayList<Integer> inCosts){
		ArrayList<Edge> edgeList = new ArrayList<Edge>(); 
		for (V[] arr : inVerts) {
			edgeList.add(new Edge(arr[0], arr[1])); 
		}
		
		Iterator<Integer> costIter = inCosts.iterator(); 
		for (Edge currEdge : edgeList) {
			currEdge.cost = costIter.next(); 
		}
		return edgeList; 
	}
	
	@Override
	public Set<? extends V> getNeighbors(V vertex) {
		//first we need to find the vertex that we want the neighbors of 
		Set<V> outputVertices = new HashSet<V>(); 
		for (Edge e : edges) {
			if (e.firstVertex == vertex) {
				outputVertices.add(e.secondVertex); 
			}
		}
		return outputVertices; 
	}

	@Override
	public int getEdgeCost(V start, V end) {
		for (Edge e : edges) {
			if (e.firstVertex == start && e.secondVertex == end) {
				return e.cost; 
			}
		}
		
		//shouldn't have to do this unless the specified edge doesn't exist 
		throw new IllegalArgumentException(); 
	}
	
	
	public void setEdgeCost(V start, V end, int inCost) {
		for (Edge e : edges) {
			if (e.firstVertex == start && e.secondVertex == end) {
				e.cost = inCost; 
			}
		}
	}


	@Override
	public int numVertices() {
		return vertices.size(); 
	}

	@Override
	public Iterator<V> iterator() {
		return new myIter(vertices); 
	}
	
	private class myIter implements Iterator<V>{

		private ArrayDeque<V> undiscovered; 
		private ArrayList<Edge> innerEdgeList; 
		
		private myIter(ArrayList<V> vertList) {
			undiscovered = new ArrayDeque<V>(); 
			for (V vert : vertList) {
				undiscovered.offer(vert); 
			}
		}
		
		@Override
		public boolean hasNext() {
			return !undiscovered.isEmpty(); 
		}

		//this part could cause things to not be linear time so be careful/rewrite
		@Override
		public V next() {
			return undiscovered.poll(); 
		}
		
	}
}
