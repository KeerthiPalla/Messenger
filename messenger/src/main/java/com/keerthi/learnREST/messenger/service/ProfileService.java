package com.keerthi.learnREST.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.keerthi.learnREST.messenger.database.Database;
import com.keerthi.learnREST.messenger.model.Profile;


public class ProfileService {
	
private Map<String, Profile> profiles=Database.getProfiles();
	
public ProfileService() {
profiles.put("keerthi", new Profile(1L,"Keerthi","Palla", "HYD"));		
	}

	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String ProfileName) {
		return profiles.get(ProfileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile)
	{
		if(profile.getProfileName().isEmpty()) {
		return null;
	}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	
	}

	public Profile removeProfile(String ProfileName) {
		return profiles.remove(ProfileName);
	}
	
}
