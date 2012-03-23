package com.fbinsight.client.events;


@SuppressWarnings("rawtypes")
public class ChartsEvent extends com.fbinsight.client.widgets.Event {
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	private int chartId;
	
	public ChartsEvent(int chartId) {
		super(TYPE);
		this.chartId = chartId;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

}
