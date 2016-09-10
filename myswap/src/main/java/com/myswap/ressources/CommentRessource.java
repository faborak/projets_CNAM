package com.myswap.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.myswap.exceptions.CommentInsertException;
import com.myswap.exceptions.CommentNotFoundException;
import com.myswap.exceptions.CommentUpdateException;
import com.myswap.models.Comment;
import com.myswap.services.CommentService;
import com.myswap.utilitaires.Secured;

/**
 * Classe effectuant le CRUD pour les objets de type Comment.
 * 
 */
@Path("comment")
public class CommentRessource {

	/**
	 * CommentService.
	 */
	private CommentService commentService = new CommentService();
	public void setCommentService(CommentService commentService){this.commentService = commentService;}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Response findComment(@PathParam("id") long id) {
		
		Comment comment = null;
		
		try {
			comment = commentService.findComment(id);
		} catch (CommentNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(comment).build();
		
	}
	
	@GET
	@Path("/getByUser/{id}")
	@Produces({ "application/json" })
	public Response findCommentByUser(@PathParam("id") long id) {
		
		List<Comment> comments = null;
		
		try {
			comments = commentService.findCommentsByUser(id);
		} catch (CommentNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(comments).build();
		
	}

	/**
	 * M�thode de test d'insertion en cascade via cascade=CascadeType.PERSIST
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({"application/json"})
	@Secured
	public Response insertComment(@FormParam("label") String label, @FormParam("mark") Integer mark,
			@FormParam("noting") String notingId, @FormParam("noted") String notedId) {

		Comment comment = null;
		
		try {
			comment = commentService.insertComment(label, mark, notingId, notedId);
		} catch (CommentInsertException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(comment).build();
		
	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	@Secured
	public void deleteComment(@PathParam("id") long id) {

		commentService.deleteComment(id);

	}

	/**
	 * Update de la classe comment.
	 */
	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	@Secured
	public Response updateComment(@FormParam("id") Long id, @FormParam("label") String label, @FormParam("mark") Integer mark
			) {

		Comment comment = null;
		
		try {
			comment = commentService.updateComment(id, label, mark);
		} catch (CommentUpdateException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(comment).build();
		
		

	}

}