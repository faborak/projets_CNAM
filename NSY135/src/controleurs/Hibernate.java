package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.TestsHibernate;
import modeles.webscope.Artiste;
import modeles.webscope.Internaute;
import modeles.webscope.Pays;
import modeles.webscope.Role;
import modeles.webscope.Video;
import modeles.webscope.Film;

/**
 * Servlet implementation class Hibernate
 */
@WebServlet("/Hibernate")
public class Hibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VUES = "/vues/hibernate/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hibernate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// Notre objet modèle: accès à MySQL
		// La vue par défaut
		String maVue = VUES + "index.jsp";
		try {
			if (action == null) {
				// Rien à faire
			} else if (action.equals("connexion")) {
				// Action + vue test de connexion
				maVue = VUES + "connexion.jsp";
			} else if (action.equals("insertion")) {
				// Action + vue test de connexion
				maVue = VUES + "insertion.jsp";
			} else if (action.equals("lecture")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Pays> resultat = tstHiber.lecturePays();
				request.setAttribute("listepays", resultat);
				maVue = VUES + "PaysA.jsp";
			} else if (action.equals("lectureHQL")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Pays> resultat = tstHiber.lecturePaysHQL();
				request.setAttribute("listepays", resultat);
				maVue = VUES + "PaysA.jsp";
			} else if (action.equals("ListeDeFilms")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<modeles.Film> resultat = tstHiber.listedefilms();
				request.setAttribute("listefilms", resultat);
				maVue = VUES + "ListeDeFilms.jsp";
			} else if (action.equals("ListeInternautes")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Internaute> resultat = tstHiber.listeInternautes();
				request.setAttribute("listeinternautes", resultat);
				maVue = VUES + "ListeInternautes.jsp";
			} else if (action.equals("InsertionGravity")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				tstHiber.insertFilm();
				maVue = VUES + "insertionReussie.jsp";
			} else if (action.equals("AffichageArtistes")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Artiste> resultat = tstHiber.afficheArtistes();
				request.setAttribute("listeartistes", resultat);
				maVue = VUES + "ListeDesArtistes.jsp";
			} else if (action.equals("AffichageRoles")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Role> resultat = tstHiber.afficheRoles();
				request.setAttribute("listeroles", resultat);
				maVue = VUES + "ListeDeRoles.jsp";
			} else if (action.equals("AffichageVideos")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				List<Video> resultat = tstHiber.afficheVideos();
				request.setAttribute("listevideos", resultat);
				maVue = VUES + "ListeDeVideos.jsp";
			} else if (action.equals("LectureParCle")) {
				// Action + vue test de connexion
				TestsHibernate tstHiber = new TestsHibernate();
				Film resultat = tstHiber.lectureFilmParCle();
				request.setAttribute("filmparcle", resultat);
				maVue = VUES + "ListeParCle.jsp";
			} else if (action.equals("RechercheHQLCriteria")) {
				// Action + vue test de connexion
				maVue = VUES + "RechercheHQLCriteria.jsp";
			}
		} catch (Exception e) {
			maVue = VUES + "exception.jsp";
			request.setAttribute("message", e.getMessage());
		}
		// On transmet à la vue
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(maVue);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String maVue = VUES;
		
		try {
//			methode post de l'insertion de Pays
//			String codePays = request.getParameter("codepays");
//			String nom = request.getParameter("nom");
//			String langue = request.getParameter("langue");
//			TestsHibernate tstHiber = new TestsHibernate();
//			Pays monPays = new Pays();
//			monPays.setCode(codePays);
//			monPays.setNom(nom);
//			monPays.setLangue(langue);
//			tstHiber.insertPays(monPays);
//			maVue = VUES + "insertionReussie.jsp";
			
			String Titre = request.getParameter("titre");
			String Annee = request.getParameter("annee");
			String Genre = request.getParameter("genre");
			TestsHibernate tstHiber = new TestsHibernate();
			List<Film> resultat = tstHiber.rechercheHQLCriteria(Titre, Annee, Genre);
			request.setAttribute("listefilms", resultat);
			maVue = VUES + "ResultatRehercheHQLCriteria.jsp";
			
		} catch (Exception e) {
			maVue = VUES + "exception.jsp";
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(maVue);
		dispatcher.forward(request, response);
	}

}
