package main.java.utils;

import main.java.utils.Constants;

import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphGenerator {

	// Generate a random graph
	public static Graph generate(int size, int avgDegree, Graph graph) {		
		Generator gen = new RandomGenerator(avgDegree);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<size; i++) {
		   gen.nextEvents();
		}
		gen.end();
	
		return graph;
	}

}
