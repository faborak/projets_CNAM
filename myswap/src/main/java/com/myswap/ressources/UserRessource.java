package com.myswap.ressources;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.myswap.models.User;
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
	public User findUser(@PathParam("email") String email) {

		return userService.findUser(email);
	}

	@GET
	@Path("/getById/{id}")
	@Produces({ "application/json" })
	public User findUser(@PathParam("id") long id) {

		return userService.findUser(id);
	}

	/**
	 * M�thode d'insertion d'un nouvel utilisateur.
	 * 
	 */
	@POST
	// @Path("/insert/{name}/{lastname}/{email}/{phoneNumber}/{street}/{state}/{zipcode}/{city}/{pic}")
	@Path("/insert")
	@Consumes({ "application/json" })
	public long insertUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city, @FormParam("pic") File pic) {

		return userService.insertUser(name, lastname, email, phoneNumber, street, state, zipcode, city, pic);
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
	public void updateUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city, @FormParam("pic") File pic) {

		userService.updateUser(name, lastname, email, phoneNumber, street, state, zipcode, city, pic);
	}

}