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
<jsp:include page="header.jsp"></jsp:include>

<jsp:include page="nav.jsp"></jsp:include>

<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente != null && utente.isAmministratore() == true){%>
    <form action="admin-forward-redirect">
        <input name="insLibro" type="submit" value="Inserisci Libro">
        <input name="modLibro" type="submit" value="Modifica Libro">
        <input name="delLibro" type="submit" value="Rimuovi Libro">
        <input name="insUtente" type="submit" value="Inserisci Utente">
        <input name="modUtente" type="submit" value="Modifica Utente">
        <input name="delUtente" type="submit" value="Rimuovi Utente">
        <input name="viewOrdini" type="submit" value="Visualizza Ordini">
        <input name="insAutore" type="submit" value="Inserisci Autore">
        <input name="modAutore" type="submit" value="Modifica Autore">
        <input name="delAutore" type="submit" value="Rimuovi Autore">
        <input name="insGenere" type="submit" value="Inserisci Genere">
        <input name="modGenere" type="submit" value="Modifica Genere">
        <input name="delGenere" type="submit" value="Rimuovi Genere">
    </form>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
