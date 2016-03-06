package main.test.ui;

import main.java.ui.frames.MainFrame;
import main.java.ui.panels.WelcomePanel;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WelcomPanelTesting {

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
	public void test() {
		String startButton = ((WelcomePanel) mainFrame.getContainer().getWelcomePanel()).startButton.getName();
		demo.button(startButton).click();
	}
	
}
