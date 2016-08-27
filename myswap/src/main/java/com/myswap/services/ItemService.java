package com.myswap.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.exceptions.AddPictureException;
import com.myswap.exceptions.DealNotFoundException;
import com.myswap.exceptions.ItemInsertException;
import com.myswap.exceptions.ItemNotFoundException;
import com.myswap.exceptions.ItemUpdateException;
import com.myswap.exceptions.UserNotFoundException;
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
	public Item findItem(long id) throws ItemNotFoundException {
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
		
		if (item == null){
			throw new ItemNotFoundException("no item for this id");
		}
		
		return item;
	}

	/**
	 * Insertion d'un nouvel Item.
	 * 
	 */
	public Item insertItem(String name, String description, String cost,
			String userId, Set<String> dealsId) throws ItemInsertException {

		Item item = new Item();

		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

		item.setName(name);
		Date date = new Date();
		item.setDateCreation(date);
		item.setDateModification(date);
		item.setDescription(description);
		item.setCost(Float.parseFloat(cost));

		User user = new User();
		try {
			user = userService.findUser(userId);
		} catch (UserNotFoundException e1) {
			throw new ItemInsertException("User id is not found in database.");
		}

		item.setOwner(user);

		// normalement, un nouvel objet n'est dans aucun Deal !
		for (String dealId : dealsId) {
			Deal deal = new Deal();
			try {
				deal = dealService.findDeal(Long.parseLong(dealId));
			} catch (NumberFormatException | DealNotFoundException e) {
				throw new ItemInsertException("Deal id problem.");
			}
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
	public Item updateItem(Long id, String name, String description,
			String cost, String userId, Set<String> dealsId) throws ItemUpdateException {

		Item item;
		try {
			item = findItem(id);
		} catch (ItemNotFoundException e1) {
			throw new ItemUpdateException("No item found for this id.");
		}

//		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.FRENCH);

		item.setName(name);
		item.setDateModification(new Date());
		item.setDescription(description);
		item.setCost(Float.parseFloat(cost));

		User user = new User();
		try {
			user = userService.findUser(userId);
		} catch (UserNotFoundException e1) {
			throw new ItemUpdateException("No user found for this id.");
		}

		item.setOwner(user);

		for (String dealId : dealsId) {
			Deal deal = new Deal();
			try {
				deal = dealService.findDeal(Long.parseLong(dealId));
			} catch (NumberFormatException | DealNotFoundException e) {
				throw new ItemUpdateException("Deal id problem.");
			}
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
		
		return item;
		
	}
	
	/**
	 * Remontée des catégories d'Item.
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
	 * Recherche par critères.
	 * 
	 */
	public List<Item> findItemsByCriterias(String category, String costMin, String costMax, String keyword, long idReprise, int maxResult) throws ItemNotFoundException{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		List<Item> items = new ArrayList<Item>();
		
		try {
			Criteria criteria = session.createCriteria(Item.class);

			Category categoryObject = new Category();
			categoryObject.setCode(category);
			
			if (category != null && category != "") {
				criteria.add(Restrictions.eqOrIsNull("category", categoryObject));
			}
			
			if (costMin != null && costMin != "") {
				float costMinNumerique = Float.valueOf(costMin.trim()).floatValue();
				criteria.add(Restrictions.ge("cost",costMinNumerique));
			}
			
			if (costMax != null && costMax != "") {
				float costMaxNumerique = Float.valueOf(costMax.trim()).floatValue();
				criteria.add(Restrictions.le("cost", costMaxNumerique));
			}
			
			if (keyword != null && keyword != "") {
				criteria.add(Restrictions.like("name", "%" + keyword + "%"));
			}
			
			// limite le nombre de résultat par page
			criteria.setMaxResults(maxResult);

			items = (List<Item>) criteria.list();
			for (Item item : items){
			  item.getDeals().size();
			}

		} catch (RuntimeException e) {
			logger.error("RuntimeException in ItemService/findItemsByCriterias : " + e.getMessage());
		} finally {
			session.close();
		}
		
		if (items == null) {
			throw new ItemNotFoundException("No item for theses criterias.");
		}

		return items;
	}
	
	public List<Item> findTendances() throws ItemNotFoundException {
		// TODO table de mémorisation des tendances
		List<Item> items = new ArrayList<Item>();
		
		items = findItemsByCriterias("Informatique", "","", "", 1, 3);
		
		return items;
	}
	
	/**
	 * Methode non appelee desormais.
	 */
	public List<Item> findProposed() throws ItemNotFoundException {
		List<Item> items = new ArrayList<Item>();
		
		items = findItemsByCriterias("Informatique", "","", "", 1, 9);
		
//		return items;
		return null;
	}
	
	/**
	 * Remontée des catégories d'Item.
	 * 
	 */
	public ItemPicture addPicture(String picName, String picPath, long itemId) throws AddPictureException {
	
		Item item = new Item();
		try {
			item = findItem(itemId);
		} catch (ItemNotFoundException e1) {
			throw new AddPictureException("No item found for this id.");
		}
		
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