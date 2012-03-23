package com.fbinsight.test;

import java.util.ArrayList;
import java.util.List;

public class FProfil extends FObject {

	private String projectId;
	private String first_name;
	private String last_name;
	private String link;
	private String username;
	private String birthday;
	private FObject hometown;
	private FObject location;
	private String gender;
	private String email;
	private String website;
	private List<FEducation> education;
	private List<FWork> work;
	private List<FObject> concepts;
	
	private List<FObject> books;
	private List<FObject> events;
	private List<FObject> likes;
	private List<FObject> movies;
	private List<FObject> music;
	private List<FObject> television;
	private List<FObject> friends;
	
	public FProfil() {
	}

	public FProfil(String id, String name) {
		this(id, name, null);
	}

	public FProfil(String id, String name, String type) {
		super(id, name, type);
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public FObject getHometown() {
		return hometown;
	}

	public void setHometown(FObject hometown) {
		this.hometown = hometown;
	}

	public FObject getLocation() {
		return location;
	}

	public void setLocation(FObject location) {
		this.location = location;
	}

	public List<FEducation> getEducation() {
		return education;
	}

	public void setEducation(List<FEducation> education) {
		this.education = education;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<FObject> getBooks() {
		return books;
	}

	public void setBooks(List<FObject> books) {
		this.books = books;
	}

	public List<FObject> getEvents() {
		return events;
	}

	public void setEvents(List<FObject> events) {
		this.events = events;
	}

	public List<FObject> getLikes() {
		return likes;
	}

	public void setLikes(List<FObject> likes) {
		this.likes = likes;
	}

	public List<FObject> getMovies() {
		return movies;
	}

	public void setMovies(List<FObject> movies) {
		this.movies = movies;
	}

	public List<FObject> getMusic() {
		return music;
	}

	public void setMusic(List<FObject> music) {
		this.music = music;
	}

	public List<FObject> getTelevision() {
		return television;
	}

	public void setTelevision(List<FObject> television) {
		this.television = television;
	}

	public List<FObject> getFriends() {
		return friends;
	}

	public void setFriends(List<FObject> friends) {
		this.friends = friends;
	}

	public List<FObject> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<FObject> concepts) {
		this.concepts = concepts;
	}
	
	public void addConcept(FObject concept){
		if(concepts == null)
			concepts = new ArrayList<FObject>();
		concepts.add(concept);
	}

	@Override
	public String toString() {
		return "FProfil [" + " id=" + getId() + ", name=" + getName() + ", category=" + getCategory() + "first_name="
				+ first_name + ", last_name=" + last_name + ", link=" + link + ", username=" + username + ", birthday="
				+ birthday + ", hometown=" + hometown + ", location=" + location + ", education=" + education + ", work=" + work
				+ ", gender=" + gender + ", email=" + email + ", website=" + website + ", concepts=" + concepts
				+ ", friends=" + friends + ", books=" + books + ", movies=" + movies + ", music=" + music
				+ ", television=" + television + ", likes=" + likes + "]";
	}

	public List<FWork> getWork() {
		return work;
	}

	public void setWork(List<FWork> work) {
		this.work = work;
	}
}
