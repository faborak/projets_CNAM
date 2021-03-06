package com.myswap.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.myswap.exceptions.CommentInsertException;
import com.myswap.exceptions.CommentNotFoundException;
import com.myswap.exceptions.CommentUpdateException;
import com.myswap.exceptions.UserNotFoundException;
import com.myswap.models.Comment;
import com.myswap.models.User;
import com.myswap.utilitaires.Secured;

/**
 * Acces to database Comment.
 * 
 */
public class CommentService {

	// ======================================
    // =             Attributes             =
    // ======================================
	private Session session;
	
	private static Logger logger = Logger.getLogger(CommentService.class);

	private UserService userService = new UserService();

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ======================================
    // =           Business methods         =
    // ======================================
	public Comment findComment(long id) throws CommentNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Comment comment = null;
		try {
			Criteria criteria = session.createCriteria(Comment.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			comment = (Comment) criteria.uniqueResult();
		} catch (RuntimeException e) {
			logger.error("RuntimeException in CommentService/findComment : " + e.getMessage());
		} finally {
			session.close();
		}

		if (comment == null) {
			throw new CommentNotFoundException("no comment found for this id.");
		}

		return comment;
	}

	public List<Comment> findCommentsByUser(long id) throws CommentNotFoundException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Comment> comments = new ArrayList<Comment>();
		User user = null;
		try {
			Criteria criteria = session.createCriteria(User.class);

			criteria.add(Restrictions.eqOrIsNull("id", id));

			user = (User) criteria.uniqueResult();
			comments.addAll(user.getCommentsOnUser());
			comments.addAll(user.getCommentsWrited());

		} catch (RuntimeException e) {
			logger.error("RuntimeException in CommentService/findComment : " + e.getMessage());
		} finally {
			session.close();
		}

		if (comments.isEmpty()) {
			throw new CommentNotFoundException("no comment found for this id.");
		}

		return comments;
	}

	@Secured
	public Comment insertComment(String label, Integer mark, String notingId, String notedId)
			throws CommentInsertException {

		Comment comment = new Comment();
		comment.setLabel(label);

		if (mark == null) {
			throw new CommentInsertException("mark should not be null.");
		} else {
			comment.setMark(mark);
		}

		User noting = new User();
		try {
			noting = userService.findUser(Long.parseLong(notingId));
		} catch (UserNotFoundException e1) {
			throw new CommentInsertException("No user for this id.");
		}
		User noted = new User();
		try {
			noted = userService.findUser(Long.parseLong(notedId));
		} catch (UserNotFoundException e1) {
			throw new CommentInsertException("No user for this id.");
		}
		comment.setNoting(noting);
		comment.setNoted(noted);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(comment);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in CommentService/insertComment : " + e.getMessage());
		} finally {
			session.close();
		}

		return comment;

	}

	@Secured
	public void deleteComment(long id) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression du comment en base.
		try {

			Criteria commentList = session.createCriteria(Comment.class).add(Restrictions.eqOrIsNull("id", id));
			for (Object comment : commentList.list()) {
				session.delete(comment);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("RuntimeException in CommentService/deleteComment : " + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Secured
	public Comment updateComment(Long id, String label, Integer mark) throws CommentUpdateException {

		Comment comment;
		try {
			comment = findComment(id);
		} catch (CommentNotFoundException e1) {
			throw new CommentUpdateException("No comment for this Id.");
		}
		comment.setLabel(label);
		comment.setMark(mark);

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(comment);

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in CommentService/updateComment : " + e.getMessage());
		} finally {
			session.close();
		}

		return comment;

	}

}