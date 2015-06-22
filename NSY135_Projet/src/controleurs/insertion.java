package controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.MethodeInsertion;

/**
 * Servlet implementation class insertion
 */
@WebServlet("/insertion")
public class insertion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String VUES = "/vues/insertion/";
	
	String redirection = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		// La vue par défaut
		String maVue = null;
		try {
			if (action == null) {
				maVue = "/vues/Exception.jsp";
			} else if (action.equals("InsertionCascade")) {
				MethodeInsertion methodeinsertion = new MethodeInsertion();
				methodeinsertion.insérerEnCascade();
				maVue = VUES + "InsertionCascade.jsp";
			} else if (action.equals("EffacerHumain")) {
				MethodeInsertion MI = new MethodeInsertion();
				MI.deleteInsertionDeTest();
				maVue = VUES + "EffacerCascade.jsp";
			}
		} catch (Exception e) {
			maVue = "/vues/exception.jsp";
			request.setAttribute("message", e.getMessage());
		}
		// On transmet à la vue
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String maVue = null;
//
//		try {
//			if (redirection == null){
//				maVue = VUES + "Redirection.jsp";
//			} else if (redirection == "TrouverHumain") {
//				String Nom = request.getParameter("nom");
//				String SalaireMin = request.getParameter("salaire_min");
//				String SalaireMax = request.getParameter("salaire_max");
//				String Equipe = request.getParameter("equipe");
//				MethodeInsertion methodeinsertion = new MethodeInsertion();
//				methodeinsertion.insérerHumainEnCascade(Nom,
//						SalaireMin, SalaireMax, Equipe);
//				maVue = VUES + "InsertionReussie.jsp";
//	}
//		} catch (Exception e) {
//			maVue = "/vues/exception.jsp";
//			request.setAttribute("message", e.getMessage());
//		}
//		RequestDispatcher dispatcher = getServletContext()
//				.getRequestDispatcher(maVue);
//		dispatcher.forward(request, response);
	}
}
