
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<jsp:include page="header.jsp"/>
<hr/>
<%@include file="menu.jsp" %>
<hr/>
<!-- corps de la page courante ... -->
<h2>Edition <c:choose >
	<c:when test="${livre == null }">d'un nouveau livre</c:when>
	<c:otherwise>du livre ${livre.id}</c:otherwise>
</c:choose></h2>
<form action="action?id=${livre.id }" method="get">

Titre &nbsp;&nbsp;: <input type="text" name="titre" value="${livre.titre }"> <br/>
Auteur &nbsp;: <input type="text" name="auteur" value="${livre.auteur }"><br/>
Parution : <input type="text" name="parution" value="${livre.parution }"><br/>
<c:choose>
	<c:when test="${livre != null }">
		<button type="submit" name="action" value="update">Modifier</button>
		<button type="submit" name="action" value="delete">Supprimer</button>
		<input type="hidden" name="id" value="${livre.id }"> <br/>
	</c:when>
	<c:otherwise><button type="submit" name="action" value="create">Créer</button></c:otherwise>
</c:choose>
<button type="submit" name="action" value="return">Retour</button>
</form>

<hr/>
<%@include file="footer.jsp" %>

</body>
</html>