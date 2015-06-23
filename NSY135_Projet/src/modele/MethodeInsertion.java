package modele;

import modeles.exploitminiere.Equipe;
import modeles.exploitminiere.Gisement;
import modeles.exploitminiere.Humain;
import modeles.exploitminiere.Hzk2;
import modeles.exploitminiere.Modele;
import modeles.exploitminiere.Ouvrier;
import modeles.exploitminiere.Robot;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class MethodeInsertion {
	
	private Session session;

	/**
	 * Méthode de test d'insertion en cascade via cascade=CascadeType.PERSIST
	 *     
	 */
	public void insérerEnCascade(){
			
//	Cette méthode vise à créer plusieurs objets et les sauvegarder en cascade, via la méthode persist
//	il est notable que Robot est sauvegardé et cascade, et sauvegarde lui-même son Modèle en cascade.
		
		// Création de l'ensemble des éléments à rendre persistants.
			Equipe equipe4 = new Equipe();
			equipe4.setNom("Equipe 4");
			Hzk2 Hzk2 = new Hzk2();
			Hzk2.setNom("Hzk2 10");
			Hzk2.addequipesAffectees(equipe4);
			Hzk2.setDate_mise_en_service("2015-06-21");
			Hzk2.setRendement_mensuel((float) 0.5);
			Hzk2.setDensite((float) 0.1);
			Humain Humain11 = new Humain();
			Humain11.setNom("Humain 11");
			Humain11.setSalaire(2100);
			Humain11.setDate_affectation("2014-06-21");
			Robot Robot12 = new Robot();
			Robot12.setNom("Robot 12");
			Robot12.setNumero_serie(1234);
			Modele Modele9999 = new Modele();
			Modele9999.setNom("Modele 9999");
			Modele9999.setCout_exploitation_mensuel(1780);
			Modele9999.setDate_conception("2015-06-21");
			Robot12.setModele(Modele9999);
			Robot12.setDate_affectation("2014-06-21");
			Humain Humain13 = new Humain();
			Humain13.setNom("Humain 13");
			Humain13.setSalaire(2300);
			Humain13.setDate_affectation("2014-06-21");
			equipe4.addPersonnel(Humain11);
			equipe4.addPersonnel(Robot12);
			equipe4.addPersonnel(Humain13);
			equipe4.setManager(Humain11);

			try {
				SessionFactory sessionFactory = new Configuration().configure()
						.buildSessionFactory();
				session = sessionFactory.openSession();

				session.beginTransaction();
				
				// Utilisatin de la méthode persist.
				session.persist(equipe4);
				
				session.getTransaction().commit();
				
			} catch (RuntimeException e) {
				if (session.getTransaction() != null)
					session.getTransaction().rollback();
				throw e; 
			} finally {
				session.close();
			}
	}
	
	/**
	 * Méthode pour retour sur l'insertion en cascade.
	 *     
	 */
	public void deleteInsertionDeTest() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Suppression un à un des éléments créés plus haut.
		try {

			Criteria gisement = session.createCriteria(Gisement.class).add(
					Restrictions.eqOrIsNull("nom", "Hzk2 10"));
			for (Object f : gisement.list()) {
				session.delete(f);
			}

			Criteria ouvriers = session.createCriteria(Ouvrier.class).add(
					Restrictions.or(
							Restrictions.eqOrIsNull("nom", "Humain 11"),
							Restrictions.eqOrIsNull("nom", "Robot 12"),
							Restrictions.eqOrIsNull("nom", "Humain 13")));
			for (Object f : ouvriers.list()) {
				session.delete(f);
			}

			Criteria modele = session.createCriteria(Modele.class).add(
					Restrictions.eqOrIsNull("nom", "Modele 9999"));
			for (Object f : modele.list()) {
				session.delete(f);
			}

			Criteria equipe = session.createCriteria(Equipe.class).add(
					Restrictions.eqOrIsNull("nom", "Equipe 4"));
			for (Object f : equipe.list()) {
				session.delete(f);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}
}
