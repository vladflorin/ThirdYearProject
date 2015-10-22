package main.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.algorithms.SaturationLargestFirstAlgorithm;
import main.java.com.GraphGenerator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class SaturationLargestFirstAlgorithmTest {
	
	@Test
	public void testSaturationLargestFirstAlgorithmRandomGraph() throws InterruptedException {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));
				
		SaturationLargestFirstAlgorithm saturationLF = new SaturationLargestFirstAlgorithm();
		saturationLF.init(randomGraph);
		saturationLF.compute();
		
		System.out.println("k: " + saturationLF.getK());
		System.out.println("time: " + saturationLF.getTime());
		
		assertNotNull(randomGraph);
	}
	
	@Test
	public void testSaturationLargestFirstAlgorithmGivenGraph() throws InterruptedException {		
		Graph graph = createGraph();
				
		System.out.println("Graph size: " + graph.getNodeCount());
		
		SaturationLargestFirstAlgorithm saturationLF = new SaturationLargestFirstAlgorithm();
		saturationLF.init(graph);
		saturationLF.compute();
		
		System.out.println("k: " + saturationLF.getK());
		System.out.println("time: " + saturationLF.getTime());	
		
		assertNotNull(graph);
	}
	
	private Graph createGraph() {
		Graph graph = new SingleGraph("GivenGraph");
		graph.addNode("0");
		graph.addNode("1");
		graph.addNode("2");
		graph.addNode("3");
		graph.addNode("4");
		graph.addNode("5");
		graph.addNode("6");

		graph.addEdge("01", "0", "1");
		graph.addEdge("12", "1", "2");
		graph.addEdge("13", "1", "3");
		graph.addEdge("16", "1", "6");
		graph.addEdge("34", "3", "4");
		graph.addEdge("35", "3", "5");
		graph.addEdge("56", "5", "6");
		
		return graph;
		
	}
}
