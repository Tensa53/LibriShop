<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I Miei Dati</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <script src="./script/form/validateFormModificaUtente.js" type="text/javascript"></script>
    <script src="./script/checkPassword.js"></script>
    <% Utente utente = (Utente) request.getSession().getAttribute("utente");
       String msg = (String) request.getAttribute("msg");
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cognomeP = (String) request.getAttribute("msgcognomeP");
        String passwordP = (String) request.getAttribute("msgpasswordP");
        String controllopassword = (String) request.getAttribute("msgcontrollopassword");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">

    <%if(msg != null){%>
        <p>${msg}</p>
    <%}%>

    <form action="conferma-modifiche-utente-cliente" method="post" name="modifica-utente" onsubmit="return validateFormModificaUtente()" >
        <label for="mail">Mail : <%=utente.getMail()%>
        </label><br>
        <input type="hidden" id="mail" name="mail" value="<%=utente.getMail()%>">
        <label for="nome">Nome : </label> <br>
        <p class="error" id="nomeP"><%if (nomeP != null) {%><%=nomeP%><%}%></p>
        <input type="text" name="nome" id="nome" value="<%=utente.getNome()%>" required><br>
        <label for="cognome">Cognome : </label> <br>
        <p class="error" id="cognomeP"><%if (cognomeP != null) {%><%=cognomeP%><%}%></p>
        <input type="text" name="cognome" id="cognome" value="<%=utente.getCognome()%>" required><br>
        <label for="password">Password : </label>
        <p class="error" id="passwordP"><%if (passwordP != null) {%><%=passwordP%><%}%></p>
        <p class="error" id="controllopassword"><%if (controllopassword != null) {%><%=controllopassword%><%}%></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un
            numero.)</p>
        <input type="password" name="password" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}"><br>
        <input type="checkbox" onclick="checkPassword()">Mostra Password<br><br>
        <input type="submit" value="Conferma Modifiche">
        <button formaction="area-riservata">Annulla</button>
    </form>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
