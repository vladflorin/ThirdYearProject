package main.java.com.gui.frames;

import javax.swing.JFrame;

import main.java.com.gui.panels.Container;
import main.java.com.gui.panels.InputPanel;
import main.java.com.gui.panels.WelcomePanel;
import main.java.com.utils.Constants;

import java.awt.CardLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private Container containerPanel;
	private WelcomePanel welcomePanel;
	private InputPanel inputPanel;
	
	public MainFrame() throws InterruptedException {		
		welcomePanel = new WelcomePanel();
		inputPanel = new InputPanel();
		
		setupContainer();

		setupFrame();
	}
	
	private void setupFrame() throws InterruptedException {			
		// Add the containerPanel
		this.setContentPane(containerPanel);
		
		containerPanel.getCardLayout().show(containerPanel, "welcomePanel");

		// Set size
		this.setSize(1100, 626);
		this.setResizable(false);
		this.setVisible(true);

		Thread.sleep(Constants.LOGO_WAIT_TIME);
		
		containerPanel.getCardLayout().show(containerPanel, "inputPanel");
		
		// Close application
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setupContainer() {
		containerPanel = new Container();
		
		containerPanel.add(welcomePanel, "welcomePanel");
		containerPanel.add(inputPanel, "inputPanel");
	}
	
}
