package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.lowagie.text.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import main.java.algorithms.GreedyAlgorithm;
import main.java.utils.Constants;
import main.java.utils.GraphGenerator;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import java.awt.GridLayout;

import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class AlgorithmPanel extends JPanel implements ViewerListener  {
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;

	JButton btnNewButton;
	
	ViewerPipe fromViewer;
	
	public AlgorithmPanel() {
		setupPanel();
	}
	
	public AlgorithmPanel(Graph givenGraph) {
		this.graph = Graphs.clone(givenGraph);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(928, 30, 248, 597);
		panel_1.setBackground(Color.RED);
		add(panel_1);
		
		btnNewButton = new JButton("Random graph");
		btnNewButton.setBounds(48, 6, 135, 40);
		btnNewButton.addActionListener(new GenerateGraphActionListener());
		panel_1.setLayout(null);
		
		panel_1.add(btnNewButton);

		new Thread(new Runnable() {
        	public void run() {
        		while(loop) {
        			fromViewer.pump();
        		}
        	}
        }).start();
	}
		
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	class GenerateGraphActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			graph.clear();
            graph = GraphGenerator.generate(10, 3, graph);
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