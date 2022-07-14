<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Indirizzi</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <%List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");%>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

    <%for(Indirizzo indirizzo : indirizzi){%>
        <form action="conferma-indirizzo" method="post">
            <ul>
                <li>Via : <%=indirizzo.getVia()%></li>
                <li>Civico :<%=indirizzo.getCivico()%></li>
                <li>Città : <%=indirizzo.getCitta()%></li>
                <li>CAP : <%=indirizzo.getCAP()%></li>
                <li>Provincia : <%=indirizzo.getProvincia()%></li>
                <input type="hidden" value="<%=indirizzo.getVia()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCivico()%>" name="indirizzo">
                <input type="hidden" value="<%=indirizzo.getCitta()%>" name="indirizzo">
                <input type="submit" value="Conferma il seguente indirizzo">
            </ul>
        </form>
    <%}%>
</body>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</html>
