<%@ page import="com.example.progettotsw.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Visualizza Ordini</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<div id="container-forms" class="center">

<%if(msgerror != null){%>
  <h3 class="error">${msgerror}</h3>
<%}%>


<%if(msgsuccess != null){%>
  <h3 class="success">${msgsuccess}</h3>
<%}%>


  <%if(ordini.size() > 0){
    for(Ordine o : ordini){%>

  <fieldset>
    <legend>Ordine N. <%=o.getId()%></legend>
    <form method="post">
      <input type="hidden" name="id" value="<%=o.getId()%>">
    <p>Data e Ora Ordine : <%=o.getDataOrdineReversedString()%></p>
    <p>Utente : <%=o.getUtente().getMail()%></p>
    <p>Indirizzo di spedizione : <%=o.getIndirizzo().getVia()%> <%=o.getIndirizzo().getCivico()%> <%=o.getIndirizzo().getCAP()%> <%=o.getIndirizzo().getCitta()%></p>
    <p>Pagamento con carta N. : <%=o.getPagamento().getFormattedNumeroCarta()%></p>
    <button formaction="annulla-ordine">Annulla Ordine</button>
    <p>Totale : <%=o.getTotale()%>€</p>
    <p>Dettagli Libri : </p>
    <div class="container-dettagli-ordine">
      <%for (Dettaglio d : o.getDettagli()) {
        String link = request.getContextPath() + "/page-libro?isbn=" + d.getLibro().getISBN();%>
      <p class="text-left"><a href="<%=link%>"><img id="img-libro-ordine" src="<%=d.getLibro().getFoto()%>"></a> ISBN : <%=d.getLibro().getISBN()%> - Titolo : <%=d.getLibro().getTitolo()%> - Quantità : <%=d.getQuantita()%> - Prezzo : <%=d.getPrezzo()%>€</p>
      <%}%>
    </div>
    </form>
  </fieldset>

  <%}
  } else {%>
  <h3 class="center">Non ci sono ordini</h3>
  <%}%>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
