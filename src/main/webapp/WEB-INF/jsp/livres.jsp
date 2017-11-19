
<%@page import="java.util.List"%>
<%@page import="fr.orsys.biblio.entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>livres</title>
</head>
<body>
<%@include file="header.jsp" %>
<hr/>
<%@include file="menu.jsp" %>
<hr/>
<!-- corps de la page courante ... -->
<h2>Liste des livres</h2>

<table border="1">
	<tr><td>Id</td><td>Titre</td><td>Auteur</td><td>Parution</td></tr>
	<%for( Livre l : (List
			<Livre>)request.getAttribute("livres"))  { %>
		<tr><td><a href="editer?id=<%=l.getId()%>"> <%=l.getId() %></a></td><td><%=l.getTitre() %></td><td><%=l.getAuteur() %></td><td><%=l.getParution() %></td></tr>
	<%} %>
<tr align=center><td colspan="4"><a href="editer" >Nouveau livre</a></td></tr>
</table>
<hr/>
<%@include file="footer.jsp" %>

</body>
</html>