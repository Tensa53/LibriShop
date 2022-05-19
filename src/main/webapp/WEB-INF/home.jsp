<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <meta name="viewport" content="width=device-width, initial-scale = 1">
</head>
<body>


<jsp:include page="header.jsp"></jsp:include>

<jsp:include page="nav.jsp"></jsp:include>


<div id="benvenuti">
    <h2>Benvenuti sul nostro sito di vendita libri. Usa la barra per cercare uno specifico libro</h2>
</div>

<div id="ricerca">
    <form>
        <input type="text">
        <input type="submit" value="cerca">
    </form>
</div>

<div id="container-catalogo">

    <% List<Libro> libri = (List<Libro>) request.getAttribute("libri");

        for (Libro l:libri) { %>
    <figure class = "catalogo-item">
        <img src="<%=l.getFoto()%>"/>
        <figcaption><a href="https://www.google.com/search?q=regulators"><%=l.getTitolo()%></a></figcaption>
        <figcaption><%=l.getPrezzo()%>€</figcaption>
    </figure>
    <% } %>
</div>

<!-- <a href="http://www.google.com">Visualizza altro</a> --->

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>