package com.services;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.models.Deal;
import com.models.SwapObject;
import com.models.User;

/**
 * Classe effectuant le CRUD pour les objets de type Deal.
 * 
 */
@Path("deal")
public class DealService {

	private static Logger logger = Logger.getLogger(DealService.class);
	private Session session;
	
	/**
	 * UserService.
	 */
	private UserService userService = new UserService();
	public void setUserService(UserService userService){this.userService = userService;}
	
	/**
	 * ItemService.
	 */
	private ItemService itemService = new ItemService();
	public void setItemService(ItemService itemService){this.itemService = itemService;}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Deal findDeal(@PathParam("id") long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Deal deal = null;
		try {
			Criteria criteria = session.createCriteria(Deal.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			// pour la pagination, on peut ajouter criteria.setMaxResults(10),
			// etc, et utiliser une cl� de reprise � chaque appel.
			// inutilis� dans le cadre de ce projet.

			deal = (Deal) criteria.uniqueResult();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in DealService/findDeal : " + e.getMessage());
		} finally {
			session.close();
		}

		return deal;

		// faire un retour via Response ?
//		 return Response.status(200).entity(deal).build();
	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	@POST
	@Path("/insert/{initator}/{proposed}/{status}/{swapObjects}")
	@Consumes({ "application/json" })
	public long insertDeal(@PathParam("initator") String initatorId, @PathParam("proposed") String proposedId,
			@PathParam("status") Integer status, @PathParam("swapObjects") Set<String> swapObjectsId) {

		Deal deal = new Deal();
		
		User initator = new User();
		initator = userService.findUser(initatorId);
		User proposed = new User();
		proposed = userService.findUser(proposedId);
		deal.setInitiator(initator);
		deal.setProposed(proposed);
		deal.setStatus(status);
		
		for (String soId : swapObjectsId) {
			SwapObject so = new SwapObject();
			so = itemService.findItem(Long.parseLong(soId));
			deal.addSwapObjects(so);
		}

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(deal);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in DealService/insertDeal : " + e.getMessage());
		} finally {
			session.close();
		}

		return deal.getIdDeal();
	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteDeal(@PathParam("id") long id) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression du deal en base.
		try {

			Criteria dealList = session.createCriteria(Deal.class).add(Restrictions.eqOrIsNull("id_deal", id));
			for (Object deal : dealList.list()) {
				session.delete(deal);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in DealService/deleteDeal : " + e.getMessage());
		} finally {
			session.close();
		}

	}

	/**
	 * Update de la classe deal.
	 */
	@POST
	@Path("/update/{id}/{initator}/{proposed}/{status}/{swapObjects}")
	@Consumes({ "application/json" })
	public void updateDeal(@PathParam("id") Long id, @PathParam("status") Integer status,
			@PathParam("swapObjects") Set<String> swapObjectsId) {

		Deal deal = findDeal(id);
		deal.setStatus(status);
		for (String soId : swapObjectsId) {
			SwapObject so = new SwapObject();
			so = itemService.findItem(Long.parseLong(soId));
			deal.addSwapObjects(so);
		}

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(deal);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in DealService/updateDeal : " + e.getMessage());
		} finally {
			session.close();
		}

	}

}