package main.java.com.gui.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.lowagie.text.Font;

@SuppressWarnings("serial")
public class InputPanel extends JPanel {
	
	public InputPanel() {
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTextPane version = new JTextPane();
		version.setEditable(false);
		version.setText("input");
		version.setBounds(6, 584, 36, 16);
		add(version);
	}
}
