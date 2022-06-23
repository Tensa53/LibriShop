<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente != null && utente.isAmministratore() == true){%>
    <form action="admin-forward-redirect" class="center">
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
        <h4>Operazioni Autore</h4>
        <input name="insAutore" type="submit" value="Inserisci Autore">
        <input name="modDelAutore" type="submit" value="Modifica/Rimuovi Autore">
        <br>
        <h4>Operazioni Genere</h4>
        <input name="insGenere" type="submit" value="Inserisci Genere">
        <input name="modDelGenere" type="submit" value="Modifica/Rimuovi Genere">
    </form>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
