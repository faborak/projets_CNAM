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

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.models.Deal;
import com.myswap.models.SwapObject;
import com.myswap.models.User;
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
	public Deal findDeal(@PathParam("id") long id) {
		
		return dealService.findDeal(id);

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	@POST
	@Path("/insert")
	@Consumes({ "application/json" })
	public long insertDeal(@FormParam("initator") String initatorId, @FormParam("proposed") String proposedId,
			@FormParam("status") Integer status, @FormParam("swapObjects") Set<String> swapObjectsId) {

		return dealService.insertDeal(initatorId, proposedId, status, swapObjectsId);
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
	public void updateDeal(@FormParam("id") Long id, @FormParam("status") Integer status,
			@FormParam("swapObjects") Set<String> swapObjectsId) {

		dealService.updateDeal(id, status, swapObjectsId);

	}

}