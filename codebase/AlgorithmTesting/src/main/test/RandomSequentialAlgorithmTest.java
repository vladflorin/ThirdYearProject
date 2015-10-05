package main.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.java.algorithms.GreedyColouringAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.com.GraphGenerator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class RandomSequentialAlgorithmTest {

	@Test
	public void testGenerateRandomSequence() {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));
		List<Integer> randomSeq = RandomSequentialAlgorithm.generateRandomSequence(randomGraph.getNodeCount());
		
		System.out.println("Graph size: " + randomGraph.getNodeCount());
		for (int index = 0; index < randomSeq.size(); index++) {
			System.out.print(randomSeq.get(index) + " ");
		}
		
		assertNotNull(randomGraph);
		assertNotNull(randomSeq);
	}
	
	@Test
	public void testGreedyAlgorithmRandomGraph() throws InterruptedException {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));
				
		RandomSequentialAlgorithm randomSequential = new RandomSequentialAlgorithm();
		randomSequential.init(randomGraph);
		randomSequential.compute();
		
		System.out.println("k: " + randomSequential.getK());
		System.out.println("time: " + randomSequential.getTime());
		
		assertNotNull(randomGraph);
	}
	
	@Test
	public void testGreedyAlgorithmGivenGraph() throws InterruptedException {		
		Graph graph = createGraph();
		
		System.out.println("Graph size: " + graph.getNodeCount());
		
		RandomSequentialAlgorithm randomSequential = new RandomSequentialAlgorithm();
		randomSequential.init(graph);
		randomSequential.compute();
		
		System.out.println("k: " + randomSequential.getK());
		System.out.println("time: " + randomSequential.getTime());
		

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
