package main.java.report;

import java.util.ArrayList;

public class ReportTestItem {

	private String algorithmName;
	private ArrayList<Long> time;
	private ArrayList<Integer> k;

	public ReportTestItem(long size) {
		super();
		time = new ArrayList<Long>();
		k = new ArrayList<Integer>();
	}

	public String getAlgorithmName() {
		return algorithmName;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public ArrayList<Long> getTime() {
		return time;
	}

	public void setTime(ArrayList<Long> time) {
		this.time = time;
	}

	public ArrayList<Integer> getK() {
		return k;
	}

	public void setK(ArrayList<Integer> k) {
		this.k = k;
	}	

}
