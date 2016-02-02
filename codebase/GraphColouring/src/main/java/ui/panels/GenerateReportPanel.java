package main.java.ui.panels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

import main.java.com.AlgorithmsTestingUI;
import main.java.utils.Constants;
import main.java.utils.Utils;

@SuppressWarnings("serial")
public class GenerateReportPanel extends JPanel {
	
	JFrame parent;
	
	final static Logger logger = Logger.getLogger(InputPanel.class);

	private JTextField txtFieldNoOfGraphs;
	private int noOfGraphs;
	
	private JTextField txtFieldSizeGraphs;
	private int sizeGraphs;
	
	JLabel lblErrorMessage;
	JLabel lblSpinner;
	
	JLabel lblTrue;
	JLabel lblFalse;
	
	public GenerateReportPanel() {
		
	}
	
	public GenerateReportPanel(JFrame givenFrame) {
		
		this.parent = givenFrame;
		
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
		lblSpinner.setBounds(252, 218, 76, 61);
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
		
		lblTrue = new JLabel("Successfully generated the report.");
		lblTrue.setForeground(new Color(34, 139, 34));
		lblTrue.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTrue.setBounds(171, 230, 236, 37);
		lblTrue.setVisible(false);
		add(lblTrue);
		
		lblFalse = new JLabel("Failed to generate the report.");
		lblFalse.setForeground(new Color(255, 0, 0));
		lblFalse.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFalse.setBounds(190, 230, 206, 37);
		lblFalse.setVisible(false);
		add(lblFalse);
	}
	
	class ReportGenerator extends SwingWorker<Boolean, Integer>
	{
	    protected Boolean doInBackground() throws Exception
	    {
	    	parent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    	// GENERATE REPORT TODO
	    	AlgorithmsTestingUI algTesting = new AlgorithmsTestingUI();
	    	boolean result = false;
	    	try {
				result = algTesting.createReport(noOfGraphs, sizeGraphs);
			} catch (Exception exception) {
				logger.error("Something went wrong: " + exception);
				lblSpinner.setVisible(true);
				lblTrue.setVisible(false);
        		lblFalse.setVisible(true);
			}
	        return result;
	    }

	    protected void done()
	    {
	        try
	        {
	        	parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        	
	        	if (get()) {
	        		lblTrue.setVisible(true);
	        		lblFalse.setVisible(false);
	        	} else {
	        		lblTrue.setVisible(false);
	        		lblFalse.setVisible(true);
	        	}
	        	
	        	lblSpinner.setVisible(false);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
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
			
			if ((noOfGraphs <= 0) || (sizeGraphs <=0)) {
				isValid = false;
			}
			
			return isValid;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lblTrue.setVisible(false);
			lblFalse.setVisible(false);
			
			if (validateInputLabels()) {
				lblErrorMessage.setVisible(false);
				
				logger.info("START: Generate Report");
				lblSpinner.setVisible(true);
				
				new ReportGenerator().execute();
				
				logger.info("END: Generate Report");
				
			} else {
				lblErrorMessage.setVisible(true);
			}
		}
		
	}
	
}
