<%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 01/06/22
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>

<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<body>
<div id="container-todo">
    <form action="register">
        <label for = "nome">Nome : </label> <br>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "mail">Mail : </label> <br>
        <input type="email" name="mailr" id="mail" required><br>
        <label for = "username">Username : </label> <br>
        <input type="text" name="usernamer" id="username" required><br>
        <label for = "password">Password : </label><br>
        <input type="password" name="passwordr" id="password" required><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false">
        <input type="submit" value="Registrati">
    </form>
</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
