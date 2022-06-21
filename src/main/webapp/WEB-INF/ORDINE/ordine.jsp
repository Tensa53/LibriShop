<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/stile.css">
<html>
<head>
    <title>Ordine</title>
    <%Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");%>
</head>
<body>

<%String msg = (String) request.getAttribute("msg");
    if (msg != null) {
%>

<p class="error">${msg}</p>

<%}%>

<form action="completa-ordine" method="post">
<div>
    <div id="container-pagamento left">
        <button formaction="scegli-pagamento">Scegli una modalità di pagamento</button>
        <%
            Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");
            if(pagamento != null){%>
              <p>Hai scelto la carta numero : <%=pagamento.getNumeroCarta()%></p>
            <%}%>
    </div>

    <div id="container-indirizzo left">
        <button formaction="scegli-indirizzo">Scegli un indirizzo di spedizione</button>
        <%
            Indirizzo indirizzo = (Indirizzo) request.getSession().getAttribute("indirizzo");
            if(indirizzo != null){%>
        <p>Hai scelto l'indirizzo Via : <%=indirizzo.getVia()%> <%=indirizzo.getCivico()%> <%=indirizzo.getCAP()%></p>
        <%}%>
    </div>
</div>

<div id="container-ordine">
  <p>Riepilogo ordine</p>
  <ul>
  <%for(Dettaglio d : carrello.getDettagli()){%>
      <li><%=d.getLibro().getTitolo()%> <%=d.getQuantita()%> <%=d.getPrezzo().toString()%>€</li>
  <%}%>
  </ul>
  <p>Totale : <%=carrello.getTotale().toString()%>€</p>
  <input type="submit" value="Completa ordine">
</div>
</form>
</body>
</html>