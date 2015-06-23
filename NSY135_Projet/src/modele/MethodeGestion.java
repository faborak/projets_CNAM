package modele;

import java.util.List;

import modeles.exploitminiere.Bouzon;
import modeles.exploitminiere.Equipe;
import modeles.exploitminiere.Humain;
import modeles.exploitminiere.Hzk2;
import modeles.exploitminiere.Modele;
import modeles.exploitminiere.Robot;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * La classe MethodeGestion utilise du Criteria.
 * 
 */
public class MethodeGestion {

	private Session session;

	public List<Humain> trouverHumain(String Nom, String SalaireMin,
			String SalaireMax, String Equipe) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Humain> humain = null;
		try {
			Criteria criteria = session.createCriteria(Humain.class);
			int SalaireMinNumerique = 0;
			int SalaireMaxNumerique = 0;

			if (Nom != "" && Nom != null) {
				criteria.add(Restrictions.eqOrIsNull("nom", Nom));
			}
			if (SalaireMin != "" && SalaireMin != null) {
				SalaireMinNumerique = Integer.parseInt(SalaireMin);
				criteria.add(Restrictions.ge("salaire", SalaireMinNumerique));
			}
			if (SalaireMax != "" && SalaireMax != null) {
				SalaireMaxNumerique = Integer.parseInt(SalaireMax);
				criteria.add(Restrictions.le("salaire", SalaireMaxNumerique));
			}

			if (Equipe != "" && Equipe != null) {
				Criteria requeteequipe = session.createCriteria(Equipe.class)
						.add(Restrictions.eqOrIsNull("nom", Equipe));
				Equipe listequipe = (Equipe) requeteequipe.uniqueResult();
				criteria.add(Restrictions.eqOrIsNull("equipe", listequipe));
			}

			// pour la pagination, on peut ajouter criteria.setMaxResults(10),
			// etc, et utiliser une clé de reprise à chaque appel.
			// inutilisé dans le cadre de ce projet.

			humain = (List<Humain>) criteria.list();
		} catch (RuntimeException e) {

		} finally {
			session.close();
		}
		return humain;
	}

	public List<Robot> trouverRobot(String Nom, String NumeroMin,
			String NumeroMax, String NomModele) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Robot> robot = null;
		try {
			Criteria criteria = session.createCriteria(Robot.class);

			if (Nom != "" && Nom != null) {
				criteria.add(Restrictions.eqOrIsNull("nom", Nom));
			}
			if (NumeroMin != "" && NumeroMin != null) {
				int NumeroMinNumerique = Integer.parseInt(NumeroMin);
				criteria.add(Restrictions
						.ge("numero_serie", NumeroMinNumerique));
			}
			if (NumeroMax != "" && NumeroMax != null) {
				int NumeroMaxNumerique = Integer.parseInt(NumeroMax);
				criteria.add(Restrictions
						.le("numero_serie", NumeroMaxNumerique));
			}
			if (NomModele != "" && NomModele != null) {
				Modele modele = new Modele();
				modele.setNom(NomModele);

				criteria.add(Restrictions.eqOrIsNull("modele", modele));
			}
			robot = (List<Robot>) criteria.list();
		} catch (RuntimeException e) {

		} finally {
			session.close();
		}
		return robot;
	}

	public List<Bouzon> trouverBouzon(String Nom, String RendementMin,
			String RendementMax, String MiseEnService) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Bouzon> bouzon = null;
		try {
			Criteria criteria = session.createCriteria(Bouzon.class);

			if (Nom != "" && Nom != null) {
				criteria.add(Restrictions.eqOrIsNull("nom", Nom));
			}
			if (RendementMin != "" && RendementMin != null) {
				float RendementMinNumerique = Float
						.valueOf(RendementMin.trim()).floatValue();
				criteria.add(Restrictions.ge("rendement_mensuel",
						RendementMinNumerique));
			}
			if (RendementMax != "" && RendementMax != null) {
				float RendementMaxNumerique = Float
						.valueOf(RendementMax.trim()).floatValue();
				criteria.add(Restrictions.le("rendement_mensuel",
						RendementMaxNumerique));
			}
			if (MiseEnService != "" && MiseEnService != null) {
				criteria.add(Restrictions.eq("date_mise_en_service",
						MiseEnService));
			}
			bouzon = (List<Bouzon>) criteria.list();
		} catch (RuntimeException e) {

		} finally {
			session.close();
		}
		return bouzon;
	}

	public List<Hzk2> trouverHzk2(String Nom, String RendementMin,
			String RendementMax, String MiseEnService) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Hzk2> Hzk2 = null;
		try {
			Criteria criteria = session.createCriteria(Hzk2.class);

			if (Nom != "" && Nom != null) {
				criteria.add(Restrictions.eqOrIsNull("nom", Nom));
			}
			if (RendementMin != "" && RendementMin != null) {
				float RendementMinNumerique = Float
						.valueOf(RendementMin.trim()).floatValue();
				criteria.add(Restrictions.ge("rendement_mensuel",
						RendementMinNumerique));
			}
			if (RendementMax != "" && RendementMax != null) {
				float RendementMaxNumerique = Float
						.valueOf(RendementMax.trim()).floatValue();
				criteria.add(Restrictions.le("rendement_mensuel",
						RendementMaxNumerique));
			}
			if (MiseEnService != "" && MiseEnService != null) {
				criteria.add(Restrictions.eq("date_mise_en_service",
						MiseEnService));
			}
			Hzk2 = (List<Hzk2>) criteria.list();
		} catch (RuntimeException e) {

		} finally {
			session.close();
		}
		return Hzk2;
	}

	public List<Equipe> trouverEquipe(String Nom) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Equipe> equipe = null;
		try {
			Criteria criteria = session.createCriteria(Equipe.class);
			criteria.add(Restrictions.eqOrIsNull("nom", Nom));
			criteria.setFetchMode("personnel", FetchMode.JOIN);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			equipe = (List<Equipe>) criteria.list();
		} catch (RuntimeException e) {

		} finally {
			session.close();
		}
		return equipe;
	}

}
