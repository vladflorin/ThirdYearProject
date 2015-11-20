package main.java.ui.panels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import main.java.utils.Constants;

import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel {
	
	public WelcomePanel() {
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);
		
		// Add logoLabel
		JLabel logoLabel = new JLabel("");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(Constants.LOGO_PATH_M));
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setBounds(240, 237, 640, 98);
		add(logoLabel);

		JTextPane version = new JTextPane();
		version.setEditable(false);
		version.setFont(new Font("Candara", Font.PLAIN, 11));
		version.setText("V1.0");
		version.setBounds(6, 584, 36, 16);
		add(version);
	}

}