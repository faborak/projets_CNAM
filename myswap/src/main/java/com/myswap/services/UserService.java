package com.myswap.services;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.models.Account;
import com.myswap.models.Activity;
import com.myswap.models.Adress;
import com.myswap.models.User;
import com.myswap.models.UserPicture;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	private Session session;

	public User findUser(String email) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = null;
		Account account = null;
		try {
			Criteria criteria = session.createCriteria(Account.class);

			criteria.add(Restrictions.eqOrIsNull("email", email));

			account = (Account) criteria.uniqueResult();
			user = account.getUser();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		return user;
	}

	public User findUser(long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = null;
		try {
			Criteria criteria = session.createCriteria(User.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			user = (User) criteria.uniqueResult();
			user.getWholeOfItems().size();
			user.getCommentsOnUser().size();
			user.getCommentsWriteds().size();
			user.getDealsInitator().size();
			user.getDealsProposed().size();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		return user;
	}

	/**
	 * M�thode de test d'insertion en cascade via cascade=CascadeType.PERSIST
	 * 
	 */
	public User insertUser(String name, String lastname, String email, String phoneNumber, String street, String state,
			String zipcode, String city) {

		Account account = new Account();
		account.setPhoneNumber(phoneNumber);
		account.setEmail(email);

		Adress adress = new Adress();
		adress.setStreet(street);
		adress.setState(state);
		adress.setZipcode(zipcode);
		adress.setCity(city);

		User user = new User();
		user.setName(name);
		user.setLastname(lastname);
		user.setAccount(account);
		user.setAdress(adress);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(user);

			Criteria criteria = session.createCriteria(User.class);

			criteria.add(Restrictions.eqOrIsNull("email", email));
			User usertemp = (User) criteria.list();

			account.setId(usertemp.getId());
			adress.setId(usertemp.getId());

			session.save(account);
			session.save(adress);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService/insertUser : " + e.getMessage());
		} finally {
			session.close();
		}

		return user;

	}

	/**
	 * M�thode pour retour sur l'insertion en cascade.
	 * 
	 */
	public void deleteUser(long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression du user en base.
		try {

			Criteria userList = session.createCriteria(User.class).add(Restrictions.eqOrIsNull("id_user", id));
			for (Object user : userList.list()) {
				session.delete(user);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in UserService/deleteUser : " + e.getMessage());
		} finally {
			session.close();
		}

		// Suppression de l'adresse en base.
		try {

			Criteria adressList = session.createCriteria(Adress.class).add(Restrictions.eqOrIsNull("id_user", id));
			for (Object adress : adressList.list()) {
				session.delete(adress);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in UserService/deleteUser : " + e.getMessage());
		} finally {
			session.close();
		}

		// Suppression de l'account en base.
		try {

			Criteria accountList = session.createCriteria(Account.class).add(Restrictions.eqOrIsNull("id_user", id));
			for (Object account : accountList.list()) {
				session.delete(account);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in UserService/deleteUser : " + e.getMessage());
		} finally {
			session.close();
		}

	}

	/**
	 * Update de la classe user. TODO : un update en cascade via la classe User
	 * sur account et adress est possible.
	 */
	public User updateUser(String name, String lastname, String email, String phoneNumber, String street, String state,
			String zipcode, String city) {

		User user = findUser(email);

		Account account = user.getAccount();
		account.setPhoneNumber(phoneNumber);
		account.setEmail(email);

		Adress adress = user.getAdress();
		adress.setStreet(street);
		adress.setState(state);
		adress.setZipcode(zipcode);
		adress.setCity(city);

		user.setName(name);
		user.setLastname(lastname);
		user.setAccount(account);
		user.setAdress(adress);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user);
			session.saveOrUpdate(user.getAccount());
			session.saveOrUpdate(user.getAdress());

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService/updateUser : " + e.getMessage());
		} finally {
			session.close();
		}
		
		return user;

	}

	public User findUserByToken(String token) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = null;
		Activity activity = null;
		try {
			Criteria criteria = session.createCriteria(Activity.class);

			criteria.add(Restrictions.eqOrIsNull("token", token));

			activity = (Activity) criteria.uniqueResult();
			user = activity.getUser();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		return user;
	}

	/**
	 * Update of the user last activity in database.
	 * 
	 * @param user
	 * @return
	 */
	public User updateActivity(User user) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user);
			session.saveOrUpdate(user.getActivity());

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService.updateActivity : " + e.getMessage());
		} finally {
			session.close();
		}

		return user;
	}
	
	/**
	 * Remont�e des cat�gories d'Item.
	 * 
	 */
	public UserPicture addPicture(String picName, String picPath, long userId){
	
		User user = new User();
		user = findUser(userId);
	
		UserPicture userPicture = new UserPicture();
		userPicture.setOwner(user);
		userPicture.setName(picName);
		userPicture.setPath(picPath);
		
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user);
			session.save(userPicture);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService/updateUser : " + e.getMessage());
		} finally {
			session.close();
		}
		
		return userPicture;
	}	
	
	/**
	 * Delete des images dans la base.
	 * 
	 */
	public void deletePicture(long pictureId){
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression de la picture en base (delete en cascade sur User)
		try {

			Criteria userPicList = session.createCriteria(UserPicture.class).add(Restrictions.eqOrIsNull("id_picture", pictureId));
			for (Object userPicture : userPicList.list()) {
				session.delete(userPicture);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in UserService/deleteUser : " + e.getMessage());
		} finally {
			session.close();
		}
	}	

}