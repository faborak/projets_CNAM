package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.MethodeGestion;
import modeles.exploitminiere.Bouzon;
import modeles.exploitminiere.Equipe;
import modeles.exploitminiere.Humain;
import modeles.exploitminiere.Hzk2;
import modeles.exploitminiere.Robot;

/**
 * Servlet implementation class Gestion
 */
@WebServlet(description = "Controleur de la partie gestion de l'application Mines.", urlPatterns = { "/gestion" })
public class gestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String VUES = "/vues/gestion/";
	
	String redirection = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Méthodes get permettant l'affichage des formulaires de recherche.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String maVue = null;
		
		try {
			if (action.equals("TrouverHumain")) {
				maVue = VUES + "RechercheHumain.jsp";
				redirection = "TrouverHumain";
			} else if (action.equals("TrouverRobot")) {
				maVue = VUES + "RechercheRobot.jsp";
				redirection = "TrouverRobot";
			} else if (action.equals("TrouverBouzon")) {
				maVue = VUES + "RechercheBouzon.jsp";
				redirection = "TrouverBouzon";
			} else if (action.equals("TrouverHzk2")) {
				maVue = VUES + "RechercheHzk2.jsp";
				redirection = "TrouverHzk2";
			} else if (action.equals("TrouverEquipe")) {
				maVue = VUES + "RechercheEquipe.jsp";
				redirection = "TrouverEquipe";
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Méthodes post de recherches des formulaires. Appelle les méthodes de calcul de couts.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String maVue = null;

		try {
			if (redirection == null) {
				maVue = VUES + "Redirection.jsp";
			} else if (redirection == "TrouverHumain") {
				String Nom = request.getParameter("nom");
				String SalaireMin = request.getParameter("salaire_min");
				String SalaireMax = request.getParameter("salaire_max");
				String Equipe = request.getParameter("equipe");
				MethodeGestion methodegestion = new MethodeGestion();
				List<Humain> resultat = methodegestion.trouverHumain(Nom,
						SalaireMin, SalaireMax, Equipe);
				if (resultat.size() != 0) {
					for (Humain humain : resultat) {
						humain.calculCout();
					}
					request.setAttribute("listehumains", resultat);
					maVue = VUES + "ResultatRechercheHumain.jsp";
				} else {
					maVue = VUES + "AucunResultat.jsp";
				}
			} else if (redirection == "TrouverRobot") {
				String Nom = request.getParameter("nom");
				String NumeroMin = request.getParameter("numero_min");
				String NumeroMax = request.getParameter("numero_max");
				String Modele = request.getParameter("modele");
				MethodeGestion methodegestion = new MethodeGestion();
				List<Robot> resultat = methodegestion.trouverRobot(Nom,
						NumeroMin, NumeroMax, Modele);
				if (resultat.size() != 0) {
					for (Robot robot : resultat) {
						robot.calculCout();
					}
					request.setAttribute("listerobots", resultat);
					maVue = VUES + "ResultatRechercheRobot.jsp";
				} else {
					maVue = VUES + "AucunResultat.jsp";
				}
			} else if (redirection == "TrouverBouzon") {
				String Nom = request.getParameter("nom");
				String RendementMin = request.getParameter("rendement_min");
				String RendementMax = request.getParameter("rendement_max");
				String MiseEnService = request
						.getParameter("date_mise_en_service");
				MethodeGestion methodegestion = new MethodeGestion();
				List<Bouzon> resultat = methodegestion.trouverBouzon(Nom,
						RendementMin, RendementMax, MiseEnService);
				if (resultat.size() != 0) {
					for (Bouzon bouzon : resultat) {
						bouzon.calculRevenu();
					}
					request.setAttribute("listebouzons", resultat);
					maVue = VUES + "ResultatRechercheBouzon.jsp";
				} else {
					maVue = VUES + "AucunResultat.jsp";
				}
			} else if (redirection == "TrouverHzk2") {
				String Nom = request.getParameter("nom");
				String RendementMin = request.getParameter("rendement_min");
				String RendementMax = request.getParameter("rendement_max");
				String MiseEnService = request
						.getParameter("date_mise_en_service");
				MethodeGestion methodegestion = new MethodeGestion();
				List<Hzk2> resultat = methodegestion.trouverHzk2(Nom,
						RendementMin, RendementMax, MiseEnService);
				if (resultat.size() != 0) {
					for (Hzk2 hzk2 : resultat) {
						hzk2.calculRevenu();
					}
					request.setAttribute("listehzk2", resultat);
					maVue = VUES + "ResultatRechercheHzk2.jsp";
				} else {
					maVue = VUES + "AucunResultat.jsp";
				}

			} else if (redirection == "TrouverEquipe") {
				String Nom = request.getParameter("nom");
				MethodeGestion methodegestion = new MethodeGestion();
				List<Equipe> resultat = methodegestion.trouverEquipe(Nom);
				if (resultat.size() != 0) {
					for (Equipe equipe : resultat) {
						equipe.calculCout();
					}
					request.setAttribute("listeequipes", resultat);
					maVue = VUES + "ResultatRechercheEquipe.jsp";
				} else {
					maVue = VUES + "AucunResultat.jsp";
				}
			}
		} catch (Exception e) {
			maVue = "/vues/exception.jsp";
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(maVue);
		dispatcher.forward(request, response);
	}

}
