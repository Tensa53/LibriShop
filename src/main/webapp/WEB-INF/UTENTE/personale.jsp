<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personale</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<h3 class="center">Area riservata</h3>

<%Utente utente = (Utente) session.getAttribute("utente");
if (utente != null && utente.isAmministratore() == false){%>
    <form action="user-forward-redirect" class="center" method="post">
        <input name="iMieiDati" type="submit" value="I miei Dati">
        <br><br>
        <input name="iMieiOrdini" type="submit" value="I miei Ordini">
        <br><br>
        <input name="iMieiIndirizzi" type="submit" value="I miei Indirizzi">
        <br><br>
        <input name="iMieiMetodiDiPagamento" type="submit" value="I Miei Metodi di Pagamento">
    </form>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>
<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
