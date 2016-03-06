package main.java.ui.panels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import main.java.utils.Constants;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel {
	
	Container container;
	
	public WelcomePanel(Container container) {
		setupPanel();
		this.container = container;
	}

	public JButton startButton;
	
	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);
		
		// Add logoLabel
		JLabel logoLabel = new JLabel("");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(getClass().getResource(Constants.LOGO_PATH_M)));
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setBounds(311, 272, 640, 98);
		add(logoLabel);

		JTextPane version = new JTextPane();
		version.setEditable(false);
		version.setFont(new Font("Candara", Font.PLAIN, 11));
		version.setText("V4.1");
		version.setBounds(6, 661, 36, 16);
		add(version);
		
		startButton = new JButton("START");
		startButton.setName("startButton");
		startButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		startButton.setForeground(Color.BLACK);
		startButton.setBounds(512, 420, 196, 48);
		startButton.addActionListener(new StartButtonActionListener());
		add(startButton);
	}

	class StartButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			container.clearAllPanels();
			container.getCardLayout().show(container, "inputPanel");
		}
	}
}