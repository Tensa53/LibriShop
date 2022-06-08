<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
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

<div id="container-ricerca" class="center">
    <form action="ricerca">
        <input type="text" name="ricerca" required>
        <input type="submit" value="cerca">
    </form>
</div>

<div id="container-catalogo">

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