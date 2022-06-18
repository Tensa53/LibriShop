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
    <script src="./script/validateFormRegistrazione.js" type="text/javascript"></script>
    <script src="./script/mail.js" type="text/javascript"></script>
</head>

<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<body>
<div id="container-registrazione">
    <form name="Registrazione" action="register" onsubmit="return validateFormRegistrazione()">
        <label for = "nome">Nome : </label> <br>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-mail">Mail : </label> <br>
        <p id="controllomail"></p>
        <input type="email" name="mailr" id="controlla-mail" onblur="ControllaMail()" required><br>
        <label for = "username">Username : </label> <br>
        <input type="text" name="usernamer" id="username" required><br>
        <label for = "password">Password : </label>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false"><br>
        <input type="submit" value="Registrati">
    </form>
</div>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

</body>
</html>