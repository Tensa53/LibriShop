<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I Miei Dati</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/username.js" type="text/javascript"></script>
    <% Utente utente = (Utente) request.getSession().getAttribute("utente");
       String msg = (String) request.getAttribute("msg");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-registrazione" class="center">

    <%if(msg != null){%>
        <p>${msg}</p>
    <%}%>

    <form action="conferma-modifiche-utente-cliente" method="post"> <!--- onsubmit="return validateFormModificaUtente()" --->
        <label for = "mail" id="mail">Mail :  <%=utente.getMail()%></label> <br>
        <input type="hidden" value="<%=utente.getMail()%>" name="mail">
        <label for = "nome">Nome : </label> <br>
        <p id="nomeP"></p>
        <input type="text" name="nome" id="nome" value="<%=utente.getNome()%>" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <p id="cognomeP"></p>
        <input type="text" name="cognome" id="cognome" value="<%=utente.getCognome()%>" required><br>
        <p id="mailP"></p>
        <p id="controllomail"></p>
        <label for = "controlla-username">Username : </label><br>
        <p id="usernameP"></p>
        <p id="controllousername"></p>
        <input type="text" name="username" id="controlla-username" value="<%=utente.getUsername()%>" onblur="ControllaUsername()" required><br>
        <label for = "password">Password : </label>
        <p id="passwordP"></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}"><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false"><br>
        <input type="submit" value="Conferma Modifiche">
        <button formaction="area-riservata">Annulla</button>
    </form>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
