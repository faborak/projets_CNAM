package com.myswap.ressources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.myswap.services.AuthenticationService;

@Path("/authentication")
public class AuthenticationRessource {

	public static final String CLIENT_ID = "853972608044-m14mdhc3q2k437nfqbob5hti7div33u6.apps.googleusercontent.com";
	public static final String SECRET_ID = "W0q5CJb7tSqSMmeK4hHPLeBF";

	/**
	 * Le AuthenticationService.
	 */
	AuthenticationService authenticationService = new AuthenticationService();

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/authenticate")
	public Response authenticateUser(@FormParam("mail") String mail, @FormParam("password") String password) {

		return authenticationService.authenticateUser(mail, password);
	}

}
