package com.myswap.ressources;

import java.io.File;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.myswap.models.Item;
import com.myswap.services.ItemService;
import com.myswap.utilitaires.Secured;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
@Path("item")
public class ItemRessource {

	/**
	 * ItemService.
	 */
	private ItemService itemService = new ItemService();
	public void setItemService(ItemService itemService){this.itemService = itemService;}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Item findItem(@PathParam("id") long id) {
		
		return itemService.findItem(id);
	}

	/**
	 * Insertion d'un nouvel Item.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	@Secured
	public long insertItem(@FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("user") String userId, @FormParam("pic") File pic,
			@FormParam("deals") Set<String> dealsId) {

		return itemService.insertItem(name, dateCreation, dateModification, description, cost, userId, pic, dealsId);

	}

	/**
	 * Suppression d'un Item en base.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	@Secured
	public void deleteItem(@PathParam("id") long id) {
		itemService.deleteItem(id);

	}

	/**
	 * Update de la classe item.
	 */
	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	@Secured
	public void updateItem(@FormParam("id") Long id, @FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("user") String userId, @FormParam("pic") File pic,
			@FormParam("deals") Set<String> dealsId) {

		itemService.updateItem(id, name, dateCreation, dateModification, description, cost, userId, pic, dealsId);

	}

}