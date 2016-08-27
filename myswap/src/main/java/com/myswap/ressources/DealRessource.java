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

import com.myswap.exceptions.DealInsertException;
import com.myswap.exceptions.DealNotFoundException;
import com.myswap.exceptions.DealUpdateException;
import com.myswap.models.Deal;
import com.myswap.services.DealService;
import com.myswap.utilitaires.Secured;

/**
 * Classe effectuant le CRUD pour les objets de type Deal.
 * 
 */
@Path("deal")
@Secured
public class DealRessource {

	/**
	 * DealService.
	 */
	private DealService dealService = new DealService();
	public void setDealService(DealService dealService){this.dealService = dealService;}
	
	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Response findDeal(@PathParam("id") long id) {
		
		Deal deal = null;
		
		try {
			deal = dealService.findDeal(id);
		} catch (DealNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deal).build();

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	public Response insertDeal(@FormParam("initator") String initatorId, @FormParam("proposed") String proposedId,
			 @FormParam("swapObjects") Set<String> swapObjectsId) {

		Deal deal = null;
		String status = "En cours de création";
		
		try {
			deal = dealService.insertDeal(initatorId, proposedId, status, swapObjectsId);
		} catch (DealInsertException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deal).build();
	}

	/**
	 * Mï¿½thode pour retour sur l'insertion en cascade.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteDeal(@PathParam("id") long id) {

		dealService.deleteDeal(id);

	}

	/**
	 * Update de la classe deal.
	 */
	@POST
	@Path("/update")
	@Consumes({ "application/json" })
	public Response updateDeal(@FormParam("id") Long id, @FormParam("status") String status,
			@FormParam("swapObjects") Set<String> swapObjectsId) {

		Deal deal = null;
		
		try {
			deal = dealService.updateDeal(id, status, swapObjectsId);
		} catch (DealUpdateException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deal).build();		
		
	}

}