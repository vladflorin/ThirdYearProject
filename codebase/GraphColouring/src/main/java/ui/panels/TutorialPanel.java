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
	JTextPane txtNodesToBeColoured;
	JTextPane txtNodesAlreadyColoured;
	JScrollPane slider;
	JScrollPane slider2;
	JScrollPane slider3;
	JScrollPane slider4;
	
	JButton btnGenerate;
	JButton btnPrevStep;
	JButton btnNextStep;
	
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
	private JButton btnColourGraph;
	private JTextPane txtPaneAlgorithmsSteps;
	private JLabel lblAlgorithmsSteps;
	
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
		lblAlgorithmName.setForeground(Color.BLACK);
		lblAlgorithmName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName.setBounds(103, 19, 191, 27);
		mainPanel.add(lblAlgorithmName);
		
		JLabel lblColouringSeq = new JLabel();
		lblColouringSeq.setText("Step 1: Generate colouring sequence");
		lblColouringSeq.setForeground(Color.RED);
		lblColouringSeq.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblColouringSeq.setBounds(19, 58, 268, 27);
		mainPanel.add(lblColouringSeq);
		
		txtColourSeq = new JTextPane();
		txtColourSeq.setEditable(false);
		txtColourSeq.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtColourSeq.setBackground(new Color(224, 255, 255));
		txtColourSeq.setForeground(Color.BLUE);
		txtColourSeq.setBounds(19, 80, 350, 38);
		
		slider = new JScrollPane(txtColourSeq);
		slider.setBounds(19, 80, 350, 38);
		slider.setBorder(null);
		mainPanel.add(slider);
		
		JLabel lblNodesColoured = new JLabel();
		lblNodesColoured.setText("Nodes to be coloured:");
		lblNodesColoured.setForeground(Color.BLACK);
		lblNodesColoured.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNodesColoured.setBounds(19, 198, 151, 27);
		mainPanel.add(lblNodesColoured);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setToolTipText("Generate the colouring sequence.");
		btnGenerate.setForeground(Color.BLACK);
		btnGenerate.setBounds(135, 119, 132, 35);
		btnGenerate.addActionListener(new GenerateSequenceActionListener());
		mainPanel.add(btnGenerate);
		
		JLabel lblStepColour = new JLabel();
		lblStepColour.setText("Step 2: Colour the graph's nodes");
		lblStepColour.setForeground(Color.RED);
		lblStepColour.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblStepColour.setBounds(19, 170, 234, 27);
		mainPanel.add(lblStepColour);
		
		JLabel lblNodesAlreadyColoured = new JLabel();
		lblNodesAlreadyColoured.setText("Nodes already coloured:");
		lblNodesAlreadyColoured.setForeground(Color.BLACK);
		lblNodesAlreadyColoured.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNodesAlreadyColoured.setBounds(19, 259, 163, 27);
		mainPanel.add(lblNodesAlreadyColoured);
		
		txtNodesToBeColoured = new JTextPane();
		txtNodesToBeColoured.setForeground(new Color(0, 128, 0));
		txtNodesToBeColoured.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtNodesToBeColoured.setEditable(false);
		txtNodesToBeColoured.setBackground(new Color(224, 255, 255));
		txtNodesToBeColoured.setBounds(19, 222, 350, 38);
		
		slider2 = new JScrollPane(txtNodesToBeColoured);
		slider2.setBounds(19, 222, 350, 38);
		slider2.setBorder(null);
		mainPanel.add(slider2);
		
		txtNodesAlreadyColoured = new JTextPane();
		txtNodesAlreadyColoured.setForeground(new Color(255, 165, 0));
		txtNodesAlreadyColoured.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtNodesAlreadyColoured.setEditable(false);
		txtNodesAlreadyColoured.setBackground(new Color(224, 255, 255));
		txtNodesAlreadyColoured.setBounds(19, 283, 350, 38);
		
		slider3 = new JScrollPane(txtNodesAlreadyColoured);
		slider3.setBounds(19, 283, 350, 38);
		slider3.setBorder(null);
		mainPanel.add(slider3);
		
		btnPrevStep = new JButton("Previous Step");
		btnPrevStep.setBackground(new Color(255, 0, 0));
		btnPrevStep.setBounds(19, 320, 105, 38);
		btnPrevStep.setOpaque(true);
		btnPrevStep.setEnabled(false);
		mainPanel.add(btnPrevStep);
		
		btnNextStep = new JButton("Next Step");
		btnNextStep.setBackground(new Color(30, 144, 255));
		btnNextStep.setBounds(125, 320, 105, 38);
		btnNextStep.setOpaque(true);
		btnNextStep.setEnabled(false);
		btnNextStep.addActionListener(new ComputeNextStepActionListener());
		mainPanel.add(btnNextStep);
		
		btnColourGraph = new JButton("Colour graph");
		btnColourGraph.setOpaque(true);
		btnColourGraph.setBackground(new Color(50, 205, 50));
		btnColourGraph.setBounds(264, 320, 105, 38);
		btnColourGraph.setEnabled(false);
		btnColourGraph.addActionListener(new ComputeActionListener());
		mainPanel.add(btnColourGraph);
		
		txtPaneAlgorithmsSteps = new JTextPane();
		txtPaneAlgorithmsSteps.setForeground(new Color(0, 0, 255));
		txtPaneAlgorithmsSteps.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtPaneAlgorithmsSteps.setEditable(false);
		txtPaneAlgorithmsSteps.setBackground(new Color(224, 255, 255));
		txtPaneAlgorithmsSteps.setBounds(19, 385, 350, 90);
		
		slider4 = new JScrollPane(txtPaneAlgorithmsSteps);
		slider4.setBounds(19, 385, 350, 90);
		slider4.setBorder(null);
		mainPanel.add(slider4);
		
		lblAlgorithmsSteps = new JLabel();
		lblAlgorithmsSteps.setText("Algorithm's steps:");
		lblAlgorithmsSteps.setForeground(Color.BLACK);
		lblAlgorithmsSteps.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAlgorithmsSteps.setBounds(19, 359, 132, 27);
		mainPanel.add(lblAlgorithmsSteps);
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
			txtNodesToBeColoured.setText(randomSequentialAlgorithm.getScript());
			break;
		}
		
		txtNodesToBeColoured.setCaretPosition(0);
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
		txtNodesToBeColoured.setText("");
		txtNodesAlreadyColoured.setText("");
		btnGenerate.setEnabled(true);
		
		btnNextStep.setEnabled(false);
		btnPrevStep.setEnabled(false);
		btnColourGraph.setEnabled(false);
		
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
				
				updateButtons();
			}
			
			updateTxtPanels();
			
			txtColourSeq.setText(tutorial.getColouringSequence().toString());
			txtColourSeq.setCaretPosition(0);
		}	
	}
	
	class ComputeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (tutorial != null && tutorial.getColouringSequence().size() > 0) {
				tutorial.compute();
			}
			
			updateTxtPanels();
			updateButtons();
		}
		
	}
	
	class ComputeNextStepActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (tutorial != null && tutorial.getColouringSequence().size() > 0) {
				tutorial.computeNextStep();
			}
			
			updateTxtPanels();
			updateButtons();
		}
		
	}
	
	public void updateTxtPanels() {
		txtNodesToBeColoured.setText(tutorial.getColouringSequence().toString());
		txtNodesToBeColoured.setCaretPosition(0);
		
		txtNodesAlreadyColoured.setText(tutorial.getPreviousNodesList().toString());
		txtNodesAlreadyColoured.setCaretPosition(0);	
	}
	
	public void updateButtons() {
		if (tutorial.getColouringSequence().size() > 0) {
			btnColourGraph.setEnabled(true);
			btnNextStep.setEnabled(true);
		} else {
			btnColourGraph.setEnabled(false);
			btnNextStep.setEnabled(false);
		}
		
		if (tutorial.getPreviousNodesList().size() > 0) {
			btnPrevStep.setEnabled(true);
		} else {
			btnPrevStep.setEnabled(false);
		}
		
	}
}