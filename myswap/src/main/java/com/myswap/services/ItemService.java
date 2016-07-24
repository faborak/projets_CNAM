package com.myswap.services;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
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
import com.myswap.models.Item;
import com.myswap.models.User;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
@Path("item")
public class ItemService {

	private Session session;
	private static Logger logger = Logger.getLogger(ItemService.class);
	
	/**
	 * UserService.
	 */
	private UserService userService = new UserService();
	public void setUserService(UserService userService){this.userService = userService;}

	/**
	 * DealService.
	 */
	private DealService dealService;
	public void setDealService(DealService dealService){this.dealService = dealService;}
	
	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public Item findItem(@PathParam("id") long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Item item = null;
		try {
			Criteria criteria = session.createCriteria(Item.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			// pour la pagination, on peut ajouter criteria.setMaxResults(10),
			// etc, et utiliser une cl� de reprise � chaque appel.
			// inutilis� dans le cadre de ce projet.

			item = (Item) criteria.uniqueResult();
			// load the deals
			item.getDeals().size();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in ItemService/findItem : " + e.getMessage());
		} finally {
			session.close();
		}
		return item;
	}

	/**
	 * Insertion d'un nouvel Item.
	 * 
	 */
	@POST
//	@Path("/insert/{name}/{dateCreation}/{dateModification}/{description}/{cost}/{user}/{pic}/{deals}")
	@Path("/insert")
	@Consumes({ "application/json" })
	public long insertItem(@FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("user") String userId, @FormParam("pic") File pic,
			@FormParam("deals") Set<String> dealsId) {

		Item item = new Item();
		
		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		
		item.setName(name);
		try {
			item.setDateCreation(df.parse(dateCreation));
			item.setDateModification(df.parse(dateModification));
		} catch (ParseException pe) {
			logger.error("ParseException in ItemService/insertItem : " + pe.getMessage());
		}
		
		item.setDescription(description);
		item.setCost(Float.parseFloat(cost));
		
		User user = new User();
		user = userService.findUser(userId);
		
		item.setOwner(user);
		
		for (String dealId : dealsId) {
			Deal deal = new Deal();
			deal = dealService.findDeal(Long.parseLong(dealId));
			item.addDeal(deal);
		}

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(item);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in ItemService/insertItem : " + e.getMessage());
		} finally {
			session.close();
		}

		// est-ce que Hibernate valorise l'id de l'item après son insertion ?
		return item.getIdSwapObjet();

		// TODO
		// Ici, il faut cr�er le dossier sur le serveur pour d�poser sa photo

	}

	/**
	 * Suppression d'un Item en base.
	 * 
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteItem(@PathParam("id") long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression du item en base.
		try {

			Criteria itemList = session.createCriteria(Item.class).add(Restrictions.eqOrIsNull("id_item", id));
			for (Object item : itemList.list()) {
				session.delete(item);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in ItemService/deleteItem : " + e.getMessage());
		} finally {
			session.close();
		}

		// TODO
		// Suppression de l'image sur le serveur.

	}

	/**
	 * Update de la classe item.
	 */
	@POST
//	@Path("/update/{id}/{name}/{dateCreation}/{dateModification}/{description}/{cost}/{user}/{pic}/{deals}")
	@Path("/update")
	@Consumes({ "application/json" })
	public void updateItem(@FormParam("id") Long id, @FormParam("name") String name, @FormParam("dateCreation") String dateCreation,
			@FormParam("dateModification") String dateModification, @FormParam("description") String description,
			@FormParam("cost") String cost, @FormParam("user") String userId, @FormParam("pic") File pic,
			@FormParam("deals") Set<String> dealsId) {

		Item item = findItem(id);

		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.FRENCH);
		
		item.setName(name);
		try {
			item.setDateCreation(df.parse(dateCreation));
			item.setDateModification(df.parse(dateModification));
		} catch (ParseException pe) {
			logger.error("ParseException in ItemService/insertItem : " + pe.getMessage());
		}
		
		item.setDescription(description);
		item.setCost(Float.parseFloat(cost));
		
		User user = new User();
		user = userService.findUser(userId);
		
		item.setOwner(user);
		
		for (String dealId : dealsId) {
			Deal deal = new Deal();
			deal = dealService.findDeal(Long.parseLong(dealId));
			item.addDeal(deal);
		}

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(item);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in ItemService/updateItem : " + e.getMessage());
		} finally {
			session.close();
		}

		// TODO
		// Ici, il faut modifier la photo en fonction de l'id

	}

}