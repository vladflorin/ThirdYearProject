package main.test.ui;

import junit.framework.Assert;
import main.java.ui.frames.MainFrame;
import main.java.ui.panels.InputPanel;
import main.java.ui.panels.WelcomePanel;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputPanelTesting {

	private FrameFixture demo;
	private MainFrame mainFrame;
	
	@Before
	public void setUp() throws InterruptedException {
		mainFrame = new MainFrame();
		demo = new FrameFixture(mainFrame);
	}
	
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void testAddNodes() {
		String startButton = ((WelcomePanel) mainFrame.getContainer().getWelcomePanel()).startButton.getName();
		demo.button(startButton).click();
		
		String addNodeButton = ((InputPanel) mainFrame.getContainer().getInputPanel()).btnAddNode.getName();
		
		int numberOfNodes = 50;
		
		for (int index = 1; index <= numberOfNodes; index++) {
			demo.button(addNodeButton).click();
		}
		
		Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), numberOfNodes);
	}
	
	
}
