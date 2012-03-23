package com.fbinsight.shared;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Statistics implements Serializable{

	private ArrayList<Item> items;
	
	public Statistics() {
	}

	public Statistics(ArrayList<Item> items) {
		super();
		this.items = items;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item){
		if(items == null){
			items = new ArrayList<Item>();
		}
		boolean exist = false;
		for (Item i : items) {
			if(item.getType() != null){
				if(i.getType() == item.getType()){
					i.increment();
					exist = true;
				}
			}else{
				if(i.getName().equalsIgnoreCase(item.getName())){
					i.increment();
					exist = true;
				}
			}
		}
		if(!exist){
			items.add(item);
		}
	}
	

	@Override
	public String toString() {
		return "Statistics [items=" + items + "]";
	}
	
}
