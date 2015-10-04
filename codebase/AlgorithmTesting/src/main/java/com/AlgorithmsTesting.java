package main.java.com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.algorithms.GreedyColouringAlgorithm;
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
				
				logger.info(index);
				// Greedy Algorithm Testing
				currentTest.getAlgorithmList().add(greedyColouring(currentTest));
				
				// TODO: Do the magic generate report
				currentTest = cleanMemory(currentTest);			
			}
			
			logger.info("END: Testing currentGraphSize = " + currentGraphSize);
		}
		
		logger.info("END:  Testing");	
	}
	
	private static Algorithm greedyColouring(Test currentTest) {
		Algorithm greedyAlgorithm = new Algorithm("Greedy Colouring Algorithm");
		greedyAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		GreedyColouringAlgorithm greedyColouring = new GreedyColouringAlgorithm();
		greedyColouring.init(greedyAlgorithm.getColoredGraph());
		greedyColouring.compute();

		return greedyAlgorithm;
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
		currentTest.setInitialGraph(null);
		for (int index = 0; index < currentTest.getAlgorithmList().size(); index++) {
			currentTest.getAlgorithmList().get(index).setColoredGraph(null);
		}
		return currentTest;
	}
	
}
