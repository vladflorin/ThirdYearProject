package main.java.com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.algorithms.GreedyAlgorithm;
import main.java.algorithms.LargestFirstAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.algorithms.SmallestLastAlgorithm;
import main.java.com.GraphGenerator;
import main.java.com.Test;
import main.java.report.Report;
import main.java.report.ReportItem;
import main.java.report.ReportTestItem;
import main.java.utils.Constants;
import net.sf.dynamicreports.report.exception.DRException;

import org.apache.log4j.Logger;

public class AlgorithmsTesting {

	static {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}

	final static Logger logger = Logger.getLogger(AlgorithmsTesting.class);

	private static String[] algorithmNames = {"Greedy", "Random Sequential", "Largest First", "Smallest Last"};
	
	public static void main(String[] args) throws DRException, IOException {
		  
		logger.info("START: Testing");
		
		long[] graphSizeList = Constants.GRAPH_SIZE_LIST;
		
		validateGraphSizeList(graphSizeList);
		
		Report report = new Report();
		
		for (int count = 0; count < graphSizeList.length; count++) {
			long currentGraphSize = graphSizeList[count];
			
			int avgDegree = (int) (currentGraphSize / Constants.AVG_DEGREE_WEIGHT * Math.random()) + 1;
						
			validateGraphSize(currentGraphSize);
			
			logger.info("START: Testing currentGraphSize = " + currentGraphSize + " avgDegree = " + avgDegree);
			
			List<Test> listOfTests = new ArrayList<Test>();

			for (int index = 0; index < Constants.NUMBER_OF_GRAPHS; index++) {
				logger.info("Current graph: " + index);

				Test currentTest = new Test(currentGraphSize - GraphGenerator.getAvgDegree(), avgDegree);
				listOfTests.add(currentTest);
				logger.info("Current graph: " + index + " graph generation");

				// Greedy Algorithm Testing
				currentTest.getAlgorithmList().add(greedyColouring(currentTest));				
				currentTest.resetInitialGraph();
				logger.info("Current graph: " + index + " greedyColouring");

				// Random Sequential Algorithm Testing
				currentTest.getAlgorithmList().add(randomSequential(currentTest));				
				currentTest.resetInitialGraph();
				logger.info("Current graph: " + index + " randomSequential");

				// Largest First Algorithm Testing
				currentTest.getAlgorithmList().add(largestFirst(currentTest));				
				currentTest.resetInitialGraph();
				logger.info("Current graph: " + index + " largestFirst");

				// Smallest Last Algorithm Testing
				currentTest.getAlgorithmList().add(smallestLast(currentTest));				
				currentTest.resetInitialGraph();
				logger.info("Current graph: " + index + " smallestLast");

				// Clean memory
				currentTest = cleanMemory(currentTest);			
			}
			
			ReportItem reportItem = null;
			
			try {
				logger.info("START: Generate ReportItem element for graphSize = " + currentGraphSize);
				reportItem = generateReportItem(listOfTests, currentGraphSize);
				report.getReportItemList().add(reportItem);
				logger.info("END: Generate ReportItem element for graphSize = " + currentGraphSize);
			} catch (Exception e) {
				logger.info("Something went wrong while generating the reportItem" , e);
			}
			
			logger.info("END: Testing currentGraphSize = " + currentGraphSize);
		}
		
		report.build();
		
		logger.info("END: Testing");
	}
	
	private static Algorithm greedyColouring(Test currentTest) {
		Algorithm greedyAlgorithm = new Algorithm("Greedy");
		greedyAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		GreedyAlgorithm greedyColouring = new GreedyAlgorithm();
		greedyColouring.init(greedyAlgorithm.getColoredGraph());
		greedyColouring.compute();

		greedyAlgorithm.setK(greedyColouring.getK());
		greedyAlgorithm.setTime(greedyColouring.getTime());
		
		return greedyAlgorithm;
	}
	
	private static Algorithm randomSequential(Test currentTest) {
		Algorithm randomSequentialAlgorithm = new Algorithm("Random Sequential");
		randomSequentialAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		RandomSequentialAlgorithm randomSequentialColouring = new RandomSequentialAlgorithm();
		randomSequentialColouring.init(randomSequentialAlgorithm.getColoredGraph());
		randomSequentialColouring.compute();

		randomSequentialAlgorithm.setK(randomSequentialColouring.getK());
		randomSequentialAlgorithm.setTime(randomSequentialColouring.getTime());
		
		return randomSequentialAlgorithm;
	}
	
	private static Algorithm largestFirst(Test currentTest) {
		Algorithm largestFirstAlgorithm = new Algorithm("Largest First");
		largestFirstAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		LargestFirstAlgorithm largestFirstColouring = new LargestFirstAlgorithm();
		largestFirstColouring.init(largestFirstAlgorithm.getColoredGraph());
		largestFirstColouring.compute();

		largestFirstAlgorithm.setK(largestFirstColouring.getK());
		largestFirstAlgorithm.setTime(largestFirstColouring.getTime());
		
		return largestFirstAlgorithm;
	}
	
	private static Algorithm smallestLast(Test currentTest) {
		Algorithm smallestLastAlgorithm = new Algorithm("Smallest Last");
		smallestLastAlgorithm.setColoredGraph(currentTest.getInitialGraph());
		
		SmallestLastAlgorithm smallestLastColouring = new SmallestLastAlgorithm();
		smallestLastColouring.init(smallestLastAlgorithm.getColoredGraph());
		smallestLastColouring.compute();

		smallestLastAlgorithm.setK(smallestLastColouring.getK());
		smallestLastAlgorithm.setTime(smallestLastColouring.getTime());
		
		return smallestLastAlgorithm;
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
	
	private static ReportItem generateReportItem(List<Test> list, long currentGraphSize) {
		ReportItem reportItem = new ReportItem();
		
		for (int algorithmIndex = 0; algorithmIndex < algorithmNames.length; algorithmIndex++) {
			reportItem.setNoOfGraphs(Constants.NUMBER_OF_GRAPHS);
			reportItem.setSizeOfGraph(currentGraphSize);
			
			ReportTestItem reportTestItem = new ReportTestItem();
			reportTestItem.setAlgorithmName(algorithmNames[algorithmIndex]);
			for (int index = 0; index < list.size(); index++) {
				reportTestItem.getTime().add(index, list.get(index).getAlgorithmList().get(algorithmIndex).getTime());
				reportTestItem.getK().add(index, list.get(index).getAlgorithmList().get(algorithmIndex).getK());
			}
			
			reportItem.getTestList().add(reportTestItem);
		}
		
		return reportItem;
	}
}
