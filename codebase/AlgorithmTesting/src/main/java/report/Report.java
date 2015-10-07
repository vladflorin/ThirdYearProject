package main.java.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class Report {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss"); 
	private static String filePath = "/Users/vladflorin/Eclipse/Documents/reports/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";

	private List<ReportItem> reportItemList;
	
	public Report() {
		super();
		reportItemList = new ArrayList<ReportItem>();
	}

	public static void main(String[] args) throws FileNotFoundException, DRException {
		build();
	}
	
	private static void build() throws FileNotFoundException, DRException {
		
		// Add Title
		TextFieldBuilder<String> title = DynamicReports.cmp.text("Graph Colouring Report");
		
		try {
			report()
				.title(title)
				.toPdf(new FileOutputStream(new File(filePath)));
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReportItem> getReportItemList() {
		return reportItemList;
	}

	public void setReportItemList(List<ReportItem> reportItemList) {
		this.reportItemList = reportItemList;
	}

}
