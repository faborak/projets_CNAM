package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.Lectures;
import modeles.webscope.Film;

/**
 * Servlet implementation class Requeteur
 */
@WebServlet("/Requeteur")
public class Requeteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SERVER = "localhost", BD = "webscope",
			LOGIN = "Fabo", PASSWORD = "Celenus", VUES = "/vues/criteria/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Requeteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// La vue par défaut
		String maVue = VUES + "index.jsp";
		Lectures lecture = new Lectures();
		
		try {
			if (action == null) {
				// Rien à faire
			} else if (action.equals("LectureParCle")) {
				Film resultat = lecture.lectureFilmParCle();
				request.setAttribute("filmparcle", resultat);
//				maVue = VUES + "ListeParCle.jsp"; 
				maVue = VUES + "ListeParCleUniquementLeTitre.jsp";
			} else if (action.equals("LectureParTitre")) {
				List<Film> resultat = lecture.parTitre("Impitoyable"); //Film en dur, il faudrait faire une jsp de get
				request.setAttribute("filmpartitre", resultat);
				maVue = VUES + "ParTitre.jsp";		
			}else if (action.equals("RechercheHQLCriteria")) {
				maVue = VUES + "RechercheHQLCriteria.jsp";
			} else if (action.equals("ChargementParLot")) {
				List<Film> resultat = lecture.chargementParLot(); 
				request.setAttribute("films", resultat);
				maVue = VUES + "ChargementParLot.jsp";
			} else if (action.equals("DeleteGravity")) {
				lecture.deleteGravity(); 
				maVue = VUES + "suppressionReussie.jsp";
			}else if (action.equals("DynamiqueDesObjetsPersistants")) {
				lecture.DynamiqueDesObjetsPersistants(); 
				maVue = VUES + "insertionReussie.jsp";
			} else if (action.equals("InsertionEnCascade")) {
				lecture.insertionEnCascade(); 
				maVue = VUES + "insertionReussie.jsp";
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
		
//		Methode Post de RechercheHQLCriteria
		
		String Titre = "";
		String Annee = "";
		String Genre = "";
		try {
			Titre = request.getParameter("titre");
			Annee = request.getParameter("annee");
			Genre = request.getParameter("genre");
			Lectures lecture = new Lectures();
			List<Film> resultat = lecture.rechercheHQLCriteria(Titre, Annee,
					Genre);
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
