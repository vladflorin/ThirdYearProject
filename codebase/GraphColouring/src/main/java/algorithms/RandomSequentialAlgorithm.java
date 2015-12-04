package main.java.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class RandomSequentialAlgorithm implements Algorithm{

	Graph graph;
	int k;
	long time;
	List<Node> randomSeq;
	
	String script = "";

	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
		
		// Generate random sequence
		randomSeq = generateRandomSequence(graph);
		
		script = script + "Step 1: Colouring sequence\n" + getNodeIdSequence().toString() + "\n\n"; 
		script = script + "Step 2: Colour the nodes\n";
	}

	public void compute() {
		long startTime = System.nanoTime();
		
		for (Node currentNode : randomSeq) {
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");
			script = script + "Assign node " + currentNode.getId() + " colour " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + "\n";
		}

		long stopTime = System.nanoTime();
		time = stopTime - startTime;
	}
	
	public static List<Node> generateRandomSequence(Graph graph) { 
        List<Node> nodeList = new ArrayList<Node>();
        
        for (Node node : graph.getNodeSet()) {
        	nodeList.add(node);
        }
        
        Collections.shuffle(nodeList);    
		return nodeList;
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
	
	public List<Integer> getNodeIdSequence() {
		List<Integer> result = new ArrayList<>();
		
		for (Node currentNode : randomSeq) {
			result.add(Integer.parseInt(currentNode.getId()));
		}
		
		return result;
	}
	
	public String getScript() {
		return script;
	}

}
