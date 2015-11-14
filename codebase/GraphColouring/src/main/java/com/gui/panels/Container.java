package main.java.com.gui.panels;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import main.java.com.utils.Constants;

import javax.swing.JTextPane;

import java.awt.Font;

@SuppressWarnings("serial")
public class Container extends JPanel {

	public CardLayout cardLayout = new CardLayout();
	
	public Container() {
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(cardLayout);
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
