package com.fbinsight.test;

public class FEducation extends FObject {

	private FObject school;
	private FObject year;
	private String type;

	public FObject getSchool() {
		return school;
	}

	public void setSchool(FObject school) {
		this.school = school;
	}

	public FObject getYear() {
		return year;
	}

	public void setYear(FObject year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FEducation [school=" + school + ", year=" + year + ", type="
				+ type + "]";
	}
	
}
