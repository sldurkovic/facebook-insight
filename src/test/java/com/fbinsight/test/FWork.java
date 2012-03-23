package com.fbinsight.test;

public class FWork {

	private FObject employer;
	private FObject location;
	private FObject position;

	public FObject getEmployer() {
		return employer;
	}

	public void setEmployer(FObject employer) {
		this.employer = employer;
	}

	public FObject getLocation() {
		return location;
	}

	public void setLocation(FObject location) {
		this.location = location;
	}

	public FObject getPosition() {
		return position;
	}

	public void setPosition(FObject position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "FWork [employer=" + employer + ", location=" + location + ", position=" + position + "]";
	}

}
