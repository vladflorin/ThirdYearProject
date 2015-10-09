package main.java.report;

import java.awt.BorderLayout;
import java.awt.Color;
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
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class Report {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss"); 
	private static String filePath = "/Users/vladflorin/Eclipse/Documents/reports/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";

	private static List<ReportItem> reportItemList;
	
	private static TextFieldBuilder<String> newLine = DynamicReports.cmp.text("\n");

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
			TextFieldBuilder<String> testNumber = DynamicReports.cmp.text("\nTest " + (++count)).setStyle(headingLeft);
			TextFieldBuilder<String> sizeOfGraph = DynamicReports.cmp.text("Graph size: " + reportItem.getSizeOfGraph() + "; Number of graphs: " + reportItem.getNoOfGraphs() + "\n").setStyle(textLeft);
			
			report.addTitle(testNumber);
			report.addTitle(sizeOfGraph);

			generateKTable(report, reportItem);
			generateTimeTable(report, reportItem);
			
		}
		
		report.toPdf(new FileOutputStream(new File(filePath)));
	}
	
	private static void generateKTable(JasperReportBuilder mainReport, ReportItem reportItem) {
		JasperReportBuilder report = DynamicReports.report();
		
		// StyleBuilders
		StyleBuilder underlineBoldStyle = DynamicReports.stl.style().bold().underline();
		StyleBuilder textTitle = DynamicReports.stl.style(underlineBoldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder boldStyle = stl.style().bold(); 
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

		TextFieldBuilder<String> chromaticNumber = DynamicReports.cmp.text("Chromatic Number\n").setStyle(textTitle);
		report.addTitle(chromaticNumber);
		
		TextColumnBuilder<String> algorithm = col.column("Algorithm", "algorithm", type.stringType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> avg = col.column("Average", "avg", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> min = col.column("Minimum", "min", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> max = col.column("Maximum", "max", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		
		report.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
		report.columns(algorithm, avg, min, max);
		report.setDataSource(createDataSourceK(reportItem.getTestList(), reportItem.getSizeOfGraph()));
		
		mainReport.title(cmp.horizontalList(cmp.subreport(report)));
		mainReport.addTitle(newLine);
	}
	
	private static void generateTimeTable(JasperReportBuilder mainReport, ReportItem reportItem) {
		JasperReportBuilder report = DynamicReports.report();

		// StyleBuilders
		StyleBuilder underlineBoldStyle = DynamicReports.stl.style().bold().underline();
		StyleBuilder textTitle = DynamicReports.stl.style(underlineBoldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder boldStyle = stl.style().bold(); 
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

		TextFieldBuilder<String> chromaticNumber = DynamicReports.cmp.text("Execution Time (nanoseconds)\n").setStyle(textTitle);
		report.addTitle(chromaticNumber);
		
		TextColumnBuilder<String> algorithm = col.column("Algorithm", "algorithm", type.stringType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> avg = col.column("Average", "avg", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> min = col.column("Minimum", "min", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> max = col.column("Maximum", "max", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		
		report.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
		report.columns(algorithm, avg, min, max);
		report.setDataSource(createDataSourceTime(reportItem.getTestList(), reportItem.getSizeOfGraph()));
				
		mainReport.title(cmp.horizontalList(cmp.subreport(report)));
		mainReport.addTitle(newLine);
	}
	
	private static List<TableItem> createDataSourceK(List<ReportTestItem> testList, long graphSize) {
		List<TableItem> list = new ArrayList<TableItem>();
		
		for (ReportTestItem reportTestItem : testList) {
			String alg = reportTestItem.getAlgorithmName();
			long min = graphSize;
			long max = -1;
			long sum = 0;
			List<Integer> kList = reportTestItem.getK();
			for (int index = 0; index < kList.size(); index++) {
				if (kList.get(index) <= min) {
					min = kList.get(index);
				}
				if (kList.get(index) >= max) {
					max = kList.get(index);
				}
				sum = sum + kList.get(index);
			}
			float avg = (float) sum / kList.size();
			list.add(new TableItem(alg, avg, min, max));
		}

		return list;
	}
	
	private static List<TableItem> createDataSourceTime(List<ReportTestItem> testList, long graphSize) {
		List<TableItem> list = new ArrayList<TableItem>();
		
		for (ReportTestItem reportTestItem : testList) {
			String alg = reportTestItem.getAlgorithmName();
			long min = Long.MAX_VALUE;
			long max = -1;
			long sum = 0;
			List<Long> timeList = reportTestItem.getTime();
			for (int index = 0; index < timeList.size(); index++) {
				if (timeList.get(index) <= min) {
					min = timeList.get(index);
				}
				if (timeList.get(index) >= max) {
					max = timeList.get(index);
				}
				sum = sum + timeList.get(index);
			}
			float avg = (float) sum / timeList.size();
			list.add(new TableItem(alg, avg, min, max));
		}

		return list;
	}
	public List<ReportItem> getReportItemList() {
		return reportItemList;
	}

	public void setReportItemList(List<ReportItem> reportItemList) {
		this.reportItemList = reportItemList;
	}

}
