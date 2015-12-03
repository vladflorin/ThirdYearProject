package main.java.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class LargestFirstAlgorithm implements Algorithm{

	Graph graph;
	int k;
	long time;
	
	List<Node> largestFirstList;
	
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;

		// Non-increasing order of degrees
		largestFirstList = generateLargestFirstList();		
	}

	public void compute() {
		long startTime = System.nanoTime();
		
		System.out.println(largestFirstList);
		
		for (Node currentNode : largestFirstList) {
			if (currentNode != null) {
				currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
				currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");
			}
		}
				
		long stopTime = System.nanoTime();
		time = stopTime - startTime;
	}
	
	public List<Node> generateLargestFirstList() {
		List<Node> list = new ArrayList<Node>();
		
		for (int index = 0; index < graph.getNodeCount(); index++) {
			
			Node nodeMaxDegree = null;
			int max = -1;
			
			for (Node node : graph.getNodeSet()) {
				if (node.getDegree() > max && !list.contains(node)) {
					max = node.getDegree();
					nodeMaxDegree = node;
				}
			}
			
			if (nodeMaxDegree != null) {
				list.add(nodeMaxDegree);
			}
		}
		
		return list;
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
	
	public long getTime() {
		return time;
	}

}
