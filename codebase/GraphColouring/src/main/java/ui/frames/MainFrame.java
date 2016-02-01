package main.java.ui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import main.java.com.GraphColouring;
import main.java.ui.panels.Container;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	final static Logger logger = Logger.getLogger(MainFrame.class);
	
	private Container container;
	
	public MainFrame() throws InterruptedException {
		container = new Container();
		setupFrame();
	}
	
	private void setupFrame() throws InterruptedException {
		// Add the containerPanel
		this.setContentPane(container);
		
		// Set size
		this.setBackground(Color.WHITE);
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setVisible(true);

		container.getCardLayout().show(container, "welcomePanel");
		
		// Close application
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}

	public Container getContainer() {
		return container;
	}
}
