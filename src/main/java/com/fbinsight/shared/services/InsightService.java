package com.fbinsight.shared.services;

import com.fbinsight.shared.Statistics;
import com.fbinsight.shared.config.Configuration;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("gwt.rpc")
public interface InsightService extends RemoteService {
	
	Configuration getConfig();
	
	void facebook(String token);
	
	Statistics getLikesStatistics(String token, String query,int friends);
	
	Statistics getAgeStatistics(String token, String query,int friends);
	
	Statistics getLocationStatistics(String token, String query,int friends);


}
