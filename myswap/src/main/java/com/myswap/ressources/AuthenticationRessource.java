package com.myswap.ressources;

import java.util.HashMap;
import java.util.Map;

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
	@Consumes("application/x-www-form-urlencoded")
	@Path("/authenticate")
	public Response authenticateUser(@FormParam("mail") String mail, @FormParam("password") String password) {

		Map<String, Object> reponse =new HashMap<>();
		String token = authenticationService.authenticateUser(mail, password);
		
		if (token == null){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			reponse.put("token", token);
			return Response.ok(reponse).build();
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Path("/islogged")
	public Response isLogged(@FormParam("token") String token) {

		boolean isLogged = authenticationService.isLogged(token);
		
		if (isLogged == false){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			Map<String, Object> reponse =new HashMap<>();
			reponse.put("isLogged", isLogged);
			return Response.ok(reponse).build();
		}
	}

}
