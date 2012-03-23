package com.fbinsight.test;

public class FObject extends Entity{

	private String category;

	public FObject() {
	}

	public FObject(String id, String name, String type) {
		super(id, name);
		this.category = type;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String type) {
		this.category = type;
	}
	

	@Override
	public String toString() {
		return "FObject [id=" + id + ", name=" + name + ", category=" + category + "]";
	}


}
