package com.fbinsight.client.widgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetStack extends Container<VerticalPanel> {

	private static final double SPACE = 10;
	private Widget[] widgets;

	public WidgetStack(Widget... widgets) {
		super(new VerticalPanel(), false);
		this.widgets = widgets;
		render();
	}

	@Override
	protected void render() {
		for (Widget widget : widgets) {
			widget.getElement().getStyle().setMarginBottom(SPACE, Unit.PX);
			addWidget(widget);
		}
	}

	public Widget getTopWidget() {
		return widgets[0];
	}

	public Widget removeFromTop() {
		boolean success = getTopContainer().remove(0);
		if (success) {
			return widgets[0];
		}
		return null;
	}
}