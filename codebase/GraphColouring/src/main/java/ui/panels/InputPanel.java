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

@SuppressWarnings("serial")
public class InputPanel extends JPanel{
	
	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	Viewer viewer;
	View view;

	JButton btnNewButton;
	JPanel graphPanel;
	
	public InputPanel() {
		setupPanel();
	}

	private void setupPanel(){
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		
		add((Component) view, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Constants.LOGO_PATH_S));
		add(lblNewLabel, BorderLayout.PAGE_END);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		add(panel_1, BorderLayout.EAST);
		
		btnNewButton = new JButton("Random graph");
		btnNewButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e)
            {
            	graph.clear();
                graph = GraphGenerator.generate(10, graph);
            }
        });
		panel_1.setLayout(new GridLayout(10, 3, 0, 0));
		
		panel_1.add(btnNewButton);
		
		JButton btnAddNode = new JButton("Add Node");
		btnAddNode.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e)
            {
                graph.addNode("" + new Date());
            }
        });
		panel_1.add(btnAddNode);
		
		JButton colourGraph = new JButton("Greedy Colour");
		colourGraph.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e)
            {
            	GreedyAlgorithm greedyColouring = new GreedyAlgorithm();
        		greedyColouring.init(graph);
        		greedyColouring.compute();
            }
        });
		panel_1.add(colourGraph);
		
		
	}
		
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JPanel getGraphPanel() {
		return graphPanel;
	}

	public void setGraphPanel(JPanel graphPanel) {
		this.graphPanel = (JPanel) graphPanel;
	}
}
