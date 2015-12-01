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
						
		// Create new JFrame
		logger.info("START: Open MainFrame");
		try {
			MainFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
			logger.info("END: Open MainFrame");
		} catch (Exception e) {
			logger.error("Something went wrong" + e);
		}
		
	}
	
}
