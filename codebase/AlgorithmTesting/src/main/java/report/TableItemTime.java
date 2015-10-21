package main.java.report;

public class TableItemTime {

	private String algorithm;
	private float avg;
	private float min;
	private float max;
	private float stdDev;
	
	public TableItemTime(String algorithm, float avg, float min, float max, float stdDev) {
		super();
		this.algorithm = algorithm;
		this.avg = (float) (avg / 1000000.0);
		this.min = (float) (min / 1000000.0);;
		this.max = (float) (max / 1000000.0);;
		this.stdDev = (float) (stdDev / 1000000.0);;
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

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getStdDev() {
		return stdDev;
	}

	public void setStdDev(float stdDev) {
		this.stdDev = stdDev;
	}

}
