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

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.myswap.utilitaires.Secured;

/**
 * Classe effectuant le CRUD pour les objets de type Deal.
 * 
 */
@Path("deal")
@Secured
public class DealRessource {

	private static Logger logger = Logger.getLogger(DealRessource.class);
	private Session session;
	
	/**
	 * DealService.
	 */
	private DealRessource dealService = new DealRessource();
	public void setDealService(DealRessource dealService){this.dealService = dealService;}
	
	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Response findDeal(@PathParam("id") long id) {
		
		return Response.ok(dealService.findDeal(id)).build();

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	public Response insertDeal(@FormParam("initator") String initatorId, @FormParam("proposed") String proposedId,
			@FormParam("status") Integer status, @FormParam("swapObjects") Set<String> swapObjectsId) {

		return Response.ok(dealService.insertDeal(initatorId, proposedId, status, swapObjectsId)).build();
	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
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
	public Response updateDeal(@FormParam("id") Long id, @FormParam("status") Integer status,
			@FormParam("swapObjects") Set<String> swapObjectsId) {

		return Response.ok(dealService.updateDeal(id, status, swapObjectsId)).build();

	}

}