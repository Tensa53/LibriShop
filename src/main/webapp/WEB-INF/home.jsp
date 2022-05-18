<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<div class="header">
    <h1><%= "LOGO e uno SLOGAN" %>
    <img src="./img/utente.png">
    <img src="./img/carrello.png">
</div>

<ul class="topnav">
    <li class="active"><a href="http://www.google.com">Home</a></li>
    <li><a href="http://www.google.com">Chi Siamo</a></li>
    <li><a href="http://www.google.com">Dove Siamo</a></li>
    <li><a href="http://www.google.com">Contattaci</a></li>
</ul>


<div class="benvenuti">
    <h2>Benvenuti sul nostro sito di vendita libri <br> Usa la barra per cercare uno specifico libro</h2>
</div>

<div class="ricerca">
    <form>
        <input type="text">
        <input type="submit" value="cerca">
    </form>

</div>

    <% List<Libro> libri = (List<Libro>) request.getAttribute("libri");

        for (Libro l:libri) { %>
    <div class = "catalogo">
        <img src="<%=l.getFoto()%>"/>
        <p><a href="https://www.google.com/search?q=regulators"><%=l.getTitolo()%></a></p>
        <p><%=l.getPrezzo()%>€</p>

    </div>
    <% } %>

<a class="right" href="http://www.google.com">Visualizza altro</a>

<div class="footer">
    <p> FOOTER </p>
</div>

</body>
</html>