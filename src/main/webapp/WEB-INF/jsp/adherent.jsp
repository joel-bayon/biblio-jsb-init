<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
 function control(form) {
	alert("je fais un traitement de surface ... !");
	return true;
}

</script>
<title>livres</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<%@include file="menu.jsp" %>
<hr/>
<!-- corps de la page courante ... -->
<h2>Edition 
	<c:choose>
		<c:when test="${adherent != null }">de l'adhérent ${adherent.id}</c:when>
		<c:otherwise>d'un nouvel adhérent</c:otherwise>
	</c:choose>
</h2>
<form action="action?id=${adherent.id }" method="get" onsubmit="return control(this);">
Nom &nbsp;&nbsp;: <input type="text" name="nom" value="${adherent.nom }"> <br/>
Prénom &nbsp;: <input type="text" name="prenom" value="${adherent.prenom }"><br/>
tel : <input type="text" name="tel" value="${adherent.tel }"><br/>
email : <input type="text" name="email" value="${adherent.email }"><br/>
<c:choose>
		<c:when test="${adherent != null }">
			<button type="submit" name="action" value="update">Modifier</button>
			<button type="submit" name="action" value="delete">Supprimer</button>
			<input type="hidden" name="id" value="${adherent.id }"> <br/>
		</c:when>
		<c:otherwise><button type="submit" name="action" value="create">Créer</button></c:otherwise>
	</c:choose>

<button type="submit" name="action" value="return">Retour</button>
</form>

<hr/>
<%@include file="footer.jsp" %>

</body>
</html>