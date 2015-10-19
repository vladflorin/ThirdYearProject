package main.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.algorithms.SmallestLastAlgorithm;
import main.java.com.GraphGenerator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class SmallestLastAlgorithmTest {

	@Test
	public void testGenerateSmallestLastSequence() throws InterruptedException {
		Graph graph = createGraph();
		List<Integer> smallestLastSeq = SmallestLastAlgorithm.generateSmallestLastSequence(Graphs.clone(graph));

		System.out.println("Graph size: " + graph.getNodeCount());
		for (int index = 0; index < smallestLastSeq.size(); index++) {
			System.out.print(smallestLastSeq.get(index) + " ");
		}
		
		assertNotNull(graph);
		assertNotNull(smallestLastSeq);
	}
	
	@Test
	public void testSmallestLastAlgorithmRandomGraph() throws InterruptedException {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));
	
		SmallestLastAlgorithm smallestLast = new SmallestLastAlgorithm();
		smallestLast.init(randomGraph);
		smallestLast.compute();

		System.out.println("k: " + smallestLast.getK());
		System.out.println("time: " + smallestLast.getTime());
	
		assertNotNull(randomGraph);
	}
	
	@Test
	public void testSmallestLastAlgorithmGivenGraph() throws InterruptedException {		
		Graph graph = createGraph();
		
		System.out.println("Graph size: " + graph.getNodeCount());
		
		SmallestLastAlgorithm smallestLast = new SmallestLastAlgorithm();
		smallestLast.init(graph);
		smallestLast.compute();
		
		System.out.println("k: " + smallestLast.getK());
		System.out.println("time: " + smallestLast.getTime());	

		Thread.sleep(60000);
		
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
