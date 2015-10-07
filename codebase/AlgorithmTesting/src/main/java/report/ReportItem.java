package main.java.report;

import java.util.ArrayList;
import java.util.List;

public class ReportItem {

	private long sizeOfGraph;
	private long noOfGraphs;
	private List<ReportTestItem> testList;
	
	public ReportItem() {
		super();
		testList = new ArrayList<ReportTestItem>();
	}

	public long getSizeOfGraph() {
		return sizeOfGraph;
	}
	
	public void setSizeOfGraph(long sizeOfGraph) {
		this.sizeOfGraph = sizeOfGraph;
	}
	
	public long getNoOfGraphs() {
		return noOfGraphs;
	}
	
	public void setNoOfGraphs(long noOfGraphs) {
		this.noOfGraphs = noOfGraphs;
	}
	
	public List<ReportTestItem> getTestList() {
		return testList;
	}
	
	public void setTestList(List<ReportTestItem> testList) {
		this.testList = testList;
	}
}
