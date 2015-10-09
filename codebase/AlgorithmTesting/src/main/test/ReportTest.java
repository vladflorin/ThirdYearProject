package main.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Test;

import main.java.report.Report;
import main.java.report.ReportItem;
import main.java.report.ReportTestItem;

public class ReportTest {

	private final static long noOfGraphs = 10;
	private final static long sizeOfGraph = 50;
	
	@Test
	public void testBuildReport() throws DRException, IOException {
		Report report = new Report();
	
		report.getReportItemList().add(generateReportItem());
		
		report.build();
		
		/*for (ReportItem reportItem : report.getReportItemList()) {
			if (reportItem != null) {
				System.out.println("Size of graph: " + reportItem.getSizeOfGraph());
				System.out.println("Number of graphs: " + reportItem.getNoOfGraphs());
				System.out.println("Number of algorithms: " + reportItem.getTestList().size());
				List<ReportTestItem> list = reportItem.getTestList();
				for (int index = 0; index < list.size(); index++) {
					System.out.println(list.get(index).getAlgorithmName());
					System.out.println("Time: " + list.get(index).getTime());
					System.out.println("K   : " + list.get(index).getK());
					System.out.println();
				}
			}
		} */
		
		assertNotNull(report);
	}
	
	private ReportItem generateReportItem() {
		ReportItem reportItem = new ReportItem();
		
		reportItem.setNoOfGraphs(noOfGraphs);
		reportItem.setSizeOfGraph(sizeOfGraph);
		
		reportItem.getTestList().add(generateReportTestItem("Greedy Algorithm"));
		reportItem.getTestList().add(generateReportTestItem("RS Algorithm"));
		reportItem.getTestList().add(generateReportTestItem("LF Algorithm"));

		return reportItem;
	}
	
	private ReportTestItem generateReportTestItem(String algorithmName) {
		ReportTestItem reportTestItem = new ReportTestItem();
		
		reportTestItem.setAlgorithmName(algorithmName);
		
		for (int index = 0; index < noOfGraphs; index++) {
			reportTestItem.getK().add((int)Math.random() * 5);
			reportTestItem.getTime().add((long) (Math.random() * 100000));
		}
		
		return reportTestItem;
	}
	
}
