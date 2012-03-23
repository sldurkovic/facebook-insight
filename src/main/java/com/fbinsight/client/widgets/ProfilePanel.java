package com.fbinsight.client.widgets;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.fbinsight.client.gin.Injector;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ProfilePanel extends Container<HorizontalPanel>{
	
	private FBButton button;
	private Label username;
	private String token;
	
	public ProfilePanel() {
		super(new HorizontalPanel(),true);
	}
	
	@Override
	protected void render() {
		button = new FBButton();
		username = new Label("");
		addWidget(username);
		username.setVisible(false);
		addWidget(button);
		
		Injector.INSTANCE.getEventBus().addHandler(LoginEvent.TYPE, new Event.Handler<LoginEvent>() {
			public void on(LoginEvent e) {
				token = e.getClient().getToken();
				username.setText(e.getClient().getName());
				button.setVisible(false);
				username.setVisible(true);
			}
		});
	}
	
	
	
}
