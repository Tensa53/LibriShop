<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 08/06/22
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<script>
    <jsp:include page="/script/quantita.js"></jsp:include>
</script>

<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<div id="container-carrello">

<%
    int i = 0;
    String quantita = "quantita";
    String isbn = "isbn";
    String prezzo = "prezzo";
    Carrello carrello = (Carrello) session.getAttribute("carrello");
    BigDecimal totaleCarrello = carrello.getTotale();

    int c = totaleCarrello.compareTo(new BigDecimal(0.00));

    if(c > 0){ %>
    <p class="error">Non puoi selezionare più di 5 copie di un libro</p>
        <% for(Dettaglio d : carrello.getDettagli()){%>
            <div>
            <form action="rimuovi-dal-carrello">
                <a href="http://localhost:8080/progettoTSW_war_exploded/page-libro?isbn=<%=d.getLibro().getISBN()%>"><img src="<%=d.getLibro().getFoto()%>"></a>
                <p><a href="http://localhost:8080/progettoTSW_war_exploded/page-libro?isbn=<%=d.getLibro().getISBN()%>"><%=d.getLibro().getTitolo()%></a></p>
                <input type="number" min="1" max="5" value="<%=d.getQuantita()%>" id="<%=quantita+i%>" name="<%=quantita+i%>" oninput="updateQuantita(<%=i%>)">
                <input type="hidden" id="<%=isbn+i%>" name="<%=isbn+i%>" value="<%=d.getLibro().getISBN()%>">
                <input type="hidden" id="i" name="i" value="<%=i%>">
                <p id="<%=prezzo+i%>" name="<%=prezzo+i%>"><%=d.getPrezzo().toString()%>€</p>
                <input type="submit" value="Rimuovi dal carrello">
            </form>
            </div>
        <%  i++;
        }%>
            <p id="totale">Totale : <%=carrello.getTotale().toString()%>€</p>
            <form action="conferma-ordine"  method="post">
                <input type="submit" value="Conferma Ordine">
            </form>
    <%}else{%>
            <p>vuoto</p>
    <%}%>

</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
