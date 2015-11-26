package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import main.java.utils.Constants;
import main.java.utils.GraphGenerator;
import main.java.utils.Utils;

import javax.swing.JButton;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ViewerListener  {
	
	Container container;
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	ViewerPipe fromViewer;
	
	// Random Graph Generator UI
	JButton btnGenerateRandomGraph;
	private JTextField noOfNodesTextField;
	int noOfNodes;
	private JTextField avgDegreeTextField;
	int avgDegree;
	JLabel generateRandomGraphErrorLabel;
	
	// Upload graph UI
	final JFileChooser fc = new JFileChooser();
	JButton btnUploadGraph;
	
	// Navigation panel UI
	JButton btnNextPanel;
	JButton btnHomePanel;
	JLabel nextPanelErrorLabel;
	
	public InputPanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
	}

	public Graph getGraph() {
		Graph newGraph = Graphs.clone(graph);
		return newGraph;
	}
	
	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(null);
		graphPanel.setBackground(Color.WHITE);
		graphPanel.setBounds(17, 30, 899, 597);
		add(graphPanel);
		graphPanel.setLayout(new BorderLayout(0, 0));
		
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		
		fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);
        
		graphPanel.add((Component) view, BorderLayout.CENTER); 
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(17, 636, 250, 38);
		lblNewLabel.setIcon(new ImageIcon(Constants.LOGO_PATH_S));
		add(lblNewLabel);
		
		JPanel randomGeneratePanel = new JPanel();
		randomGeneratePanel.setBorder(new LineBorder(new Color(255, 160, 122), 3, true));
		randomGeneratePanel.setBackground(new Color(255, 250, 205));
		randomGeneratePanel.setBounds(928, 302, 248, 129);
		add(randomGeneratePanel);
		
		btnGenerateRandomGraph = new JButton("Random graph");
		btnGenerateRandomGraph.setBounds(57, 83, 135, 40);
		btnGenerateRandomGraph.addActionListener(new GenerateGraphActionListener());
		randomGeneratePanel.setLayout(null);
		
		randomGeneratePanel.add(btnGenerateRandomGraph);
		
		JLabel numberOfNodesLabel = new JLabel("Number of nodes:");
		numberOfNodesLabel.setBounds(16, 16, 121, 16);
		randomGeneratePanel.add(numberOfNodesLabel);
		
		noOfNodesTextField = new JTextField();
		noOfNodesTextField.setBounds(152, 10, 67, 28);
		randomGeneratePanel.add(noOfNodesTextField);
		noOfNodesTextField.setColumns(10);
		
		JLabel avgDegreeLabel = new JLabel("Average degree:");
		avgDegreeLabel.setBounds(16, 44, 141, 16);
		randomGeneratePanel.add(avgDegreeLabel);
		
		avgDegreeTextField = new JTextField();
		avgDegreeTextField.setColumns(10);
		avgDegreeTextField.setBounds(152, 38, 67, 28);
		randomGeneratePanel.add(avgDegreeTextField);
		
		generateRandomGraphErrorLabel = new JLabel("Please provide only valid integer values.");
		generateRandomGraphErrorLabel.setForeground(Color.RED);
		generateRandomGraphErrorLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 9));
		generateRandomGraphErrorLabel.setBounds(26, 65, 193, 16);
		generateRandomGraphErrorLabel.setVisible(false);
		randomGeneratePanel.add(generateRandomGraphErrorLabel);
		
		JLabel infoLabel = new JLabel("*");
		infoLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
		infoLabel.setForeground(Color.RED);
		infoLabel.setBounds(117, 44, 15, 16);
		infoLabel.setToolTipText("Needs to be smaller than the number of nodes.");
		randomGeneratePanel.add(infoLabel);
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(new LineBorder(new Color(34, 139, 34), 3, true));
		navigationPanel.setBackground(new Color(240, 255, 240));
		navigationPanel.setBounds(928, 534, 248, 93);
		add(navigationPanel);
		navigationPanel.setLayout(null);
		
		btnNextPanel = new JButton("");
		btnNextPanel.setIcon(new ImageIcon(Constants.RIGHT_ICON_PATH));
		btnNextPanel.setBounds(163, 20, 55, 55);
		btnNextPanel.addActionListener(new NextPanelActionListener());
		navigationPanel.add(btnNextPanel);
		
		btnHomePanel = new JButton("");
		btnHomePanel.setIcon(new ImageIcon(Constants.HOME_ICON_PATH));
		btnHomePanel.setBounds(96, 20, 55, 55);
		navigationPanel.add(btnHomePanel);
		
		nextPanelErrorLabel = new JLabel("Please provide a graph as input.");
		nextPanelErrorLabel.setForeground(Color.RED);
		nextPanelErrorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		nextPanelErrorLabel.setBounds(52, 71, 155, 16);
		nextPanelErrorLabel.setVisible(false);
		navigationPanel.add(nextPanelErrorLabel);
		
		JPanel uploadGraphPanel = new JPanel();
		uploadGraphPanel.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
		uploadGraphPanel.setBackground(new Color(224, 255, 255));
		uploadGraphPanel.setBounds(928, 443, 248, 79);
		add(uploadGraphPanel);
		uploadGraphPanel.setLayout(null);
		
		btnUploadGraph = new JButton("Upload graph");
		btnUploadGraph.setBounds(58, 19, 135, 40);
		btnUploadGraph.addActionListener(new UploadGraphActionListener());
		uploadGraphPanel.add(btnUploadGraph);
		
		JPanel drawGraphPanel = new JPanel();
		drawGraphPanel.setBorder(new LineBorder(Color.GRAY, 3, true));
		drawGraphPanel.setBounds(928, 30, 248, 260);
		add(drawGraphPanel);

		new Thread(new Runnable() {
        	public void run() {
        		while(loop) {
        			fromViewer.pump();
        		}
        	}
        }).start();
		
	}

	public void clear() {
		noOfNodesTextField.setText("");
		avgDegreeTextField.setText("");
		nextPanelErrorLabel.setVisible(false);
		generateRandomGraphErrorLabel.setVisible(false);
	}
	
	class GenerateGraphActionListener implements ActionListener{
		private Boolean validateInputLabels() {
			Boolean isValid = false;
			
			String noOfNodesString = noOfNodesTextField.getText();
			String avegDegreeString = avgDegreeTextField.getText();
			
			if (Utils.isInteger(noOfNodesString) && Utils.isInteger(avegDegreeString)) {
				isValid = true;
				noOfNodes = Integer.parseInt(noOfNodesString);
				avgDegree = Integer.parseInt(avegDegreeString);
			}
			
			return isValid;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validateInputLabels()) {
				generateRandomGraphErrorLabel.setVisible(false);
				graph.clear();
				if (noOfNodes > avgDegree && noOfNodes != 0 && avgDegree != 0) {
					graph = GraphGenerator.generate(noOfNodes - avgDegree - 1, avgDegree, graph);
				} else {
					generateRandomGraphErrorLabel.setVisible(true);
					noOfNodesTextField.setText("");
					avgDegreeTextField.setText("");
				}
			} else {
				generateRandomGraphErrorLabel.setVisible(true);
				noOfNodesTextField.setText("");
				avgDegreeTextField.setText("");
			}
		}
		
	}
	
	class UploadGraphActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileFilter filter = new FileNameExtensionFilter("DGS File", "dgs");
			fc.setFileFilter(filter);
			
			int returnVal = fc.showOpenDialog(InputPanel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            System.out.println("Opening: " + file.getName() + ".");
	        } else {
	        	System.out.println("Open command cancelled by user.");
	        }
		}
		
	}
	
	class NextPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph.getNodeCount() > 0) {
				nextPanelErrorLabel.setVisible(false);
				container.getAlgorithmPanel().setGraph(graph);
				container.getCardLayout().show(container, "algorithmPanel");
			} else {
				nextPanelErrorLabel.setVisible(true);
			}
		}
		
	}

	public void viewClosed(String id) {
		loop = false;
	}
	 
	public void buttonPushed(String id) {
		System.out.println("Button pushed on node "+id);
	}
	 
	public void buttonReleased(String id) {
		System.out.println("Button released on node "+id);
	}
}