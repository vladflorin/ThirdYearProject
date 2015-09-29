package main.java.com;

import org.graphstream.graph.Graph;

public class Test {

	public Test() {
		super();
	}

	private Graph initialGraph;
	private Graph resultGraph;
	private int chromaticNumber;
	private long time;

	public Graph getInitialGraph() {
		return initialGraph;
	}
	
	public void setInitialGraph(Graph initialGraph) {
		this.initialGraph = initialGraph;
	}
	
	public Graph getResultGraph() {
		return resultGraph;
	}
	
	public void setResultGraph(Graph resultGraph) {
		this.resultGraph = resultGraph;
	}
	
	public int getChromaticNumber() {
		return chromaticNumber;
	}
	
	public void setChromaticNumber(int chromaticNumber) {
		this.chromaticNumber = chromaticNumber;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
}
