package com.myswap.ressources;

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

import com.myswap.exceptions.DealInsertException;
import com.myswap.exceptions.DealNotFoundException;
import com.myswap.exceptions.DealUpdateException;
import com.myswap.models.Deal;
import com.myswap.services.DealService;
import com.myswap.utilitaires.Secured;

/**
 * 
 * @author myswap
 * Rest WevService for Deals and Swaps in myswap.
 */
@Path("deal")
public class DealRessource {

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
	
	@GET
	@Path("/getByUser/{id}")
	@Produces({ "application/json" })
	public Response findDealByUser(@PathParam("id") long id) {
		
		List<Deal> deals = null;
		
		try {
			deals = dealService.findDealByUser(id);
		} catch (DealNotFoundException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deals).build();

	}

	@POST
	@Path("/insert")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Secured
	public Response insertDeal(@FormParam("initator") String initatorId, @FormParam("proposed") String proposedId,
			 @FormParam("swapObjects") Set<String> swapObjectsId) {

		Deal deal = null;
		
		try {
			deal = dealService.insertDeal(initatorId, proposedId, swapObjectsId);
		} catch (DealInsertException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deal).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Secured
	public void deleteDeal(@PathParam("id") long id) {

		dealService.deleteDeal(id);

	}

	@POST
	@Path("/update")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Secured
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
	
	@POST
	@Path("/modifyStatus")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Secured
	public Response ModifyStatus(@FormParam("id") Long id, @FormParam("status") String status) {

		Deal deal = null;
		
		try {
			deal = dealService.modifyStatus(id, status);
		} catch (DealUpdateException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok(deal).build();		
		
	}

}