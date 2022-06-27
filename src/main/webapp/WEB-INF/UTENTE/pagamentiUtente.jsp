<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Pagamenti</title>
  <%List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");%>
  <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<form method="post">
  <label for="numeroCartar">Numero Carta : </label><br>
  <input type="text" name="numeroCartar" id="numeroCartar"><br>
  <label for="scadenzar">Scadenza : </label><br>
  <input type="date" name="scadenzar" id="scadenzar"><br>
  <label for="CCVr">CCV </label><br>
  <input type="number" min="100" max="999" name="ccvr" id="ccvr"><br>
  <input type="submit" value="Inserisci un nuovo metodo di Pagamento">
</form>


<%for(Pagamento pagamento : pagamenti){%>
<div id="container-pagamento-utente">
<form method="post">
  <label for="numeroCarta">Numero Carta : </label><br>
  <input type="text" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta" id="numeroCarta"><br>
  <label for="scadenza">Scadenza : </label><br>
  <input type="date" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenza" id="scadenza"><br>
  <label for="CCV">CCV </label><br>
  <input type="number" min="100" max="999" value="<%=pagamento.getCCV()%>" name="ccv" id="ccv"><br>
  <button formaction="conferma-modifiche-pagamento">Modifica Pagamento</button>
  <button formaction="rimuovi-pagamento">Rimuovi Pagamento</button>
  <button formaction="area-riservata">Annulla</button>
</form>
</div>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>