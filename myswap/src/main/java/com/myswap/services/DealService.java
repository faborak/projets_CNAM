package com.myswap.services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.exceptions.DealInsertException;
import com.myswap.exceptions.DealNotFoundException;
import com.myswap.exceptions.DealUpdateException;
import com.myswap.exceptions.ItemNotFoundException;
import com.myswap.exceptions.UserNotFoundException;
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

	public Deal findDeal(long id) throws DealNotFoundException {
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

		if (deal == null){
			throw new DealNotFoundException("No deal found for this id.");
		}
		
		return deal;

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	public Deal insertDeal(String initatorId, String proposedId,
			String statusString, Set<String> swapObjectsId) throws DealInsertException {

		Deal deal = new Deal();
		
		User initator = new User();
		try {
			initator = userService.findUser(initatorId);
		} catch (UserNotFoundException e1) {
			throw new DealInsertException("No user found for this Deal.");
		}
		User proposed = new User();
		try {
			proposed = userService.findUser(proposedId);
		} catch (UserNotFoundException e1) {
			throw new DealInsertException("No user found for this Deal.");
		}
		deal.setInitiator(initator);
		deal.setProposed(proposed);
		Status status = new Status();
		status.setCode(statusString);
		deal.setStatus(status);
		
		for (String soId : swapObjectsId) {
			SwapObject so = new SwapObject();
			try {
				so = itemService.findItem(Long.parseLong(soId));
			} catch (NumberFormatException | ItemNotFoundException e) {
				throw new DealInsertException("Item Id problem.");
			}
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
	public Deal updateDeal(Long id, String statusString,Set<String> swapObjectsId) throws DealUpdateException{

		Deal deal;
		try {
			deal = findDeal(id);
		} catch (DealNotFoundException e1) {
			throw new DealUpdateException("No deal found for this id");
		}
		Status status = new Status();
		status.setCode(statusString);
		deal.setStatus(status);
		for (String soId : swapObjectsId) {
			SwapObject so = new SwapObject();
			try {
				so = itemService.findItem(Long.parseLong(soId));
			} catch (NumberFormatException | ItemNotFoundException e) {
				throw new DealUpdateException("Item id problem.");
			}
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