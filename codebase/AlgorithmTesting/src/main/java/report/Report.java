package main.java.report;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class Report {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss"); 
	private static String filePath = "/Users/vladflorin/Eclipse/Documents/reports/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";

	private static List<ReportItem> reportItemList;
	
	public Report() {
		super();
		reportItemList = new ArrayList<ReportItem>();
	}
	
	public static void build() throws DRException, IOException {
		
		JasperReportBuilder report = DynamicReports.report();	
		
		// Styles
		StyleBuilder boldStyle = DynamicReports.stl.style().bold();
		StyleBuilder underlineStyle = DynamicReports.stl.style().underline();
		StyleBuilder underlineBoldStyle = DynamicReports.stl.style().bold().underline();
		StyleBuilder boldCentered = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT);
		StyleBuilder titleLeft = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(16).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder headingLeft = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(14).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder subHeadingLeft = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(14).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder textLeft = DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE);;

		// Create title
		TextFieldBuilder<String> title = DynamicReports.cmp.text("Algorithms' Analysis Report").setStyle(titleLeft);
		
		// Create logo
		InputStream stream = new BufferedInputStream(new FileInputStream("/Users/vladflorin/Eclipse/Documents/utils/logo.png"));
		Image image = ImageIO.read(stream);
		ImageBuilder img = DynamicReports.cmp.image(image).setFixedDimension(180, 40).setStyle(DynamicReports.stl.style().setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.LEFT));
		FillerBuilder filler = DynamicReports.cmp.filler().setStyle(DynamicReports.stl.style().setTopBorder(DynamicReports.stl.pen2Point())).setFixedHeight(2);
		
		// Add title and logo
		report.title(DynamicReports.cmp.horizontalFlowList(title, img).newRow().newRow().add(filler));
		
		// Generate body
		int count = 0;
		for (ReportItem reportItem : reportItemList) {
			TextFieldBuilder<String> testNumber = DynamicReports.cmp.text("\nTest " + (count +1)).setStyle(headingLeft);
			TextFieldBuilder<String> sizeOfGraph = DynamicReports.cmp.text("Graph size: " + reportItem.getSizeOfGraph() + "; Number of graphs: " + reportItem.getNoOfGraphs()).setStyle(textLeft);
			
			report.addTitle(testNumber);
			report.addTitle(sizeOfGraph);
		}
		
		report.toPdf(new FileOutputStream(new File(filePath)));
	}
	
	public List<ReportItem> getReportItemList() {
		return reportItemList;
	}

	public void setReportItemList(List<ReportItem> reportItemList) {
		this.reportItemList = reportItemList;
	}

}
