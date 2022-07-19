<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Pagamento</title>
    <%Pagamento pagamento = (Pagamento) request.getAttribute("pagamento");%>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateFormModificaPagamento.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">
    <form method="post" name="modifica-pagamento">
        <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCartaF">
        <input type="hidden" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenzaF">
        <input type="hidden" value="<%=pagamento.getCCV()%>" name="CCVF">
        <label for="numeroCarta">Numero Carta : </label><br>
        <p class="error" id="cartaP2"></p>
        <input type="number" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta" id="numeroCarta" required><br>
        <label for="scadenza">Scadenza : </label><br>
        <input type="date" value="<%=pagamento.getScadenzaReversedString()%>" name="scadenza" id="scadenza"  required><br>
        <label for="CCV">CCV </label><br>
        <input type="number" value="<%=pagamento.getCCV()%>" name="ccv" id="ccv" required><br><br>
        <button onclick="return validateFormModificaPagamento()" formaction="conferma-modifiche-pagamento">Conferma Modifiche</button>
        <button formaction="area-riservata">Annulla</button>
    </form>
</div>

<script src="./script/dataMin.js"></script>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
