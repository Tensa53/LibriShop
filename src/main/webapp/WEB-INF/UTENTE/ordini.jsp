<%@ page import="com.example.progettotsw.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Visualizza Ordini</title>
  <link rel="stylesheet" type="text/css" href="./css/header.css">
  <link rel="stylesheet" type="text/css" href="./css/navbar.css">
  <link rel="stylesheet" type="text/css" href="./css/footer.css">
  <link rel="stylesheet" type="text/css" href="./css/body-form.css">
  <link rel="stylesheet" type="text/css" href="./css/stile.css">
  <%List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
    String msgerror = (String) request.getAttribute("msgerror");
    String msgsuccess = (String) request.getAttribute("msgsuccess");
  %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<%if(msgerror != null){%>
  <p class="error">${msgerror}</p>
<%}%>


<%if(msgsuccess != null){%>
  <p class="success">${msgsuccess}</p>
<%}%>


<div id="container-ordini">


  <%if(ordini.size() > 0){
    for(Ordine o : ordini){%>

  <div class="container-ordini-item">
    <form method="post">
    <p>Ordine N. <%=o.getId()%></p>
      <input type="hidden" name="id" value="<%=o.getId()%>">
    <p>Data e Ora Ordine : <%=o.getDataOrdineReversedString()%></p>
    <p>Utente : <%=o.getUtente().getMail()%></p>
    <p>Indirizzo di spedizione : Via <%=o.getIndirizzo().getVia()%> <%=o.getIndirizzo().getCivico()%> <%=o.getIndirizzo().getCAP()%> <%=o.getIndirizzo().getCitta()%></p>
    <p>Pagamento con carta N. : <%=o.getPagamento().getNumeroCarta()%></p>
    <button formaction="annulla-ordine">Annulla Ordine</button>
    <p>Totale : <%=o.getTotale()%>€</p>
    <p>Dettagli Libri : </p>
    <div class="container-dettagli-ordine">
      <ul>
        <%for(Dettaglio d : o.getDettagli()) {%>
        <li><%=d.getLibro().getISBN()%> - <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%>€</li>
        <%}%>
      </ul>
    </div>
    </form>
  </div>

  <%}
  } else {%>
  <p>Non ci sono ordini</p>
  <%}%>

  <jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</div>
</body>
</html>
