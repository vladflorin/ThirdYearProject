package main.java.dimacs;

import org.graphstream.graph.Graph;

public class DimacsTest {

	private Graph graph;
	private int benchmark;
	
	public DimacsTest(Graph graph, int benchmark) {	
		this.graph = graph;
		this.benchmark = benchmark;
	}
	
	public Graph getGraph() {
		return graph;
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public int getBenchmark() {
		return benchmark;
	}
	
	public void setBenchmark(int benchmark) {
		this.benchmark = benchmark;
	}
}
