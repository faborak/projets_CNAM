package services;

import java.io.File;
import java.util.Date;
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

import models.Deal;
import models.Item;
import models.User;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
@Path("item")
public class ItemService {

	private Session session;
	private static Logger logger = Logger.getLogger(ItemService.class);

	@GET
	@Path("{id}")
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

			item = (Item) criteria.list();
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
	@Path("{name}/{dateCreation}/{dateModification}/{description}/{cost}/{user}/{pic}/{deals}")
	@Consumes({ "application/json" })
	public long insertItem(@PathParam("name") String name, @PathParam("dateCreation") Date dateCreation,
			@PathParam("dateModification") Date dateModification, @PathParam("description") String description, @PathParam("cost") Float cost,
			@PathParam("user") User user, @PathParam("pic") File pic, @PathParam("deals") Set<Deal> deals) {

		Item item = new Item();
		item.setName(name);
		item.setDateCreation(dateCreation);
		item.setDateModification(dateModification);
		item.setDescription(description);
		item.setCost(cost);
		item.setOwner(user);
		for (Deal d : deals) {
			item.addDeal(d);
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
	@Path("{id}")
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
	@Path("{id}/{name}/{dateCreation}/{dateModification}/{description}/{cost}/{user}/{pic}/{deals}")
	@Consumes({ "application/json" })
	public void updateItem(@PathParam("id") Long id, @PathParam("name") String name, @PathParam("dateCreation") Date dateCreation,
			@PathParam("dateModification") Date dateModification, @PathParam("description") String description, @PathParam("cost") Float cost,
			@PathParam("user") User user, @PathParam("pic") File pic, @PathParam("deals") Set<Deal> deals) {

		Item item = findItem(id);

		item.setName(name);
		item.setDateModification(dateModification);
		item.setDescription(description);
		item.setCost(cost);
		for (Deal d : deals) {
			item.addDeal(d);
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