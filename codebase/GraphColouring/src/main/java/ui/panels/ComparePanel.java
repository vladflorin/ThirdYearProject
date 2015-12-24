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

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class ComparePanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(ComparePanel.class);

	Container container;
	
	private String algorithm1 = null;	
	private String algorithm2 = null;

	protected boolean loop = true;
	
	Graph graph1 = new SingleGraph("graph" + new Date());
	Graph graph2 = new SingleGraph("graph2" + new Date());
	
	Viewer viewer;
	View view;
	
	// Results UI 1
	JLabel lblAlgorithmName1;
	JLabel lblKValue1;
	JTextPane txtColourSeq1;
	JScrollPane slider1;
	JLabel lblAlg1;
	
	// Results UI 2
	JLabel lblAlgorithmName2;
	JLabel lblKValue2;
	JLabel lblAlg2;
	JTextPane txtColourSeq2;
	JScrollPane slider2;
	
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
	
	public ComparePanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel graph1Panel = new JPanel();
		graph1Panel.setBorder(new LineBorder(UIManager.getColor("window"), 2, true));
		graph1Panel.setBackground(Color.WHITE);
		graph1Panel.setBounds(17, 30, 421, 566);
		add(graph1Panel);
		graph1Panel.setLayout(new BorderLayout(0, 0));
		
		viewer = new Viewer(graph1, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		
		graph1Panel.add((Component) view, BorderLayout.CENTER); 
			
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
		
		JPanel resultPanel1 = new JPanel();
		resultPanel1.setBorder(new LineBorder(new Color(255, 165, 0), 3, true));
		resultPanel1.setBackground(new Color(255, 250, 240));
		resultPanel1.setBounds(928, 30, 248, 240);
		add(resultPanel1);
		resultPanel1.setLayout(null);
		
		lblAlgorithmName1 = new JLabel();
		lblAlgorithmName1.setText("Algorithm");
		lblAlgorithmName1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAlgorithmName1.setForeground(Color.RED);
		lblAlgorithmName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName1.setBounds(29, 35, 191, 27);
		resultPanel1.add(lblAlgorithmName1);
		
		JLabel lblChromaticNumber = new JLabel();
		lblChromaticNumber.setText("Chromatic number:");
		lblChromaticNumber.setForeground(Color.BLACK);
		lblChromaticNumber.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblChromaticNumber.setBounds(19, 92, 135, 27);
		resultPanel1.add(lblChromaticNumber);
		
		lblKValue1 = new JLabel();
		lblKValue1.setHorizontalAlignment(SwingConstants.CENTER);
		lblKValue1.setText("--");
		lblKValue1.setForeground(Color.BLUE);
		lblKValue1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblKValue1.setBounds(144, 92, 63, 27);
		resultPanel1.add(lblKValue1);
		
		JLabel lblColouringSeq = new JLabel();
		lblColouringSeq.setText("Colouring sequence:");
		lblColouringSeq.setForeground(Color.BLACK);
		lblColouringSeq.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblColouringSeq.setBounds(19, 116, 148, 27);
		resultPanel1.add(lblColouringSeq);
		
		txtColourSeq1 = new JTextPane();
		txtColourSeq1.setEditable(false);
		txtColourSeq1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		txtColourSeq1.setBackground(new Color(255, 250, 240));
		txtColourSeq1.setForeground(Color.BLUE);
		txtColourSeq1.setBounds(19, 138, 209, 85);
		
		slider1 = new JScrollPane(txtColourSeq1);
		slider1.setBounds(19, 138, 213, 85);
		slider1.setBorder(null);
		resultPanel1.add(slider1);
		
		JPanel resultPanel2 = new JPanel();
		resultPanel2.setLayout(null);
		resultPanel2.setBorder(new LineBorder(new Color(165, 42, 42), 3, true));
		resultPanel2.setBackground(new Color(255, 250, 250));
		resultPanel2.setBounds(928, 282, 248, 240);
		add(resultPanel2);
		
		lblAlgorithmName2 = new JLabel();
		lblAlgorithmName2.setText("Algorithm");
		lblAlgorithmName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName2.setForeground(Color.RED);
		lblAlgorithmName2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAlgorithmName2.setBounds(30, 35, 191, 27);
		resultPanel2.add(lblAlgorithmName2);
		
		JLabel label_1 = new JLabel();
		label_1.setText("Chromatic number:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setBounds(19, 92, 135, 27);
		resultPanel2.add(label_1);
		
		lblKValue2 = new JLabel();
		lblKValue2.setText("--");
		lblKValue2.setHorizontalAlignment(SwingConstants.CENTER);
		lblKValue2.setForeground(Color.BLUE);
		lblKValue2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblKValue2.setBounds(144, 92, 63, 27);
		resultPanel2.add(lblKValue2);
		
		JLabel label_3 = new JLabel();
		label_3.setText("Colouring sequence:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(19, 116, 148, 27);
		resultPanel2.add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		textPane.setEditable(false);
		textPane.setBackground(new Color(255, 250, 240));
		textPane.setBounds(156, 244, 63, 20);
		resultPanel2.add(textPane);
		
		txtColourSeq2 = new JTextPane();
		txtColourSeq2.setForeground(Color.BLUE);
		txtColourSeq2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		txtColourSeq2.setEditable(false);
		txtColourSeq2.setBackground(new Color(255, 250, 250));
		txtColourSeq2.setBounds(19, 147, 213, 85);
		
		slider2 = new JScrollPane(txtColourSeq2);
		slider2.setBounds(19, 147, 217, 85);
		slider2.setBorder(null);
		resultPanel2.add(slider2);
				
		JPanel graph2Panel = new JPanel();
		graph2Panel.setBorder(new LineBorder(UIManager.getColor("window"), 2, true));
		graph2Panel.setBackground(Color.WHITE);
		graph2Panel.setBounds(468, 30, 421, 566);
		add(graph2Panel);
		graph2Panel.setLayout(new BorderLayout(0, 0));
		
		viewer = new Viewer(graph2, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		
		graph2Panel.add((Component) view, BorderLayout.CENTER); 
		
		lblAlg1 = new JLabel();
		lblAlg1.setText("Algorithm");
		lblAlg1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlg1.setForeground(Color.RED);
		lblAlg1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAlg1.setBounds(133, 597, 191, 27);
		add(lblAlg1);
		
		lblAlg2 = new JLabel();
		lblAlg2.setText("Algorithm");
		lblAlg2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlg2.setForeground(Color.RED);
		lblAlg2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAlg2.setBounds(604, 597, 191, 27);
		add(lblAlg2);
	}

	public void colourGraph1() {
		if (algorithm1 == null) {
			return;
		}
				
		switch (algorithm1) {
		case Constants.GREEDY:
			greedyAlgorithm = new GreedyAlgorithm();
			greedyAlgorithm.init(graph1);
			greedyAlgorithm.compute();
			lblKValue1.setText(Integer.toString(greedyAlgorithm.getK()));
			txtColourSeq1.setText(greedyAlgorithm.getNodeIdSequence().toString());
			break;

		case Constants.RANDOM_SEQ:
			randomSequentialAlgorithm = new RandomSequentialAlgorithm();
			randomSequentialAlgorithm.init(graph1);
			randomSequentialAlgorithm.compute();
			lblKValue1.setText(Integer.toString(randomSequentialAlgorithm.getK()));
			txtColourSeq1.setText(randomSequentialAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.LARGEST_FIRST:
			largestFirstAlgorithm = new LargestFirstAlgorithm();
			largestFirstAlgorithm.init(graph1);
			largestFirstAlgorithm.compute();
			lblKValue1.setText(Integer.toString(largestFirstAlgorithm.getK()));
			txtColourSeq1.setText(largestFirstAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.SMALLEST_LAST:
			smallestLastAlgorithm = new SmallestLastAlgorithm();
			smallestLastAlgorithm.init(graph1);
			smallestLastAlgorithm.compute();
			lblKValue1.setText(Integer.toString(smallestLastAlgorithm.getK()));
			txtColourSeq1.setText(smallestLastAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.CONNECTED_SEQ:
			connectedSequentialAlgorithm = new ConnectedSequentialAlgorithm();
			connectedSequentialAlgorithm.init(graph1);
			connectedSequentialAlgorithm.compute();
			lblKValue1.setText(Integer.toString(connectedSequentialAlgorithm.getK()));
			txtColourSeq1.setText(connectedSequentialAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.SATURATION_LF:
			saturationLargestFirstAlgorithm = new SaturationLargestFirstAlgorithm();
			saturationLargestFirstAlgorithm.init(graph1);
			saturationLargestFirstAlgorithm.compute();
			lblKValue1.setText(Integer.toString(saturationLargestFirstAlgorithm.getK()));
			txtColourSeq1.setText(saturationLargestFirstAlgorithm.getNodeIdSequence().toString());
			break;
		}	
		txtColourSeq1.setCaretPosition(0);
	}
	
	public void colourGraph2() {
		if (algorithm2 == null) {
			return;
		}
				
		switch (algorithm2) {
		case Constants.GREEDY:
			greedyAlgorithm = new GreedyAlgorithm();
			greedyAlgorithm.init(graph2);
			greedyAlgorithm.compute();
			lblKValue2.setText(Integer.toString(greedyAlgorithm.getK()));
			txtColourSeq2.setText(greedyAlgorithm.getNodeIdSequence().toString());
			break;

		case Constants.RANDOM_SEQ:
			randomSequentialAlgorithm = new RandomSequentialAlgorithm();
			randomSequentialAlgorithm.init(graph2);
			randomSequentialAlgorithm.compute();
			lblKValue2.setText(Integer.toString(randomSequentialAlgorithm.getK()));
			txtColourSeq2.setText(randomSequentialAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.LARGEST_FIRST:
			largestFirstAlgorithm = new LargestFirstAlgorithm();
			largestFirstAlgorithm.init(graph2);
			largestFirstAlgorithm.compute();
			lblKValue2.setText(Integer.toString(largestFirstAlgorithm.getK()));
			txtColourSeq2.setText(largestFirstAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.SMALLEST_LAST:
			smallestLastAlgorithm = new SmallestLastAlgorithm();
			smallestLastAlgorithm.init(graph2);
			smallestLastAlgorithm.compute();
			lblKValue2.setText(Integer.toString(smallestLastAlgorithm.getK()));
			txtColourSeq2.setText(smallestLastAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.CONNECTED_SEQ:
			connectedSequentialAlgorithm = new ConnectedSequentialAlgorithm();
			connectedSequentialAlgorithm.init(graph2);
			connectedSequentialAlgorithm.compute();
			lblKValue2.setText(Integer.toString(connectedSequentialAlgorithm.getK()));
			txtColourSeq2.setText(connectedSequentialAlgorithm.getNodeIdSequence().toString());
			break;
			
		case Constants.SATURATION_LF:
			saturationLargestFirstAlgorithm = new SaturationLargestFirstAlgorithm();
			saturationLargestFirstAlgorithm.init(graph2);
			saturationLargestFirstAlgorithm.compute();
			lblKValue2.setText(Integer.toString(saturationLargestFirstAlgorithm.getK()));
			txtColourSeq2.setText(saturationLargestFirstAlgorithm.getNodeIdSequence().toString());
			break;
		}	
		txtColourSeq2.setCaretPosition(0);
	}
	
	public String getAlgorithm1() {
		return algorithm1;
	}

	public void setAlgorithm1(String algorithm) {
		this.algorithm1 = algorithm;
		lblAlgorithmName1.setText(algorithm);
		lblAlg1.setText(algorithm);
	}
	
	public String getAlgorithm2() {
		return algorithm1;
	}

	public void setAlgorithm2(String algorithm) {
		this.algorithm2 = algorithm;
		lblAlgorithmName2.setText(algorithm);
		lblAlg2.setText(algorithm);
	}
	
	public Graph getGraph1() {
		return graph1;
	}

	public void setGraph1(Graph graph) {
		this.graph1.clear();
		Graphs.mergeIn(this.graph1, graph);
		
		List<Integer> nodeList = new ArrayList<>();
		for (Node node : this.graph1.getNodeSet()) {
			NodeUtils.setInitialSize(node);
			nodeList.add(Integer.parseInt(node.getId()));
		}
		
		Integer[] array = nodeList.toArray(new Integer[nodeList.size()]);
	}
	

	public Graph getGraph2() {
		return graph2;
	}

	public void setGraph2(Graph graph) {
		this.graph2.clear();
		Graphs.mergeIn(this.graph2, graph);
		
		List<Integer> nodeList = new ArrayList<>();
		for (Node node : this.graph2.getNodeSet()) {
			NodeUtils.setInitialSize(node);
			nodeList.add(Integer.parseInt(node.getId()));
		}
		
		Integer[] array = nodeList.toArray(new Integer[nodeList.size()]);
	}

	public void clear() {
		// Algorithm 1
		lblAlgorithmName1.setText("Algorithm 1");
		lblAlg1.setText("Algorithm 1");
		lblKValue1.setText("--");
		txtColourSeq1.setText("");
		
		// Algorithm 2
		lblAlgorithmName2.setText("Algorithm 2");
		lblAlg2.setText("Algorithm 2");
		lblKValue2.setText("--");
		txtColourSeq2.setText("");
	}
	
	class WelcomePanelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			container.getCardLayout().show(container, "welcomePanel");		
			// Clear all the panels
			container.clearAllPanels();
		}	
	}
	
	class PreviousPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("START: Change ComparePanel to AlgorithmPanel");
			container.getAlgorithmPanel().clear();
			container.getCardLayout().show(container, "algorithmPanel");
			logger.info("END: Change ComparePanel to AlgorithmPanel");
		}	
	}
}