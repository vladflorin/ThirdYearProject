package main.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.java.algorithms.LargestFirstAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.com.GraphGenerator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class LargestFirstAlgorithmTest {

	@Test
	public void testGenerateRandomSequence() {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 10));
		List<Integer> degreeList = LargestFirstAlgorithm.initialiseDegreeList(randomGraph);
		
		for (int index = 0; index < randomGraph.getNodeCount(); index++) {
			Node currentNode = randomGraph.getNode(LargestFirstAlgorithm.getMaxDegreeIndex(degreeList));
			System.out.println(currentNode.getIndex());
		}
		
		assertNotNull(randomGraph);
	}
	
	@Test
	public void testInitialiseDegreeListGivenGraph() {
		Graph graph = createGraph();
		List<Integer> degreeList = LargestFirstAlgorithm.initialiseDegreeList(graph);
		
		for (int index = 0; index < graph.getNodeCount(); index++) {
			Node currentNode = graph.getNode(LargestFirstAlgorithm.getMaxDegreeIndex(degreeList));
			System.out.println(currentNode.getIndex());
		}
		
		assertNotNull(graph);
	}
	
	@Test
	public void testLargestFirstAlgorithmRandomGraph() throws InterruptedException {
		Graph randomGraph = GraphGenerator.generate((long) (Math.random() * 20));
				
		LargestFirstAlgorithm largestFirst = new LargestFirstAlgorithm();
		largestFirst.init(randomGraph);
		largestFirst.compute();
		
		randomGraph.display();
		Thread.sleep(10000);
		
		System.out.println("k: " + largestFirst.getK());
		System.out.println("time: " + largestFirst.getTime());
		
		assertNotNull(randomGraph);
	}
	
	@Test
	public void testLargestFirstAlgorithmGivenGraph() throws InterruptedException {		
		Graph graph = createGraph();

		System.out.println("Graph size: " + graph.getNodeCount());
		
		LargestFirstAlgorithm largestFirst = new LargestFirstAlgorithm();
		largestFirst.init(graph);
		largestFirst.compute();
		
		System.out.println("k: " + largestFirst.getK());
		System.out.println("time: " + largestFirst.getTime());
		

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
