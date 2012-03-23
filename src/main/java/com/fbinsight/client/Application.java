package com.fbinsight.client;

import com.fbinsight.client.charts.LikesChart;
import com.fbinsight.client.gin.Injector;
import com.fbinsight.client.widgets.Event;
import com.fbinsight.client.widgets.LoginEvent;
import com.fbinsight.shared.config.Configuration;
import com.fbinsight.shared.services.InsightServiceAsync;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;


public class Application {
	private Configuration config;
	private InsightServiceAsync service;
	private String token;
	
	@Inject
	public Application(InsightServiceAsync service) {
		this.service = service;
	}
	
	public void run(){
		/* Hide the loading... */
		Element loading = Document.get().getElementById("loader");
		loading.getParentElement().removeChild(loading);
		
		Injector.INSTANCE.getEventBus().addHandler(LoginEvent.TYPE, new Event.Handler<LoginEvent>() {
			public void on(LoginEvent e) {
				token = e.getClient().getToken();
			}
		});

		RootLayoutPanel.get().add(new ApplicationShell());
		
	}
	
	
	public void getConfig(final AsyncCallback<Configuration> callback) {
		if(config == null) {
			Injector.INSTANCE.getService().getConfig(new AsyncCallback<Configuration>() {
				public void onSuccess(Configuration result) {
					config = result;
					callback.onSuccess(config);
				}
				public void onFailure(Throwable caught) {
				}
			});
		} else {
			callback.onSuccess(config);
		}
	}
	
	public String getToken(){
		return token;
	}
	
}
