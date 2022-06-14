<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<script>
    <jsp:include page="/script/suggerimenti.js"></jsp:include>
</script>

<jsp:include page="header.jsp"></jsp:include>

<jsp:include page="nav.jsp"></jsp:include>

<div id="benvenuti" class="titoli center">
    <h2>Benvenuti sul nostro sito di vendita libri. Usa la barra per cercare uno specifico libro</h2>
</div>

<div id="container-ricerca" class="center">
    <form action="ricerca">
        <input type="text" required autocomplete="off" name="ricerca" id="ricerca-ajax" list="titoli" onkeyup="getTitoli()">
        <datalist id="titoli">
        </datalist>
        <input type="submit" value="cerca">
    </form>
</div>

<div class="titoli center">
    <%
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null){
    %>
            <h3>Benvenut* <%=utente.getUsername()%>. I nostri consigli : </h3>
    <%}else{%>
            <h3>Benvenut* guest. I nostri consigli : </h3>
    <%}%>
</div>

<div id="container-catalogo center">

    <% List<Libro> libri = (List<Libro>) request.getAttribute("libri");

        for (Libro l:libri) { %>
    <figure class = "catalogo-item">
        <form action="page-libro">
        <input type="image" src="<%=l.getFoto()%>">
        <figcaption><input type="submit" value="<%=l.getTitolo()%>"></figcaption>
        <input type="hidden" name="isbn" value="<%=l.getISBN()%>">
        <figcaption><%=l.getPrezzo()%>€</figcaption>
        </form>
    </figure>
    <% } %>
</div>

<!-- <a href="http://www.google.com">Visualizza altro</a> --->

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>