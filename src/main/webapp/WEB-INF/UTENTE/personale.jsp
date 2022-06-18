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
    <title>Personale</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<p><%=request.getServletPath().contains("/WEB-INF/UTENTE/")%></p>

<%Utente utente = (Utente) session.getAttribute("utente");
if (utente != null && utente.isAmministratore() == false){%>
    <form action="user-forward-redirect" method="post">
        <input name="iMieiDati" type="submit" value="I miei Dati">
        <input name="iMieiOrdini" type="submit" value="I miei Ordini">
        <input name="iMieiIndirizzi" type="submit" value="I miei Indirizzi">
        <input name="iMieiMetodiDiPagamento" type="submit" value="I Miei Metodi di Pagamento">
    </form>
<%}else{%>
<p>Non sei autorizzato ad accedere a questa pagina</p>
<%}%>
<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
