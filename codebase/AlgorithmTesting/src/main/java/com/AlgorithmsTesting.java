package main.java.com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.algorithms.GreedyColouringAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.com.GraphGenerator;
import main.java.com.Test;
import main.java.utils.Constants;

import org.apache.log4j.Logger;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class AlgorithmsTesting {

	static {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	final static Logger logger = Logger.getLogger(AlgorithmsTesting.class);
		
	public static void main(String[] args) {
		  
		logger.info("START: Testing");
		
		long[] graphSizeList = Constants.GRAPH_SIZE_LIST;
		
		validateGraphSizeList(graphSizeList);
		
		for (int count = 0; count < graphSizeList.length; count++) {
			long currentGraphSize = graphSizeList[count];
			
			validateGraphSize(currentGraphSize);
			
			logger.info("START: Testing currentGraphSize = " + currentGraphSize);
			
			List<Test> listOfTests = new ArrayList<Test>();

			for (int index = 0; index < Constants.NUMBER_OF_GRAPHS; index++) {
				Test currentTest = new Test(currentGraphSize - GraphGenerator.getAvgDegree());
				listOfTests.add(currentTest);
				
				logger.info("Current graph: " + index);
				
				// Greedy Algorithm Testing
				currentTest.getAlgorithmList().add(greedyColouring(currentTest));				
				currentTest.resetInitialGraph();
				
				// Random Sequential Testing
				currentTest.getAlgorithmList().add(randomSequential(currentTest));				
				currentTest.resetInitialGraph();
				
				// TODO: Do the magic generate report
				
				// Clean memory
				currentTest = cleanMemory(currentTest);			
			}
			
			// Print
			/*for (int index = 0; index < listOfTests.size(); index ++) {
				System.out.println("TEST " + index);
				Test currentTest = listOfTests.get(index);
				List<Algorithm> list = currentTest.getAlgorithmList();
				for (int i = 0; i < list.size(); i ++) {
					Algorithm currentAlgo = list.get(i);
					System.out.println(currentAlgo.getName() + ": k = " + currentAlgo.getK() + " , time = " + currentAlgo.getTime());
				}
			} */
			
			logger.info("END: Testing currentGraphSize = " + currentGraphSize);
		}
		
		logger.info("END: Testing");	
	}
	
	private static Algorithm greedyColouring(Test currentTest) {
		Algorithm greedyAlgorithm = new Algorithm("Greedy Colouring Algorithm");
		greedyAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		GreedyColouringAlgorithm greedyColouring = new GreedyColouringAlgorithm();
		greedyColouring.init(greedyAlgorithm.getColoredGraph());
		greedyColouring.compute();

		greedyAlgorithm.setK(greedyColouring.getK());
		greedyAlgorithm.setTime(greedyColouring.getTime());
		
		return greedyAlgorithm;
	}
	
	private static Algorithm randomSequential(Test currentTest) {
		Algorithm randomSequentialAlgorithm = new Algorithm("Random Sequential Colouring Algorithm");
		randomSequentialAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		RandomSequentialAlgorithm randomSequentialColouring = new RandomSequentialAlgorithm();
		randomSequentialColouring.init(randomSequentialAlgorithm.getColoredGraph());
		randomSequentialColouring.compute();

		randomSequentialAlgorithm.setK(randomSequentialColouring.getK());
		randomSequentialAlgorithm.setTime(randomSequentialColouring.getTime());
		
		return randomSequentialAlgorithm;
	}
	
	private static void validateGraphSize(long graphSize) {
		if (graphSize <= (Constants.AVG_DEGREE + 1)) {
			logger.error("Something went wrong. The graphSize should be more than " + (Constants.AVG_DEGREE + 1));
			System.exit(1);
		}
	}
	
	private static void validateGraphSizeList(long[] graphSizeList) {
		if (graphSizeList.length <= 0) {
			logger.error("graph size list in the Constants file is empty");
			System.exit(1);
		}
	}
	
	private static Test cleanMemory(Test currentTest) {
		currentTest.getInitialGraph().clear();
		return currentTest;
	}
	
}
