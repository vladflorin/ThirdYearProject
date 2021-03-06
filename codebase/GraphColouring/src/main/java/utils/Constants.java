package main.java.utils;

import javax.swing.text.html.CSS;

import sun.org.mozilla.javascript.internal.Node;

public class Constants {

	// Logo
	public final static int LOGO_WAIT_TIME = 5000;
	public final static String LOGO_PATH_M = "/images/logo-medium.png";
	public final static String LOGO_PATH_S = "/images/logo-small.png";
	
	public final static String CAMERA = "/images/camera.png";
	public final static String INFO_ICON_PATH = "/images/info2.png";

	
	public final static String LEFT_ICON_PATH = "/images/left_arrow.png";

	public final static String RIGHT_ICON_PATH = "/images/right_arrow.png";
	public final static String HOME_ICON_PATH = "/images/home.png";

	public final static String CHART_ICON_PATH = "/images/chart.png";
	
	public final static String REPORT_SPINNER = "/images/spinner.gif";
	public final static String REPORT_SPINNER2 = "/images/spinner2.gif";
	
	public final static String DOCUMENT_ICON_PATH = "/images/document.png";
	
	public final static String RS_PATH = "/src/algorithms/rs.txt";
	public final static String LF_PATH = "/src/algorithms/lf.txt";
	public final static String SL_PATH = "/src/algorithms/sl.txt";
	public final static String CS_PATH = "/src/algorithms/cs.txt";
	public final static String SLF_PATH = "/src/algorithms/slf.txt";

	public final static String GREEDY = "Greedy";
	public final static String RANDOM_SEQ = "Random Sequential";
	public final static String LARGEST_FIRST = "Largest First";
	public final static String SMALLEST_LAST = "Smallest Last";
	public final static String CONNECTED_SEQ = "Connected Sequential";
	public final static String SATURATION_LF = "Saturation Largest First";

	public final static String[] ALGORITHMS = {GREEDY, RANDOM_SEQ, LARGEST_FIRST, SMALLEST_LAST, CONNECTED_SEQ, SATURATION_LF};
	
	public final static String[] ALGORITHMS_TUTORIAL = {GREEDY, RANDOM_SEQ, LARGEST_FIRST, SMALLEST_LAST, CONNECTED_SEQ};
	
	public final static String[] COLOURS =  {"Blue", "Red", "Green", "Orange", "Pink",
		"Yellow", "Beige", "Bisque", "BlueViolet", "CadetBlue", 
		"Chartreuse", "CornflowerBlue", "Cornsilk", "Crimson", "Cyan", "DarkBlue", "DarkCyan", 
		"DarkGoldenRod", "DarkGray", "DarkGreen", "DarkKhaki", "DarkMagenta", "DarkOliveGreen", 
		"Darkorange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray", 
		"DarkTurquoise", "DarkViolet", "DeepPink", "DeepSkyBlue", "DimGray", "DodgerBlue", 
		"FireBrick", "FloralWhite", "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold", "GoldenRod", "Gray", 
		"GreenYellow", "HoneyDew", "HotPink", "IndianRed", "Indigo", "Ivory", "Khaki", "Lavender", 
		"LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue", "LightCoral", "LightCyan", "LightGoldenRodYellow", 
		"LightGray", "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen", "LightSkyBlue", 
		"LightSlateGray", "LightSteelBlue", "LightYellow", "Lime", "LimeGreen", "Linen", "Magenta", 
		"Maroon", "MediumAquaMarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen", "MediumSlateBlue", 
		"MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream", "MistyRose", "Moccasin", 
		"NavajoWhite", "OldLace", "OliveDrab", "OrangeRed", "Orchid", "PaleGoldenRod", 
		"PaleGreen", "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru", "Plum", "PowderBlue", 
		"Purple", "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon", "SandyBrown", "SeaGreen", "SeaShell", "Sienna", 
		"Silver", "SkyBlue", "SlateBlue", "SlateGray", "Snow", "SpringGreen", "SteelBlue", "Tan", "Teal", 
		"Thistle", "Tomato", "Turquoise", "Wheat", "White", "WhiteSmoke", "YellowGreen"};
	
	public final static String RS_TOOLTIP = "<html>The vertices of the graph are <br> randomly ordered for colouring.</html>";
	public final static String LF_TOOLTIP = "<html>The vertices of high degree <br> are prioritised for colouring.</html>";
	public final static String GREEDY_TOOLTIP = "<html>The vertices of the graph are <br> ordered for colouring <br> based on their indexes.</html>";
	public final static String SL_TOOLTIP = "<html>Vertices with few neighbours <br> ought to be coloured as <br> late as possible.</html>";
	public final static String CS_TOOLTIP = "<html>Vertices adjacent to those already <br> coloured should be regarded as <br> candidates for colouring.</html>";

}
