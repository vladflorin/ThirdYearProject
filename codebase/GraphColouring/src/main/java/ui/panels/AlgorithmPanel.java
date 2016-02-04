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

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AlgorithmPanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(AlgorithmPanel.class);

	Container container;

	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	
	// Navigation panel UI
	JButton btnHomePanel;
	JButton btnPreviousPanel;
	
	// Single algorithm mode
	JComboBox comboBox;
	JButton btnColourGraph;
	JLabel singleAlgErrorLabel;
	
	// Tutorial mode
	JComboBox comboBox_1;
	JLabel lblErrorTutorial;
	JButton btnTutorial;
	
	// Compare algorithms mode
	JComboBox comboBoxCompare2;
	JComboBox comboBoxCompare1;
	JLabel compareAlgErrorLabel;
	JButton btnCompareAlgorithms;
	
	public AlgorithmPanel(Container currentContainer) {
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(222, 184, 135), 3, true));
		panel_1.setBounds(928, 30, 248, 126);
		panel_1.setBackground(new Color(250, 240, 230));
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel selectSingleAlgLabel = new JLabel("Select algorithm");
		selectSingleAlgLabel.setBounds(71, 16, 106, 16);
		panel_1.add(selectSingleAlgLabel);
		
		singleAlgErrorLabel = new JLabel("Please select an algorithm.");
		singleAlgErrorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		singleAlgErrorLabel.setForeground(Color.RED);
		singleAlgErrorLabel.setBounds(57, 60, 132, 16);
		singleAlgErrorLabel.setVisible(false);
		panel_1.add(singleAlgErrorLabel);
		
		comboBox = new JComboBox(Constants.ALGORITHMS);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(20, 32, 202, 27);
		comboBox.addActionListener(new SingleGraphColouringComboboxActionListener());
		panel_1.add(comboBox);
		
		btnColourGraph = new JButton("Colour graph");
		btnColourGraph.setForeground(Color.BLACK);
		btnColourGraph.setBounds(57, 78, 132, 35);
		btnColourGraph.addActionListener(new SingleGraphColouringActionListener());
		panel_1.add(btnColourGraph);
				
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 127, 80), 3, true));
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(928, 308, 248, 214);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectAlgorithm = new JLabel("Select algorithm 1");
		lblSelectAlgorithm.setBounds(71, 19, 114, 16);
		panel.add(lblSelectAlgorithm);
		
		compareAlgErrorLabel = new JLabel("Please select two algorithms.");
		compareAlgErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		compareAlgErrorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		compareAlgErrorLabel.setForeground(Color.RED);
		compareAlgErrorLabel.setBounds(45, 128, 154, 16);
		compareAlgErrorLabel.setVisible(false);
		panel.add(compareAlgErrorLabel);
		
		comboBoxCompare1 = new JComboBox(Constants.ALGORITHMS);
		comboBoxCompare1.setSelectedIndex(-1);
		comboBoxCompare1.setBounds(20, 35, 202, 27);
		panel.add(comboBoxCompare1);
		
		JLabel lblSelectAlgorithm_1 = new JLabel("Select algorithm 2");
		lblSelectAlgorithm_1.setBounds(71, 74, 114, 16);
		panel.add(lblSelectAlgorithm_1);
		
		comboBoxCompare2 = new JComboBox(Constants.ALGORITHMS);
		comboBoxCompare2.setSelectedIndex(-1);
		comboBoxCompare2.setBounds(20, 89, 202, 27);
		panel.add(comboBoxCompare2);
		
		btnCompareAlgorithms = new JButton("Compare algorithms");
		btnCompareAlgorithms.setForeground(Color.BLACK);
		btnCompareAlgorithms.setBounds(45, 160, 154, 35);
		btnCompareAlgorithms.addActionListener(new CompareAlgorithmsActionListener());
		panel.add(btnCompareAlgorithms);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 139), 3, true));
		panel_2.setBounds(928, 168, 248, 128);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Select algorithm");
		label.setBounds(71, 16, 102, 16);
		panel_2.add(label);
		
		comboBox_1 = new JComboBox(Constants.ALGORITHMS_TUTORIAL);
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(20, 34, 202, 27);
		panel_2.add(comboBox_1);
		
		lblErrorTutorial = new JLabel("Please select an algorithm.");
		lblErrorTutorial.setForeground(new Color(255, 0, 0));
		lblErrorTutorial.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		lblErrorTutorial.setBounds(57, 62, 130, 16);
		lblErrorTutorial.setVisible(false);
		panel_2.add(lblErrorTutorial);
		
		btnTutorial = new JButton("Tutorial");
		btnTutorial.setForeground(Color.BLACK);
		btnTutorial.setBounds(57, 79, 132, 35);
		btnTutorial.addActionListener(new TutorialActionListener());
		panel_2.add(btnTutorial);
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
	
	public void clear() {
		singleAlgErrorLabel.setVisible(false);
		comboBox.setSelectedIndex(-1);
		
		lblErrorTutorial.setVisible(false);
		comboBox_1.setSelectedIndex(-1);
		
		comboBoxCompare1.setSelectedIndex(-1);
		comboBoxCompare2.setSelectedIndex(-1);
		compareAlgErrorLabel.setVisible(false);
	}
	
	class PreviousPanelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("START: Change AlgorithmPanel to InputPanel");
			container.getInputPanel().clear();
			container.getCardLayout().show(container, "inputPanel");
			logger.info("END: Change AlgorithmPanel to InputPanel");
		}	
	}
	
	class SingleGraphColouringActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedIndex() == -1) {
				singleAlgErrorLabel.setVisible(true);
			} else {
				String selectedAlgorithm = (String) comboBox.getSelectedItem();
				singleAlgErrorLabel.setVisible(false);
				logger.info("Selected algorithm: " + selectedAlgorithm);
				displayColouringPanel(selectedAlgorithm);
			}
		}
		
		public void displayColouringPanel(String algorithm) {
			logger.info("START: Change AlgorithmPanel to ColouringPanel");
			container.getColouringPanel().clear();
			container.getColouringPanel().setGraph(graph);
			container.getColouringPanel().setAlgorithm(algorithm);
			container.getColouringPanel().colourGraph();
			container.getCardLayout().show(container, "colouringPanel");
			logger.info("END: Change AlgorithmPanel to ColouringPanel");
		}
	}
	
	class TutorialActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboBox_1.getSelectedIndex() == -1) {
				lblErrorTutorial.setVisible(true);
			} else {
				String selectedAlgorithm = (String) comboBox_1.getSelectedItem();
				lblErrorTutorial.setVisible(false);
				logger.info("Selected algorithm: " + selectedAlgorithm);
				displayTutorialPanel(selectedAlgorithm);
			}
		}
		
		public void displayTutorialPanel(String algorithm) {
			logger.info("START: Change AlgorithmPanel to TutorialPanel");
			container.getTutorialPanel().clear(); 
			container.getTutorialPanel().setGraph(graph);
			container.getTutorialPanel().setAlgorithm(algorithm);
			container.getCardLayout().show(container, "tutorialPanel");
			logger.info("END: Change AlgorithmPanel to TutorialPanel");
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
	
	class SingleGraphColouringComboboxActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			singleAlgErrorLabel.setVisible(false);
		}
	}
	
	class CompareAlgorithmsActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboBoxCompare1.getSelectedIndex() == -1 || comboBoxCompare2.getSelectedIndex() == -1) {
				compareAlgErrorLabel.setVisible(true);
			} else {
				compareAlgErrorLabel.setVisible(false);
				String selectedAlgorithm1 = (String) comboBoxCompare1.getSelectedItem();
				String selectedAlgorithm2 = (String) comboBoxCompare2.getSelectedItem();			
				logger.info("Selected algorithms: " + selectedAlgorithm1 + ", " + selectedAlgorithm2);
				displayComparePanel(selectedAlgorithm1, selectedAlgorithm2);
			}
		}
		
		public void displayComparePanel(String algorithm1, String algorithm2) {
			logger.info("START: Change AlgorithmPanel to ComparePanel");
			container.getComparePanel().clear();
			container.getComparePanel().setGraph1(graph);
			container.getComparePanel().setGraph2(graph);
			container.getComparePanel().setAlgorithm1(algorithm1);
			container.getComparePanel().setAlgorithm2(algorithm2);
			container.getComparePanel().colourGraph1();
			container.getComparePanel().colourGraph2();
			container.getCardLayout().show(container, "comparePanel");
			logger.info("END: Change AlgorithmPanel to ComparePanel");
		}
	}
}