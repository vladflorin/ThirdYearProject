package main.java.com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			
			List<Test> listOfTests = initialiseListOfTests(currentGraphSize);

			for (int index = 0; index < listOfTests.size(); index++) {
				Test currentTest = listOfTests.get(index);
			}
			
			logger.info("END: Testing currentGraphSize = " + currentGraphSize);
		}
		
		logger.info("END:  Testing");	
	}
	
	private static List<Test> initialiseListOfTests(long graphSize) {
		List<Test> listOfTests = new ArrayList<Test>();
		try {
			for (int index = 0; index < Constants.NUMBER_OF_GRAPHS; index++) {
				Test newTest = new Test(graphSize - GraphGenerator.getAvgDegree());
				listOfTests.add(newTest);
			}
			logger.info("Succesfully initialised listOfTests: numberOfGraphs: " + listOfTests.size() + ", graphSize: " + graphSize);
		} catch (Exception e) {
			logger.error("Something went wrong", e);
		}
		return listOfTests;
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
	
}
