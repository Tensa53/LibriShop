<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagamenti</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <%List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");%>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">

<%for(Pagamento pagamento : pagamenti){%>
<form action="conferma-pagamento" method="post">
    <ul class="nobullet">
        <li class="bold">Numero Carta : <%=pagamento.getFormattedNumeroCarta()%></li>
        <li>Scadenza : <%=pagamento.getScadenzaString()%></li>
        <li>CCV : <%=pagamento.getCCV()%></li>
        <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta">
        <input type="submit" value="Conferma il seguente tipo di pagamento">
    </ul>
</form>
<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
