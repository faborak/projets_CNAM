package com.myswap.ressources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.myswap.exceptions.AddPictureException;
import com.myswap.exceptions.UserInsertException;
import com.myswap.exceptions.UserNotFoundException;
import com.myswap.exceptions.UserUpdateException;
import com.myswap.models.User;
import com.myswap.models.UserPicture;
import com.myswap.services.UserService;
import com.myswap.utilitaires.Secured;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
@Path("user")
public class UserRessource {

	UserService userService = new UserService();

	@GET
	@Path("/getByEmail/{email}")
	@Produces({ "application/json" })
	@Secured
	public Response findUser(@PathParam("email") String email) {

		User user = null;
		
		try {
			user = userService.findUser(email);
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(user).build();
	}

	@GET
	@Path("/getById/{id}")
	@Produces({ "application/json" })
	public Response findUser(@PathParam("id") long id) {
		
		User user = null;
		
		try {
			user = userService.findUser(id);
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.ok(user).build();
	}
	
	@GET
	@Path("/getUserByItem/{id}")
	@Produces({ "application/json" })
	public Response findUserByItem(@PathParam("id") long id) {
		
		User user = null;
		
		try {
			user = userService.findUserByItem(id);
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.ok(user).build();
	}
	
	@POST
	@Path("/getCurrentUser")
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ "application/json" })
	public Response findUserByToken(@FormParam("token") String token) {
	
		User user = null;
		
		try {
			user = userService.findUserByToken(token);
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(user).build();
	}

	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	@Secured
	public Response insertUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("password") String password, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city) {

		try {
			return Response.ok(userService.insertUser(name, lastname, email, password, phoneNumber, street, state, zipcode, city)).build();
		} catch (UserInsertException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

//	@DELETE
//	@Path("/delete/{id}")
//	@Secured
//	public void deleteUser(long id) {
//		
//		userService.deleteUser(id);
//	}

	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	@Secured
	public Response updateUser(@FormParam("name") String name, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("emailChecked") boolean emailChecked, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("phoneChecked") boolean phoneChecked, @FormParam("street") String street, @FormParam("state") String state, @FormParam("zipcode") String zipcode,
			@FormParam("city") String city, @FormParam("school") String school, @FormParam("job") String job, @FormParam("about") String about) {

		User user = null;
		
		try {
			user = userService.updateUser(name, lastname, email, emailChecked, phoneNumber, phoneChecked, street, state, zipcode, city, school, job, about);
		} catch (UserUpdateException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(user).build();
	}
	
		/**
	 * Insertion d'une nouvelle Picture.
	 * 
	 */
	@POST
	@Path("/insertPicture")
	@Consumes({ "application/json" })
	@Secured
	public Response insertItem(@FormParam("picPath") String picPath,
			@FormParam("userId") long userId) {

		UserPicture userPicture = null;
		
		try {
			userPicture = userService.addPicture(picPath, userId);
		} catch (AddPictureException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		
		return Response.ok(userPicture).build();

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