package fr.orsys.biblio.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebServlet("/welcome")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
/*		String nom = req.getParameter("nom");
		nom = nom.toUpperCase(); // simulation d'un traitement m�tier ...
		req.setAttribute("nom", nom);*/
		req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
		
	}

}
