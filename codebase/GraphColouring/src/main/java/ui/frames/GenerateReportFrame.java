package main.java.ui.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.java.ui.panels.GenerateReportPanel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GenerateReportFrame extends JFrame {
	
	public GenerateReportFrame() {
		
		GenerateReportPanel panel = new GenerateReportPanel(this);
		panel.setBounds(0, 0, 545, 298);

		
		getContentPane().setLayout(null);
		
		this.setSize(545, 320);
		this.getContentPane().add(panel);
		

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
