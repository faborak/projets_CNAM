package com.myswap.ressources;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
	public Response findItem(@PathParam("id") long id) {
		
		return Response.ok(itemService.findItem(id)).build();
	}
	
	@GET
	@Path("/getCategories")
	@Produces({ "application/json" })
	public Response findCategories() {
		
		return Response.ok(itemService.findCategories()).build();
	}
	
	@POST
	@Path("/getItemsByCriterias")
	@Produces({ "application/json" })
	public Response findItemsByCriterias(@FormParam("category") String category, @FormParam("costMin")  String costMin,  @FormParam("costMax")  String costMax, @FormParam("name")  String name, @FormParam("idReprise")  long idReprise) {
		
		return Response.ok(itemService.findItemsByCriterias(category, costMin, costMax, name, idReprise)).build();
	}

	/**
	 * Insertion d'un nouvel Item.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	@Secured
	public Response insertItem(@FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("userId") String userId, @FormParam("deals") Set<String> dealsId) {

		return Response.ok(itemService.insertItem(name, dateCreation, dateModification, description, cost, userId, dealsId)).build();

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
	public Response updateItem(@FormParam("id") Long id, @FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("userId") String userId, @FormParam("deals") Set<String> dealsId) {

		return Response.ok(itemService.updateItem(id, name, dateCreation, dateModification, description, cost, userId, dealsId)).build();

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
			@FormParam("itemId") long itemId) {

		return Response.ok(itemService.addPicture(picName, picPath, itemId)).build();

	}
	
	/**
	 * Suppression d'une Picture en base.
	 * 
	 */
	@DELETE
	@Path("/deletePicture/{id}")
	@Secured
	public void deleteItemPicture(@PathParam("id") long pictureId) {
		itemService.deletePicture(pictureId);
	}

}