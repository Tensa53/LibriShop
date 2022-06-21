<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Libro" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 21/06/22
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rimuovi Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <script src="./script/immagine.js"></script>
    <%
        List<Libro> libri = (List<Libro>) request.getSession().getAttribute("libri");
        String msg = (String) request.getAttribute("msg");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<p>Scegli un libro da rimuovere dal catalogo selezionando il suo codice ISBN</p>


<%if(libri != null){%>
<form action="rimuovi-libro-catalogo" method="post">
    <select name="isbn-libro">
        <%for(Libro l : libri){%>
        <option onclick="loadImage('./img/LaCasa.jpg')" value="<%=l.getISBN()%>" ><%=l.getISBN()%> - <%=l.getTitolo()%></option>
        <%}%>
    </select>
    <input type="submit" value="Rimuovi Libro">
</form>
<img id="img-libro">
<%}%>

<%if (msg != null){%>
<p class="green">${msg}</p>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
