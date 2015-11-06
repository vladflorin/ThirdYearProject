package main.java.com;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class HelloWorld {

	static {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}

	final static Logger logger = Logger.getLogger(HelloWorld.class);

	public static void main(String[] args) {
				
		System.out.println("Hello");
		
		logger.info("START");

	}
	
}
