package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.MethodeAccueil;
import modeles.exploitminiere.Equipe;
import modeles.exploitminiere.Gisement;
import modeles.exploitminiere.Modele;
import modeles.exploitminiere.Ouvrier;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/accueil")
public class accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SERVER = "localhost", BD = "exploitation_miniere",
			LOGIN = "Fabo", PASSWORD = "Celenus", VUES = "/vues/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public accueil() {
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
		MethodeAccueil methodeaccueil;
		// La vue par défaut
		String maVue = VUES + "index.jsp";
		methodeaccueil = new MethodeAccueil();
		
		try {
			if (action == null) {
				// Rien à faire
			} else if (action.equals("ListeGisements")) {
				List<Gisement> listeGisement = methodeaccueil.lectureGisement();
				request.setAttribute("listeGisement", listeGisement);
				maVue = VUES + "ListeGisements.jsp";
			}  else if (action.equals("ListeEquipes")) {
				List<Equipe> listeEquipe = methodeaccueil.lectureEquipe();
				request.setAttribute("ListeEquipe", listeEquipe);
				maVue = VUES + "ListeEquipes.jsp";
			}  else if (action.equals("ListeOuvriers")) {
				List<Ouvrier> listeOuvrier = methodeaccueil.lectureOuvrier();
				request.setAttribute("ListeOuvriers", listeOuvrier);
				maVue = VUES + "ListeOuvriers.jsp";
			}  else if (action.equals("ListeModeles")) {
				List<Modele> listeModele = methodeaccueil.lectureModele();
				request.setAttribute("listeModele", listeModele);
				maVue = VUES + "ListeModeles.jsp";
			}
		} catch (Exception e) {
			maVue = VUES + "exception.jsp";
			request.setAttribute("message", e.getMessage());
		}
		// On transmet à la vue
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
