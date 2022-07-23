<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modifica Utente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateFormModUtente.js" type="text/javascript"></script>
    <script src="./script/checkPassword.js"></script>
    <% String msg = (String) request.getAttribute("msg");
        String msgerr = (String) request.getAttribute("msgerr");
        Utente utenteMod = (Utente) request.getAttribute("utente");
        List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cognomeP = (String) request.getAttribute("msgcognomeP");
        String mailP = (String) request.getAttribute("msgmailP");
        String passwordP = (String) request.getAttribute("msgpasswordP");
        String controllomail = (String) request.getAttribute("msgcontrollomail");
        String controllopassword = (String) request.getAttribute("msgcontrollopassword");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center" id="container-forms">

    <% if (msg != null) {%>
    <h3 class="success">${msg}</h3>
    <%}%>

    <div class="error">

    <% if (msgerr != null) {%>
    <h3>
    <ul class="nobullet">

        <li>${msgerr}</li>

        <%if (nomeP != null) {%>
        <li><%=nomeP%>
        </li>
        <%}%>

        <%if (cognomeP != null) {%>
        <li><%=cognomeP%>
        </li>
        <%}%>

        <%if (mailP != null ){%>
        <li><%=mailP%></li>
        <%}%>

        <%if (controllomail != null ){%>
        <li><%=controllomail%></li>
        <%}%>

        <%if (passwordP != null ){%>
        <li><%=passwordP%></li>
        <%}%>

        <%if (controllopassword != null ){%>
        <li><%=controllopassword%></li>
        <%}%>

    </ul>
    </h3>

    <%}%>

    </div>

    <%if (utenti != null) {%>
    <p>Scegli un utente da modificare selezionando la sua mail : </p>
    <form action="cerca-utente-da-modificare" method="post">
        <select name="mail-utente">
            <%for (Utente u : utenti) {%>
            <option value="<%=u.getMail()%>"><%=u.getMail()%> - <%=u.getNome() + " " + u.getCognome()%>
            </option>
            <%}%>
        </select><br><br>
        <input type="submit" value="Modifica Utente">
        <button formaction="rimuovi-utente">Rimuovi Utente</button>
    </form>
    <%}%>

    <%if (utenteMod != null) {%>

    <form name="ModUtente" action="conferma-modifiche-utente" method="post" onsubmit="return validateFormModUtente()">
        <label for="mail">Mail : <%=utenteMod.getMail()%>
        </label><br>
        <input type="hidden" id="mail" name="mail" value="<%=utenteMod.getMail()%>">
        <label for="nome">Nome : </label> <br>
        <p class="error" id="nomeP"><%if (nomeP != null) {%><%=nomeP%><%}%></p>
        <input type="text" name="nome" id="nome" value="<%=utenteMod.getNome()%>" required><br>
        <label for="cognome">Cognome : </label> <br>
        <p class="error" id="cognomeP"><%if (cognomeP != null) {%><%=cognomeP%><%}%></p>
        <input type="text" name="cognome" id="cognome" value="<%=utenteMod.getCognome()%>" required><br>
        <label for="password">Password : </label>
        <p class="error" id="passwordP"><%if (passwordP != null) {%><%=passwordP%><%}%></p>
        <p class="error" id="controllopassword"><%if (controllopassword != null) {%><%=controllopassword%><%}%></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un
            numero.)</p>
        <input type="password" name="password" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}"><br>
        <input type="checkbox" onclick="checkPassword()">Mostra Password<br><br>
        <label>Amministratore : </label><br>
        <%if (utenteMod.isAmministratore()) {%>
        <input type="radio" name="amministratore" value="true" checked>SI
        <input type="radio" name="amministratore" value="false">NO<br><br>
        <%} else {%>
        <input type="radio" name="amministratore" value="true">SI
        <input type="radio" name="amministratore" value="false" checked>NO<br><br>
        <%}%>
        <input type="submit" value="Conferma Modifiche">
    </form>

    <%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
