package com.myswap.services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.models.Deal;
import com.myswap.models.Status;
import com.myswap.models.SwapObject;
import com.myswap.models.User;

/**
 * Classe effectuant le CRUD pour les objets de type Deal.
 * 
 */
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

	public Deal findDeal(long id) {
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
			// load the items
			deal.getSwapObjects().size();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in DealService/findDeal : " + e.getMessage());
		} finally {
			session.close();
		}

		return deal;

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	public Deal insertDeal(String initatorId, String proposedId,
			Status status, Set<String> swapObjectsId) {

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

		return deal;
	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
	 * 
	 */
	public void deleteDeal(long id) {

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
	public Deal updateDeal(Long id, Status status,Set<String> swapObjectsId) {

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

		return deal;
	}

}