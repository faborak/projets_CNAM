package com.myswap.ressources;

import java.util.ArrayList;
import java.util.List;
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

import com.myswap.exceptions.AddPictureException;
import com.myswap.exceptions.ItemInsertException;
import com.myswap.exceptions.ItemNotFoundException;
import com.myswap.exceptions.ItemUpdateException;
import com.myswap.models.Item;
import com.myswap.models.ItemPicture;
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
		
		Item item = null;
		
		try {
			item = itemService.findItem(id);
		} catch (ItemNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(item).build();
	}
	
	@GET
	@Path("/getCategories")
	@Produces({ "application/json" })
	public Response findCategories() {
		
		return Response.ok(itemService.findCategories()).build();
	}
	
	@GET
	@Path("/getTendances")
	@Produces({ "application/json" })
	public Response findTendances() {
		
		List<Item> items = new ArrayList<Item>();
		
		try {
			items = itemService.findTendances();
		} catch (ItemNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(items).build();
	}
	
//	@GET
//	@Path("/getProposed")
//	@Produces({ "application/json" })
//	public Response findProposed() {
//		
//		return Response.ok(itemService.findProposed()).build();
//	}
	
	@POST
	@Path("/getItemsByCriterias")
	@Produces({ "application/json" })
	public Response findItemsByCriterias(@FormParam("category") String category, @FormParam("costMin")  String costMin,  @FormParam("costMax")  String costMax, @FormParam("keyword")  String keyword, @FormParam("idReprise")  long idReprise, @FormParam("maxResult") int maxResult) {
		
		List<Item> items = new ArrayList<Item>();
		
		try {
			items = itemService.findItemsByCriterias(category, costMin, costMax, keyword, idReprise, maxResult);
		} catch (ItemNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(items).build();
	}

	/**
	 * Insertion d'un nouvel Item.
	 * 
	 */
	@POST
	@Path("/insert")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Secured
	public Response insertItem(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("userId") String userId, @FormParam("deals") Set<String> dealsId) {

		Item item = null;
		
		try {
			item = itemService.insertItem(name, description, cost, userId, dealsId);
		} catch (ItemInsertException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(item).build();
		

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
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Secured
	public Response updateItem(@FormParam("id") Long id, @FormParam("name") String name, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("token") String token, @FormParam("deals") Set<String> dealsId) {

		Item item = null;
		
		try {
			item = itemService.updateItem(id, name, description, cost, token, dealsId);
		} catch (ItemUpdateException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(item).build();
		
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

		ItemPicture itempicture = null;
		
		try {
			itempicture = itemService.addPicture(picName, picPath, itemId);
		} catch (AddPictureException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(itempicture).build();
		
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