package main.java.tutorials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class GreedyTutorial extends Tutorial{
	
	@Override
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be 0 (no color)
		k = 0;
		
		// Generate colouring sequence
		colouringSequence = generateColouringSequence(graph);
	}
	
	private static List<Node> generateColouringSequence(Graph graph) { 
        List<Node> nodeList = new ArrayList<Node>();
        
        for (Node node : graph.getNodeSet()) {
        	nodeList.add(node);
        }
        
		return nodeList;
	}
}
