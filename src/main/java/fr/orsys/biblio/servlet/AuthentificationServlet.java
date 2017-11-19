package fr.orsys.biblio.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet({"/authentification/login", "/authentification/validation" })
@SuppressWarnings("serial")
public class AuthentificationServlet extends HttpServlet {
	// pas d'attribut dans une servlet ....
	Properties users = new Properties();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String newUrl = null;
		
		switch (req.getServletPath()) {
		case "/authentification/login":
			newUrl = "/WEB-INF/jsp/login.jsp";break;
			
		case "/authentification/logout":
			req.getSession().removeAttribute("nom");
			req.getSession().invalidate();
			newUrl = "/WEB-INF/jsp/login.jsp";break;
			
		case "/authentification/validation":
			String login = req.getParameter("login");
			String password = req.getParameter("password");
			String value = users.getProperty(login);
			if(value != null && value.equals(password)) {
				req.getSession().setAttribute("nom", login);
				Date dateConnexion = new Date();
				req.getSession().setAttribute("dateConnexion", dateConnexion);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				System.out.println(sdf.format(dateConnexion));
				resp.sendRedirect(getServletContext().getContextPath() + "/welcome");
				return;
			}
			else {
				req.setAttribute("erreur", "Echec dans l'authentification (combinaison login/password incorrecte ...)");
				newUrl = "/WEB-INF/jsp/login.jsp";
			}
			break;
			
		}
		req.getRequestDispatcher(newUrl).forward(req, resp);
		
	}
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletConfig config = getServletConfig();
		String relativePathFile = config.getInitParameter("userFile");
		String realPathFile =getServletContext().getRealPath(relativePathFile);
		System.out.println("relativePathFile=" + relativePathFile);
		System.out.println("realPathFile=" + realPathFile);
		
		try {
			InputStream input = new FileInputStream(realPathFile);
			users.load(input);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
