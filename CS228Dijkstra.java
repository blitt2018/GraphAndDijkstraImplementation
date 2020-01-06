package cs228hw4.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class uses Dijkstras shortest path algorithm to give the shortest path on a given graph to any node from a given node. 
 * The class is capable of running Dijkstras, computing the shortest distance to a node from a given node, and giving the shortest path from a given node to any othe node. 
 */
public class CS228Dijkstra<V> implements Dijkstra<V> {
	
	/**
	 * a hash map of distances that eventually has our shortest distance to each node from a given start node
	 */
	HashMap<V,Integer> distances; 
	
	/**
	 * a hash map of predecessors that can be used to find the shortest path from a given node to any other node 
	 */
	HashMap<V,V> pred; 
	
	/**
	 * an array list containing nodes in the "open" set. Nodes we have seen but aren't finished with essentially. 
	 */
	ArrayList<V> open; 
	
	/**
	 * an array list containing nodes in the "closed" set. Nodes that we have the shortest path for
	 */
	ArrayList<V> closed; 
	
	/**
	 * a graph that is the graph we want to find shortest paths on 
	 */
	DiGraph<V> graph; 
	
	public CS228Dijkstra(DiGraph<V> inGraph) {
		graph = inGraph; 
		distances = new HashMap<V,Integer>(); 
		pred = new HashMap<V, V>(); 
		open = new ArrayList<V>(); 
		closed = new ArrayList<V>(); 
		
		//initializes the pred and distances hash tables with null values for each node 
		for (V node : graph) {
			distances.put(node, null); 
			pred.put(node, null);
		}
	}
	
	/**
	    * Uses Dijkstra's shortest path algorithm to calculate and store the 
	    * shortest paths to every vertex in the graph as well as the total costs
	    * of each of those paths.  This should run in O(E log V) time, where E is
	    * the size of the edge set, and V is the size of the vertex set.
	    * 
	    * @param start the vertex from which shortest paths should be calculated
	    */
	@Override
	public void run(V start) {
		for (V node : graph) {
			if (node.equals(start)) {
				distances.put(node, 0); 
			} else {
				distances.put(node, Integer.MAX_VALUE);
			}
		}
		open.add(start);
		
		while (open.size() > 0) {
			//get value which is the neighbor of x having minimum distance/cost
			//doesn't matter what we init iterating to 
			V iterating = open.get(0);  
			int minDist = Integer.MAX_VALUE;  
			for (V node: open) {
				if (distances.get(node) < minDist) {
					minDist = distances.get(node); 
					iterating = node; 
				}
			}
			
			//remove value we are currently examining 
			open.remove(iterating);
			if (!open.contains(iterating)) {
				closed.add(iterating); 
			}
			
			for (V neighbor : graph.getNeighbors(iterating)) {
				if (!closed.contains(neighbor)) {
					int alt = distances.get(iterating) + graph.getEdgeCost(iterating, neighbor); 
					if (alt < distances.get(neighbor)) {
						distances.put(neighbor, alt); 
						pred.put(neighbor, iterating); 
						if (!open.contains(neighbor)) {
							open.add(neighbor); 
						}
					}
				}
			}
		}
		//System.out.println("Dijkstra distances"); 
		//System.out.println(distances); 
		
		//System.out.println("Dijkstra preds"); 
		//System.out.println(pred); 
	}

	   /**
	    * Retrieve, in O(V) time, the pre-calculated shortest path to the given 
	    * node. Uses overloaded helper method that works recursively. 
	    * 
	    * @param vertex the vertex to which the shortest path, from the start 
	    *        vertex, is being requested
	    * @return a list of vertices comprising the shortest path from the start 
	    *         vertex to the given destination vertex, both inclusive
	    */
	@Override
	public List<V> getShortestPath(V vertex) {
		 //a little clunky, but meets the time requirement 
		 List<V> output = getShortestPath(vertex, new ArrayList<V>()); 
		 Collections.reverse(output);
		 return(output); 
	}

	   /**
	    * Helper method to do the work described in the above method. 
	    * 
	    * Retrieve, in O(V) time, the pre-calculated shortest path to the given 
	    * node.
	    * 
	    * @param vertex the vertex to which the shortest path, from the start 
	    *        vertex, is being requested
	    * @param list an empty list to fill recursively and then return 
	    * @return a list of vertices comprising the shortest path from the given destination vertex to the start vertex, both inclusive
	    */
	private List<V> getShortestPath(V vertex, ArrayList<V> list){
		//System.out.println(distances); 
		if (distances.get(vertex) == 0) {
			list.add(vertex); 
			return list; 
		} else {
			list.add(vertex); 
			return getShortestPath(pred.get(vertex), list); 
		}
		
	}
	
	   /**
	    * Retrieve, in constant time, the total cost to reach the given vertex from
	    * the start vertex via the shortest path.  If there is no path, this value
	    * is Integer.MAX_VALUE.
	    * 
	    * @param vertex the vertex to which the cost of the shortest path, from the
	    *        start vertex, is desired
	    * @return the cost of the shortest path to the given vertex from the start
	    *         vertex or Integer.MAX_VALUE if there is path
	    */
	@Override
	public int getShortestDistance(V vertex) {
		return distances.get(vertex); 
	}

}
