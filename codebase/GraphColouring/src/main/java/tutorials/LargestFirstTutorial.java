package main.java.tutorials;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class LargestFirstTutorial extends Tutorial{

	@Override
	public void init(Graph givenGraph) {
		graph = givenGraph;	
		
		// Initialise the color of each node with zero
		initialiseGraph();

		// Initially the chromatic number of the graph should be -1 (no color)
		k = 0;

		// Non-increasing order of degrees
		colouringSequence = generateColouringSequence(graph);
	}

	public List<Node> generateColouringSequence(Graph graph) {
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

}
