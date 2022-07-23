<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente != null && utente.isAmministratore() == true){%>
    <form action="admin-forward-redirect" class="center" method="post">
        <h4>Operazioni Libro</h4>
        <input name="insLibro" type="submit" value="Inserisci Libro">
        <input name="modDelLibro" type="submit" value="Modifica/Rimuovi Libro">
        <br>
        <h4>Operazioni Utente</h4>
        <input name="insUtente" type="submit" value="Inserisci Utente">
        <input name="modDelUtente" type="submit" value="Modifica/Rimuovi Utente">
        <br>
        <h4>Operazioni Ordini</h4>
        <input name="viewOrdini" type="submit" value="Visualizza Ordini">
        <br>
        <h4>Operazioni Autore e Genere</h4>
        <input name="opsAutoreGenere" type="submit" value="Gestione Autore e Genere">
    </form>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
