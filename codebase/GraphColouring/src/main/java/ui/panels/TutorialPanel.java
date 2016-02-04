package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import main.java.algorithms.ConnectedSequentialAlgorithm;
import main.java.algorithms.GreedyAlgorithm;
import main.java.algorithms.LargestFirstAlgorithm;
import main.java.algorithms.RandomSequentialAlgorithm;
import main.java.algorithms.SaturationLargestFirstAlgorithm;
import main.java.algorithms.SmallestLastAlgorithm;
import main.java.tutorials.RandomSequentialTutorial;
import main.java.tutorials.Tutorial;
import main.java.utils.Constants;
import main.java.utils.NodeUtils;

import org.apache.log4j.Logger;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.Button;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.Scrollbar;

import javax.swing.JScrollBar;

import java.awt.Panel;

@SuppressWarnings("serial")
public class TutorialPanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(ColouringPanel.class);

	Container container;
	
	private String algorithm = null;
	private Tutorial tutorial = null;
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	
	// Results UI
	JLabel lblAlgorithmName;
	JTextPane txtColourSeq;
	JTextPane txtPaneScript;
	JScrollPane slider;
	JScrollPane slider2;
	
	JButton btnGenerate;
	
	// Navigation panel UI
	JButton btnHomePanel;
	JButton btnPreviousPanel;
	
	// Algorithms
	RandomSequentialAlgorithm randomSequentialAlgorithm = null;
	LargestFirstAlgorithm largestFirstAlgorithm = null;
	SmallestLastAlgorithm smallestLastAlgorithm = null;
	ConnectedSequentialAlgorithm connectedSequentialAlgorithm = null;
	SaturationLargestFirstAlgorithm saturationLargestFirstAlgorithm = null;
	GreedyAlgorithm greedyAlgorithm = null;
	
	public TutorialPanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(null);
		graphPanel.setBackground(Color.WHITE);
		graphPanel.setBounds(17, 30, 762, 597);
		add(graphPanel);
		graphPanel.setLayout(new BorderLayout(0, 0));
		
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		
		graphPanel.add((Component) view, BorderLayout.CENTER); 
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(17, 636, 250, 38);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource(Constants.LOGO_PATH_S)));
		add(lblNewLabel);
				
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(null);
		navigationPanel.setBorder(new LineBorder(new Color(34, 139, 34), 3, true));
		navigationPanel.setBackground(new Color(240, 255, 240));
		navigationPanel.setBounds(928, 534, 248, 93);
		add(navigationPanel);
		
		btnHomePanel = new JButton("");
		btnHomePanel.setIcon(new ImageIcon(getClass().getResource(Constants.HOME_ICON_PATH)));
		btnHomePanel.setBounds(96, 20, 55, 55);
		btnHomePanel.addActionListener(new WelcomePanelActionListener());
		navigationPanel.add(btnHomePanel);
		
		btnPreviousPanel = new JButton("");
		btnPreviousPanel.setIcon(new ImageIcon(getClass().getResource(Constants.LEFT_ICON_PATH)));
		btnPreviousPanel.setBounds(29, 20, 55, 55);
		btnPreviousPanel.addActionListener(new PreviousPanelActionListener());
		navigationPanel.add(btnPreviousPanel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 255), 3, true));
		mainPanel.setBackground(new Color(224, 255, 255));
		mainPanel.setBounds(791, 30, 385, 492);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		lblAlgorithmName = new JLabel();
		lblAlgorithmName.setText("Algorithm");
		lblAlgorithmName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAlgorithmName.setForeground(new Color(255, 0, 0));
		lblAlgorithmName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName.setBounds(103, 19, 191, 27);
		mainPanel.add(lblAlgorithmName);
		
		JLabel lblColouringSeq = new JLabel();
		lblColouringSeq.setText("Step 1: Generate colouring sequence");
		lblColouringSeq.setForeground(new Color(0, 128, 0));
		lblColouringSeq.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblColouringSeq.setBounds(19, 58, 268, 27);
		mainPanel.add(lblColouringSeq);
		
		txtColourSeq = new JTextPane();
		txtColourSeq.setEditable(false);
		txtColourSeq.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		txtColourSeq.setBackground(new Color(224, 255, 255));
		txtColourSeq.setForeground(Color.BLUE);
		txtColourSeq.setBounds(19, 90, 348, 28);
		
		slider = new JScrollPane(txtColourSeq);
		slider.setBounds(19, 90, 352, 28);
		slider.setBorder(null);
		mainPanel.add(slider);
		
		JLabel lblNodeId = new JLabel();
		lblNodeId.setText("Script");
		lblNodeId.setForeground(Color.BLACK);
		lblNodeId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNodeId.setBounds(19, 251, 44, 27);
		mainPanel.add(lblNodeId);
		
		txtPaneScript = new JTextPane();
		txtPaneScript.setForeground(Color.BLUE);
		txtPaneScript.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtPaneScript.setEditable(false);
		txtPaneScript.setBackground(new Color(224, 255, 255));
		txtPaneScript.setBounds(19, 279, 209, 190);
		
		/*slider2 = new JScrollPane(txtPaneScript);
		slider2.setBounds(19, 279, 213, 190);
		slider2.setBorder(null);
		mainPanel.add(slider2); */
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setToolTipText("Generate the colouring sequence.");
		btnGenerate.setForeground(Color.BLACK);
		btnGenerate.setBounds(135, 119, 132, 35);
		btnGenerate.addActionListener(new GenerateSequenceActionListener());
		mainPanel.add(btnGenerate);
		
		JLabel lblStepColour = new JLabel();
		lblStepColour.setText("Step 2: Colour the graph's nodes");
		lblStepColour.setForeground(new Color(46, 139, 87));
		lblStepColour.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblStepColour.setBounds(19, 170, 234, 27);
		mainPanel.add(lblStepColour);
	}

	public void colourGraph() {
		if (algorithm == null) {
			return;
		}
				
		switch (algorithm) {
		case Constants.RANDOM_SEQ:
			randomSequentialAlgorithm = new RandomSequentialAlgorithm();
			randomSequentialAlgorithm.init(graph);
			randomSequentialAlgorithm.compute();
			txtColourSeq.setText(randomSequentialAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(randomSequentialAlgorithm.getScript());
			break;
		}
		
		txtPaneScript.setCaretPosition(0);
		txtColourSeq.setCaretPosition(0);
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
		lblAlgorithmName.setText(algorithm);
		
		switch (algorithm) {
		case Constants.RANDOM_SEQ:
			tutorial = new RandomSequentialTutorial();
		}
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph.clear();
		Graphs.mergeIn(this.graph, graph);
		
		List<Integer> nodeList = new ArrayList<>();
		for (Node node : this.graph.getNodeSet()) {
			NodeUtils.setInitialSize(node);
			nodeList.add(Integer.parseInt(node.getId()));
		}
		
		Integer[] array = nodeList.toArray(new Integer[nodeList.size()]);
	}
	
	public void clear() {
		lblAlgorithmName.setText("Algorithm");
		txtColourSeq.setText("");
		txtPaneScript.setText("");
		btnGenerate.setEnabled(true);
		tutorial = null;
	}
	
	class PreviousPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("START: Change TutorialPanel to AlgorithmPanel");
			container.getAlgorithmPanel().clear();
			container.getCardLayout().show(container, "algorithmPanel");
			logger.info("END: Change ColouringPanel to AlgorithmPanel");
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
	
	class GenerateSequenceActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tutorial.init(graph);
			
			if (tutorial.getColouringSequence().size() > 0) {
				btnGenerate.setEnabled(false);
			}
			
			txtColourSeq.setText(tutorial.getColouringSequence().toString());
			txtColourSeq.setCaretPosition(0);
		}	
	}
}