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
import org.junit.Test;



public class ItemRessourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(ItemRessource.class);
	}

	@Test
	public void getByIdOkTest() {
		final Response response = target("item/get/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> item = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		assertEquals("id", item.get("idSwapObjet"), 1);
	}
	
	@Test
	public void getByIdNotFoundTest() {
		final Response response = target("item/get/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getByUserOkTest() {
		final Response response = target("item/getItemsByUser/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		List<LinkedHashMap<String, Object>> items = (ArrayList<LinkedHashMap<String, Object>>) response.readEntity(Object.class);
		LinkedHashMap<String, Object> item = (LinkedHashMap<String, Object>) items.get(0);
		Map user = (HashMap<String, Object>) item.get("owner");
		assertEquals("id", user.get("id"), 1);
	}
	
	@Test
	public void getByUserNotFoundTest() {
		final Response response = target("item/getItemsByUser/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getItemsByCriteriasOkTest() {
		Form form = new Form().param("category", "Informatique");
		form.param("costMin", "");
		form.param("costMax", "");
		form.param("keyword", "objet");
		form.param("idReprise", "");
		form.param("maxResult", "");
		
		Entity<Form> entity = Entity.form(form);
		
		// insert new item
		final Response response = target("item/getItemsByCriterias").request().post(entity);
		List<LinkedHashMap<String, Object>> items = (ArrayList<LinkedHashMap<String, Object>>) response.readEntity(Object.class);
		LinkedHashMap<String, Object> item = (LinkedHashMap<String, Object>) items.get(0);
		assertEquals("item", item.get("idSwapObjet"), 1);
	}

	@Test
	public void insertItemTest() {
		
		Form form = new Form().param("name", "name");
		form.param("name", "name");
		form.param("description", "description");
		form.param("cost", "1");
		form.param("userId", "1");
		form.param("category", "Informatique");
		
		Entity<Form> entity = Entity.form(form);
		
		// insert new item
		Response response1 = target("item/insert").request().post(entity);
		LinkedHashMap<String, Object> item1 = (LinkedHashMap<String, Object>) response1.readEntity(Object.class);
		int id = (Integer) item1.get("idSwapObjet");
	    
		// find the item
	    final Response response = target("item/get/"+id).request().get(Response.class);
		assertEquals("status", 200, response.getStatus());

		// delete the item
		// this method doesn't work with Grizzly, the Response is 400 : bad request
		Response response3 = target("item/delete/"+id).request().delete();
		
		// item is deleted and should not be found
		// this test is an  error, consequence of the precedent test
		final Response response2 = target("item/get/"+id).request().get(Response.class);
		assertEquals("status", 204, response2.getStatus());
	}
	
}