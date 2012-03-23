package com.fbinsight.client.widgets;



@SuppressWarnings("rawtypes")
public class LoginEvent extends Event {
	
	public static final Type<Handler> TYPE = new Type<Handler>();

	private SocialClient client;
	
	public LoginEvent(SocialClient client) {
		super(TYPE);
		this.client = client;
	}

	public SocialClient getClient() {
		return client;
	}

	public void setClient(SocialClient client) {
		this.client = client;
	}

}
