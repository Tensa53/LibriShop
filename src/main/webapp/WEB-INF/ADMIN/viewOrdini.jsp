<%@ page import="com.example.progettotsw.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza Ordini</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <%
        List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
        List<String> mailUtenti = (List<String>) request.getAttribute("mailUtenti");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">


    <div id="container-ordini">

        <%if (ordini.size() > 0) {%>

        <div id="container-filtro-utente" class="center">
            <form action="filtra-ordini-utente" method="post">
                <select name="mail-utente">
                    <option value="Nessun Filtro">Non Applicare il filtro</option>
                    <%for (String mail : mailUtenti) {%>
                    <option value="<%=mail%>"><%=mail%>
                    </option>
                    <%}%>
                </select>
                <input type="submit" value="Filtra per Utente">
            </form>
        </div>


        <%for (Ordine o : ordini) {%>

        <fieldset>
            <legend>Ordine N. <%=o.getId()%>
            </legend>
            <p>Data Ordine : <%=o.getDataOrdineReversedString()%>
            </p>
            <p>Utente : <%=o.getUtente().getMail()%>
            </p>
            <p>Indirizzo di spedizione : <%=o.getIndirizzo().getVia()%> <%=o.getIndirizzo().getCivico()%> <%=o.getIndirizzo().getCAP()%> <%=o.getIndirizzo().getCitta()%>
            </p>
            <p>Pagamento con carta N. : <%=o.getPagamento().getFormattedNumeroCarta()%>
            </p>
            <p>Totale : <%=o.getTotale()%>€</p>
            <p>Dettagli Libri : </p>
            <div class="container-dettagli-ordine">
                <ul class="nobullet text-left">
                    <%for (Dettaglio d : o.getDettagli()) {
                        String link = request.getContextPath() + "/page-libro?isbn=" + d.getLibro().getISBN();%>
                    <li><a href="<%=link%>"><img id="img-libro-ordine" src="<%=d.getLibro().getFoto()%>"> </a> ISBN : <%=d.getLibro().getISBN()%> - Titolo : <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%>€
                    </li>
                    <%}%>
                </ul>
            </div>
        </fieldset>

        <%
            }
        } else {
        %>
        <h3 class="center">Non ci sono ordini</h3>
        <%}%>

    </div>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
