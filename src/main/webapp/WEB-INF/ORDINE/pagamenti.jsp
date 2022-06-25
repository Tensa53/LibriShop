<%@ page import="com.example.progettotsw.model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagamenti</title>
    <%List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");%>
</head>
<body>
<%for(Pagamento pagamento : pagamenti){%>
<form action="conferma-pagamento" method="post">
    <ul>
        <li>Numero Carta : <%=pagamento.getNumeroCarta()%></li>
        <li>Scadenza : <%=pagamento.getScadenzaString()%></li>
        <li>CCV : <%=pagamento.getCCV()%></li>
        <input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta">
        <input type="submit" value="Conferma il seguente tipo di pagamento">
    </ul>
</form>
<%}%>
</body>
</html>
