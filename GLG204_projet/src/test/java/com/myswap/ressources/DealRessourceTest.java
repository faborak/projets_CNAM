package com.myswap.ressources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;



public class DealRessourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(DealRessource.class);
	}

	@Test
	public void getByIdOkTest() {
		final Response response = target("deal/get/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		LinkedHashMap<String, Object> deal = (LinkedHashMap<String, Object>) response.readEntity(Object.class);
		assertEquals("id", deal.get("idDeal"), 1);
	}
	
	@Test
	public void getByIdNotFoundTest() {
		final Response response = target("deal/get/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}
	
	@Test
	public void getByUserOkTest() {
		final Response response = target("deal/getByUser/1").request().get(Response.class);
		assertEquals("status", 200, response.getStatus());
		List<LinkedHashMap<String, Object>> deals = (ArrayList<LinkedHashMap<String, Object>>) response.readEntity(Object.class);
		LinkedHashMap<String, Object> deal = (LinkedHashMap<String, Object>) deals.get(0);
		
		// the test needs the user to be the initiator of the deal
		Map user = (HashMap<String, Object>) deal.get("initiator");
		assertEquals("id", user.get("id"), 1);
	}
	
	@Test
	public void getByUserNotFoundTest() {
		final Response response = target("deal/getByUser/0").request().get(Response.class);
		assertEquals("status", 204, response.getStatus());
	}

    @Ignore
	@Test
	public void insertDealTest() {
		
		Set<String> swapObjects = new HashSet<String>();
		swapObjects.add("1");
		swapObjects.add("2");
		
		Form form = new Form().param("initator", "1");
		form.param("proposed", "2");
		//form.param("swapObjects", swapObjects);
		
		Entity<Form> entity = Entity.form(form);
		
		// insert new deal
		Response response1 = target("deal/insert").request().post(entity);
		LinkedHashMap<String, Object> deal1 = (LinkedHashMap<String, Object>) response1.readEntity(Object.class);
		int id = (Integer) deal1.get("id");
	    
		// find the deal
	    final Response response = target("deal/getById/"+id).request().get(Response.class);
		assertEquals("status", 200, response.getStatus());

		// delete the deal
		// this method doesn't work with Grizzly, the Response is 400 : bad request
		Response response3 = target("deal/delete/"+id).request().delete();
		
		// deal is deleted and should not be found
		// this test is an  error, consequence of the precedent test
		final Response response2 = target("deal/getById/"+id).request().get(Response.class);
		assertEquals("status", 204, response2.getStatus());
	}
	
}