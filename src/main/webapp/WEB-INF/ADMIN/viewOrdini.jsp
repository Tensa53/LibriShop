<%@ page import="com.example.progettotsw.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza Ordini</title>
    <link rel="stylesheet" href="./css/stile.css">
    <%List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
        List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-filtro-utente">
    <form action="filtra-ordini-utente" class="right" method="post">
        <select name="mail-utente">
            <option value="Nessun Filtro">Non Applicare il filtro</option>
        <%for (Utente u : utenti){%>
            <option value="<%=u.getMail()%>"><%=u.getMail()%> - <%=u.getUsername()%> - <%=u.getNome() + " " + u.getCognome()%></option>
        <%}%>
        </select>
        <input type="submit" value="Filtra per Utente">
    </form>
</div>

<div id="container-ordini">


    <%if(ordini.size() > 0){
        for(Ordine o : ordini){%>

    <div class="container-ordini-item">
        <p>Ordine N. <%=o.getId()%></p>
        <p>Data Ordine : <%=o.getDataOrdineReversedString()%></p>
        <p>Utente : <%=o.getUtente().getMail()%></p>
        <p>Totale : <%=o.getTotale()%>€</p>
        <p>Dettagli Libri : </p>
        <div class="container-dettagli-ordine">
            <ul>
                <%for(Dettaglio d : o.getDettagli()) {%>
                <li><%=d.getLibro().getISBN()%> - <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%>€</li>
                <%}%>
            </ul>
        </div>
    </div>

    <%}
    } else {%>
    <p>Non ci sono ordini</p>
    <%}%>

    <jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</div>
</body>
</html>
