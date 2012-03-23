package com.fbinsight.shared.services;

import com.fbinsight.shared.Statistics;
import com.fbinsight.shared.config.Configuration;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>SemsegService</code>.
 */
public interface InsightServiceAsync {
	
	void getConfig(AsyncCallback<Configuration> callback);

	void facebook(String token,AsyncCallback<Void> callback);

	void getLikesStatistics(String token, String query, int i, AsyncCallback<Statistics> callback);

	void getAgeStatistics(String token, String query, int num,
			AsyncCallback<Statistics> asyncCallback);

	void getLocationStatistics(String token, String query, int num,
			AsyncCallback<Statistics> asyncCallback);
	
}
