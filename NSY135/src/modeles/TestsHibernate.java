package modeles;

import java.util.List;

import modeles.webscope.Artiste;
import modeles.webscope.Film;
import modeles.webscope.Genre;
import modeles.webscope.Internaute;
import modeles.webscope.Pays;
import modeles.webscope.Role;
import modeles.webscope.Video;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestsHibernate {
	private static final Integer port = 3306;
	/**
	 * Objet Session de Hibernate
	 */
	private Session session;

	/**
	 * Constructeur sans connexion
	 */
	// public TestsHibernate() throws ClassNotFoundException {
	// /* On commence par "charger" le pilote MySQL */
	// Class.forName("com.mysql.jdbc.Driver");
	// }

	// public void connect(String server, String bd, String u, String p)
	// throws SQLException {
	// String url = "jdbc:mysql://" + server + ":" + port + "/" + bd;
	// connexion = DriverManager.getConnection(url, u, p);
	// }

	public void TestsHibernate() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public void insertPays(Pays pays) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
//		session.save(pays);
//		session.getTransaction().commit();
		Transaction tx = null;
        try {
                tx = session.beginTransaction();
                session.save(pays);
                tx.commit();
        } catch (RuntimeException e) {
                if (tx != null)
                        tx.rollback();
                throw e; // Gérer le message (log, affichage, etc.)
        } finally {
                session.close();
        }
	}

	public List<Pays> lecturePays() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Pays.class);
		return criteria.list();
	}

	public List<Pays> lecturePaysHQL() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Query query = session.createQuery("from Pays");
		return query.list();
	}
	
	public List<modeles.Film> listedefilms() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		Query query = session.createQuery("from Film");
		return query.list();
	}
	
	public List<Internaute> listeInternautes() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		Query query = session.createQuery("from Internaute");
		return query.list();
	}
	
	public void insertFilm() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        Film gravity = new Film();
        gravity.setTitre("Gravity");
        gravity.setAnnee(2013);

        Genre genre = new Genre();
        genre.setCode("Science-fiction");
        gravity.setGenre(genre);

        Artiste cuaron = new Artiste();
        cuaron.setPrenom("Alfonso");
        cuaron.setNom("Cuaron");

        // Le réalisateur de Gravity est Alfonso Cuaron
        //gravity.setRealisateur(cuaron);
        // Films réalisés par A. Curaon?
        for (Film f : cuaron.getFilmsRealises()) {
                System.out.println("Curaron a réalisé " + f.getTitre());
        }
        cuaron.addFilmsRealise(gravity);

        // Sauvegardons dans la base
        session.save(gravity);
        session.save(cuaron);
        session.getTransaction().commit();
	}
	
	public List<Artiste> afficheArtistes() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Artiste");
		return query.list();
	}
	
	public List<Role> afficheRoles() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Role");
		return query.list();        
	}
	
	public List<Video> afficheVideos() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Video");
		return query.list();        
	}
	
	public Film lectureFilmParCle() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        return (Film) session.load(Film.class, 8);        
	}
	
	public List<Film> rechercheHQLCriteria(String titre, String a, String g){
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
        session.beginTransaction();
        
        int annee = Integer.parseInt(a);
		Criteria criteria = session.createCriteria(Film.class);
//		criteria.add (Expression.eqOrIsNull("titre", titre));
//		criteria.add (Expression.eqOrIsNull("annee", annee));
//		criteria.add (Expression.eqOrIsNull("genre", g));
		
		return criteria.list();
        
//        HQL :
//        if (titre != "" && a != ""){
//            int annee = Integer.parseInt(a);
//			Query q = session.createQuery("from Film f where f.titre= :titre and f.annee = :annee");
//			q.setString ("titre", titre);
//			q.setInteger("annee", annee);
//			return q.list();
//        }	else if (titre != ""){
//			Query q = session.createQuery("from Film f where f.titre= :titre");
//			q.setString ("titre", titre);
//			return q.list();
//        } else if (a != ""){
//            int annee = Integer.parseInt(a);
//			Query q = session.createQuery("from Film f where f.annee = :annee");
//			q.setInteger("annee", annee);
//			return q.list();
////        } else if (g != ""){
////        	Genre genre = new Genre();
////            genre.setCode(g);
////			Query q = session.createQuery("from Film f where f.genre = :genre");
////			q.setString("titre", titre);
////			return q.list();
//        }
//		return null;
	}

}