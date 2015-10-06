package main.java.com;

import org.graphstream.graph.Graph;

public class Algorithm {

	private String name;
	private long time;
	private int k;
	private Graph coloredGraph;
	
	public Algorithm(String name) {
		super();
		this.name = name;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public int getK() {
		return k;
	}
	
	public void setK(int k) {
		this.k = k;
	}
	
	public Graph getColoredGraph() {
		return coloredGraph;
	}
	
	public void setColoredGraph(Graph coloredGraph) {
		this.coloredGraph = coloredGraph;
	}
	
	public String getName() {
		return name;
	}

}
