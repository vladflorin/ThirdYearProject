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

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.border.LineBorder;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AlgorithmPanel extends JPanel  {
	
	Container container;

	protected boolean loop = true;
	
	Graph graph = new SingleGraph("graph" + new Date());
	
	Viewer viewer;
	View view;
	
	// Navigation panel UI
	JButton btnHomePanel;
	JButton btnPreviousPanel;
	
	public AlgorithmPanel(Container currentContainer) {
		this.container = currentContainer;
		System.out.println(this.graph.getId());
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(222, 184, 135), 3, true));
		panel_1.setBounds(928, 30, 248, 220);
		panel_1.setBackground(new Color(250, 240, 230));
		add(panel_1);
				
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 127, 80), 3, true));
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(928, 262, 248, 260);
		add(panel);
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph.clear();
		Graphs.mergeIn(this.graph, graph);
	}
	
	class PreviousPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			container.getInputPanel().clear();
			container.getCardLayout().show(container, "inputPanel");			
		}
		
	}
}