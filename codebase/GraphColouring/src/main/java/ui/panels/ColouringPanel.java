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
import main.java.utils.Constants;
import main.java.utils.NodeUtils;

import org.apache.log4j.Logger;
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
public class ColouringPanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(ColouringPanel.class);

	Container container;
	private String algorithm = null;
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	
	// Results UI
	JLabel lblAlgorithmName;
	JLabel lblKValue;
	JTextPane txtColourSeq;
	JTextPane panelColour;
	JTextPane txtPaneScript;
	JScrollPane slider;
	JScrollPane slider2;
	
	JComboBox comboBoxNodeId;
	
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
	
	public ColouringPanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
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
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new LineBorder(new Color(255, 165, 0), 3, true));
		resultPanel.setBackground(new Color(255, 250, 240));
		resultPanel.setBounds(928, 30, 248, 492);
		add(resultPanel);
		resultPanel.setLayout(null);
		
		lblAlgorithmName = new JLabel();
		lblAlgorithmName.setText("Algorithm");
		lblAlgorithmName.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAlgorithmName.setForeground(Color.RED);
		lblAlgorithmName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName.setBounds(28, 19, 191, 27);
		resultPanel.add(lblAlgorithmName);
		
		JLabel lblChromaticNumber = new JLabel();
		lblChromaticNumber.setText("Chromatic number:");
		lblChromaticNumber.setForeground(Color.BLACK);
		lblChromaticNumber.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblChromaticNumber.setBounds(19, 92, 135, 27);
		resultPanel.add(lblChromaticNumber);
		
		lblKValue = new JLabel();
		lblKValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblKValue.setText("--");
		lblKValue.setForeground(Color.BLUE);
		lblKValue.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblKValue.setBounds(144, 92, 63, 27);
		resultPanel.add(lblKValue);
		
		JLabel lblColouringSeq = new JLabel();
		lblColouringSeq.setText("Colouring sequence:");
		lblColouringSeq.setForeground(Color.BLACK);
		lblColouringSeq.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblColouringSeq.setBounds(19, 116, 148, 27);
		resultPanel.add(lblColouringSeq);
		
		txtColourSeq = new JTextPane();
		txtColourSeq.setEditable(false);
		txtColourSeq.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		txtColourSeq.setBackground(new Color(255, 250, 240));
		txtColourSeq.setForeground(Color.BLUE);
		txtColourSeq.setBounds(19, 138, 209, 85);
		
		slider = new JScrollPane(txtColourSeq);
		slider.setBounds(19, 138, 213, 85);
		slider.setBorder(null);
		resultPanel.add(slider);
		
		JLabel lblNodeId = new JLabel();
		lblNodeId.setText("Node ID:");
		lblNodeId.setForeground(Color.BLACK);
		lblNodeId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNodeId.setBounds(19, 240, 63, 27);
		resultPanel.add(lblNodeId);
		
		comboBoxNodeId = new JComboBox();
		comboBoxNodeId.setBounds(78, 241, 71, 27);
		comboBoxNodeId.addActionListener(new ComboboxActionListener());
		resultPanel.add(comboBoxNodeId);
		
		panelColour = new JTextPane();
		panelColour.setForeground(Color.BLACK);
		panelColour.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		panelColour.setEditable(false);
		panelColour.setBackground(new Color(255, 250, 240));
		panelColour.setBounds(156, 244, 63, 20);
		resultPanel.add(panelColour);
		
		txtPaneScript = new JTextPane();
		txtPaneScript.setForeground(Color.BLUE);
		txtPaneScript.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtPaneScript.setEditable(false);
		txtPaneScript.setBackground(new Color(255, 250, 240));
		txtPaneScript.setBounds(19, 279, 209, 190);
		
		slider2 = new JScrollPane(txtPaneScript);
		slider2.setBounds(19, 279, 213, 190);
		slider2.setBorder(null);
		resultPanel.add(slider2);
	}

	public void colourGraph() {
		if (algorithm == null) {
			return;
		}
				
		switch (algorithm) {
		case Constants.GREEDY:
			greedyAlgorithm = new GreedyAlgorithm();
			greedyAlgorithm.init(graph);
			greedyAlgorithm.compute();
			lblKValue.setText(Integer.toString(greedyAlgorithm.getK()));
			txtColourSeq.setText(greedyAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(greedyAlgorithm.getScript());			
			break;

		case Constants.RANDOM_SEQ:
			randomSequentialAlgorithm = new RandomSequentialAlgorithm();
			randomSequentialAlgorithm.init(graph);
			randomSequentialAlgorithm.compute();
			lblKValue.setText(Integer.toString(randomSequentialAlgorithm.getK()));
			txtColourSeq.setText(randomSequentialAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(randomSequentialAlgorithm.getScript());
			break;
			
		case Constants.LARGEST_FIRST:
			largestFirstAlgorithm = new LargestFirstAlgorithm();
			largestFirstAlgorithm.init(graph);
			largestFirstAlgorithm.compute();
			lblKValue.setText(Integer.toString(largestFirstAlgorithm.getK()));
			txtColourSeq.setText(largestFirstAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(largestFirstAlgorithm.getScript());
			break;
			
		case Constants.SMALLEST_LAST:
			smallestLastAlgorithm = new SmallestLastAlgorithm();
			smallestLastAlgorithm.init(graph);
			smallestLastAlgorithm.compute();
			lblKValue.setText(Integer.toString(smallestLastAlgorithm.getK()));
			txtColourSeq.setText(smallestLastAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(smallestLastAlgorithm.getScript());
			break;
			
		case Constants.CONNECTED_SEQ:
			connectedSequentialAlgorithm = new ConnectedSequentialAlgorithm();
			connectedSequentialAlgorithm.init(graph);
			connectedSequentialAlgorithm.compute();
			lblKValue.setText(Integer.toString(connectedSequentialAlgorithm.getK()));
			txtColourSeq.setText(connectedSequentialAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(connectedSequentialAlgorithm.getScript());
			break;
			
		case Constants.SATURATION_LF:
			saturationLargestFirstAlgorithm = new SaturationLargestFirstAlgorithm();
			saturationLargestFirstAlgorithm.init(graph);
			saturationLargestFirstAlgorithm.compute();
			lblKValue.setText(Integer.toString(saturationLargestFirstAlgorithm.getK()));
			txtColourSeq.setText(saturationLargestFirstAlgorithm.getNodeIdSequence().toString());
			txtPaneScript.setText(saturationLargestFirstAlgorithm.getScript());
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
		comboBoxNodeId.setModel(new DefaultComboBoxModel(array));
	}
	
	public void clear() {
		lblAlgorithmName.setText("Algorithm");
		lblKValue.setText("--");
		txtColourSeq.setText("");
		panelColour.setBackground(new Color(255, 250, 240));
		txtPaneScript.setText("");
	}
	
	class PreviousPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("START: Change ColouringPanel to AlgorithmPanel");
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
	
	class ComboboxActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int value = (int) comboBoxNodeId.getSelectedItem();
			Node selectedNode = graph.getNode(Integer.toString(value));
			
			panelColour.setText(Constants.COLOURS[(selectedNode.getAttribute("colour"))]);
		}
		
	}
}