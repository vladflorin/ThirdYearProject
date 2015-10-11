package main.java.report;

public class TableItem {

	private String algorithm;
	private float avg;
	private long min;
	private long max;
	private float stdDev;
	
	public TableItem(String algorithm, float avg, long min, long max, float stdDev) {
		super();
		this.algorithm = algorithm;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.stdDev = stdDev;
	}

	public float getStdDev() {
		return stdDev;
	}

	public void setStdDev(float stdDev) {
		this.stdDev = stdDev;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}
	
}
