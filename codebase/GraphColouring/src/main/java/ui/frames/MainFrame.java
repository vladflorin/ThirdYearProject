package main.java.ui.frames;

import java.awt.Color;
import javax.swing.JFrame;
import main.java.ui.panels.Container;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

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
		
		Thread.sleep(3500);
		
		container.getCardLayout().show(container, "inputPanel");
	
		// Close application
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}

	public Container getContainer() {
		return container;
	}
}
