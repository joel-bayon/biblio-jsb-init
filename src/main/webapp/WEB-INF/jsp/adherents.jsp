
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<h2>Liste des adhérents</h2>

<table border="1">
	<tr><td>Id</td><td>Nom</td><td>Prénom</td><td>Email</td></tr>
	<c:forEach var="a" items="${adherents }">
		<tr><td><a href="editer?id=${a.id }">${a.id }</a></td><td>${a.nom }</td><td>${a.prenom }</td><td>${a.email }></td></tr>
	</c:forEach>
	
<tr align=center><td colspan="4"><a href="editer" >Nouveau adhérent</a></td></tr>
</table>
<hr/>
<%@include file="footer.jsp" %>

</body>
</html>