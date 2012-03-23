package com.fbinsight.client.events;


@SuppressWarnings("rawtypes")
public class FilterEvent extends com.fbinsight.client.widgets.Event {
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	private String filter;
	private int num;
	
	public FilterEvent(String filter, int num) {
		super(TYPE);
		this.filter = filter;
		this.num = num;
	}
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

}
