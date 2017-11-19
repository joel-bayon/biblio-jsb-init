package fr.orsys.biblio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.orsys.biblio.entity.Adherent;
import fr.orsys.biblio.service.Bibliotheque;

/**
 * Servlet implementation class LivreServlet
 */
@WebServlet(name="adherentServlet", urlPatterns={ "/adherent/lister", "/adherent/editer", "/adherent/action" })
public class AdherentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bibliotheque biblio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdherentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		String nextView = "/WEB-INF/jsp/";
		Integer id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : 0;
		switch (url) {
		case "/adherent/lister":
			//request.setAttribute("livres", biblio.listerLivre());
			request.setAttribute("adherents", biblio.getAdherentDao().findAll());
			nextView += "adherents.jsp";
			break;
		case "/adherent/editer":
			
			request.setAttribute("adherent", biblio.getAdherentDao().findOne(id));
			nextView += "adherent.jsp";
					
			break;
		case "/adherent/action":
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			Adherent a = new Adherent(nom, prenom, tel, email);
			if("update".equals(request.getParameter("action"))) {
				a.setId(id);
				biblio.getAdherentDao().update(a);
			}
			if("delete".equals(request.getParameter("action"))) {
			
				biblio.getAdherentDao().delete(id);
			}
			if("create".equals(request.getParameter("action"))) {
				
				biblio.ajouterAdherent(a);
			}
			
			nextView = "lister";
			break;

		default:
	
		}
		request.getRequestDispatcher(nextView).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		biblio = (Bibliotheque)getServletContext().getAttribute("biblio");
	}

}
