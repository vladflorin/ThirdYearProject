package main.java.report;

import java.io.Serializable;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;

@SuppressWarnings("serial")
public class ChartCustomizer implements DRIChartCustomizer, Serializable{

	
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		ValueAxis axis = chart.getCategoryPlot().getRangeAxis();
		TickUnits tickUnits = new TickUnits();
		tickUnits.add(new NumberTickUnit(2));
		axis.setStandardTickUnits(tickUnits);
	}
}
