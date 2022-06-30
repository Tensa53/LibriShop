<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/validateFormRegistrazione.js" type="text/javascript"></script>
    <script src="./script/mail.js" type="text/javascript"></script>
    <script src="./script/username.js" type="text/javascript"></script>
</head>

<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<body>
<div id="container-registrazione" class="center">
    <form name="Registrazione" action="register" onsubmit="return validateFormRegistrazione()">
        <label for = "nome">Nome : </label> <br>
        <p id="nomeP"></p>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <p id="cognomeP"></p>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-mail">Mail : </label> <br>
        <p id="mailP"></p>
        <p id="controllomail"></p>
        <input type="email" name="mailr" id="controlla-mail" onblur="ControllaMail()" required><br>
        <label for = "controlla-username">Username : </label><br>
        <p id="usernameP"></p>
        <p id="controllousername"></p>
        <input type="text" name="usernamer" id="controlla-username" onblur="ControllaUsername()" required><br>
        <label for = "password">Password : </label>
        <p id="passwordP"></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false"><br>
        <input type="submit" value="Registrati">
    </form>
</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>

</body>
</html>