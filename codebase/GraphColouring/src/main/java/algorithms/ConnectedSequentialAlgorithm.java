package main.java.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	long startTime;

	List<Node> seq;
		
	String script = "";

	public void init(Graph givenGraph) {
		startTime = System.nanoTime();
		
		graph = givenGraph;
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
		
		// Generate random sequence
		seq = generateBfsSequence(graph);

		script = script + "Step 1: Colouring sequence\n" + getNodeIdSequence().toString() + "\n\n"; 
		script = script + "Step 2: Colour the nodes\n";
	}

	public void compute() {		
		for (Node currentNode : seq) {
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");		
			
			script = script + "Assign node " + currentNode.getId() + " colour " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + "\n";
		}

		long stopTime = System.nanoTime();
		time = stopTime - startTime;
	}
	
	public static List<Node> generateBfsSequence(Graph graph) {
		List<Node> list = new ArrayList<Node>();
	
		for (Node node : graph.getNodeSet()) {
			if (!list.contains(node)) {
				Iterator<Node> iterator = node.getBreadthFirstIterator();
				while (iterator.hasNext()) {
					Node currentNode = iterator.next();
					if (!list.contains(currentNode)) {
						list.add(currentNode);
					}
				}
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

	public List<Integer> getNodeIdSequence() {
		List<Integer> result = new ArrayList<>();
		
		for (Node currentNode : seq) {
			result.add(Integer.parseInt(currentNode.getId()));
		}
		
		return result;
	}
	
	public String getScript() {
		return script;
	}
}
