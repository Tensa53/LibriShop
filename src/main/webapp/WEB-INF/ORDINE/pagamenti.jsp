<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pagamenti</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<%if(pagamenti.size() > 0){
    for(Pagamento pagamento : pagamenti){%>
    <form action="conferma-pagamento" method="post">
        <ul class="nobullet">
            <li class="bold">Numero Carta : <%=pagamento.getFormattedNumeroCarta()%></li>
            <li>Scadenza : <%=pagamento.getScadenzaString()%></li>
            <li>CCV : <%=pagamento.getCCV()%></li>
            <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta">
            <input type="submit" value="Conferma il seguente tipo di pagamento">
        </ul>
    </form>
    <%}}else {%>
    <h3 class="error">Non hai nessun metodo di pagamento, <a href="<%=request.getContextPath() + "/user-forward-redirect?iMieiMetodiDiPagamento=i%20Miei%20Metodi%20Di%20Pagamento"%>">inseriscine uno nuovo</a></h3>
    <%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
