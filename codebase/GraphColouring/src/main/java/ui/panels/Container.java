package main.java.ui.panels;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import main.java.utils.Constants;

import javax.swing.JTextPane;

import java.awt.Font;

@SuppressWarnings("serial")
public class Container extends JPanel {

	public CardLayout cardLayout = new CardLayout();

	private WelcomePanel welcomePanel;
	private InputPanel inputPanel;
	private AlgorithmPanel algorithmPanel;
	private ColouringPanel colouringPanel;
	private ComparePanel comparePanel;
		
	public Container() {
		setupPanel();
		setupContainer();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(cardLayout);
	}
	
	private void setupContainer() {
		welcomePanel = new WelcomePanel(this);
		inputPanel = new InputPanel(this);
		algorithmPanel = new AlgorithmPanel(this);
		colouringPanel = new ColouringPanel(this);
		comparePanel = new ComparePanel(this);
		
		this.add(welcomePanel, "welcomePanel");
		this.add(inputPanel, "inputPanel");
		this.add(algorithmPanel, "algorithmPanel");
		this.add(colouringPanel, "colouringPanel");
		this.add(comparePanel, "comparePanel");
	}
	
	public void clearAllPanels() {
		inputPanel.clear();
		algorithmPanel.clear();
		colouringPanel.clear();
		comparePanel.clear();
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}	

	public InputPanel getInputPanel() {
		return inputPanel;
	}

	public WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}

	public AlgorithmPanel getAlgorithmPanel() {
		return algorithmPanel;
	}
	
	public ColouringPanel getColouringPanel() {
		return colouringPanel;
	}

	public void setColouringPanel(ColouringPanel colouringPanel) {
		this.colouringPanel = colouringPanel;
	}

	public ComparePanel getComparePanel() {
		return comparePanel;
	}

	public void setComparePanel(ComparePanel comparePanel) {
		this.comparePanel = comparePanel;
	}

}
