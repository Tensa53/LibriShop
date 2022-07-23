<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-carrello.css">
    <script src="./script/ajax/quantita.js"></script>
    <%
        int i = 0;
        String quantita = "quantita";
        String isbn = "isbn";
        String prezzo = "prezzo";
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        List<Libro> indisponibili = (List<Libro>) request.getAttribute("indisponibili");
        BigDecimal totaleCarrello = carrello.getTotale();
        List<Dettaglio> dettagli = carrello.getDettagli();
        int c = totaleCarrello.compareTo(new BigDecimal(0.00));
    %>
</head>
<body>

<jsp:include page="INCLUDE/header.jsp"></jsp:include>

<jsp:include page="INCLUDE/nav.jsp"></jsp:include>

<div id="container-carrello">

    <%if (indisponibili.size() > 0){%>
        <h3 class="error center">
        <ul class="nobullet">
            <li>I seguenti libri non sono attualmente disponibili o non più in vendita : </li>
            <%for (Libro l : indisponibili){%>
                <li><%=l.getISBN()%> - <%=l.getTitolo()%></li>
            <%}%>
        </ul>
        </h3>
    <%}%>

    <%if (c > 0) { %>
    <h3 class="error center">Non puoi selezionare più di 5 copie di un libro</h3>
    <% for (i = 0; i < dettagli.size(); i++) {
        Dettaglio d = dettagli.get(i);%>
    <div class="container-libro-item">
        <form action="rimuovi-dal-carrello">
            <div>
                <div class="left">
                    <a href="./page-libro?isbn=<%=d.getLibro().getISBN()%>"><img src="<%=d.getLibro().getFoto()%>"></a>
                </div>
                <div class="left container-info-libro">
                    <p id="titolo-libro"><a
                            href="./page-libro?isbn=<%=d.getLibro().getISBN()%>"><%=d.getLibro().getTitolo()%>
                    </a></p>
                    <p>Quantità : <input type="number" min="1" max="5" value="<%=d.getQuantita()%>" id="<%=quantita+i%>"
                                         name="<%=quantita+i%>" oninput="updateQuantita(<%=i%>)"></p>
                    <input type="hidden" id="<%=isbn+i%>" name="<%=isbn+i%>" value="<%=d.getLibro().getISBN()%>">
                    <input type="hidden" id="i" name="i" value="<%=i%>">
                    <input type="submit" value="Rimuovi dal carrello">
                </div>
                <div class="right">
                    <h3 class="prezzo-libri" id="<%=prezzo+i%>" name="<%=prezzo+i%>">Prezzo
                        : <%=d.getPrezzo().toString()%>€</h3>
                </div>
            </div>
        </form>
    </div>
    <br>
    <%}%>
    <h3 id="totale">Totale : <%=carrello.getTotale().toString()%>€</h3>
    <form action="conferma-ordine" method="post">
        <input type="submit" value="Conferma Ordine">
    </form>
    <%} else {%>
    <h3 class="center">Il carrello è vuoto</h3>
    <%}%>

</div>

<jsp:include page="INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
