package com.myswap.ressources;


import static org.junit.Assert.assertNotNull;

import javax.json.Json;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class UserRessourcesTest extends JerseyTest {
	
	public UserRessourcesTest()throws Exception {
        super("com.myswap.ressources.UserRessource");
    }
	
	@Test
	public void getByIdTest() {
		WebResource webResource = resource();
		Json responseMsg = webResource.path("user/getById/1").get(Json.class);
		assertNotNull("L'instance n'est pas créée", responseMsg.toString());
	}
	
}