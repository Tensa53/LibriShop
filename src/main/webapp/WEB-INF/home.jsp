<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/body-home.css">
    <%Utente utente = (Utente) session.getAttribute("utente");%>
</head>
<body>

<script>
    <jsp:include page="/script/ajax/suggerimenti.js"></jsp:include>
</script>

<jsp:include page="INCLUDE/header.jsp"></jsp:include>

<jsp:include page="INCLUDE/nav.jsp"></jsp:include>

<div id="container-home">

        <div class="center">
            <%if(utente != null){%>
            <h2>Benvenut* <%=utente.getNome()%> <%=utente.getCognome()%> sul nostro sito.</h2>
            <%}else{%>
            <h2>Benvenut* guest sul nostro sito. </h2>
            <%}%>
        </div>

    <div id="container-ricerca" class="center">
        <form action="ricerca">
            <input type="text" required autocomplete="off" name="ricerca" id="ricerca-ajax" list="titoli" onkeyup="getTitoli()">
            <datalist id="titoli">
            </datalist>
            <input type="submit" id="button-ricerca" value="cerca">
        </form>
    </div>

    <div id="container-catalogo-table">

        <table>
            <tr>

                <% List<Libro> libri = (List<Libro>) request.getAttribute("libri");

                    for (Libro l : libri) { %>
                <td>
                    <figure class="catalogo-item">
                        <form action="page-libro">
                            <input type="image" src="<%=l.getFoto()%>">
                            <figcaption><input type="submit" title="<%=l.getTitolo()%>" value="<%=l.getTitolo()%>"></figcaption>
                            <input type="hidden" name="isbn" value="<%=l.getISBN()%>">
                            <%if (l.getSconto().compareTo(new BigDecimal(0.00)) == 1) {%>
                            <figcaption><span
                                    class="barrato"><%=l.getPrezzo().toString()%>€</span><span> <%=l.getPrezzoScontato().toString()%>€</span>
                            </figcaption>
                            <%} else {%>
                            <figcaption><%=l.getPrezzo().toString()%>€</figcaption>
                            <%}%>
                        </form>
                    </figure>
                </td>
                <% } %>

            </tr>
        </table>
    </div>
</div>

<jsp:include page="INCLUDE/footer.jsp"></jsp:include>

</body>
</html>