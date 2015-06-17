package modeles;

import java.util.List;

import modeles.webscope.Artiste;
import modeles.webscope.Film;
import modeles.webscope.Genre;
import modeles.webscope.Pays;
import modeles.webscope.Role;
import modeles.webscope.RoleId;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class Lectures {
	private static final Integer port = 3306;
	/**
	 * Objet Session de Hibernate
	 */
	private Session session;

	public Film lectureFilmParCle() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		// ici : on peut créer un objet Film et un Film1 avec la meme requete
		// session.get(Film.class, 8) et vérifier
		// qu'ils sont identiques
		return (Film) session.get(Film.class, 8);
	}

	public List<Film> parTitre(String titre) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
//		requete simple
//		Query q = session.createQuery("from Film f where f.titre= :titre");
//		Requete pour tester les strategies de chargement : join fetch qui charge le lien dans le graphe d'objets
		Query q = session.createQuery("select film from Film as film join fetch film.realisateur where film.titre= :titre");
		q.setString("titre", titre);
		return (List<Film>) q.list();
	}

	public List<Film> rechercheHQLCriteria(String titre, String a, String g) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		// Criteria
		int annee = 0;

		Criteria criteria = session.createCriteria(Film.class);
		if (titre != "") {
			criteria.add(Restrictions.eqOrIsNull("titre", titre));
		}
		if (a != "") {
			annee = Integer.parseInt(a);
			criteria.add(Restrictions.eqOrIsNull("annee", annee));
		}
		if (g != "") {
			Genre genre = new Genre();
			genre.setCode(g);
			criteria.add(Restrictions.eqOrIsNull("genre", genre));
		}

		return criteria.list();

		// HQL :
		// if (titre != "" && a != "") {
		// int annee = Integer.parseInt(a);
		// Query q = session
		// .createQuery("from Film f where f.titre= :titre and f.annee = :annee");
		// q.setString("titre", titre);
		// q.setInteger("annee", annee);
		// return q.list();
		// } else if (titre != "") {
		// Query q = session.createQuery("from Film f where f.titre= :titre");
		// q.setString("titre", titre);
		// return q.list();
		// } else if (a != "") {
		// int annee = Integer.parseInt(a);
		// Query q = session.createQuery("from Film f where f.annee = :annee");
		// q.setInteger("annee", annee);
		// return q.list();
		// } else if (g != "") {
		// Genre genre = new Genre();
		// genre.setCode(g);
		// Query q = session.createQuery("from Film f where f.genre = :genre");
		// q.setParameter("genre", genre);
		// return q.list();
		// }
		// return null;
	}
	
	public List<Film> chargementParLot() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
//		Chargement par lot : avec @BatchSize dans la classe Film, on reduit le chargement
//		Query q = session.createQuery("from Film"); 
//		Strategie par requete chargeant ce qu'on veut : left join fetch
		Query q = session.createQuery("from Film as film left join fetch film.roles as role"); 
		return (List<Film>) q.list();
	}
	
	public void DynamiqueDesObjetsPersistants() {
		Film gravity = new Film();
		gravity.setTitre("Gravity");
		gravity.setAnnee(2013);

		Genre genre = new Genre();
		genre.setCode("Science-fiction");
		gravity.setGenre(genre);
		gravity.setTitre("Gravity");
        gravity.setAnnee(2013);
        Pays USA = new Pays();
        USA.setCode("USA");
        gravity.setPays(USA);

		// Alfonso Cuaron a réalisé Gravity
		Artiste cuaron = new Artiste();
		cuaron.setPrenom("Alfonso");
		cuaron.setNom("Cuaron");
		cuaron.addFilmsRealise(gravity);

		// Ajoutez les acteurs
		Artiste clooney = new Artiste();
		clooney.setPrenom("Georges");
		clooney.setNom("Clooney");
		Role kowalski = new Role();
		RoleId test = new RoleId();
		kowalski.setPk(test);
		kowalski.setNom("M. Kowalski");
//		kowalski.setActeur(clooney);
//		kowalski.setFilm(gravity);
		clooney.addRole(kowalski);

		Artiste bullock = new Artiste();
		bullock.setPrenom("Sandra");
		bullock.setNom("Bullock");
		Role stone = new Role();
		RoleId test1 = new RoleId();
		stone.setPk(test1);
		stone.setNom("R. Stone");
//		stone.setActeur(bullock);
//		stone.setFilm(gravity);
		bullock.addRole(stone);
		
		gravity.addRole(kowalski);
		gravity.addRole(stone);

		// Sauvegardons dans la base
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
//			session.save(test);
			
			session.save(clooney);
			session.save(bullock);
			
			session.save(cuaron);
			session.save(gravity);
			
			test.setActeur(clooney);
			test.setFilm(gravity);
			
//			Note : pour sauvegarder les roles, il faut avoir sauvegarde Gravity et donc avoir un id
			session.save(kowalski);
			session.save(stone);	

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; // Gérer le message (log, affichage, etc.)
		} finally {
			session.close();
		}
	}
	
	public void deleteGravity(){
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Film.class)
	    .add(Restrictions.eqOrIsNull("titre", "Gravity"));
	    for (Object f : criteria.list()) {
	    	session.delete(f);
        }
	    
	    // suppression en chaine
		Criteria acteurs = session.createCriteria(Artiste.class)
		.add(Restrictions.or(
		Restrictions.eqOrIsNull("nom", "Cuaron"),
		Restrictions.eqOrIsNull("nom", "Bullock"),
		Restrictions.eqOrIsNull("nom", "Clooney")
		));
		for (Object f : acteurs.list()) {
	    	session.delete(f);
        }
		
		session.getTransaction().commit();
	}
	
	public void insertionEnCascade(){
		
		Film gravity = new Film();
		gravity.setTitre("Gravity");
		gravity.setAnnee(2013);
		Genre genre = new Genre();
		genre.setCode("Science-fiction");
		gravity.setGenre(genre);
		Pays USA = new Pays();
		USA.setCode("USA");
		gravity.setPays(USA);

		// Alfonso Cuaron a réalisé Gravity
		Artiste cuaron = new Artiste();
		cuaron.setPrenom("Alfonso");
		cuaron.setNom("Cuaron");
		cuaron.addFilmsRealise(gravity);
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();
			session.persist(gravity);
			
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