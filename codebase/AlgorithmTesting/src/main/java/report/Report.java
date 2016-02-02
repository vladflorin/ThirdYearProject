package main.java.report;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

@SuppressWarnings("deprecation")
public class Report {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss"); 
	private static String filePath;
	
	private static TextFieldBuilder<String> newLine = DynamicReports.cmp.text("\n");


	private List<ReportItem> reportItemList;
		
	private static final int diagramMax = 20;
	
	public Report() {
		super();
		reportItemList = new ArrayList<ReportItem>();
		filePath = "/Users/vladflorin/Eclipse/Documents/reports/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";
	}
	
	public Report(String location) {
		super();
		reportItemList = new ArrayList<ReportItem>();
		filePath = location + "/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";
	}
	
	@SuppressWarnings("unused")
	public void build() throws DRException, IOException {
		JasperConcatenatedReportBuilder report = concatenatedReport();	

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
		
		// Generate body
		int count = 0;
		for (ReportItem reportItem : reportItemList) {
			
			JasperReportBuilder currentReport = report();
			
			// Add title and logo
			currentReport.title(DynamicReports.cmp.horizontalFlowList(title, img).newRow().newRow().add(filler));
			
			TextFieldBuilder<String> testNumber = DynamicReports.cmp.text("\nTest " + (++count)).setStyle(headingLeft);
			TextFieldBuilder<String> sizeOfGraph = DynamicReports.cmp.text("Number of graphs: " + reportItem.getNoOfGraphs() + " (number of nodes: " + reportItem.getSizeOfGraph() + ")\n").setStyle(textLeft);
			
			//currentReport.addTitle(testNumber);
			currentReport.addTitle(newLine);
			currentReport.addTitle(sizeOfGraph);

			MultiPageListBuilder builder = cmp.multiPageList();
		    builder.setSplitType(SplitType.IMMEDIATE);
		     
		    builder.add(cmp.subreport(generateKTable(reportItem)));
		    builder.add(cmp.subreport(generateTimeTable(reportItem)));
		    
		    currentReport.title(builder);
		
			report.concatenate(currentReport);
		}
		
		report.toPdf(new FileOutputStream(new File(filePath)));
	}
	
	private static JasperReportBuilder generateKTable(ReportItem reportItem) {
		JasperReportBuilder mainReport = report();
		
		// StyleBuilders
		StyleBuilder underlineBoldStyle = DynamicReports.stl.style().bold().underline();
		StyleBuilder textTitle = DynamicReports.stl.style(underlineBoldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder boldStyle = stl.style().bold(); 
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

		JasperReportBuilder report = DynamicReports.report();

		TextFieldBuilder<String> chromaticNumber = DynamicReports.cmp.text("Chromatic Number\n").setStyle(textTitle);
		report.addTitle(chromaticNumber);
		
		TextColumnBuilder<String> algorithm = col.column("Algorithm", "algorithm", type.stringType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> avg = col.column("Average", "avg", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> min = col.column("Minimum", "min", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Long> max = col.column("Maximum", "max", type.longType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> stdDev = col.column("Standard Deviation", "stdDev", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));

		report.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
		report.columns(algorithm, avg, min, max, stdDev);
		report.setDataSource(createDataSourceK(reportItem.getTestList(), reportItem.getSizeOfGraph()));
		
		JasperReportBuilder diagrams = DynamicReports.report();
		VerticalListBuilder builder = cmp.verticalList();
		 
		for (int index = 0; index <= (((int)reportItem.getNoOfGraphs() - 1) / diagramMax); index++) {
			
			JasperReportBuilder diagram = report();
			// LIST : 0 - greedy, 1 - rs, 2- lf, 3 - sl, 4 - cs, 5 - slf
			
			TextColumnBuilder<String> graph = col.column("Graph", "graph", type.stringType());
			// TODO: add new algorithms column + series + dataSource method
			TextColumnBuilder<Integer> greedyK = col.column("Greedy", "greedyK", type.integerType());
			TextColumnBuilder<Integer> rsK = col.column("RS", "rsK", type.integerType());
			TextColumnBuilder<Integer> lfK = col.column("LF", "lfK", type.integerType());
			TextColumnBuilder<Integer> slK = col.column("SL", "slK", type.integerType());
			TextColumnBuilder<Integer> csK = col.column("CS", "csK", type.integerType());
			TextColumnBuilder<Integer> slfK = col.column("SLF", "slfK", type.integerType());
	
			diagram.summary(
				cht.barChart()
					.customizers(new ChartCustomizer())
					.setCategory(graph)
					.series(
						cht.serie(greedyK), cht.serie(rsK), cht.serie(lfK), cht.serie(slK), cht.serie(csK), cht.serie(slfK))
					.setCategoryAxisFormat(cht.axisFormat().setLabel("Graph"))
				.setDataSource(createKDiagramData(reportItem, index, (index + 1) * diagramMax)));
		
			builder.add(cmp.subreport(diagram));
		}
		
		diagrams.title(builder);

		mainReport.title(cmp.verticalList(cmp.subreport(report), cmp.subreport(diagrams)));
		mainReport.addTitle(newLine);
		
		return mainReport;
	}
	
	private static JasperReportBuilder generateTimeTable(ReportItem reportItem) {
		JasperReportBuilder mainReport = report();
		
		// StyleBuilders
		StyleBuilder underlineBoldStyle = DynamicReports.stl.style().bold().underline();
		StyleBuilder textTitle = DynamicReports.stl.style(underlineBoldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE);;
		StyleBuilder boldStyle = stl.style().bold(); 
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
		
		JasperReportBuilder report = DynamicReports.report();

		TextFieldBuilder<String> chromaticNumber = DynamicReports.cmp.text("Execution Time (milliseconds)\n").setStyle(textTitle);
		report.addTitle(chromaticNumber);
		
		TextColumnBuilder<String> algorithm = col.column("Algorithm", "algorithm", type.stringType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> avg = col.column("Average", "avg", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> min = col.column("Minimum", "min", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> max = col.column("Maximum", "max", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));
		TextColumnBuilder<Float> stdDev = col.column("Standard Deviation", "stdDev", type.floatType()).setStyle(DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER));

		report.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
		report.columns(algorithm, avg, min, max, stdDev);
		report.setDataSource(createDataSourceTime(reportItem.getTestList(), reportItem.getSizeOfGraph()));

		JasperReportBuilder diagrams = DynamicReports.report();
		VerticalListBuilder builder = cmp.verticalList();
		 
		for (int index = 0; index <= (((int)reportItem.getNoOfGraphs() - 1) / diagramMax); index++) {
			JasperReportBuilder diagram = DynamicReports.report();
			
			// LIST : 0 - greedy, 1 - rs, 2 - lf, 3 - sl, 4 - cs, 5 - slf
			TextColumnBuilder<String> graph = col.column("Graph", "graph", type.stringType());
		
			// TODO: add new algorithms column + series + dataSource method
			TextColumnBuilder<Float> greedyTime = col.column("Greedy", "greedyTime", type.floatType());
			TextColumnBuilder<Float> rsTime = col.column("RS", "rsTime", type.floatType());
			TextColumnBuilder<Float> lfTime = col.column("LF", "lfTime", type.floatType());
			TextColumnBuilder<Float> slTime = col.column("SL", "slTime", type.floatType());
			TextColumnBuilder<Float> csTime = col.column("CS", "csTime", type.floatType());
			TextColumnBuilder<Float> slfTime = col.column("SLF", "slfTime", type.floatType());

			diagram.summary(
				cht.barChart()
						.setCategory(graph)
						.series(
							cht.serie(greedyTime), cht.serie(rsTime), cht.serie(lfTime), cht.serie(slTime), cht.serie(csTime), cht.serie(slfTime))
						.setCategoryAxisFormat(cht.axisFormat().setLabel("Graph"))
					.setDataSource(createTimeDiagramData(reportItem, index, (index + 1) * diagramMax)));
							
			builder.add(cmp.subreport(diagram));
		}
		
		diagrams.title(builder);

		mainReport.title(cmp.verticalList(cmp.subreport(report), cmp.subreport(diagrams)));
		
		return mainReport;	
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
			
			float stdDev = 0;
			float variance = 0;
			for (int index = 0; index < kList.size(); index++) {
				variance += ((kList.get(index) - avg) * (kList.get(index) - avg));
			}
			
			variance = (float) variance / kList.size();
			stdDev = (float) Math.sqrt(variance);
			
			list.add(new TableItem(alg, avg, min, max, stdDev));
		}

		return list;
	}
	
	private static List<TableItemTime> createDataSourceTime(List<ReportTestItem> testList, long graphSize) {
		List<TableItemTime> list = new ArrayList<TableItemTime>();
		
		for (ReportTestItem reportTestItem : testList) {
			String alg = reportTestItem.getAlgorithmName();
			float min = Float.MAX_VALUE;
			float max = -1;
			float sum = 0;
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
			
			float stdDev = 0;
			float variance = 0;
			for (int index = 0; index < timeList.size(); index++) {
				variance += ((timeList.get(index) - avg) * (timeList.get(index) - avg));
			}
			
			variance = (float) variance / timeList.size();
			stdDev = (float) Math.sqrt(variance);
			
			list.add(new TableItemTime(alg, avg, min, max, stdDev));
		}

		return list;
	}
	
	private static JRDataSource createKDiagramData(ReportItem reportItem, int currentDiagramIndex, int endIndex) {
		DRDataSource dataSource = new DRDataSource("graph", "greedyK", "rsK", "lfK", "slK", "csK", "slfK");
		List<ReportTestItem> list = reportItem.getTestList();
		
		if (endIndex > reportItem.getNoOfGraphs()) {
			endIndex = (int) reportItem.getNoOfGraphs();
		}
		
		if (list.get(0) != null) {
			for (int index = currentDiagramIndex * diagramMax; index < endIndex; index++) {
				dataSource.add(index + "", list.get(0).getK().get(index), list.get(1).getK().get(index), list.get(2).getK().get(index), list.get(3).getK().get(index), list.get(4).getK().get(index), list.get(5).getK().get(index));
			}
		}
		return dataSource;
	}
	
	private static JRDataSource createTimeDiagramData(ReportItem reportItem, int currentDiagramIndex, int endIndex) {
		DRDataSource dataSource = new DRDataSource("graph", "greedyTime", "rsTime", "lfTime", "slTime", "csTime", "slfTime");
		List<ReportTestItem> list = reportItem.getTestList();
		
		if (endIndex > reportItem.getNoOfGraphs()) {
			endIndex = (int) reportItem.getNoOfGraphs();
		}
		
		if (list.get(0) != null) {
			for (int index = currentDiagramIndex * diagramMax; index < endIndex; index++) {
				float time0 = (float) (list.get(0).getTime().get(index) / 1000000.0);
				float time1 = (float) (list.get(1).getTime().get(index) / 1000000.0);
				float time2 = (float) (list.get(2).getTime().get(index) / 1000000.0);
				float time3 = (float) (list.get(3).getTime().get(index) / 1000000.0);
				float time4 = (float) (list.get(4).getTime().get(index) / 1000000.0);
				float time5 = (float) (list.get(5).getTime().get(index) / 1000000.0);

				dataSource.add(index + "", time0, time1, time2, time3, time4, time5);
			}
		}
		return dataSource;
	}
	
	public List<ReportItem> getReportItemList() {
		return reportItemList;
	}

	public void setReportItemList(List<ReportItem> reportItemList) {
		this.reportItemList = reportItemList;
	}

}
