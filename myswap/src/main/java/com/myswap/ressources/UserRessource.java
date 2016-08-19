package com.myswap.ressources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.myswap.services.UserService;
import com.myswap.utilitaires.Secured;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
@Path("user")
@Secured
public class UserRessource {

	UserService userService = new UserService();

	@GET
	@Path("/getByEmail/{email}")
	@Produces({ "application/json" })
	public Response findUser(@PathParam("email") String email) {

		return Response.ok(userService.findUser(email)).build();
	}

	@GET
	@Path("/getById/{id}")
	@Produces({ "application/json" })
	public Response findUser(@PathParam("id") long id) {

		return Response.ok(userService.findUser(id)).build();
	}
	
	@GET
	@Path("/getCurrentUser")
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ "application/json" })
	public Response findUserByToken(@FormParam("token") String token) {
	
		return Response.ok(userService.findUserByToken(token)).build();
	}

	/**
	 * M�thode d'insertion d'un nouvel utilisateur.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	public Response insertUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city) {

		return Response.ok(userService.insertUser(name, lastname, email, phoneNumber, street, state, zipcode, city)).build();
	}

	/**
	 * M�thode de suppression d'un utilisateur.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteUser(long id) {
		
		userService.deleteUser(id);
	}

	/**
	 * Update de la classe user. TODO : un update en cascade via la classe User
	 * sur account et adress est possible.
	 */
	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	public Response updateUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city) {

		return Response.ok(userService.updateUser(name, lastname, email, phoneNumber, street, state, zipcode, city)).build();
	}
	
		/**
	 * Insertion d'une nouvelle Picture.
	 * 
	 */
	@POST
	@Path("/insertPicture")
	@Consumes({ "application/json" })
	@Secured
	public Response insertItem(@FormParam("picName") String picName, @FormParam("picPath") String picPath,
			@FormParam("userId") long userId) {

		return Response.ok(userService.addPicture(picName, picPath, userId)).build();

	}
	
	/**
	 * Suppression d'une Picture en base.
	 * 
	 */
	@DELETE
	@Path("/deletePicture/{id}")
	@Secured
	public void deletePicture(@PathParam("id") long pictureId) {
		userService.deletePicture(pictureId);
	}

}