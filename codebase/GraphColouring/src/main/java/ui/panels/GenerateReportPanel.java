package main.java.ui.panels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import main.java.utils.Constants;
import main.java.utils.Utils;

@SuppressWarnings("serial")
public class GenerateReportPanel extends JPanel {
	
	final static Logger logger = Logger.getLogger(InputPanel.class);

	private JTextField txtFieldNoOfGraphs;
	private int noOfGraphs;
	
	private JTextField txtFieldSizeGraphs;
	private int sizeGraphs;
	
	JLabel lblErrorMessage;
	JLabel lblSpinner;
	
	public GenerateReportPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNumberOfGraphs = new JLabel("Number of graphs:");
		lblNumberOfGraphs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNumberOfGraphs.setBounds(133, 78, 136, 37);
		add(lblNumberOfGraphs);
		
		JLabel lblSizeOfGraph = new JLabel("Size of graph:");
		lblSizeOfGraph.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSizeOfGraph.setBounds(169, 114, 100, 37);
		add(lblSizeOfGraph);
		
		txtFieldNoOfGraphs = new JTextField();
		txtFieldNoOfGraphs.setBounds(281, 82, 141, 31);
		add(txtFieldNoOfGraphs);
		txtFieldNoOfGraphs.setColumns(10);
		
		txtFieldSizeGraphs = new JTextField();
		txtFieldSizeGraphs.setColumns(10);
		txtFieldSizeGraphs.setBounds(281, 120, 141, 31);
		add(txtFieldSizeGraphs);
		
		JButton btnNewButton = new JButton("Generate Report");
		btnNewButton.setToolTipText("The generated report will be saved on the Desktop.");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton.setBounds(208, 181, 160, 37);
		btnNewButton.addActionListener(new GenerateGraphActionListener());
		add(btnNewButton);
		
		lblSpinner = new JLabel("");
		lblSpinner.setIcon(new ImageIcon(GenerateReportPanel.class.getResource(Constants.REPORT_SPINNER2)));
		lblSpinner.setBounds(254, 220, 76, 61);
		lblSpinner.setVisible(false);
		add(lblSpinner);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(157, 18, 250, 38);
		lblLogo.setIcon(new ImageIcon(getClass().getResource(Constants.LOGO_PATH_S)));
		add(lblLogo);
		
		lblErrorMessage = new JLabel("Please provide only valid integer values.");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblErrorMessage.setBounds(179, 148, 214, 37);
		lblErrorMessage.setVisible(false);
		add(lblErrorMessage);
	}
	
	class GenerateGraphActionListener implements ActionListener{
		private Boolean validateInputLabels() {
			Boolean isValid = false;
			
			String noOfGraphsString = txtFieldNoOfGraphs.getText();
			String sizeOfGraphString = txtFieldSizeGraphs.getText();
			
			if (Utils.isInteger(noOfGraphsString) && Utils.isInteger(sizeOfGraphString)) {
				isValid = true;
				noOfGraphs = Integer.parseInt(noOfGraphsString);
				sizeGraphs = Integer.parseInt(sizeOfGraphString);
			}
			
			return isValid;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validateInputLabels()) {
				lblErrorMessage.setVisible(false);
				
				
			} else {
				lblErrorMessage.setVisible(true);
			}
		}
		
	}
	
}
