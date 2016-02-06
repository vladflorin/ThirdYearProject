package main.java.tutorials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;

public class SmallestLastTutorial extends Tutorial{

	@Override
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be 0 (no color)
		k = 0;
		
		// Generate random sequence
		try {
			colouringSequence = generateColouringSequence(Graphs.clone(graph));
		} catch (InterruptedException e) {
			logger.error("Something went wrong while generating the colouring sequence.");
		}
	}

	public static List<Node> generateColouringSequence(Graph givenGraph) throws InterruptedException {
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

}
