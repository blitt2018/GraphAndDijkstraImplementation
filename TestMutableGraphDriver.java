package cs228hw4.graph;

import java.util.ArrayList;

public class TestMutableGraphDriver {
	public static void main(String[] args) {
		ArrayList<Integer> verts = new ArrayList<Integer>(); 
		ArrayList<Integer[]> edges = new ArrayList<Integer[]>(); 
		//Integer copyInt = new Integer(7);
		verts.add(5); 
		verts.add(7); 
		verts.add(13); 
		verts.add(25); 
		verts.add(23); 
		//verts.add(copyInt); 
		
		Integer[] edge1 = {7, 5};
		Integer[] edge2 = {5, 13}; 
		Integer[] edge3 = {7, 13}; 
		Integer[] edge4 = {7, 25}; 
		Integer[] edge5 = {5, 25}; 
		Integer[] edge6 = {7, 23}; 
		Integer[] edge7 = {25, 23}; 
		
		edges.add(edge1);
		edges.add(edge2); 
		edges.add(edge3); 
		edges.add(edge4); 
		edges.add(edge5); 
		edges.add(edge6);  
		edges.add(edge7); 
		
		ArrayList<Integer> costArr = new ArrayList<Integer>(); 
		costArr.add(3); 
		costArr.add(2);
		costArr.add(8);
		costArr.add(1);
		costArr.add(10);
		costArr.add(100); 
		costArr.add(3); 
		
		//copyInt shows that we can have duplicates because we check equality by reference 
		TestGraph<Integer> t = new TestGraph<Integer>(verts, edges, costArr); 
		System.out.println(t.getEdgeCost(5, 25)); 
		System.out.println(t.getEdgeCost(7, 13)); 
		System.out.println(t.getNeighbors(7)); 
		System.out.println(t.getNeighbors(5)); 
		//System.out.println(t.getNeighbors(copyInt)); 
		System.out.println(t.numVertices());
		
		for (Integer i : t) {
			System.out.println(i); 
		}
		
		CS228Dijkstra<Integer> myDijkstra = new CS228Dijkstra<Integer>(t); 
		myDijkstra.run(7);
		
		System.out.println("shortest paths to 23, 7, and 26"); 
		//System.out.println(myDijkstra.getShortestPath(5)); 
		System.out.println(myDijkstra.getShortestPath(23)); 
		System.out.println(myDijkstra.getShortestPath(7));
		
		
		System.out.println("shortest distances to nodes 23, 7, and 26"); 
		System.out.println(myDijkstra.getShortestDistance(23)); 
		System.out.println(myDijkstra.getShortestDistance(7)); 
		
		
		t.addVertex(45);
		Integer[] extraEdge1 = {25, 45}; 
		t.addEdge(extraEdge1);
		t.setEdgeCost(25, 45, 29);
		
		Integer[] extraEdge2 = {7, 45}; 
		t.addEdge(extraEdge2);
		t.setEdgeCost(7, 45, 11);
		
		t.setEdgeCost(7, 23, 1);
		System.out.println(t.getEdgeCost(7,  45)); 
		System.out.println(t.getNeighbors(7)); 
		
		CS228Dijkstra<Integer> nextDijkstra = new CS228Dijkstra<Integer>(t); 
		nextDijkstra.run(7);
		nextDijkstra.getShortestDistance(23); 
		System.out.println(nextDijkstra.getShortestDistance(45));
		System.out.println(nextDijkstra.getShortestPath(23)); 
		
		TestGraph<String> startEmpty = new TestGraph<String>(); 
		startEmpty.addVertex("A"); 
		startEmpty.addVertex("B");
		startEmpty.addVertex("C"); 
		
		String[] e1 = {"A","C"}; 
		String[] e2 = {"A","B"};
		startEmpty.addEdge(e1);
		startEmpty.addEdge(e2);
		
	}
}
