package main.java.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;

public class SmallestLastAlgorithm implements Algorithm{

	static Graph graph;
	int k;
	long time;
	List<Node> smallestLastSeq;
	
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
			
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
		
		// Generate random sequence
		try {
			smallestLastSeq = generateSmallestLastSequence(Graphs.clone(graph));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void compute() {
		long startTime = System.nanoTime();
		
		System.out.println(smallestLastSeq);
		
		for (Node currentNode : graph.getNodeSet()) {
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");
		}

		long stopTime = System.nanoTime();
		time = stopTime - startTime;
	}
	
	public static List<Node> generateSmallestLastSequence(Graph givenGraph) throws InterruptedException {
		ArrayList<Node> list = new ArrayList<>();
       
		while (givenGraph.getNodeCount() > 0) {
			Node minNode = null;
			int minDegree = givenGraph.getNodeCount();
		
			for (Node currentNode : givenGraph.getNodeSet()) {
				if (currentNode.getDegree() < minDegree) {
					minNode = currentNode;
					minDegree = currentNode.getDegree();
				}
			}
		
			if (minNode != null) {
				list.add(graph.getNode(minNode.getId()));
				givenGraph.removeNode(minNode);
			}
		}	
		
		// Inverse list
		Collections.reverse(list);
		
		return list;
	}
	
	private void initialiseGraph() {
		for (Node node : graph.getEachNode()) {
			node.setAttribute("colour", -1);
		}
	}
	
	public int[] initialiseUsedColours(int size) {
		int[] usedColours = new int[size];
		for (int index = 0; index < usedColours.length; index++) {
			usedColours[index] = 0;
		}
		return usedColours;
	}
	
	public int findSmallestPossibleColour(Node currentNode) {
		int[] usedColours  = initialiseUsedColours(k);

		// Get all the nodes linked tocurrentNode
		Iterator<Node> connectedNodesIterator = currentNode.getNeighborNodeIterator();			
		while (connectedNodesIterator.hasNext()){
			Node currentLinkedNode = connectedNodesIterator.next();
			int currentColour = currentLinkedNode.getAttribute("colour");
			
			if (currentColour != -1 && currentColour < k) {
				usedColours[currentColour] = 1;
			}
		}	

		int minColour = -1;

		for (int index = 0; index < usedColours.length; index++) {
			if (usedColours[index] == 0) {
				minColour = index;
			}
		}
		
		if (minColour == -1) {
			minColour = k++;
		}
		
		return minColour;
	}
	
	public int getK() {
		return k;
	}
	
	public long getTime() {
		return time;
	}

}
