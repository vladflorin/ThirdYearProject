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

import main.java.algorithms.SaturationLargestFirstAlgorithm;
import main.java.tutorials.ConnectedSequentialTutorial;
import main.java.tutorials.LargestFirstTutorial;
import main.java.tutorials.RandomSequentialTutorial;
import main.java.tutorials.SmallestLastTutorial;
import main.java.utils.Constants;
import main.java.utils.GraphGenerator;
import main.java.utils.NodeUtils;
import main.java.utils.Utils;

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
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DocumentationPanel extends JPanel  {
	
	final static Logger logger = Logger.getLogger(DocumentationPanel.class);

	Container container;

	protected boolean loop = true;
	
	Graph inputGraph = new SingleGraph("inputGraph" + new Date());
	Graph outputGraph = new SingleGraph("outputGraph" + new Date());

	JTextArea textArea;
	
	Viewer inputViewer, outputViewer;
	View inputView, outputView;
	
	// Navigation panel UI
	JButton btnHomePanel;
	JButton btnPreviousPanel;
	
	JButton btnRS, btnLF, btnSL, btnCS, btnSLF;
	
	JLabel lblAlgorithm;
	
	public DocumentationPanel(Container currentContainer) {
		this.container = currentContainer;
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel outputGraphPanel = new JPanel();
		outputGraphPanel.setBorder(new LineBorder(new Color(255, 69, 0), 3, true));
		outputGraphPanel.setBackground(Color.WHITE);
		outputGraphPanel.setBounds(17, 376, 465, 251);
		add(outputGraphPanel);
		outputGraphPanel.setLayout(new BorderLayout(0, 0));
			
		outputViewer = new Viewer(outputGraph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		outputViewer.enableAutoLayout();
		outputView = outputViewer.addDefaultView(false);
		
		outputGraphPanel.add((Component) outputView, BorderLayout.CENTER); 
		
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
		btnHomePanel.setToolTipText("Home screen");
		btnHomePanel.setIcon(new ImageIcon(getClass().getResource(Constants.HOME_ICON_PATH)));
		btnHomePanel.setBounds(96, 20, 55, 55);
		btnHomePanel.addActionListener(new WelcomePanelActionListener());
		navigationPanel.add(btnHomePanel);
		
		btnPreviousPanel = new JButton("");
		btnPreviousPanel.setToolTipText("Previous step");
		btnPreviousPanel.setIcon(new ImageIcon(getClass().getResource(Constants.LEFT_ICON_PATH)));
		btnPreviousPanel.setBounds(29, 20, 55, 55);
		btnPreviousPanel.addActionListener(new PreviousPanelActionListener());
		navigationPanel.add(btnPreviousPanel);
		
		JPanel algorithmsPanel = new JPanel();
		algorithmsPanel.setBackground(new Color(255, 255, 240));
		algorithmsPanel.setBorder(new LineBorder(new Color(255, 140, 0), 3, true));
		algorithmsPanel.setBounds(928, 30, 248, 492);
		add(algorithmsPanel);
		algorithmsPanel.setLayout(null);
		
		btnRS = new JButton("Random Sequential");
		btnRS.setBounds(32, 55, 183, 50);
		algorithmsPanel.add(btnRS);
		btnRS.addActionListener(new RSActionListener());
		
		btnLF = new JButton("Largest First");
		btnLF.setBounds(32, 140, 183, 50);
		algorithmsPanel.add(btnLF);
		btnLF.addActionListener(new LFActionListener());
		
		btnSL = new JButton("Smallest Last");
		btnSL.setBounds(32, 225, 183, 50);
		algorithmsPanel.add(btnSL);
		btnSL.addActionListener(new SLActionListener());
		
		btnCS = new JButton("Connected Sequential");
		btnCS.setBounds(32, 310, 183, 50);
		algorithmsPanel.add(btnCS);
		btnCS.addActionListener(new CSActionListener());
		
		btnSLF = new JButton("Saturation Largest First");
		btnSLF.setBounds(32, 395, 183, 50);
		btnSLF.addActionListener(new SLFActionListener());
		algorithmsPanel.add(btnSLF);
		
		JLabel lblOuputGraph = new JLabel("Ouput graph");
		lblOuputGraph.setForeground(new Color(255, 0, 0));
		lblOuputGraph.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblOuputGraph.setBounds(17, 358, 95, 16);
		add(lblOuputGraph);
		
		JPanel inputGraphPanel = new JPanel();
		inputGraphPanel.setBorder(new LineBorder(new Color(34, 139, 34), 3, true));
		inputGraphPanel.setBackground(Color.WHITE);
		inputGraphPanel.setBounds(17, 90, 465, 251);
		add(inputGraphPanel);
		inputGraphPanel.setLayout(new BorderLayout(0, 0));
		
		inputViewer = new Viewer(inputGraph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		inputViewer.enableAutoLayout();
		inputView = inputViewer.addDefaultView(false);
		
		inputGraphPanel.add((Component) inputView, BorderLayout.CENTER); 
		
		JLabel lblInputGraph = new JLabel("Input graph");
		lblInputGraph.setForeground(new Color(34, 139, 34));
		lblInputGraph.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblInputGraph.setBounds(17, 72, 95, 16);
		add(lblInputGraph);
		
		JLabel lblSmallestHardtocolourGraph = new JLabel("Smallest hard-to-colour graph");
		lblSmallestHardtocolourGraph.setToolTipText("Graph G is called hard-to-colour for algorithm A if any implementation of A leads to a suboptimal coloruing of G.  ");
		lblSmallestHardtocolourGraph.setForeground(new Color(0, 0, 0));
		lblSmallestHardtocolourGraph.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblSmallestHardtocolourGraph.setBounds(109, 31, 280, 29);
		add(lblSmallestHardtocolourGraph);
		
		JPanel panelAlgDesription = new JPanel();
		panelAlgDesription.setLayout(null);
		panelAlgDesription.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panelAlgDesription.setBackground(Color.WHITE);
		panelAlgDesription.setBounds(494, 30, 411, 492);
		add(panelAlgDesription);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setTabSize(2);
		textArea.setBounds(18, 60, 368, 426);
		panelAlgDesription.add(textArea);
		
		lblAlgorithm = new JLabel("Algorithm");
		lblAlgorithm.setForeground(Color.BLUE);
		lblAlgorithm.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAlgorithm.setBounds(18, 19, 260, 29);
		panelAlgDesription.add(lblAlgorithm);
		
		JPanel panelRef = new JPanel();
		panelRef.setLayout(null);
		panelRef.setBorder(new LineBorder(new Color(0, 0, 153), 3, true));
		panelRef.setBackground(new Color(204, 255, 255));
		panelRef.setBounds(494, 534, 411, 93);
		add(panelRef);
		
		JLabel lblNewLabel_1 = new JLabel("References");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_1.setBounds(16, 17, 80, 16);
		panelRef.add(lblNewLabel_1);
		
		JLabel lblKubaleM = new JLabel("Kubale, M. (2004). Graph colorings. Providence, American Mathematical Society.");
		lblKubaleM.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblKubaleM.setVerticalAlignment(SwingConstants.TOP);
		lblKubaleM.setBounds(16, 40, 369, 11);
		panelRef.add(lblKubaleM);
		
		JLabel lblWilsonR = new JLabel("Wilson, R. (2002). Graphs, colourings, and the four-colour theorem");
		lblWilsonR.setVerticalAlignment(SwingConstants.TOP);
		lblWilsonR.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblWilsonR.setBounds(16, 54, 369, 11);
		panelRef.add(lblWilsonR);
		
		btnRS.doClick();
		setForegroundBlackAllButtons();
		btnRS.setForeground(Color.RED);
	}

	public Graph getInputGraph() {
		return inputGraph;
	}

	public void setInputGraph(Graph graph) {
		this.inputGraph.clear();
		Graphs.mergeIn(this.inputGraph, graph);
		for (Node node : this.inputGraph.getNodeSet()) {
			NodeUtils.setInitialSize(node);
		}
	}
	
	public void clear() {
	}
	
	class PreviousPanelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("START: Change DocumentationPanel to AlgorithmPanel");
			container.getCardLayout().show(container, "algorithmPanel");
			logger.info("END: Change DocumentationPanel to AlgorithmPanel");
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
	
	class RSActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			lblAlgorithm.setText(Constants.RANDOM_SEQ);
			
			setForegroundBlackAllButtons();
			btnRS.setForeground(Color.RED);

			inputGraph = GraphGenerator.generateRSGraph(inputGraph);
			
			outputGraph.clear();
			Graphs.mergeIn(outputGraph, inputGraph);
			RandomSequentialTutorial rsTutorial = new RandomSequentialTutorial();
			rsTutorial.init(outputGraph);
			rsTutorial.compute();
			
			try {
				textArea.setText(Utils.readAlgorithmDescription(Constants.RS_PATH));
			} catch (IOException e1) {
				textArea.setText("");
				logger.info("Something went wrong while reading the file: " + e1.getMessage());
			}
		}	
	}
	
	class LFActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblAlgorithm.setText(Constants.LARGEST_FIRST);

			setForegroundBlackAllButtons();
			btnLF.setForeground(Color.RED);

			inputGraph = GraphGenerator.generateLFGraph(inputGraph);
			
			outputGraph.clear();
			Graphs.mergeIn(outputGraph, inputGraph);
			LargestFirstTutorial lfTutorial = new LargestFirstTutorial();
			lfTutorial.init(outputGraph);
			lfTutorial.compute();
			
			try {
				textArea.setText(Utils.readAlgorithmDescription(Constants.LF_PATH));
			} catch (IOException e1) {
				textArea.setText("");
				logger.info("Something went wrong while reading the file: " + e1.getMessage());
			}
		}
	}
	
	class SLActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblAlgorithm.setText(Constants.SMALLEST_LAST);

			setForegroundBlackAllButtons();
			btnSL.setForeground(Color.RED);

			inputGraph = GraphGenerator.generateSLGraph(inputGraph);
			
			outputGraph.clear();
			Graphs.mergeIn(outputGraph, inputGraph);
			SmallestLastTutorial slTutorial = new SmallestLastTutorial();
			slTutorial.init(outputGraph);
			slTutorial.compute();
			
			try {
				textArea.setText(Utils.readAlgorithmDescription(Constants.SL_PATH));
			} catch (IOException e1) {
				textArea.setText("");
				logger.info("Something went wrong while reading the file: " + e1.getMessage());
			}		
		}		
	}
	
	class CSActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblAlgorithm.setText(Constants.CONNECTED_SEQ);

			setForegroundBlackAllButtons();
			btnCS.setForeground(Color.RED);

			inputGraph = GraphGenerator.generateCSGraph(inputGraph);
			
			outputGraph.clear();
			Graphs.mergeIn(outputGraph, inputGraph);
			ConnectedSequentialTutorial csTutorial = new ConnectedSequentialTutorial();
			csTutorial.init(outputGraph);
			csTutorial.compute();
			
			try {
				textArea.setText(Utils.readAlgorithmDescription(Constants.CS_PATH));
			} catch (IOException e1) {
				textArea.setText("");
				logger.info("Something went wrong while reading the file: " + e1.getMessage());
			}
		}		
	}
	
	class SLFActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblAlgorithm.setText(Constants.SATURATION_LF);

			setForegroundBlackAllButtons();
			btnSLF.setForeground(Color.RED);

			inputGraph = GraphGenerator.generateSLFGraph(inputGraph);
			
			outputGraph.clear();
			Graphs.mergeIn(outputGraph, inputGraph);
			SaturationLargestFirstAlgorithm slfTutorial = new SaturationLargestFirstAlgorithm();
			slfTutorial.init(outputGraph);
			slfTutorial.compute();

			try {
				textArea.setText(Utils.readAlgorithmDescription(Constants.SLF_PATH));
			} catch (IOException e1) {
				textArea.setText("");
				logger.info("Something went wrong while reading the file: " + e1.getMessage());
			}
		}		
	}
	
	public void setForegroundBlackAllButtons() {
		btnRS.setForeground(Color.BLACK);
		btnLF.setForeground(Color.BLACK);
		btnSL.setForeground(Color.BLACK);
		btnCS.setForeground(Color.BLACK);
		btnSLF.setForeground(Color.BLACK);
	}
}