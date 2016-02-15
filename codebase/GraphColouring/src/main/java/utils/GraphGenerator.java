package main.java.utils;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class GraphGenerator {

	public static Graph setAttributes(Graph graph) {
		for (Node node : graph.getNodeSet()) {
			NodeUtils.setInitialSize(node);
			NodeUtils.addLabel(node);
		}
		
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		
		return graph;
	}
	
	// Generate a random graph
	public static Graph generate(int size, int avgDegree, Graph graph) {		
		Generator gen = new RandomGenerator(avgDegree);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<size; i++) {
		   gen.nextEvents();
		}
		gen.end();
	
		setAttributes(graph);
		
		return graph;
	}

	// Generate smallest hard-to-colour graphs
	public static Graph generateRSGraph(Graph graph) {
		graph.clear();
		
		graph.addNode("1");
		graph.addNode("2");
		graph.addNode("3");
		graph.addNode("4");
		
		graph.addEdge("14", "1", "4");
		graph.addEdge("43", "4", "3");
		graph.addEdge("32", "3", "2");

		setAttributes(graph);
		
		return graph;
	}
	
	public static Graph generateLFGraph(Graph graph) {
		graph.clear();

		for (int index = 1; index <= 7; index++) {
			graph.addNode(String.valueOf(index));
		}
		
		graph.addEdge("17", "1", "7");
		graph.addEdge("16", "1", "6");
		graph.addEdge("13", "1", "3");
		graph.addEdge("14", "1", "4");
		graph.addEdge("27", "2", "7");
		graph.addEdge("26", "2", "6");
		graph.addEdge("23", "2", "3");
		graph.addEdge("25", "2", "5");
		graph.addEdge("34", "3", "4");
		graph.addEdge("35", "3", "5");
		graph.addEdge("45", "5", "4");

		setAttributes(graph);

		return graph;
	}
	
	public static Graph generateSLGraph(Graph graph) {
		graph.clear();

		for (int index = 1; index <= 8; index++) {
			graph.addNode(String.valueOf(index));
		}
		
		graph.addEdge("12", "1", "2");
		graph.addEdge("13", "1", "3");
		graph.addEdge("15", "1", "5");
		graph.addEdge("17", "1", "7");	
		graph.addEdge("23", "2", "3");
		graph.addEdge("24", "2", "4");
		graph.addEdge("28", "2", "8");	
		graph.addEdge("34", "3", "4");
		graph.addEdge("35", "3", "5");	
		graph.addEdge("46", "4", "6");
		graph.addEdge("48", "4", "8");	
		graph.addEdge("56", "5", "6");
		graph.addEdge("57", "5", "7");		
		graph.addEdge("67", "6", "7");
		graph.addEdge("68", "6", "8");
		graph.addEdge("78", "7", "8");

		setAttributes(graph);

		return graph;
	}
	
	public static Graph generateCSGraph(Graph graph) {
		graph.clear();

		for (int index = 1; index <= 5; index++) {
			graph.addNode(String.valueOf(index));
		}
		
		graph.addEdge("12", "1", "2");
		graph.addEdge("15", "1", "5");
		graph.addEdge("23", "2", "3");
		graph.addEdge("24", "2", "4");	
		graph.addEdge("25", "2", "5");
		graph.addEdge("34", "3", "4");
		graph.addEdge("45", "4", "5");

		setAttributes(graph);

		return graph;
	}
	
	public static Graph generateSLFGraph(Graph graph) {
		graph.clear();

		for (int index = 1; index <= 8; index++) {
			graph.addNode(String.valueOf(index));
		}
		
		graph.addEdge("12", "1", "2");
		graph.addEdge("13", "1", "3");
		graph.addEdge("14", "1", "4");
		graph.addEdge("15", "1", "5");		
		graph.addEdge("23", "2", "3");
		graph.addEdge("24", "2", "4");
		graph.addEdge("26", "2", "6");	
		graph.addEdge("57", "5", "7");
		graph.addEdge("58", "5", "8");
		graph.addEdge("67", "6", "7");
		graph.addEdge("68", "6", "8");	
		graph.addEdge("78", "7", "8");

		setAttributes(graph);

		return graph;
	}
	
	
}
