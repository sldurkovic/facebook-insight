package com.fbinsight.client.charts;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.moxieapps.gwt.highcharts.client.AxisTitle;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import com.fbinsight.client.events.FilterEvent;
import com.fbinsight.client.gin.Injector;
import com.fbinsight.client.widgets.Event;
import com.fbinsight.client.widgets.ProfilePanel;
import com.fbinsight.shared.FType;
import com.fbinsight.shared.Item;
import com.fbinsight.shared.Statistics;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.view.client.SelectionChangeEvent;

/**
 * @author Slobodan
 */
public class LocationDonutChart extends BaseChart {

	private FlowPanel chartPanel;

	private String query="";
	private int num = 20;
	
	public LocationDonutChart() {
		chartPanel = new FlowPanel();
		getContainer().addNorth(new ProfilePanel(), 50);
		getContainer().addNorth(getLoaderWidget(),50);
		getContainer().add(chartPanel);
		Injector.INSTANCE.getEventBus().addHandler(FilterEvent.TYPE,
				new Event.Handler<FilterEvent>() {
					public void on(FilterEvent e) {
						query = e.getFilter();
						num = e.getNum();
						onShow();
					}
				});
	}
	
	private void createNewChart(ArrayList<Item> result) {
		chartPanel.clear();
		createDonutChart();
		setChartData(result);
		chartPanel.add(chart);
	}

	public Chart createDonutChart() {
		chart = new Chart()
				.setType(Series.Type.PIE)
				.setChartTitleText("Likes by user location")
				.setPlotBackgroundColor((String) null)
				.setPlotBorderWidth(null)
				.setPlotShadow(false)
				.setPiePlotOptions(
						new PiePlotOptions()
								.setAllowPointSelect(true)
								.setCursor(PlotOptions.Cursor.POINTER)
								.setPieDataLabels(
										new PieDataLabels()
												.setConnectorColor("#000000")
												.setEnabled(true)
												.setColor("#000000")
												.setFormatter(
														new DataLabelsFormatter() {
															public String format(
																	DataLabelsData dataLabelsData) {
																return "<b>"
																		+ dataLabelsData
																				.getPointName()
																		+ "</b>: "
																		+ dataLabelsData
																				.getYAsDouble()
																		+ " %";
															}
														})))
				.setLegend(
						new Legend().setLayout(Legend.Layout.VERTICAL)
								.setAlign(Legend.Align.RIGHT)
								.setVerticalAlign(Legend.VerticalAlign.TOP)
								.setX(-100).setY(100).setFloating(true)
								.setBorderWidth(1)
								.setBackgroundColor("#FFFFFF").setShadow(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: "
								+ toolTipData.getYAsDouble() + " %";
					}
				})).setWidth(600).setHeight(300);
		chart.setBackgroundColor("transparent");
		return chart;
	}

	 private void setChartData(ArrayList<Item> result) {
	        Point[] points = new Point[result.size()];
	        long total = 0;
	        
	        for (Item item : result) {
	        	total += item.getTotal();
			}
	        
	        chart.removeAllSeries();
	        
	        if(total == 0){
	        	return;
	        }
	        

	        int counter = -1;
	        
	        for (Item item : result) {
        		counter++;
        		points[counter] = new Point(item.getName(), (item.getTotal() * 100) / total);
			}
//	        chart.removeAllSeries();
	        chart.addSeries(chart.createSeries()
	                .setName("Age share")
	                .setPoints(points));
	    }
	
	@Override
	public void onShow() {
		if(Injector.INSTANCE.getApplication().getToken() != null){
			loading();
			chartPanel.clear();
			Injector.INSTANCE.getService().getLocationStatistics(Injector.INSTANCE.getApplication().getToken(), query, num, new AsyncCallback<Statistics>() {
				
				public void onSuccess(Statistics result) {
					GWT.log(":: result="+result);
					createNewChart(result.getItems());
					stopLoading();
				}
				
				public void onFailure(Throwable caught) {
					stopLoading();
				}
			});
		}
	}

}
