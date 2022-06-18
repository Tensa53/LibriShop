<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Indirizzi</title>
    <%List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");%>
</head>
<body>
    <%for(Indirizzo indirizzo : indirizzi){%>
        <form action="conferma-indirizzo" method="post">
            <ul>
                <li><%=indirizzo.getVia()%></li>
                <li><%=indirizzo.getCivico()%></li>
                <li><%=indirizzo.getCAP()%></li>
                <li><%=indirizzo.getCitta()%></li>
                <li><%=indirizzo.getProvincia()%></li>
                <li><%=indirizzo.getStato()%></li>
                <input type="hidden" value="<%=indirizzo.getVia()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCivico()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCAP()%>" name="indirizzo">
                <input type="submit" value="Conferma il seguente indirizzo">
            </ul>
        </form>
    <%}%>
</body>
</html>
