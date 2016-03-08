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
	
	private void addNodesUsingButton(int noOfNodes) {
		String startButton = ((WelcomePanel) mainFrame.getContainer().getWelcomePanel()).startButton.getName();
		demo.button(startButton).click();
		
		String addNodeButton = ((InputPanel) mainFrame.getContainer().getInputPanel()).btnAddNode.getName();
				
		for (int index = 1; index <= noOfNodes; index++) {
			demo.button(addNodeButton).click();
		}
	}
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
		int numberOfNodes = 50;
		addNodesUsingButton(numberOfNodes);
		Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), numberOfNodes);
	}
	
	@Test
	public void testClearGraph() {
		int numberOfNodes = 10;
		addNodesUsingButton(numberOfNodes);
		
		Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), numberOfNodes);
		
		String clearGraphButton = ((InputPanel) mainFrame.getContainer().getInputPanel()).btnClearGraph.getName();
		
		demo.button(clearGraphButton).click();

		Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), 0);
	}
	
	@Test
	public void testGenerateRandomGraph() {
		String startButton = ((WelcomePanel) mainFrame.getContainer().getWelcomePanel()).startButton.getName();
		demo.button(startButton).click();
		String clearGraphButton = ((InputPanel) mainFrame.getContainer().getInputPanel()).btnClearGraph.getName();

		int numberOfNodes = 10;
		for (int nodes = 1; nodes <= numberOfNodes; nodes++) {
			for (int avgDegree = 1; avgDegree <= nodes + 2; avgDegree++) {
				demo.textBox("noOfNodesField").setText(String.valueOf(nodes));
				demo.textBox("avgDegreeField").setText(String.valueOf(avgDegree));
				
				demo.button("btnGenerateRandomGraph").click();
				if (nodes > avgDegree) {
					Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), nodes);
					demo.button(clearGraphButton).click();
				} else {
					Assert.assertEquals(((InputPanel) mainFrame.getContainer().getInputPanel()).getGraph().getNodeSet().size(), 0);
					demo.label("generateRandomGraphErrorLabel").requireVisible();
					demo.button(clearGraphButton).click();
				}
			}
		}
		
		
	}
}
