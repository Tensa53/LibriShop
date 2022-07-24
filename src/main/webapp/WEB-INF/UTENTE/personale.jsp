<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Personale</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center full-height">
    <h3>Area riservata</h3>

    <form action="user-forward-redirect" method="post">
        <input name="iMieiDati" type="submit" value="I Miei Dati">
        <br><br>
        <input name="iMieiOrdini" type="submit" value="I Miei Ordini">
        <br><br>
        <input name="iMieiIndirizzi" type="submit" value="I Miei Indirizzi">
        <br><br>
        <input name="iMieiMetodiDiPagamento" type="submit" value="I Miei Metodi Di Pagamento">
    </form>
</div>


<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
