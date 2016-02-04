package main.java.tutorials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;
import main.java.utils.NodeUtils;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class Tutorial implements Algorithm{

	protected Graph graph;
	protected int k;
	
	List<Node> colouringSequence;
	private List<Node> prevNodeList = new ArrayList<Node>();
	
	private String script = "";

	public void Tutorial() {	
	}
	
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be 0 (no color)
		k = 0;
		
		// Generate colouring sequence
		colouringSequence = generateColouringSequence(graph);
	}

	public void compute() {
		for (Node currentNode : colouringSequence) {
			currentNode.setAttribute("colour", (int) findSmallestPossibleColour(currentNode));
			currentNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + ";");
			script = script + "Assign node " + currentNode.getId() + " colour " + Constants.COLOURS[(int) currentNode.getAttribute("colour")] + "\n";
		}

	}
	
	public void computeNextStep() {
		if (colouringSequence.get(0) == null) { 
			return;
		}
		
		// Get the first node of colouring sequence
		Node node = colouringSequence.get(0);
		
		// Colour the node
		node.setAttribute("colour", (int) findSmallestPossibleColour(node));
		node.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) node.getAttribute("colour")] + ";");
		
		script = script + "Assign node " + node.getId() + " colour " + Constants.COLOURS[(int) node.getAttribute("colour")] + "\n";	

		// Remove the node from the colouring sequence
		colouringSequence.remove(node);
		
		// Add node to previousNodesList
		prevNodeList.add(node);
	}
	
	public void computePreviousStep() {
		if (prevNodeList.size() == 0) {
			return;
		}
		
		// Get the last element of previousNodesList
		Node node = prevNodeList.get(prevNodeList.size() - 1);
		
		// Uncolour the node (black)
		node.setAttribute("colour", -1);
		NodeUtils.setColourBlack(node);
		
		script = script + "Undo the colouring of node " + node.getId() + "\n";	

		// Remove from previousNodesList
		prevNodeList.remove(node);
		
		// Add node to colouring sequence
		colouringSequence.add(0, node);
	}
	
	private static List<Node> generateColouringSequence(Graph graph) { 
        List<Node> nodeList = new ArrayList<Node>();
        
		return nodeList;
	}
	
	protected void initialiseGraph() {
		for (Node node : graph.getEachNode()) {
			node.setAttribute("colour", -1);
		}
	}
	
	private int[] initialiseUsedColours(int size) {
		int[] usedColours = new int[size];
		for (int index = 0; index < usedColours.length; index++) {
			usedColours[index] = 0;
		}
		return usedColours;
	}
	
	private int findSmallestPossibleColour(Node currentNode) {
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
	
	public String getScript() {
		return script;
	}

	public List<Node> getColouringSequence() {
		return colouringSequence;
	}

}
