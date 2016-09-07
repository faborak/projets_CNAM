package com.myswap.services;

import javax.ws.rs.NotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.exceptions.AddPictureException;
import com.myswap.exceptions.UserInsertException;
import com.myswap.exceptions.UserNotFoundException;
import com.myswap.exceptions.UserUpdateException;
import com.myswap.models.Account;
import com.myswap.models.Activity;
import com.myswap.models.Adress;
import com.myswap.models.Infos;
import com.myswap.models.Item;
import com.myswap.models.User;
import com.myswap.models.UserPicture;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	private Session session;

	/**
	 * 
	 * @param email
	 * @return
	 * @throws UserNotFoundException
	 */
	public User findUser(String email) throws UserNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = null;
		Account account = null;
		try {
			Criteria criteria = session.createCriteria(Account.class);

			criteria.add(Restrictions.eqOrIsNull("email", email));

			account = (Account) criteria.uniqueResult();

			if (account != null) {
				user = account.getUser();
			}

		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		if (user == null) {
			throw new UserNotFoundException("no user for this token");
		}

		return user;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 */
	public User findUser(long id) throws UserNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = null;
		try {
			Criteria criteria = session.createCriteria(User.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			user = (User) criteria.uniqueResult();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		if (user == null) {
			throw new UserNotFoundException("no user for this token");
		}

		return user;
	}

	/**
	 * 
	 * @param id
	 *            : id de l'item a rechercher.
	 * @return owner of the Item.
	 * @throws UserNotFoundException
	 */
	public User findUserByItem(long id) throws UserNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Item item = null;
		User user = null;
		try {
			Criteria criteria = session.createCriteria(Item.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			item = (Item) criteria.uniqueResult();
			user = item.getOwner();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in UserService/findUser : " + e.getMessage());
		} finally {
			session.close();
		}

		if (user == null) {
			throw new UserNotFoundException("no user for this token");
		}

		return user;
	}

	/**
	 * 
	 * @param name
	 * @param lastname
	 * @param email
	 * @param phoneNumber
	 * @param street
	 * @param state
	 * @param zipcode
	 * @param city
	 * @return
	 */
	public User insertUser(String name, String lastname, String email, String password, String phoneNumber,
			String street, String state, String zipcode, String city) throws UserInsertException {

		if (email == null || email.equals("") || password == null || password.equals("")){
			throw new UserInsertException("Email and password musn't be blank.");
		}
		
		
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
		user.setPassword(password);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.persist(user);

			account.setId(user.getId());
			adress.setId(user.getId());

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
	 * 
	 * @param id
	 */
	public void deleteUser(long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression du user en base, en cascade via les annotations PERSIST.
		try {

			Criteria userList = session.createCriteria(User.class).add(Restrictions.eqOrIsNull("id", id));
			Object user = userList.uniqueResult();
			session.delete(user);
			
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
	 * 
	 * @param name
	 * @param lastname
	 * @param email
	 * @param phoneNumber
	 * @param street
	 * @param state
	 * @param zipcode
	 * @param city
	 * @return
	 */
	public User updateUser(String name, String lastname, String email, boolean emailChecked, String phoneNumber,
			boolean phoneChecked, String street, String state, String zipcode, String city, String school, String job,
			String about) throws UserUpdateException {

		User user = null;
		try {
			user = findUser(email);
		} catch (UserNotFoundException e) {
			throw new UserUpdateException("Aucun user a updater");
		}

		Account account = user.getAccount();
		account.setPhoneNumber(phoneNumber);
		account.setPhoneChecked(phoneChecked);
		account.setEmail(email);
		account.setEmailChecked(emailChecked);
		;

		Infos infos = new Infos();
		infos.setSchool(school);
		infos.setJob(job);
		infos.setAbout(about);

		Adress adress = user.getAdress();
		adress.setStreet(street);
		adress.setState(state);
		adress.setZipcode(zipcode);
		adress.setCity(city);

		user.setName(name);
		user.setLastname(lastname);
		user.setAccount(account);
		user.setAdress(adress);
		user.setInfo(infos);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user);
			session.saveOrUpdate(user.getAccount());
			session.saveOrUpdate(user.getAdress());
			session.saveOrUpdate(user.getInfos());

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService/updateUser : " + e.getMessage());
			throw new UserUpdateException("RuntimeException in UserService/updateUser");
		} finally {
			session.close();
		}

		return user;
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws NotFoundException
	 */
	public User findUserByToken(String token) throws UserNotFoundException {
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

		if (user == null) {
			throw new UserNotFoundException("no user for this token");
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
	 * 
	 * @param picName
	 * @param picPath
	 * @param userId
	 * @return
	 */
	public UserPicture addPicture(String picPath, long userId) throws AddPictureException {

		User user = new User();
		try {
			user = findUser(userId);
		} catch (UserNotFoundException e1) {
			throw new AddPictureException("user not found for this picture");
		}

		UserPicture userPicture = new UserPicture();
		userPicture.setOwner(user);
		// userPicture.setName(picName);
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
			throw new AddPictureException("user not found for this picture");
		} finally {
			session.close();
		}

		return userPicture;
	}

	/**
	 * Delete des images dans la base.
	 * 
	 */
	public void deletePicture(long pictureId) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression de la picture en base (delete en cascade sur User)
		try {

			Criteria userPicList = session.createCriteria(UserPicture.class)
					.add(Restrictions.eqOrIsNull("id_picture", pictureId));
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