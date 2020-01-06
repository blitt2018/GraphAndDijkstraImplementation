package cs228hw4.graph;

import java.util.ArrayList;

public class TestDijkstraDriver {
	
	public static void main(String[] args) {
		ArrayList<Integer> verts = new ArrayList<Integer>(); 
		ArrayList<Integer[]> edges = new ArrayList<Integer[]>(); 
		Integer copyInt = new Integer(7);
		verts.add(5); 
		verts.add(7); 
		verts.add(13); 
		verts.add(25);
		verts.add(26); 
		verts.add(23); 
		
		
		Integer[] edge1 = {5, 7};
		Integer[] edge2 = {5, 13}; 
		Integer[] edge3 = {7, 13}; 
		Integer[] edge4 = {7, 25}; 
		Integer[] edge5 = {5, 25}; 
		Integer[] edge6 = {25, 26}; 
		Integer[] edge7 = {7, 26}; 
		Integer[] edge8 = {26, 23}; 
		
		edges.add(edge1);
		edges.add(edge2); 
		edges.add(edge3); 
		edges.add(edge4); 
		edges.add(edge5);  
		edges.add(edge6); 
		edges.add(edge7); 
		edges.add(edge8); 
		
		ArrayList<Integer> costArr = new ArrayList<Integer>(); 
		costArr.add(3); 
		costArr.add(2);
		costArr.add(8);
		costArr.add(1);
		costArr.add(10);
		costArr.add(2); 
		costArr.add(6); 
		costArr.add(1); 
		
		//copyInt shows that we can have duplicates because we check equality by reference 
		TestGraph<Integer> t = new TestGraph<Integer>(verts, edges, costArr); 
		
		CS228Dijkstra<Integer> myDijkstra = new CS228Dijkstra<Integer>(t); 
		myDijkstra.run(7);
		
		System.out.println("shortest paths to 23, 7, and 26"); 
		//System.out.println(myDijkstra.getShortestPath(5)); 
		System.out.println(myDijkstra.getShortestPath(23)); 
		System.out.println(myDijkstra.getShortestPath(7));
		System.out.println(myDijkstra.getShortestPath(26)); 
		
		System.out.println("shortest distances to nodes 23, 7, and 26"); 
		System.out.println(myDijkstra.getShortestDistance(23)); 
		System.out.println(myDijkstra.getShortestDistance(7)); 
		System.out.println(myDijkstra.getShortestDistance(26)); 
	}
	
}
