<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
<%--    <link rel="stylesheet" type="text/css" href="./css/stile.css">--%>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/sidenav.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-ricerca.css">

    <%
        List<Libro> libri = (List<Libro>) request.getAttribute("libri");
        List<Autore> autori = (List<Autore>) request.getSession().getAttribute("autori");
        List<Genere> generi = (List<Genere>) request.getSession().getAttribute("generi");
    %>

</head>
<body>

<script>
    <jsp:include page="/script/suggerimenti.js"></jsp:include>
</script>

<jsp:include page="INCLUDE/header.jsp"></jsp:include>

<jsp:include page="INCLUDE/nav.jsp"></jsp:include>

<div id="container-ricerca" class="center">
    <form action="ricerca">
        <input type="text" required autocomplete="off" name="ricerca" id="ricerca-ajax" list="titoli" onkeyup="getTitoli()">
        <datalist id="titoli">
        </datalist>
        <input type="submit" value="cerca">
    </form>
</div>

<div id="container-body-ricerca">

    <div id="container-filtri-ordine" class="sidenav">
        <div id="container-ordina-libri">
            Ordina per prezzo :
            <form action="filtra-libri">
                <select name="prezzo">
                    <option value="crescente">crescente</option>
                    <option value="decrescente">decrescente</option>
                </select>
                <%for (Libro l : libri){%>
                <input type="hidden" name="isbn-libro" value="<%=l.getISBN()%>">
                <%}%>
                <input type="submit" value="Ordina" name="ordinaPrezzo">
            </form>
        </div>

        <div id="container-filtro-libri">
            <p>Filtra per : </p>


            <form action="filtra-libri">
                <%for(Autore a : autori){%>
                <input type="radio" name="autore" value="<%=a.getCF()%>"><%=a.getNome()%><br>
                <%}%>
                <%for (Libro l : libri){%>
                <input type="hidden" name="isbn-libro" value="<%=l.getISBN()%>">
                <%}%>
                <input type="submit" name="filtraAutore" value="Filtra per Autore">
            </form>

            <br>

            <form action="filtra-libri">
                <%for(Genere g : generi){%>
                <input type="radio" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
                <%}%>
                <%for (Libro l : libri){%>
                <input type="hidden" name="isbn-libro" value="<%=l.getISBN()%>">
                <%}%>
                <input type="submit" name="filtraGenere" value="Filtra per Genere">
            </form>

        </div>
    </div>

    <div id="container-catalogo" class="main">
        <%
            for (Libro l:libri) { %>
        <figure class = "catalogo-item">
            <form action="page-libro">
                <input type="image" src="<%=l.getFoto()%>">
                <figcaption><input type="submit" value="<%=l.getTitolo()%>"></figcaption>
                <input type="hidden" name="isbn" value="<%=l.getISBN()%>">
                <%if(l.getSconto().compareTo(new BigDecimal(0.00)) == 1){%>
                <figcaption><span class="barrato"><%=l.getPrezzo().toString()%>€</span><span><%=l.getPrezzoScontato().toString()%>€</span></figcaption>
                <%}else {%>
                <figcaption><%=l.getPrezzo().toString()%>€</figcaption>
                <%}%>
            </form>
        </figure>
        <% } %>
    </div>

</div>

<jsp:include page="INCLUDE/footer.jsp"></jsp:include>

</body>
</html>