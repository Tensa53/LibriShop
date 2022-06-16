<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="com.example.progettotsw.model.Indirizzo" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 15/06/22
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordine</title>
    <%Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");%>
</head>
<body>

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
