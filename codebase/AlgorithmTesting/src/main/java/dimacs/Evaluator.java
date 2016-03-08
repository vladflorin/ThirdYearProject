package main.java.dimacs;

import java.io.IOException;

import main.java.algorithms.ConnectedSequentialAlgorithm;
import main.java.algorithms.LargestFirstAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.algorithms.SmallestLastAlgorithm;

import org.apache.log4j.Logger;
import org.graphstream.graph.implementations.Graphs;

public class Evaluator {

	final static Logger logger = Logger.getLogger(Evaluator.class);

	public static void main(String[] args) throws IOException {
		String[] files = {"dimacs250_28.txt", "dimacs1000_20.txt", "flat1000_50.txt"};
		for (int index = 0; index < files.length; index++) {
			try	{
				logger.info("*********** Current file: " + files[index] + " ***********");
				DimacsTest currentTest = GraphConstructor.readTestFromFile(files[index]);

				SmallestLastAlgorithm smallestLastAlgorithm = new SmallestLastAlgorithm();
				smallestLastAlgorithm.init(Graphs.clone(currentTest.getGraph()));
				smallestLastAlgorithm.compute();
				logger.info("Smallest last algorithm. K = " + smallestLastAlgorithm.getK() + ", benchmark = " + currentTest.getBenchmark());
			
				LargestFirstAlgorithm largestFirstAlgorithm = new LargestFirstAlgorithm();
				largestFirstAlgorithm.init(Graphs.clone(currentTest.getGraph()));
				largestFirstAlgorithm.compute();
				logger.info("Largest first algorithm. K = " + largestFirstAlgorithm.getK() + ", benchmark = " + currentTest.getBenchmark());
			
				RandomSequentialAlgorithm randomSequentialAlgorithm = new RandomSequentialAlgorithm();
				randomSequentialAlgorithm.init(Graphs.clone(currentTest.getGraph()));
				randomSequentialAlgorithm.compute();
				logger.info("Random sequential algorithm. K = " + randomSequentialAlgorithm.getK() + ", benchmark = " + currentTest.getBenchmark());
			
				ConnectedSequentialAlgorithm connectedSequentialAlgorithm = new ConnectedSequentialAlgorithm();
				connectedSequentialAlgorithm.init(Graphs.clone(currentTest.getGraph()));
				connectedSequentialAlgorithm.compute();
				logger.info("Random sequential algorithm. K = " + connectedSequentialAlgorithm.getK() + ", benchmark = " + currentTest.getBenchmark());
			
			} catch (Exception e) {
				logger.error("Something went wrong. " + e.getMessage());
			}
			
		//currentTest.getGraph().display();
		}
	}
}
