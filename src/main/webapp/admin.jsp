<%@ page import="com.example.progettotsw.model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 08/06/22
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente != null && utente.isAmministratore() == true){%>
<div id="container-todo">
    <p>ADMIN</p>
</div>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>
