<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Pagamenti</title>
  <%List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");
    String msg = (String) request.getAttribute("msg");
    String msgerrmod = (String) request.getAttribute("msgerrmod");
    String controllopagamento = (String) request.getAttribute("msgcontrollonumerocarta");
    String cartaP = (String) request.getAttribute("msgnumerocartaP");
    String scadenzaP = (String) request.getAttribute("msgscadenzaP");
    String ccvP = (String) request.getAttribute("msgccvP");%>
  <link rel="stylesheet" type="text/css" href="./css/header.css">
  <link rel="stylesheet" type="text/css" href="./css/navbar.css">
  <link rel="stylesheet" type="text/css" href="./css/footer.css">
  <link rel="stylesheet" type="text/css" href="./css/stile.css">
  <link rel="stylesheet" type="text/css" href="./css/body-form.css">
  <script src="./script/form/validateFormInserisciPagamento.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">

  <%if (msg != null){%>
  <h3 class="success center">${msg}</h3>
  <%}%>

  <%if(msgerrmod != null){%>
  <h3 class="error">
    <ul class="nobullet">
      <li><%=msgerrmod%></li>

      <%if(controllopagamento != null){%>
      <li><%=controllopagamento%></li>
      <%}%>

      <%if(cartaP != null){%>
      <li><%=cartaP%></li>
      <%}%>

      <%if(scadenzaP != null){%>
      <li><%=scadenzaP%></li>
      <%}%>

      <%if(ccvP != null){%>
      <li><%=ccvP%></li>
      <%}%>
    </ul>
  </h3>

  <%}%>

<fieldset>
  <legend>Inserisci un nuovo metodo di pagamento : <span class="error"><%if(controllopagamento != null){%><%=controllopagamento%><%}%></span></legend>
<form action="inserisci-carta" method="post" name="inserisci-pagamento" onsubmit="return validateFormInserisciPagamento()">
  <label for="numeroCarta">Numero Carta : </label><br>
  <p class="error" id="cartaP"><%if (cartaP != null){%><%=cartaP%><%}%></p>
  <input type="number" name="numeroCarta" id="numeroCarta" required><br>
  <label for="scadenza">Scadenza : <span><%if (scadenzaP != null){%><%=scadenzaP%><%}%></span></label><br>
  <input type="date" name="scadenza" id="scadenza" required><br>
  <label for="CCV">CCV </label><br>
  <p class="error" id="ccvP"><%if (ccvP != null){%><%=ccvP%><%}%></p>
  <input type="number" name="ccv" id="ccv" required><br><br>
  <input type="submit" value="Inserisci un nuovo metodo di Pagamento">
</form>
</fieldset>

<%for(Pagamento pagamento : pagamenti){%>
<div id="container-pagamento-utente">
<form method="post" name="modifica-pagamento">

  <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCartaF">
  <input type="hidden" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenzaF">
  <input type="hidden" value="<%=pagamento.getCCV()%>" name="CCVF">

  <ul class="nobullet">
    <li>Numero Carta : <%=pagamento.getFormattedNumeroCarta()%></li>
    <li>Scadenza : <%=pagamento.getScadenzaReversedString()%></li>
    <li>CCV : <%=pagamento.getCCV()%></li>
  </ul>

  <button formaction="cerca-pagamento-da-modificare">Modifica Pagamento</button>
  <button formaction="rimuovi-pagamento">Rimuovi Pagamento</button>
</form>
</div>
<%}%>

</div>

<script src="./script/dataMin.js"></script>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>