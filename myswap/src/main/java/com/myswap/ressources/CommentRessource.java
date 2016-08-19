package com.myswap.ressources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.myswap.models.Comment;
import com.myswap.services.CommentService;
import com.myswap.utilitaires.Secured;

/**
 * Classe effectuant le CRUD pour les objets de type Comment.
 * 
 */
@Path("comment")
@Secured
public class CommentRessource {

	/**
	 * CommentService.
	 */
	private CommentService commentService = new CommentService();
	public void setCommentService(CommentService commentService){this.commentService = commentService;}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Comment findComment(@PathParam("id") long id) {
		
		return commentService.findComment(id);
	}

	/**
	 * M�thode de test d'insertion en cascade via cascade=CascadeType.PERSIST
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({"application/json"})
	public long insertComment(@FormParam("label") String label, @FormParam("mark") Integer mark,
			@FormParam("noting") String notingId, @FormParam("noted") String notedId) {

		return commentService.insertComment(label, mark, notingId, notedId);

	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteComment(@PathParam("id") long id) {

		commentService.deleteComment(id);

	}

	/**
	 * Update de la classe comment.
	 */
	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	public void updateComment(@FormParam("id") Long id, @FormParam("label") String label, @FormParam("mark") Integer mark,
			@FormParam("noting") String notingId, @FormParam("noted") String notedId) {

		commentService.updateComment(id, label, mark, notingId, notedId);

	}

}