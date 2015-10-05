package main.java.com;

import java.util.ArrayList;
import java.util.List;

import main.java.utils.Constants;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

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
	
	public void resetInitialGraph() {
		for (Node node : initialGraph.getEachNode()) {
			node.setAttribute("colour", -1);
			node.addAttribute("ui.style", "fill-color: black;");
		}
	}
}
