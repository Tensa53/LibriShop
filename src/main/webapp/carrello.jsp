<%@ page import="com.example.progettotsw.model.Carrello" %>
<%@ page import="com.example.progettotsw.model.Dettaglio" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 08/06/22
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<p>Carrello</p>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");

    if(carrello.getTotale() > 0.0f){
        for(Dettaglio d : carrello.getDettagli()){%>
            <p><%=d.getLibro().getTitolo()%> <%=d.getQuantita()%> <%=d.getPrezzo()%></p>
    <%}}else{%>
            <p>vuoto</p>
    <%}%>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>
