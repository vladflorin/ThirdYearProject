package main.java.utils;

import main.java.utils.Constants;

import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphGenerator {

	// Generate a random graph
	public static Graph generate(long size, Graph graph) {		
		Boolean hasOneComponent = false;
		
		while (!hasOneComponent) {
			Generator gen = new RandomGenerator(size * Math.random());
			gen.addSink(graph);
			gen.begin();
			for(int i=0; i<size; i++) {
			   gen.nextEvents();
			}
			gen.end();
			
			ConnectedComponents cc = new ConnectedComponents();
			cc.init(graph);
			
			if (cc.getConnectedComponentsCount() == 1) {
				hasOneComponent = true;
			} else {
				graph.clear();
			}
		}		
		return graph;
	}

}
