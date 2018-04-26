package com.keerthi.learnREST.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.Uri;

import com.keerthi.learnREST.messenger.model.Message;
import com.keerthi.learnREST.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService MS = new MessageService();

	@GET
	// @Produces(MediaType.APPLICATION_XML) //returns xml. Java autoatcally converts
	// into xml
	@Produces(MediaType.APPLICATION_JSON) // return json. Usually u need to install dependency to convert into json
											// format as java cannot convert. For that u just need to remove comments in
											// POM.xml file.

	/*
	 * public List<Message> getMessage(@QueryParam("year") int year,
	 * 
	 * @QueryParam("start") int start,
	 * 
	 * @QueryParam("size") int size,){
	 * 
	 * if (Year() > 0) { return MS.getAllMessagesForYear(Year()); } if (Start() >= 0
	 * && Size() > 0) { return MS.getAllMessagesPaginated(Start(), Size()); } return
	 * MS.getAllMessages(); } These can be replaced with @beanparam by creating
	 * messageBeanFilter class and implementing these attributes there
	 */

	public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return MS.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return MS.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return MS.getAllMessages();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo UriInfo) throws URISyntaxException {
		
		// return Response.status(Status.CREATED) This statement just creates the status
		// return Response.created(new URI("/messanger/webapi/messages" +
		// newMessage.getId())) //this will give status along with the exact location in
		// header but hardcoding uri is not good
		Message newMessage = MS.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI Url = UriInfo.getAbsolutePathBuilder().path(newId).build();  //it builds the url path and adds the path of the new element then builds
		return Response.created(Url)
						.entity(newMessage)
						.build();

		// return MS.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return MS.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeMessage(@PathParam("messageId") long id) {
		MS.removeMessage(id);
	}

	@GET
	@Path("/{messageId}") // When {} is mapped in the url, send String msz as an argument when test method
							// is called
	@Produces(MediaType.APPLICATION_JSON)
	public Message Test(@PathParam("messageId") long Id) {
		return MS.getMessage(Id);
	}

	// SubResources
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();

	}
}
