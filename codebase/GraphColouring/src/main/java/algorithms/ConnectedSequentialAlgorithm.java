package main.java.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class ConnectedSequentialAlgorithm implements Algorithm{

	Graph graph;
	int k;
	long time;
	List<Integer> seq;
	
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
		
		// Generate random sequence
		seq = generateBfsSequence(graph);
	}

	public void compute() {
		long startTime = System.nanoTime();
		
		for (int index = 0; index < graph.getNodeCount(); index++) {
			Node currentNode = graph.getNode(seq.get(index));				
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");		
		}

		long stopTime = System.nanoTime();
		time = stopTime - startTime;
	}
	
	public static List<Integer> generateBfsSequence(Graph graph) {
		ArrayList<Integer> list = new ArrayList<Integer>();
        
		if (graph.getNode(0) != null) {
			Iterator<Node> iterator = graph.getNode(0).getBreadthFirstIterator();
			while (iterator.hasNext()) {
				Node currentNode = iterator.next();
				list.add(Integer.parseInt(currentNode.getId()));
			}
		}
		
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
