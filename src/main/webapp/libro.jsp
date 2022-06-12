<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="com.example.progettotsw.model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 08/06/22
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%Libro l = (Libro) request.getAttribute("libro");
      if(l == null){
    %>
        <title>Non in vendita</title>
    <%}else{%>
        <title><%=l.getTitolo()%></title>
    <%}%>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<script>
    function checkUtente(admin) {

        if (admin == true) {
            alert("Gli utenti amministratori non sono abilitati all'acquisto");
        }

    }
</script>

<%
    if(l ==  null){
%>
        <p>Il libro non è più in vendita</p>
<%}else{%>

<figure class = "catalogo-item">
    <form action="aggiungi-al-carrello">
        <img src="<%=l.getFoto()%>">
        <figcaption>Prezzo : <%=l.getPrezzo()%>€
            Quantità : <select name="quantita">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        <%
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            if(utente != null){
        %>
                <input type="submit" value="Aggiungi al carrello" onclick="checkUtente(<%=utente.isAmministratore()%>)">
        <%}else{%>
                <input type="submit" value="Aggiungi al carrello">
        <%}%>
        </figcaption>
        <input type="hidden" name="isbn" value="<%=l.getISBN()%>">
        <figcaption><%=l.getDescrizione()%></figcaption>
    </form>
</figure>

<%}%>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>
