package com.fbinsight.shared;

public enum FType {
	
	Book("Book"),
	Movie("Movie"),
	Tv_show("Tv show") ,
	Musician_Band("Musician/Band"),
	Athlete("Athlete"),
	Professional_sports_team("Professional sports team"), 
	Product_service("Product/service");
	
	private String fbLabel;

	private FType(String fbLabel) {
		this.fbLabel = fbLabel;
	}

	public String getFbLabel() {
		return fbLabel;
	}


	public static boolean isSupportedType(String type) {
		for (FType fTypes : FType.values()) {
			if(fTypes.getFbLabel().equals(type)) return true;
		}
		return false;
	}
	
	public static FType getTypeForFBType(String fbType) {
		for (FType fTypes : FType.values()) {
			if(fTypes.getFbLabel().equals(fbType)) return fTypes;
		}
		return null;
	}
	
	public static final String[] getCategories(){
		String[] categories = new String[FType.values().length];
		for (int i = 0; i < FType.values().length; i++) {
			categories[i] = FType.values()[i].getFbLabel();
		}
		return categories;
	}
	
}