package com.myswap.ressources;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.myswap.models.User;



public class UserRessourcesTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(UserRessource.class);
	}

	@Test
	public void getByIdOkTest() {
		final Response response = target("user/getById/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> user = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		assertEquals("id", user.get("id"), 1);
	}
	
	@Test
	public void getByIdNotFoundTest() {
		final Response response = target("user/getById/894").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getByEmailOkTest() {
		final Response response = target("user/getByEmail/usrint0001@gmail.com").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> user = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		Map account = (HashMap<String, Object>) user.get("account");
		assertEquals("email", account.get("email"), "usrint0001@gmail.com");
	}
	
	@Test
	public void getByEmailNotFoundTest() {
		final Response response = target("user/getByEmail/1@1.com").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getByItemOkTest() {
		final Response response = target("user/getUserByItem/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> user = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		assertEquals("id", user.get("id"), 1);
	}

	@Test
	public void insertUserTest() {
		
		Form form = new Form().param("name", "name");
		form.param("lastname", "lastname");
		form.param("email", "email");
		form.param("password", "password");
		form.param("phoneNumber", "phoneNumber");
		form.param("street", "street");
		form.param("state", "state");
		form.param("zipcode", "zipcode");
		form.param("city", "city");
		
		Entity<Form> entity = Entity.form(form);
		
		// insert new user
		Response response1 = target("user/insert").request().post(entity);
		LinkedHashMap<String, Object> user1 = (LinkedHashMap<String, Object>) response1.readEntity(Object.class);
		int id = (Integer) user1.get("id");
	    
		// find the user
	    final Response response = target("user/getById/"+id).request().get(Response.class);
		assertEquals("status", 200, response.getStatus());

		// delete the user
		Response response3 = target("user/delete/"+id).request().delete();
		
		// user is deleted and should not be found
		final Response response2 = target("user/getById/"+id).request().get(Response.class);
		assertEquals("status", 204, response2.getStatus());
	}
	
}