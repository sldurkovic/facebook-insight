package com.fbinsight.client;

import com.fbinsight.client.gin.Injector;
import com.fbinsight.shared.config.Configuration;
import com.fbinsight.shared.services.InsightService;
import com.fbinsight.shared.services.InsightServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class FBInsight implements EntryPoint{
	
	
	public void onModuleLoad() {

		Injector.INSTANCE.getApplication().run();
		
	}

}
