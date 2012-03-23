package com.fbinsight.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class Container<E extends Panel> extends Composite {

	protected E container;

	public Container(E container, boolean autoRender) {
		this.container = container;
		initWidget(this.container);
		if(autoRender){
			render();
		}
	}

	public void addWidget(Widget w) {
		container.add(w);
	}
	
	public boolean removeWidget(Widget w){
		return container.remove(w);
	}

	protected E getTopContainer() {
		return container;
	}

	protected void clearContainer() {
		container.clear();
	}
	
	protected abstract void render();

}
