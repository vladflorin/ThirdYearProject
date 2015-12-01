package main.java.utils;

import org.graphstream.graph.Node;

public class NodeUtils {

	public static Node setInitialSize(Node node) {
		node.addAttribute("ui.style", "size: 17px; shadow-mode: plain; text-size: 11;");
		return node;
	}
	
	public static Node addLabel(Node node) {
		node.addAttribute("ui.label", node.getId());
		return node;
	}
	
	public static Node setAsSelected(Node node) {
		node.addAttribute("ui.style", "fill-color: red;");
		return node;
	}
	
	public static Node setAsUnselected(Node node) {
		node.addAttribute("ui.style", "fill-color: black;");
		return node;
	}
	
}
