package com.fbinsight.client.gin;

import com.fbinsight.client.Application;
import com.fbinsight.shared.services.InsightServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({SystemModule.class, ClientModule.class})
public interface Injector extends Ginjector {
	
	static final Injector INSTANCE = GWT.create(Injector.class);
	
	Application getApplication();
	EventBus getEventBus();

	InsightServiceAsync getService();
}

