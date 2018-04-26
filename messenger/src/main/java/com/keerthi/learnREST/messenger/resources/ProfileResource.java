package com.keerthi.learnREST.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.keerthi.learnREST.messenger.model.Message;
import com.keerthi.learnREST.messenger.model.Profile;
import com.keerthi.learnREST.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService PS=new ProfileService();
	
	@GET
	//@Produces(MediaType.APPLICATION_XML) //returns xml. Java autoatcally converts into xml
	//@Produces(MediaType.APPLICATION_JSON) //return json. Usually u need to install dependency to convert into json format as java cannot convert. For that u just need to remove comments in POM.xml file.
	public List<Profile> getProfiles() {
		return PS.getAllProfiles();
	} 
	
	@POST
	public Profile addProfile(Profile profile) {
		return PS.addProfile(profile);
	}
	
	
	@PUT
	@Path("/{profileId}")
	public Profile updateProfile(@PathParam("profileId") String profileName, Profile profile ) {
		profile.setProfileName(profileName);
		return PS.updateProfile(profile);
	}
	
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		PS.removeProfile(profileName);
	}
	
	
	@GET
	@Path("/{profileName}") //When {} is mapped in the url,  send String msz as an argument when test method is called
	public Profile getProfile(@PathParam("profileName") String profileName){
		return PS.getProfile(profileName);
	}
}
