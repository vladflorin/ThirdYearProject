package main.java.algorithms;

import java.util.Iterator;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class GreedyColouringAlgorithm implements Algorithm{

	Graph graph;
	int k;
	
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
	}

	public void compute() {
		for (int index = 0; index < graph.getNodeCount(); index++) {
			Node currentNode = graph.getNode(index);
			
			System.out.println("Current node " + currentNode.getId() + " (" + currentNode.getAttribute("colour") + "): ");
			
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			
			System.out.println("Current node " + currentNode.getId() + " (" + currentNode.getAttribute("colour") + "): ");
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");
			
			System.out.println();
		}
	}
	
	public void initialiseGraph() {
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
	
}
