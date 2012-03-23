package com.fbinsight.client.charts;

import org.moxieapps.gwt.highcharts.client.Chart;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *	Chart can be empty if no data have been received for display
 */
public abstract class BaseChart extends ResizeComposite{
	
	private HorizontalPanel loader = new HorizontalPanel();
    private DockLayoutPanel dlp = new DockLayoutPanel(Style.Unit.PX);
    protected Chart chart;
	
	public BaseChart() {        
		initWidget(dlp);
		Label text = new Label("Loading chart data... ");
		Image image = new Image("images/loading.gif");
//		image.setWidth("112px");
//		image.setHeight("32px");
		loader.add(text);
		loader.add(image);
		loader.setCellVerticalAlignment(text, HasVerticalAlignment.ALIGN_MIDDLE);
		stopLoading();
//		image.getElement().getStyle().setProperty("background", "url('images/loading.gif') no-repeat");
	}
	
	public void setHeader(Widget w){
        dlp.addNorth(w, 30);
	}
	
	public DockLayoutPanel getContainer(){
        return dlp;
	}
	
	public void setSidebar(Widget w){
		dlp.addWest(new ScrollPanel(w), 150);
	}
	
	public Widget getLoaderWidget(){
		return loader;
	}
	
    protected void loading(){
        loader.setVisible(true);
    }

    protected void stopLoading(){
        loader.setVisible(false);
    }
    
    public abstract void onShow();
    
    
}
