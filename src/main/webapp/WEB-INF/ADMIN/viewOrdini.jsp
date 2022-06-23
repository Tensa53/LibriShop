<%@ page import="com.example.progettotsw.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza Ordini</title>
    <link rel="stylesheet" href="./css/stile.css">
    <%List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");%>
</head>
<body>

<div id="container-ordini">


    <%if(ordini.size() > 0){
        for(Ordine o : ordini){%>

    <div class="container-ordini-item">
        <p>Ordine N. <%=o.getId()%></p>
        <p>Data Ordine : <%=o.getDataOrdineReversedString()%></p>
        <p>Utente : <%=o.getUtente().getMail()%></p>
        <p>Totale : <%=o.getTotale()%></p>
        <div class="container-dettagli-ordine">
            <ul>
                <%for(Dettaglio d : o.getDettagli()) {%>
                <li><%=d.getLibro().getISBN()%> - <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%></li>
                <%}%>
            </ul>
        </div>
    </div>

    <%}
    } else {%>
    <p>Non ci sono ordini</p>
    <%}%>
</div>
</body>
</html>
