
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Utente</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/mail.js" type="text/javascript"></script>
    <script src="./script/username.js" type="text/javascript"></script>
    <%String msg = (String) request.getAttribute("msg");%>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<body>

<div id="container-registrazione" class="center">

<% if(msg != null) {%>
<p class="green">${msg}</p>
<%}%>

    <form name="Registrazione" action="inserisci-utente" method="post" onsubmit="return validateFormRegistrazione()">
        <label for = "nome">Nome : </label> <br>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-mail">Mail : </label> <br>
        <p id="controllomail"></p>
        <input type="email" name="mailr" id="controlla-mail" onblur="ControllaMail()" required><br>
        <label for = "controlla-username">Username : </label><br>
        <p id="controllousername"></p>
        <input type="text" name="usernamer" id="controlla-username" onblur="ControllaUsername()" required><br>
        <label for = "password">Password : </label>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <label for="amministratore">Amministratore : </label>
        <input type="radio" name="amministratore" value="true">SI
        <input type="radio" name="amministratore" value="false">NO
        <input type="submit" value="Inserisci">
    </form>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>


</body>
</html>
