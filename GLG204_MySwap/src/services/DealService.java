package services;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import models.Deal;
import models.SwapObject;
import models.User;

/**
 * Classe effectuant le CRUD pour les objets de type Deal.
 * 
 */
@Path("deal")
public class DealService {

	private static Logger logger = Logger.getLogger(DealService.class);
	private Session session;

	@GET
	@Path("{id}")
	@Produces({ "application/json" })	
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

			deal = (Deal) criteria.list();
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
	public long insertDeal(User initator, User proposed, Integer status, Set<SwapObject> swapObjects) {

		Deal deal = new Deal();
		deal.setInitiator(initator);
		deal.setProposed(proposed);
		deal.setStatus(status);
		for (SwapObject so : swapObjects){
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
	public void updateDeal(Long id, Integer status, Set<SwapObject> swapObjects) {

		Deal deal = findDeal(id);
		deal.setStatus(status);
		for (SwapObject so : swapObjects){
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