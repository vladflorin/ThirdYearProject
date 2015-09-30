package main.java.com;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;

public class Test {

	private Graph initialGraph;
	private List<Algorithm> algorithmList;
	
	public Test(long graphSize) {
		super();
		initialGraph = GraphGenerator.generate(graphSize);
		algorithmList = new ArrayList<Algorithm>();
	}

	public List<Algorithm> getAlgorithmList() {
		return algorithmList;
	}

	public void setAlgorithmList(List<Algorithm> algorithmList) {
		this.algorithmList = algorithmList;
	}

	public Graph getInitialGraph() {
		return initialGraph;
	}
	
	public void setInitialGraph(Graph initialGraph) {
		this.initialGraph = initialGraph;
	}
	
}
