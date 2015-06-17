package modele;

import java.util.List;

import modeles.exploitminiere.Equipe;
import modeles.exploitminiere.Gisement;
import modeles.exploitminiere.Modele;
import modeles.exploitminiere.Ouvrier;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MethodeAccueil {

	private Session session;
	
	public List<Gisement> lectureGisement() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Gisement> ListeGisement = null;
		try {
			Query q = session.createQuery("from Gisement");
			ListeGisement = q.list();
		} catch (RuntimeException e) {
		} finally {
			session.close();
		}
		return ListeGisement;
	}
	
	public List<Equipe> lectureEquipe() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Equipe> ListeEquipe = null;
		try {
			Query q = session.createQuery("select distinct equipe from Equipe as equipe join fetch equipe.manager join fetch equipe.gisementEnCours join fetch equipe.personnel");
			ListeEquipe = q.list();
		} catch (RuntimeException e) {
		} finally {
			session.close();	
		}
		return ListeEquipe;
	}
	
	public List<Ouvrier> lectureOuvrier() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Ouvrier> ListeOuvrier = null;
		try {
			Query q = session.createQuery("from Ouvrier as ouvrier join fetch ouvrier.equipe");
			ListeOuvrier = q.list();
		} catch (RuntimeException e) {
		} finally {
			session.close();
		}
		return ListeOuvrier;
	}
	
	public List<Modele> lectureModele() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Modele> ListeModele = null;
		try {
			Query q = session.createQuery("from Modele");
			ListeModele = q.list();
		} catch (RuntimeException e) {
		} finally {
			session.close();
		}
		return ListeModele;
	}
	
}
