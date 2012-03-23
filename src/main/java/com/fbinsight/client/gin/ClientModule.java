package com.fbinsight.client.gin;

import com.fbinsight.client.Application;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Application.class).in(Singleton.class);
	}

}
