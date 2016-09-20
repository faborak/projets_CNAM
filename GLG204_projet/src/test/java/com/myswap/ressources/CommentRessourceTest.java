package com.myswap.ressources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;



public class CommentRessourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(CommentRessource.class);
	}

	@Test
	public void getByIdOkTest() {
		final Response response = target("comment/get/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> comment = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		assertEquals("id", comment.get("idComment"), 1);
	}
	
	@Test
	public void getByIdNotFoundTest() {
		final Response response = target("comment/get/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getByUserOkTest() {
		final Response response = target("comment/getByUser/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		List<LinkedHashMap<String, Object>> comments = (ArrayList<LinkedHashMap<String, Object>>) response.readEntity(Object.class);
		LinkedHashMap<String, Object> comment = (LinkedHashMap<String, Object>) comments.get(0);
		
		// the user 1 has to be the noted
		Map user = (HashMap<String, Object>) comment.get("noted");
		assertEquals("id", user.get("id"), 1);
	}
	
	@Test
	public void getByUserNotFoundTest() {
		final Response response = target("comment/getByUser/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}

	@Ignore
	@Test
	public void insertCommentTest() {
		
		Form form = new Form().param("label", "Test de commentaire.");
		form.param("mark", "5");
		form.param("noting", "1");
		form.param("noted", "2");
		
		Entity<Form> entity = Entity.form(form);
		
		// insert new comment
		Response response1 = target("comment/insert").request().post(entity);
		LinkedHashMap<String, Object> comment1 = (LinkedHashMap<String, Object>) response1.readEntity(Object.class);
		int id = (Integer) comment1.get("id");
	    
		// find the comment
	    final Response response = target("comment/getById/"+id).request().get(Response.class);
		assertEquals("status", 200, response.getStatus());

		// delete the comment
		// this method doesn't work with Grizzly, the Response is 400 : bad request
		Response response3 = target("comment/delete/"+id).request().delete();
		
		// comment is deleted and should not be found
		// this test is an  error, consequence of the precedent test
		final Response response2 = target("comment/getById/"+id).request().get(Response.class);
		assertEquals("status", 204, response2.getStatus());
	}
	
}