package com.fbinsight.client.charts;

import java.util.HashMap;
import java.util.Map;

import org.moxieapps.gwt.highcharts.client.AxisTitle;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.BarPlotOptions;

import com.fbinsight.client.events.FilterEvent;
import com.fbinsight.client.gin.Injector;
import com.fbinsight.client.widgets.Event;
import com.fbinsight.client.widgets.ProfilePanel;
import com.fbinsight.shared.Item;
import com.fbinsight.shared.Statistics;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class LikesChart extends BaseChart {

	private FlowPanel chartPanel;
	
	private String query="";
	private int num = 20;

	private LikesDonutChart likesDonut;
	
	public LikesChart() {
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

	private void createNewChart(HashMap<String, Number[]> result) {
		createChart();
		
		chart.getYAxis()
				.setAxisTitle(
						new AxisTitle().setText("Number of likes")
								.setAlign(AxisTitle.Align.HIGH))
				.setLineWidth(1);
		chart.getXAxis().setCategories(new String[]{"friends"});
		for (Map.Entry<String, Number[]> e : result.entrySet()) {
			chart.addSeries(chart.createSeries().setName(e.getKey())
					.setPoints(e.getValue()));
		}
		chartPanel.clear();
		chartPanel.add(chart);
	}

	private void createChart() {
		chart = new Chart()
				.setWidth(800)
				.setHeight(400)
				.setType(Series.Type.BAR)
				.setChartTitleText("Likes by type")
				.setBarPlotOptions(
						new BarPlotOptions().setDataLabels(new DataLabels()
								.setEnabled(true)))
				.setLegend(
						new Legend().setLayout(Legend.Layout.VERTICAL)
								.setAlign(Legend.Align.RIGHT)
								.setVerticalAlign(Legend.VerticalAlign.TOP)
								.setX(0).setY(100).setFloating(false)
								.setBorderWidth(1)
								.setBackgroundColor("#FFFFFF").setShadow(true))
				.setCredits(new Credits().setEnabled(false))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return toolTipData.getSeriesName() + ": "
								+ toolTipData.getYAsLong() + " likes";
					}
				}));

		chart.setBackgroundColor("transparent");
	}

//		loading();
	public void onShow() {
//		HashMap<String, Number[]> map = new HashMap<String, Number[]>();
//		map.put("book", new Number[]{10});
//		map.put("movie",  new Number[]{20});
//		map.put("tv show",  new Number[]{30});
//		map.put("music",  new Number[]{40});
		if(Injector.INSTANCE.getApplication().getToken() != null){
			loading();
			chartPanel.clear();
			Injector.INSTANCE.getService().getLikesStatistics(Injector.INSTANCE.getApplication().getToken(), query, num, new AsyncCallback<Statistics>() {
				
				public void onSuccess(Statistics result) {
					GWT.log(":: result="+result);
					HashMap<String, Number[]> map = new HashMap<String, Number[]>();
					if(result.getItems() == null){
						chartPanel.add(new Label("No data found"));
					}else{
						for (Item item : result.getItems()) {
							map.put(item.getType().getFbLabel(), new Number[]{item.getTotal()});
						}
						createNewChart(map);
					}
					likesDonut.createNewChart(result.getItems());
					stopLoading();
				}
				
				public void onFailure(Throwable caught) {
					stopLoading();
				}
			});
		}
//		HashMap<String, Number[]> map = new HashMap<String, Number[]>();
//		map.put("book", new Number[]{10});
//		map.put("movie",  new Number[]{20});
//		map.put("tv show",  new Number[]{30});
//		map.put("music",  new Number[]{40});
//		createNewChart(map);

	}

	public void setRelatedChart(LikesDonutChart likesDonutChart) {
		this.likesDonut = likesDonutChart;
	}

}
