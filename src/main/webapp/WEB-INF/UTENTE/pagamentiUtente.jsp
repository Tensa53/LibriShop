<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Pagamenti</title>
  <%List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");%>
  <link rel="stylesheet" type="text/css" href="./css/header.css">
  <link rel="stylesheet" type="text/css" href="./css/navbar.css">
  <link rel="stylesheet" type="text/css" href="./css/footer.css">
  <link rel="stylesheet" type="text/css" href="./css/stile.css">
  <link rel="stylesheet" type="text/css" href="./css/body-form.css">
  <script src="./script/validateFormInserisciPagamento.js" type="text/javascript"></script>
  <script src="./script/validateFormModificaPagamento.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center">

<form action="inserisci-carta" method="post" name="inserisci-pagamento" onsubmit="return validateFormInserisciPagamento()">
  <label for="numeroCartar">Numero Carta : </label><br>
  <p id="cartaP"></p>
  <input type="number" name="numeroCartar" id="numeroCartar" required><br>
  <label for="scadenzar">Scadenza : </label><br>
  <input type="date" name="scadenzar" id="scadenzar" required><br>
  <label for="CCVr">CCV </label><br>
  <p id="ccvP"></p>
  <input type="number" name="ccvr" id="ccvr" required><br>
  <input type="submit" value="Inserisci un nuovo metodo di Pagamento">
</form>


<%for(Pagamento pagamento : pagamenti){%>
<div id="container-pagamento-utente">
<form method="post" name="modifica-pagamento">
  <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCartaF">
  <input type="hidden" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenzaF">
  <input type="hidden" value="<%=pagamento.getCCV()%>" name="CCVF">
  <label for="numeroCarta">Numero Carta : </label><br>
  <p id="cartaP2"></p>
  <input type="number" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta" id="numeroCarta" required><br>
  <label for="scadenza">Scadenza : </label><br>
  <input type="date" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenza" id="scadenza" required><br>
  <label for="CCV">CCV </label><br>
  <input type="number" value="<%=pagamento.getCCV()%>" name="ccv" id="ccv" required><br>
  <button onclick="return validateFormModificaPagamento()" formaction="conferma-modifiche-pagamento">Modifica Pagamento</button>
  <button formaction="rimuovi-pagamento">Rimuovi Pagamento</button>
  <button formaction="area-riservata">Annulla</button>
</form>
</div>
<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>