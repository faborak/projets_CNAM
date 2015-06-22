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

/**
 * La classe M�thodeAccueil utilise du HQL.
 * 
 */
public class MethodeAccueil {

	private Session session;
	
	/**
	 * Affichage des gisements.
	 * Aucune info suppl�mentaire n�c�ssaire.
	 * 
	 */
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
	
	/**
	 * Affichage des Equipes.
	 * Tous les liens d'�quipe vers les autres objets sont n�c�ssaires en raison du chagrement Lazy.
	 * 
	 */
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
	
	/**
	 * Affichage des Ouvriers.
	 * Les liens vers l'�quipe est n�c�ssaire pour l'affichage voulu.
	 * 
	 */
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
	
	/**
	 * Affichage des Mod�les.
	 * Aucune info suppl�mentaire n�c�ssaire.
	 * 
	 */
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
