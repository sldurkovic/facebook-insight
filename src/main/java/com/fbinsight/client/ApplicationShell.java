package com.fbinsight.client;

import com.fbinsight.client.events.ChartsEvent;
import com.fbinsight.client.events.FilterEvent;
import com.fbinsight.client.gin.Injector;
import com.fbinsight.client.widgets.Event;
import com.fbinsight.client.widgets.LoginEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ApplicationShell extends Composite {

	private static ApplicationShellUiBinder uiBinder = GWT
			.create(ApplicationShellUiBinder.class);

	interface ApplicationShellUiBinder extends
			UiBinder<Widget, ApplicationShell> {
	}

	@UiField
	VerticalPanel sidebar;

	@UiField
	TextBox filterTextBox;
	
	@UiField
	TextBox numFriends;

	@UiField
	Button button;

	public ApplicationShell() {
		initWidget(uiBinder.createAndBindUi(this));
//		sidebar.add(new SideLink("Profile", new ClickHandler() {
//
//			public void onClick(ClickEvent event) {
//				Injector.INSTANCE.getEventBus().fireEvent(new ChartsEvent(0));
//			}
//		}));
		sidebar.add(new SideLink("Likes chart", new ClickHandler() {

			public void onClick(ClickEvent event) {
				Injector.INSTANCE.getEventBus().fireEvent(new ChartsEvent(0));
			}
		}));
		sidebar.add(new SideLink("By age and gender", new ClickHandler() {

			public void onClick(ClickEvent event) {
				Injector.INSTANCE.getEventBus().fireEvent(new ChartsEvent(1));
			}
		}));
		
		sidebar.add(new SideLink("Likes by location", new ClickHandler() {

			public void onClick(ClickEvent event) {
				Injector.INSTANCE.getEventBus().fireEvent(new ChartsEvent(2));
			}
		}));
		button.getElement().getStyle().setMarginBottom(20, Unit.PX);
		button.getElement().getStyle().setMarginTop(10, Unit.PX);
		filterTextBox.getElement().getStyle().setMarginLeft(30, Unit.PX);
		filterTextBox.getElement().getStyle().setMarginBottom(5, Unit.PX);
		button.getElement().getStyle().setMarginLeft(30, Unit.PX);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		try{
			int num = Integer.parseInt(numFriends.getText());
			Injector.INSTANCE.getEventBus().fireEvent(new FilterEvent(filterTextBox.getText(),num));
		}catch (Exception ex) {
			Window.alert("Invalid number format");
		}
	}

	private class SideLink extends HTML {

		private final MouseOverHandler mouseOverHandler = new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				// ((Widget) event.getSource()).getElement().getStyle()
				// .setColor("black");
				((Widget) event.getSource()).getElement().getStyle()
						.setBackgroundColor("#EFF2F7");
			}
		};
		private final MouseOutHandler mouseOutHandler = new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				// ((Widget) event.getSource()).getElement().getStyle()
				// .setColor("blue");
				((Widget) event.getSource()).getElement().getStyle()
						.setBackgroundColor("");
			}
		};

		public SideLink(String title, ClickHandler clickHandler) {
			super(title);
			addMouseOverHandler(mouseOverHandler);
			addMouseOutHandler(mouseOutHandler);
			addStyleName("hand");
			// getElement().getStyle().setColor("blue");
			// getElement().getStyle().setTextDecoration(
			// Style.TextDecoration.UNDERLINE);
			if (clickHandler != null) {
				addClickHandler(clickHandler);
			}
			setWidth("199px");
			setHeight("20px");

			setStyleName("side-Link");
			addStyleName("hand");

		}

	}

}
