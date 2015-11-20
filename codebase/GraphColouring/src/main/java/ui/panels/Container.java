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
		
	public Container() {
		setupPanel();
		setupContainer();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(cardLayout);
	}
	
	private void setupContainer() {
		welcomePanel = new WelcomePanel();
		inputPanel = new InputPanel();
		
		this.add(welcomePanel, "welcomePanel");
		this.add(inputPanel, "inputPanel");
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
}
