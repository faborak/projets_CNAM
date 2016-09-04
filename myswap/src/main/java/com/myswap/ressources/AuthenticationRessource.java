package com.myswap.ressources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.myswap.exceptions.UserNotFoundException;
import com.myswap.models.User;
import com.myswap.services.AuthenticationService;
import com.myswap.services.UserService;

@Path("/authentication")
public class AuthenticationRessource {

	public static final String CLIENT_ID = "853972608044-m14mdhc3q2k437nfqbob5hti7div33u6.apps.googleusercontent.com";
	public static final String SECRET_ID = "W0q5CJb7tSqSMmeK4hHPLeBF";

	/**
	 * Le AuthenticationService.
	 */
	AuthenticationService authenticationService = new AuthenticationService();
	UserService userService = new UserService();

	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Path("/authenticate")
	public Response authenticateUser(@FormParam("mail") String mail, @FormParam("password") String password) {

		Map<String, Object> reponse = new HashMap<>();
		String token = authenticationService.authenticateUser(mail, password);

		if (token == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {

			User user = null;

			try {
				user = userService.findUser(mail);
			} catch (UserNotFoundException e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}

			reponse.put("user", user);
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
		Map<String, Object> reponse = new HashMap<>();
		reponse.put("isLogged", isLogged);

		return Response.ok(reponse).build();
	}

}
