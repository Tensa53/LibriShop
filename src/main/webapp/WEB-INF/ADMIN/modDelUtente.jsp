<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Utente</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/username.js" type="text/javascript"></script>
    <script src="./script/validateFormModUtente.js" type="text/javascript"></script>
    <% String msg = (String) request.getAttribute("msg");
        Utente utenteMod = (Utente) request.getAttribute("utente");
        List<Utente> utenti = (List<Utente>) request.getSession().getAttribute("utenti");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center">

    <% if (msg != null) {%>
    <p class="success">${msg}</p>
    <%}%>

<%if (utenti != null) {%>
<p>Scegli un utente da modificare selezionando la sua mail</p>
<form action="cerca-utente-da-modificare" method="post">
    <select name="mail-utente">
        <%for (Utente u : utenti) {%>
        <option value="<%=u.getMail()%>"><%=u.getMail()%> - <%=u.getUsername()%> - <%=u.getNome() + " " + u.getCognome()%>
        </option>
        <%}%>
    </select>
    <input type="submit" value="Modifica Utente">
    <button formaction="rimuovi-utente">Rimuovi Utente</button>
</form>
<%}%>

<%if (utenteMod != null) {%>

<form name="ModUtente" action="conferma-modifiche-utente" method="post" onsubmit="return validateFormModUtente()">
    <label for="mail">Mail : <%=utenteMod.getMail()%></label><br>
    <input type="hidden" id="mail" name="mail" value="<%=utenteMod.getMail()%>">
    <label for="nome">Nome : </label> <br>
    <p id="nomeP"></p>
    <input type="text" name="nome" id="nome" value="<%=utenteMod.getNome()%>" required><br>
    <label for="cognome">Cognome : </label> <br>
    <p id="cognomeP"></p>
    <input type="text" name="cognome" id="cognome" value="<%=utenteMod.getCognome()%>" required><br>
    <label for="controlla-username">Username : </label><br>
    <p id="usernameP"></p>
    <p id="controllousername"></p>
    <input type="text" name="username" id="controlla-username" value="<%=utenteMod.getUsername()%>" onblur="ControllaUsername()" required><br>
    <label for="password">Password : </label>
    <p id="passwordP"></p>
    <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un
        numero.)</p>
    <input type="password" name="password" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}"><br>
    <label>Amministratore : </label>
    <%if(utenteMod.isAmministratore()) {%>
    <input type="radio" name="amministratore" value="true" checked>SI
    <input type="radio" name="amministratore" value="false">NO
    <%} else {%>
    <input type="radio" name="amministratore" value="true">SI
    <input type="radio" name="amministratore" value="false" checked>NO
    <%}%>
    <input type="submit" value="Conferma Modifiche">
</form>

<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
