package main.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.java.algorithms.ConnectedSequentialAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.com.GraphGenerator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class ConnectedSequentialAlgorithmTest {

	@Test
	public void testGenerateBfsSequence() {
		Graph graph = createGraph();
		List<Integer> seq = ConnectedSequentialAlgorithm.generateBfsSequence(graph);
		
		System.out.println("Graph size: " + graph.getNodeCount());
		for (int index = 0; index < seq.size(); index++) {
			System.out.print(seq.get(index) + " ");
		}
		
		assertNotNull(graph);
		assertNotNull(seq);
	}
	
	@Test
	public void testCSAlgorithmRandomGraph() throws InterruptedException {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));

		ConnectedSequentialAlgorithm csSequential = new ConnectedSequentialAlgorithm();
		csSequential.init(randomGraph);
		csSequential.compute();
		
		System.out.println("k: " + csSequential.getK());
		System.out.println("time: " + csSequential.getTime());

		assertNotNull(randomGraph);
	}
	
	@Test
	public void testCSAlgorithmGivenGraph() throws InterruptedException {		
		Graph graph = createGraph();
		
		System.out.println("Graph size: " + graph.getNodeCount());
		
		ConnectedSequentialAlgorithm csSequential = new ConnectedSequentialAlgorithm();
		csSequential.init(graph);
		csSequential.compute();
		
		float time = (float) (csSequential.getTime() / 1000000.0);
		System.out.println("k: " + csSequential.getK());
		System.out.println("time: " + csSequential.getTime());
		System.out.println("time: " + time);
		
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
