package com.myswap.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.myswap.models.Swap;
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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ItemService.
	 */
	private ItemService itemService = new ItemService();

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public Deal findDeal(long id) throws DealNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Deal deal = null;
		try {
			Criteria criteria = session.createCriteria(Deal.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			deal = (Deal) criteria.uniqueResult();
			deal.getSwapObjects().size();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in DealService/findDeal : " + e.getMessage());
		} finally {
			session.close();
		}

		if (deal == null) {
			throw new DealNotFoundException("No deal found for this id.");
		}

		return deal;

	}

	public List<Deal> findDealByUser(long id) throws DealNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Deal> deals = new ArrayList<Deal>();
		User user = null;

		try {
			Criteria criteria = session.createCriteria(User.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			user = (User) criteria.uniqueResult();

			for (Deal d : user.getDealsInitator()) {
				d.getSwapObjects().size();
			}

			for (Deal d : user.getDealsProposed()) {
				d.getSwapObjects().size();
			}

			deals.addAll(user.getDealsInitator());
			deals.addAll(user.getDealsProposed());

		} catch (RuntimeException e) {
			logger.error("RuntimeException in DealService/findDeal : " + e.getMessage());
		} finally {
			session.close();
		}

		if (deals.size() == 0) {
			throw new DealNotFoundException("No deal found for this id.");
		}

		return deals;

	}

	/**
	 * Insertion d'un nouveau Deal.
	 * 
	 */
	public Deal insertDeal(String initatorId, String proposedId, Set<String> swapObjectsId) throws DealInsertException {

		Deal deal = new Deal();

		User initator = new User();
		try {
			initator = userService.findUser(Long.parseLong(initatorId));
		} catch (UserNotFoundException e1) {
			throw new DealInsertException("No user found for this Deal.");
		}
		User proposed = new User();
		try {
			proposed = userService.findUser(Long.parseLong(proposedId));
		} catch (UserNotFoundException e1) {
			throw new DealInsertException("No user found for this Deal.");
		}
		deal.setInitiator(initator);
		deal.setProposed(proposed);
		Status status = new Status();
		status.setCode("En attente d'acceptation par le proposed");
		deal.setStatus(status);
		deal.setDateCreation(new Date());
		deal.setDateModification(new Date());

		if (swapObjectsId == null) {
			throw new DealInsertException("no swap objets in the deal.");
		}

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

			Criteria dealList = session.createCriteria(Deal.class).add(Restrictions.eqOrIsNull("id", id));
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
	public Deal updateDeal(Long id, String statusString, Set<String> swapObjectsId) throws DealUpdateException {

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

	/**
	 * Update de la classe deal.
	 */
	public Deal modifyStatus(Long id, String statusString) throws DealUpdateException {

		Deal deal;
		try {
			deal = findDeal(id);
		} catch (DealNotFoundException e1) {
			throw new DealUpdateException("No deal found for this id");
		}

		
		// status' lifecycle
		Status status = new Status();
		if ((deal.getStatus().getCode().equals("En attente d'acceptation par l'initiateur")
				&& statusString.equals("En attente d'acceptation par le proposed"))
				|| (deal.getStatus().getCode().equals("En attente d'acceptation par l'initiateur")
						&& statusString.equals("Transaction acceptée par l'initiateur"))
				|| (deal.getStatus().getCode().equals("En attente d'acceptation par le proposed")
						&& statusString.equals("En attente d'acceptation par l'initiateur"))
				|| (deal.getStatus().getCode().equals("En attente d'acceptation par le proposed")
						&& statusString.equals("Transaction acceptée par le proposed"))
				|| (deal.getStatus().getCode().equals("Transaction acceptée par l'initiateur")
						&& statusString.equals("Transaction validée"))
				|| (deal.getStatus().getCode().equals("Transaction acceptée par le proposed")
						&& statusString.equals("Transaction validée"))
				|| (deal.getStatus().getCode().equals("En attente d'acceptation par l'initiateur")
						&& statusString.equals("Transaction refusée par l'initiateur"))
				|| (deal.getStatus().getCode().equals("En attente d'acceptation par le proposed")
						&& statusString.equals("Transaction refusée par le proposed"))
				|| (deal.getStatus().getCode().equals("Transaction acceptée par l'initiateur")
						&& statusString.equals("Transaction refusée par l'initiateur"))
				|| (deal.getStatus().getCode().equals("Transaction acceptée par le porposed")
						&& statusString.equals("TTransaction refusée par le proposed"))) {
			
			status.setCode(statusString);
			deal.setStatus(status);
		} else {
			throw new DealUpdateException("Status problem");
		}

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			// swap created if valided by both sides
			if (status.equals("Transaction validée")) {
				Swap swap = new Swap();
				swap.setDeal(deal);
				deal.setSwap(swap);
				session.save(swap);
			}
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