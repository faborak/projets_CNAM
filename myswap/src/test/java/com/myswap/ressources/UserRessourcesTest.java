package com.myswap.ressources;


import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.myswap.services.UserService;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class UserRessourcesTest extends JerseyTest {
	
	UserService userService = new UserService();
	
	public UserRessourcesTest()throws Exception {
        super("com.myswap.ressources.UserRessource");
    }
	
	@Test
	public void getByIdTest() {
		WebResource webResource = resource();
		Response response = webResource.path("user/getById/1").get(Response.class);
		assertEquals("status", 200,  response.getStatus());
	}
	
}