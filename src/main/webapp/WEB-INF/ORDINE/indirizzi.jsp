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
                <li>Via : <%=indirizzo.getVia()%></li>
                <li>Civico :<%=indirizzo.getCivico()%></li>
                <li>CAP : <%=indirizzo.getCAP()%></li>
                <li>Città : <%=indirizzo.getCitta()%></li>
                <li>Provincia : <%=indirizzo.getProvincia()%></li>
                <li>Stato : <%=indirizzo.getStato()%></li>
                <input type="hidden" value="<%=indirizzo.getVia()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCivico()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCAP()%>" name="indirizzo">
                <input type="submit" value="Conferma il seguente indirizzo">
            </ul>
        </form>
    <%}%>
</body>
</html>
