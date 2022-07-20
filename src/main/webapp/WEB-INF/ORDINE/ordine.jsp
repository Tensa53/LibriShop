<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/stile.css">
<html>
<head>
    <title>Ordine</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <%Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        String msgerrpagind = (String) request.getAttribute("msgerrpagind");
        List<String> incompatibiliStr = (List<String>) request.getAttribute("incompatibili");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<%if (msgerrpagind != null) {%>

<h3 class="error center">${msgerrpagind}</h3>

<%}%>


<%if (incompatibiliStr != null){
    if (incompatibiliStr.size()>0){%>
    <h3 class="error center">
        <ul class="nobullet">
            <li>I seguenti libri sforano la quantità acquistabile. Sei pregato di modificare il carrello</li>
            <%for (String s : incompatibiliStr){%>
                <li><%=s%></li>
            <%}%>
        </ul>
    </h3>
<%}}%>

<div id="container-forms" class="center">

<form action="completa-ordine" method="post">
<div>
    <div id="container-pagamento">
        <button formaction="scegli-pagamento">Scegli una modalità di pagamento</button>
        <%
            Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");
            if(pagamento != null){%>
              <p>Hai scelto la carta numero : <%=pagamento.getFormattedNumeroCarta()%></p>
            <%}%>
    </div>
    <br>
    <div id="container-indirizzo">
        <button formaction="scegli-indirizzo">Scegli un indirizzo di spedizione</button>
        <%
            Indirizzo indirizzo = (Indirizzo) request.getSession().getAttribute("indirizzo");
            if(indirizzo != null){%>
        <p>Hai scelto l'indirizzo : <%=indirizzo.getVia()%> <%=indirizzo.getCivico()%> <%=indirizzo.getCAP()%></p>
        <%}%>
    </div>
</div>

<div id="container-ordine">
  <p>Riepilogo ordine</p>
  <ul class="nobullet">
  <%for(Dettaglio d : carrello.getDettagli()){
      String link = request.getContextPath() + "/page-libro?isbn=" + d.getLibro().getISBN();%>
      <li><a href="<%=link%>"><img id="img-libro-ordine" src="<%=d.getLibro().getFoto()%>"> </a> ISBN : <%=d.getLibro().getISBN()%> - Titolo : <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%>€
      </li>
  <%}%>
  </ul>
  <p>Totale : <%=carrello.getTotale().toString()%>€</p>
  <input type="submit" value="Completa ordine">
</div>
</form>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
