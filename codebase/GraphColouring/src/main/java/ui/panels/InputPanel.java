package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import main.java.ui.frames.MainFrame;
import main.java.utils.Constants;
import main.java.utils.GraphGenerator;
import main.java.utils.NodeUtils;
import main.java.utils.Utils;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
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
	
	final static Logger logger = Logger.getLogger(InputPanel.class);

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
	
	// Draw graph UI
	JButton btnAddNode;
	
	JButton btnAddEdge;
	Boolean btnAddEdgePressed = false;
	
	JButton btnRemoveNode;
	Boolean btnRemoveNodePressed = false;
	
	JButton btnRemoveEdge;
	Boolean btnRemoveEdgePressed = false;
	
	Node previousNode = null;
	Node currentNode = null;
	
	// Remove graph UI
	JButton btnClearGraph;
	
	public InputPanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
	}

	public Graph getGraph() {
		Graph newGraph = Graphs.clone(graph);
		return newGraph;
	}
	
	public void clearGraph() {
		graph.clear();
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
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource(Constants.LOGO_PATH_S)));
		add(lblNewLabel);
		
		JPanel randomGeneratePanel = new JPanel();
		randomGeneratePanel.setBorder(new LineBorder(new Color(255, 160, 122), 3, true));
		randomGeneratePanel.setBackground(new Color(255, 250, 205));
		randomGeneratePanel.setBounds(928, 221, 248, 129);
		add(randomGeneratePanel);
		
		btnGenerateRandomGraph = new JButton("Random Graph");
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
		btnNextPanel.setIcon(new ImageIcon(getClass().getResource(Constants.RIGHT_ICON_PATH)));
		btnNextPanel.setBounds(163, 20, 55, 55);
		btnNextPanel.addActionListener(new NextPanelActionListener());
		navigationPanel.add(btnNextPanel);
		
		btnHomePanel = new JButton("");
		btnHomePanel.setIcon(new ImageIcon(getClass().getResource(Constants.HOME_ICON_PATH)));
		btnHomePanel.setBounds(96, 20, 55, 55);
		btnHomePanel.addActionListener(new WelcomePanelActionListener());
		navigationPanel.add(btnHomePanel);
		
		nextPanelErrorLabel = new JLabel("Please provide a graph as input.");
		nextPanelErrorLabel.setForeground(Color.RED);
		nextPanelErrorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		nextPanelErrorLabel.setBounds(52, 71, 155, 16);
		nextPanelErrorLabel.setVisible(false);
		navigationPanel.add(nextPanelErrorLabel);
		
		JButton btnReport = new JButton("");
		btnReport.setToolTipText("Generate Report");
		btnReport.setIcon(new ImageIcon(getClass().getResource(Constants.CHART_ICON_PATH)));
		btnReport.setBounds(29, 20, 55, 55);
		navigationPanel.add(btnReport);
		
		JPanel uploadGraphPanel = new JPanel();
		uploadGraphPanel.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
		uploadGraphPanel.setBackground(new Color(224, 255, 255));
		uploadGraphPanel.setBounds(928, 362, 248, 79);
		add(uploadGraphPanel);
		uploadGraphPanel.setLayout(null);
		
		btnUploadGraph = new JButton("Upload Graph");
		btnUploadGraph.setBounds(58, 19, 135, 40);
		btnUploadGraph.addActionListener(new UploadGraphActionListener());
		uploadGraphPanel.add(btnUploadGraph);
		
		JPanel drawGraphPanel = new JPanel();
		drawGraphPanel.setBorder(new LineBorder(Color.GRAY, 3, true));
		drawGraphPanel.setBounds(928, 30, 248, 179);
		add(drawGraphPanel);
		drawGraphPanel.setLayout(null);
		
		btnAddNode = new JButton("NODE");
		btnAddNode.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnAddNode.setBounds(27, 40, 83, 41);
		btnAddNode.addActionListener(new AddNodeActionListener());
		drawGraphPanel.add(btnAddNode);
		
		JLabel lblAdd = new JLabel("--- ADD ---");
		lblAdd.setForeground(new Color(34, 139, 34));
		lblAdd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAdd.setBounds(78, 24, 86, 16);
		drawGraphPanel.add(lblAdd);
		
		btnAddEdge = new JButton("EDGE");
		btnAddEdge.setToolTipText("Select two nodes to add an edge.");
		btnAddEdge.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnAddEdge.setBounds(132, 40, 83, 41);
		btnAddEdge.addActionListener(new AddEdgeActionListener());
		btnAddEdge.setOpaque(true);
		drawGraphPanel.add(btnAddEdge);
		
		JLabel lblRemove = new JLabel("--- REMOVE ---");
		lblRemove.setForeground(new Color(255, 0, 0));
		lblRemove.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRemove.setBounds(65, 98, 113, 16);
		drawGraphPanel.add(lblRemove);
		
		btnRemoveNode = new JButton("NODE");
		btnRemoveNode.setToolTipText("Select the node to be removed.");
		btnRemoveNode.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnRemoveNode.setBounds(27, 116, 83, 41);
		btnRemoveNode.setOpaque(true);
		btnRemoveNode.addActionListener(new RemoveNodeActionListener());
		drawGraphPanel.add(btnRemoveNode);
		
		btnRemoveEdge = new JButton("EDGE");
		btnRemoveEdge.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnRemoveEdge.setBounds(132, 116, 83, 41);
		btnRemoveEdge.setOpaque(true);
		btnRemoveEdge.addActionListener(new RemoveEdgeActionListener());
		drawGraphPanel.add(btnRemoveEdge);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(928, 453, 248, 69);
		add(panel);
		
		btnClearGraph = new JButton("Clear Graph");
		btnClearGraph.setBounds(57, 17, 135, 36);
		btnClearGraph.addActionListener(new ClearGraphActionListener());
		panel.add(btnClearGraph);

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
		btnAddEdgePressed = false;
		btnAddEdge.setBackground(null);

		btnRemoveEdgePressed = false;
		btnRemoveEdge.setBackground(null);

		btnRemoveNodePressed = false;
		btnRemoveNode.setBackground(null);
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
				logger.info("START: generate random graph");
				try {
					generateRandomGraphErrorLabel.setVisible(false);
					if (noOfNodes > avgDegree && noOfNodes != 0 && avgDegree != 0) {
						graph.clear();
						graph = GraphGenerator.generate(noOfNodes - avgDegree - 1, avgDegree, graph);
					} else {
						generateRandomGraphErrorLabel.setVisible(true);
						noOfNodesTextField.setText("");
						avgDegreeTextField.setText("");
					}
				} catch (Exception exception) { 
					logger.error("Something went wrong." + exception);
				}
				logger.info("END: generate random graph");
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
	            String filePath = file.getPath();
	            logger.info("Opening: " + filePath + ".");
	            
	            graph.clear();
	            
	            FileSource fs = null;
	            try {
					fs = FileSourceFactory.sourceFor(filePath);
					fs.addSink(graph);
					try {
						fs.begin(filePath);
						while (fs.nextEvents()) {
						}
					} catch (IOException e2) {
						logger.error(e2);
					}
					
					try {
						fs.end();
					} catch (IOException e2) {
						logger.error(e2);
					} finally {
						fs.removeSink(graph);
					}
					
					for (Node node : graph.getNodeSet()) {
						NodeUtils.setInitialSize(node);
						NodeUtils.addLabel(node);
					}
					
					graph.addAttribute("ui.quality");
					graph.addAttribute("ui.antialias");
					
				} catch (IOException e1) {
					logger.error("There was an error while upload the graph. " + e1);
				}
	            
	        } else {
	        	logger.info("Open command cancelled by user.");
	        }
		}
		
	}
	
	class NextPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph.getNodeCount() > 0) {
				nextPanelErrorLabel.setVisible(false);
				container.getAlgorithmPanel().setGraph(graph);
				container.getAlgorithmPanel().clear();
				logger.info("START: Change InputPanel to AlgorithmPanel");
				try {
					container.getCardLayout().show(container, "algorithmPanel");
					logger.info("END: Change InputPanel to AlgorithmPanel");
				} catch (Exception exception) {
					logger.error("Something went wrong." + exception);
				}
			} else {
				nextPanelErrorLabel.setVisible(true);
			}
		}
		
	}
	
	class WelcomePanelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			container.getCardLayout().show(container, "welcomePanel");		
			// Clear all the panels
			container.clearAllPanels();
		}	
	}
	
	class ClearGraphActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ((graph != null) && (graph.getNodeSet().size() != 0)) {
				graph.clear();
			}
		}
		
	}
	
	class AddNodeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph != null) {
				String newNodeId = Integer.toString(getNewNodeId());				
				Node currentNode = graph.addNode(newNodeId);
				currentNode = NodeUtils.setInitialSize(graph.getNode(newNodeId));
				currentNode = NodeUtils.addLabel(graph.getNode(newNodeId));
			}
		}
		
		private int getNewNodeId() {
			int max = -1;
			
			for (Node currentNode : graph.getNodeSet())
			{
				if (Integer.parseInt(currentNode.getId()) > max) {
					max = Integer.parseInt(currentNode.getId());
				}
			}
			
			return (max + 1);
		}
	}
	
	class AddEdgeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph.getNodeSet().size() > 1) {
				if (!btnAddEdgePressed) {
					btnAddEdgePressed = true;
					btnAddEdge.setBackground(Color.GREEN);
					enableButtons(btnAddEdge, false);
				} else {
					if (previousNode != null) {
						NodeUtils.setColourBlack(previousNode);
						previousNode = null; 
					}

					currentNode = null;

					btnAddEdgePressed = false;
					btnAddEdge.setBackground(null);
					enableButtons(btnAddEdge, true);
				}
			}
		}		
	}
	
	class RemoveNodeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph.getNodeSet().size() >= 1 || btnRemoveNodePressed) {
				if (!btnRemoveNodePressed) {
					btnRemoveNodePressed = true;
					btnRemoveNode.setBackground(Color.RED);
					enableButtons(btnRemoveNode, false);
				} else {
					btnRemoveNodePressed = false;
					btnRemoveNode.setBackground(null);
					enableButtons(btnRemoveNode, true);
				}
			}
		}	
	}
	
	class RemoveEdgeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (graph.getEdgeSet().size() >= 1 || btnRemoveEdgePressed) {
				if (!btnRemoveEdgePressed) {
					btnRemoveEdgePressed = true;
					btnRemoveEdge.setBackground(Color.RED);
					enableButtons(btnRemoveEdge, false);
				} else {
					if (previousNode != null) {
						NodeUtils.setColourBlack(previousNode);
						previousNode = null;
					}
					
					currentNode = null;
					
					btnRemoveEdgePressed = false;
					btnRemoveEdge.setBackground(null);
					enableButtons(btnRemoveEdge, true);
				}
			}
		}
	}

	public void viewClosed(String id) {
		loop = false;
	}
	 
	public void buttonPushed(String id) {
		if (btnAddEdgePressed) {
			if (previousNode == null) {
				previousNode = graph.getNode(id);
				NodeUtils.setColourGreen(previousNode);
			} else if (previousNode != graph.getNode(id) && !previousNode.hasEdgeBetween(graph.getNode(id))) {
				currentNode = graph.getNode(id);
				NodeUtils.setColourGreen(currentNode);
				graph.addEdge(Math.random() + id, previousNode, currentNode);
				
				NodeUtils.setColourBlack(previousNode);
				NodeUtils.setColourBlack(currentNode);
				
				previousNode = null;
				currentNode = null;
			} else if (!previousNode.hasEdgeBetween(graph.getNode(id))) {
				NodeUtils.setColourBlack(previousNode);
				previousNode = null;
			}
		} else if (btnRemoveNodePressed) {
			graph.removeNode(id);
		} else if (btnRemoveEdgePressed) {
			if (previousNode == null) {
				previousNode = graph.getNode(id);
				NodeUtils.setColourRed(previousNode);
			} else if (previousNode != graph.getNode(id) && previousNode.hasEdgeBetween(graph.getNode(id))) {
				currentNode = graph.getNode(id);
				NodeUtils.setColourRed(currentNode);
				graph.removeEdge(previousNode, currentNode);
				
				NodeUtils.setColourBlack(previousNode);
				NodeUtils.setColourBlack(currentNode);
				
				previousNode = null;
				currentNode = null;
			} else if (previousNode.equals(graph.getNode(id))) {
				NodeUtils.setColourBlack(previousNode);
				previousNode = null;
			}
		}
	}
	 
	public void buttonReleased(String id) {
		// TODO: Button released on node id
	}
	
	public void enableButtons(JButton currentButton, Boolean enable) {
		if (!currentButton.equals(btnAddNode)) { btnAddNode.setEnabled(enable); }
		if (!currentButton.equals(btnAddEdge)) { btnAddEdge.setEnabled(enable); }
		if (!currentButton.equals(btnRemoveNode)) { btnRemoveNode.setEnabled(enable); }
		if (!currentButton.equals(btnRemoveEdge)) { btnRemoveEdge.setEnabled(enable); }
		if (!currentButton.equals(btnGenerateRandomGraph)) { btnGenerateRandomGraph.setEnabled(enable); }
		if (!currentButton.equals(btnUploadGraph)) { btnUploadGraph.setEnabled(enable); }
		if (!currentButton.equals(btnHomePanel)) { btnHomePanel.setEnabled(enable); }
		if (!currentButton.equals(btnNextPanel)) { btnNextPanel.setEnabled(enable); }
		if (!currentButton.equals(btnClearGraph)) { btnClearGraph.setEnabled(enable); }
	}
}