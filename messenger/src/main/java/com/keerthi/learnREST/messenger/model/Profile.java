package com.keerthi.learnREST.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Profile {
	private long id;
	private String FirstName;
	private String LastName;
	private String ProfileName;
	private Date created;
	
	public Profile() {
		
	}
	
	public Profile(long id, String firstName, String lastName, String profileName) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		ProfileName = profileName;
		this.created=new Date();
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getProfileName() {
		return ProfileName;
	}
	public void setProfileName(String profileName) {
		ProfileName = profileName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
