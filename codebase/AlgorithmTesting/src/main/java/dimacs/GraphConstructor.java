package main.java.dimacs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphConstructor {

	final static Logger logger = Logger.getLogger(GraphConstructor.class);

	public static DimacsTest readTestFromFile(String path) throws IOException {
		
		int benchmark = 0;
		Graph graph = new SingleGraph(path);
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("").getAbsolutePath().concat("/src/main/java/dimacs/input/" + path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("There was an error. " + e.getMessage());
		}
		
		try {
			int noOfNodes = 0;
			
		    String line = br.readLine();

		    String[] splited = line.split("\\s+");
		    
		    if (splited[0].equals("b")) {
		    	benchmark = Integer.parseInt(splited[1]);
		    	//logger.info("Benchmark value: " + benchmark);
		    }
		    
		    line = br.readLine();
		    splited = line.split("\\s+");
		    
		    if (splited[0].equals("v")) {
		    	noOfNodes = Integer.parseInt(splited[1]);
		    	for (int index = 0; index < noOfNodes; index++) {
		    		graph.addNode(String.valueOf(index));
		    	}
		    }
		    
		    line = br.readLine();
		    while (line != null) {
		    	splited = line.split("\\s+");
			    
			    if (splited[0].equals("e")) {
			    	int node1 = Integer.parseInt(splited[1]) - 1;
			    	int node2 = Integer.parseInt(splited[2]) - 1;

			    	graph.addEdge(String.valueOf(node1).concat(String.valueOf(node2)).concat(String.valueOf(Math.random())), 
			    			String.valueOf(node1), String.valueOf(node2));
			    }
			    
		        line = br.readLine();
		    }
		    
		    //logger.info("Graph created. Number of nodes: " + noOfNodes + "; " + graph.getNodeCount());
		    
		} finally {
		    br.close();
		}
		
		DimacsTest dimacsTest = new DimacsTest(graph, benchmark);
		
		return dimacsTest;
	}
	
}
