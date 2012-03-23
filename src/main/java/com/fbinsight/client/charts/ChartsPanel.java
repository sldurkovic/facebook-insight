package com.fbinsight.client.charts;

import com.fbinsight.client.events.ChartsEvent;
import com.fbinsight.client.gin.Injector;
import com.fbinsight.client.images.ImageFactory;
import com.fbinsight.client.widgets.Container;
import com.fbinsight.client.widgets.Event;
import com.fbinsight.client.widgets.LoginEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChartsPanel extends Container<FlowPanel>{
	
	private LikesChart likesChart;
	private LikesDonutChart likesDonutChart;
	

	private AgeChart ageChart;

	private LocationDonutChart locationDonutChart;
	
	private DeckPanel deck;
	private static Image loading = ImageFactory.loading();
	
	public ChartsPanel() {
		super(new FlowPanel(),true);
		Injector.INSTANCE.getEventBus().addHandler(LoginEvent.TYPE, new Event.Handler<LoginEvent>() {
			public void on(LoginEvent e) {
				likesChart.onShow();
				likesDonutChart.onShow();
				ageChart.onShow();
				locationDonutChart.onShow();
			}
		});
		
		Injector.INSTANCE.getEventBus().addHandler(ChartsEvent.TYPE, new Event.Handler<ChartsEvent>() {
			public void on(ChartsEvent e) {		
				deck.showWidget(e.getChartId());
			}
		});
	}
	
	@Override
	protected void render() {
		addStyleName("charts-Panel");
		likesChart = new LikesChart();
		likesChart.setHeight("500px");
		likesDonutChart = new LikesDonutChart();
		likesDonutChart.setHeight("500px");
		
		ageChart = new AgeChart();
		ageChart.setHeight("500px");
		locationDonutChart = new LocationDonutChart();
		locationDonutChart.setHeight("500px");
		likesChart.setRelatedChart(likesDonutChart);

		deck = new DeckPanel();
		deck.add(new ChartStack(likesChart, likesDonutChart));
		deck.add(new ChartStack(ageChart));
		deck.add(new ChartStack(locationDonutChart));
		addWidget(deck);
		deck.showWidget(0);
//		deck.showWidget(0);
//		likesChart.onShow();
//		addWidget(loading);
//		likesChart.onShow();
	}
	
	public class ChartStack extends Container<VerticalPanel>{
		
		public ChartStack(BaseChart... charts) {
			super(new VerticalPanel(),true);
			for (BaseChart baseChart : charts) {
				baseChart.getElement().getStyle().setMarginBottom(20, Unit.PX);
				addWidget(baseChart);
			}
		}
		@Override
		protected void render() {
		}
	}
	
}
