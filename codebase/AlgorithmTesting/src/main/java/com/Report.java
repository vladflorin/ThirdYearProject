package main.java.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class Report {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	private static String filePath = "/Users/vladflorin/Eclipse/Documents/reports/GraphColouring Report " + dateFormat.format(new Date())+ ".pdf";
	
	public Report() {
		super();
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
	
}
