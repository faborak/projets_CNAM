package com.myswap.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.models.Category;
import com.myswap.models.Deal;
import com.myswap.models.Item;
import com.myswap.models.ItemPicture;
import com.myswap.models.User;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
public class ItemService {

	private Session session;
	private static Logger logger = Logger.getLogger(ItemService.class);

	/**
	 * UserService.
	 */
	private UserService userService = new UserService();

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * DealService.
	 */
	private DealService dealService;

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}

	/**
	* Methode de recherche par Id.
	*/
	public Item findItem(long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Item item = null;
		try {
			Criteria criteria = session.createCriteria(Item.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			item = (Item) criteria.uniqueResult();
			// load the deals
			item.getDeals().size();
			// item.getOwner().getCommentsWriteds().size();
			// item.getOwner().getCommentsOnUser().size();
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
	public Item insertItem(String name, String dateCreation, String dateModification, String description, String cost,
			String userId, Set<String> dealsId) {

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

		// normalement, un nouvel objet n'est dans aucun Deal !
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

		// Image 
		//	addPicture(picName, picPath, item.getId);
			
		return item;
	}

	/**
	 * Suppression d'un Item en base.
	 * 
	 */
	public void deleteItem(long id) {
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

	}

	/**
	 * Update de la classe item.
	 */
	public Item updateItem(Long id, String name, String dateCreation, String dateModification, String description,
			String cost, String userId, Set<String> dealsId) {

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
		
		// Image 
		// addPicture(picName, picPath, itemId);

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
		
		return item;
		
	}
	
	/**
	 * Remont�e des cat�gories d'Item.
	 * 
	 */
	public List<Category> findCategories() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		List<Category> categories = new ArrayList<Category>();
		
		try {
			Criteria categoryList = session.createCriteria(Category.class);
			for (Object category : categoryList.list()) {
				categories.add((Category) category);
			}

		} catch (RuntimeException e) {
			logger.error("RuntimeException in ItemService/getCategories : " + e.getMessage());
		} finally {
			session.close();
		}

		return categories;
	}
	
	/**
	 * Remont�e des cat�gories d'Item.
	 * 
	 */
	public List<Item> findItemsByCriterias(String category, String costMin, String costMax, String name, long idReprise) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		List<Item> items = new ArrayList<Item>();
		
		try {
			Criteria criteria = session.createCriteria(Item.class);

			if (category != null && category != "") {
				criteria.add(Restrictions.eqOrIsNull("code", category));
			}
			
			if (costMin != null && costMin != "") {
				float costMinNumerique = Float.valueOf(costMin.trim()).floatValue();
				criteria.add(Restrictions.ge("cost",costMinNumerique));
			}
			
			if (costMax != null && costMax != "") {
				float costMaxNumerique = Float.valueOf(costMax.trim()).floatValue();
				criteria.add(Restrictions.le("cost", costMaxNumerique));
			}
			
			if (name != null && name != "") {
				criteria.add(Restrictions.eqOrIsNull("name", name));
			}
			
			// limite le nombre de r�sultat par page
			// criteria.setMaxResults(15);

			items = (List<Item>) criteria.list();

		} catch (RuntimeException e) {
			logger.error("RuntimeException in ItemService/getCategories : " + e.getMessage());
		} finally {
			session.close();
		}

		return items;
	}
	
	/**
	 * Remont�e des cat�gories d'Item.
	 * 
	 */
	public ItemPicture addPicture(String picName, String picPath, long itemId){
	
		Item item = new Item();
		item = findItem(itemId);
	
		ItemPicture itemPicture = new ItemPicture();
		itemPicture.setItemRepresented(item);
		itemPicture.setName(picName);
		itemPicture.setPath(picPath);
		
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(item);
			session.save(itemPicture);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in ItemService/updateItem : " + e.getMessage());
		} finally {
			session.close();
		}
		
		return itemPicture;
	}	
	
	/**
	 * Delete des images dans la base.
	 * 
	 */
	public void deletePicture(long pictureId){
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression de la picture en base (delete en cascade sur Item)
		try {

			Criteria itemPicList = session.createCriteria(ItemPicture.class).add(Restrictions.eqOrIsNull("id_picture", pictureId));
			for (Object itemPicture : itemPicList.list()) {
				session.delete(itemPicture);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in ItemService/deleteItem : " + e.getMessage());
		} finally {
			session.close();
		}
	}	

}