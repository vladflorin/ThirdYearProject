package main.java.com;

import main.java.utils.Constants;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphGenerator {

	private final static int avgDegreeWeight = Constants.AVG_DEGREE_WEIGHT;
	private final static int avgDegree = Constants.AVG_DEGREE;

	// Generate a random graph
	public static Graph generate(long size) {
		Graph graph = new SingleGraph("Random" + Math.random());
		Generator gen = new RandomGenerator(avgDegree);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<size; i++) {
		    gen.nextEvents();
		}
		gen.end();
		return graph;
	}
	
	// Generate a random graph using avgDegree
		public static Graph generate(long size, int avgDegree) {
			Graph graph = new SingleGraph("Random" + Math.random());
			Generator gen = new RandomGenerator(avgDegree);
			gen.addSink(graph);
			gen.begin();
			for(int i=0; i<size; i++) {
			    gen.nextEvents();
			}
			gen.end();
			return graph;
		}
	
	public static int getAvgDegree() {
		return avgDegreeWeight + 1;
	}

}
