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


<jsp:include page="header.jsp"></jsp:include>

<jsp:include page="nav.jsp"></jsp:include>

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

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>