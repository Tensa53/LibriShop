<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%Libro l = (Libro) request.getAttribute("libro");
    List<Genere> generi = (List<Genere>) request.getAttribute("genere");
    Autore autore = (Autore) request.getAttribute("autore");
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    GregorianCalendar oggi = new GregorianCalendar();

      if(l == null){
    %>
        <title>Non in vendita</title>
    <%}else{%>
        <title><%=l.getTitolo()%></title>
    <%}%>

    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-pageLibro.css">

</head>
<body>
<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

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
        <h3 class="center">Il libro non è più in vendita</h3>
<%}else{%>

<h1 class="center"><%=l.getTitolo()%></h1>

<figure class = "catalogo-item">
    <form action="aggiungi-al-carrello">
        <img class="left" src="<%=l.getFoto()%>">
        <%if(l.getSconto().compareTo(new BigDecimal(0.00)) == 1){%>
        <figcaption class="catalogo-item">Prezzo : <span class="barrato"><%=l.getPrezzo().toString()%>€</span><span><%=l.getPrezzoScontato().toString()%>€</span><br>
        <%}else{%>
            <figcaption class="catalogo-item">Prezzo : <%=l.getPrezzo().toString()%> € <br>
        <%}%>
        <%if (l.getDisponibilita() > 0){%>
            Quantità : <input type="number" name="quantita" value="1" min="1" max="5">
        <%}else{%>
            Quantità : Attualmente non acquistabile
        <%}%>
        <%
            if(utente != null){
                if(l.getDataPubblicazione().after(oggi) || l.getDisponibilita() == 0){
        %>
                    <input type="submit" value="Aggiungi al carrello" onclick="checkUtente(<%=utente.isAmministratore()%>)" disabled>
                <%}else{%>
                    <input type="submit" value="Aggiungi al carrello" onclick="checkUtente(<%=utente.isAmministratore()%>)">
        <%}}else{
                if(l.getDataPubblicazione().after(oggi)){%>
                    <input type="submit" value="Aggiungi al carrello" disabled>
                <%}else{%>
                    <input type="submit" value="Aggiungi al carrello">
        <%}}%>
                    <h4>Informazioni sul libro:</h4>
                Editore: <%=l.getEditore()%><br>
                Data pubblicazione: <%=l.getDataPubblicazioneString()%><br>
                ISBN: <%=l.getISBN()%>
        </figcaption>
        <input type="hidden" name="isbn" value="<%=l.getISBN()%>">
            <figcaption class="catalogo-item caption"><h2>Descrizione:</h2><%=l.getDescrizione()%></figcaption>
    </form>
</figure>

<%}%>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
