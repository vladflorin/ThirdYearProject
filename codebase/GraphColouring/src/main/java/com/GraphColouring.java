package main.java.com;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.ui.frames.MainFrame;

import org.apache.log4j.Logger;

public class GraphColouring {

	static {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}

	final static Logger logger = Logger.getLogger(GraphColouring.class);

	public static void main(String[] args) throws InterruptedException {
				
		logger.info("START GraphColouring application");
		
		// Create new JFrame
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
				
		logger.info("END GraphColouring application");
		
	}
	
}
