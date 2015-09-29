package main.java.com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.GraphGenerator;
import main.java.com.Test;

import org.apache.log4j.Logger;
import org.graphstream.graph.Graph;

public class AlgorithmsTesting {

	private final static long[] graphSize = {10, 100, 1000, 10000, 100000};
	private final static long noOfGraphs = 1000;

	static{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	final static Logger logger = Logger.getLogger(AlgorithmsTesting.class);
	
	private static List<Test> listOfTests = new ArrayList<Test>();
	
	public static void main(String[] args) {
		  
		logger.info("This is info: START" );
		for (int test = 0; test < graphSize.length; test ++) {
			long currentSize = graphSize[test];
			
			// Generate random graph
			Graph graph = GraphGenerator.generate(currentSize - GraphGenerator.getAvgDegree());

		}
		logger.info("This is info: END" );
		
		try {
			String name = null;
			System.out.println(name.toString());
		} catch (Exception e){
			logger.error("Something went wrong ", e);
		}
		
	}
	
}
