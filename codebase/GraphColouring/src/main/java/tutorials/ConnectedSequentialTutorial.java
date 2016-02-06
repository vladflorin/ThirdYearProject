package main.java.tutorials;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class ConnectedSequentialTutorial extends Tutorial{

	@Override
	public void init(Graph givenGraph) {
		graph = givenGraph;
		
		// Initialise the color of each node with zero
		initialiseGraph();
		
		// Initially the chromatic number of the graph should be 0 (no color)
		k = 0;
		
		// Generate random sequence
		colouringSequence = generateColouringSequence(graph);
	}

	public static List<Node> generateColouringSequence(Graph graph) {
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
	
}
