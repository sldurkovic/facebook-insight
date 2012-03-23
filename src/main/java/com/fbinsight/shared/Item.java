package com.fbinsight.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


@SuppressWarnings("serial")
public class Item implements Serializable, IsSerializable{

	private FType type;
	private String name;
	private int total;
	
	public Item() {
	}
	
	public Item(String name) {
		this.name = name;
		total++;
	}

	public Item(FType type) {
		super();
		this.type = type;
		total++;
	}

	public Item(FType type, int total) {
		super();
		this.type = type;
		this.total = total;
	}

	public FType getType() {
		return type;
	}

	public void setType(FType type) {
		this.type = type;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [type=" + type + ", name=" + name + ", total=" + total
				+ "]";
	}

	public void increment() {
		total++;
	}
	
	
}
