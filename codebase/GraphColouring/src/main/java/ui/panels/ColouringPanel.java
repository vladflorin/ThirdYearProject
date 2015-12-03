package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.Button;

@SuppressWarnings("serial")
public class ColouringPanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(AlgorithmPanel.class);

	Container container;
	private String algorithm = null;
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	
	// Navigation panel UI
	JButton btnHomePanel;
	JButton btnPreviousPanel;
	
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
		lblNewLabel.setIcon(new ImageIcon(Constants.LOGO_PATH_S));
		add(lblNewLabel);
				
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(null);
		navigationPanel.setBorder(new LineBorder(new Color(34, 139, 34), 3, true));
		navigationPanel.setBackground(new Color(240, 255, 240));
		navigationPanel.setBounds(928, 534, 248, 93);
		add(navigationPanel);
		
		btnHomePanel = new JButton("");
		btnHomePanel.setIcon(new ImageIcon("/Users/vladflorin/Eclipse/Documents/utils/home.png"));
		btnHomePanel.setBounds(96, 20, 55, 55);
		navigationPanel.add(btnHomePanel);
		
		btnPreviousPanel = new JButton("");
		btnPreviousPanel.setIcon(new ImageIcon("/Users/vladflorin/Eclipse/Documents/utils/left_arrow.png"));
		btnPreviousPanel.setBounds(29, 20, 55, 55);
		btnPreviousPanel.addActionListener(new PreviousPanelActionListener());
		navigationPanel.add(btnPreviousPanel);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new LineBorder(new Color(255, 165, 0), 3, true));
		resultPanel.setBackground(new Color(255, 250, 240));
		resultPanel.setBounds(928, 30, 248, 492);
		add(resultPanel);
	}

	public void colourGraph() {
		if (algorithm == null) {
			return;
		}
		
		switch (algorithm) {
		case Constants.GREEDY:
			GreedyAlgorithm greedyColouring = new GreedyAlgorithm();
			greedyColouring.init(graph);
			greedyColouring.compute();
			break;

		case Constants.RANDOM_SEQ:
			RandomSequentialAlgorithm randomSequentialColouring = new RandomSequentialAlgorithm();
			randomSequentialColouring.init(graph);
			randomSequentialColouring.compute();
			break;
			
		case Constants.LARGEST_FIRST:
			LargestFirstAlgorithm largestFirstColouring = new LargestFirstAlgorithm();
			largestFirstColouring.init(graph);
			largestFirstColouring.compute();
			break;
			
		case Constants.SMALLEST_LAST:
			SmallestLastAlgorithm smallestLastColouring = new SmallestLastAlgorithm();
			smallestLastColouring.init(graph);
			smallestLastColouring.compute();
			break;
			
		case Constants.CONNECTED_SEQ:
			ConnectedSequentialAlgorithm connectedSequentialColouring = new ConnectedSequentialAlgorithm();
			connectedSequentialColouring.init(graph);
			connectedSequentialColouring.compute();
			break;
			
		case Constants.SATURATION_LF:
			SaturationLargestFirstAlgorithm saturationLargestFirstColouring = new SaturationLargestFirstAlgorithm();
			saturationLargestFirstColouring.init(graph);
			saturationLargestFirstColouring.compute();
			break;
		}
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph.clear();
		Graphs.mergeIn(this.graph, graph);
		for (Node node : this.graph.getNodeSet()) {
			NodeUtils.setInitialSize(node);
		}
	}
	
	// TODO:
	public void clear() {
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
}