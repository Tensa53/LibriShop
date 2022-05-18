<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<% List<Libro> libri = (List<Libro>) request.getAttribute("libri");
    for (Libro l:libri) { %>
            <img src="<%=l.getFoto()%>"/>
        <% } %>

</body>
</html>