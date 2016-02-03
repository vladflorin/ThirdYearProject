package main.java.algorithms;

import java.util.Iterator;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class SaturationLargestFirstAlgorithm implements Algorithm{

	Graph graph;
	int k;
	long time;
	
	long startTime;
	
	public void init(Graph givenGraph) {
		startTime = System.nanoTime();

		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;
	}

	public void compute() {		
		while (!isGraphFullyColoured()) {
			// Find the vertex with the highest saturation
			Node maxSaturationNode = findMaxSaturationNode();
			
			// Assign smallest possible colour to the minSaturationVertex
			maxSaturationNode.setAttribute("colour", findSmallestPossibleColour(maxSaturationNode));
			maxSaturationNode.addAttribute("ui.style", "fill-color: " + Constants.COLOURS[(int) maxSaturationNode.getAttribute("colour")] + ";");
		}
				
		long stopTime = System.nanoTime();
		time = stopTime - startTime;
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
	
	private Boolean isGraphFullyColoured() {
		Boolean coloured = true;
		for (Node currentNode : graph.getNodeSet()) {
			if ((int) currentNode.getAttribute("colour") == -1) {
				coloured = false;
				break;
			}
		}
		return coloured;
	}
	
	private Node findMaxSaturationNode() {
		Node maxSaturationNode = null;
		long maxSaturation = -1;
		
		for (Node node : graph.getNodeSet()) {
			if ((int)node.getAttribute("colour") == -1) {
				long currentNodeSaturation = getNodeSaturation(node);
			
				if (currentNodeSaturation > maxSaturation) {
					maxSaturation = currentNodeSaturation;
					maxSaturationNode = node;
				} else if ((currentNodeSaturation == maxSaturation) && (node.getDegree() > maxSaturationNode.getDegree())) {
					maxSaturation = currentNodeSaturation;
					maxSaturationNode = node;
				}
			}
		}
		
		return maxSaturationNode;
	}
	
	private long getNodeSaturation(Node node) {
		long saturation = 0;
		
		Iterator<Node> iterator = node.getNeighborNodeIterator();
		
		while (iterator.hasNext()) {
			Node currentNode = iterator.next();
			
			int colour = currentNode.getAttribute("colour");
			if (colour != -1) {
				Boolean uniqueColour = true;
				
				Iterator<Node> nodeIterator = node.getNeighborNodeIterator();
				while (nodeIterator.hasNext()) {
					Node node2 = nodeIterator.next();
					if ((int) node2.getAttribute("colour") == colour) {
						uniqueColour = false;
						break;
					}
				}
				
				if (uniqueColour) {
					saturation++;
				}
			}
		}
		
		return saturation;
	}
	
	public int getK() {
		return k;
	}
	
	public long getTime() {
		return time;
	}

}
