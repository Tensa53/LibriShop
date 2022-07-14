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
    <script src="./script/username.js" type="text/javascript"></script>
    <script src="./script/validateFormModificaUtente.js" type="text/javascript"></script>
    <% Utente utente = (Utente) request.getSession().getAttribute("utente");
       String msg = (String) request.getAttribute("msg");
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cognomeP = (String) request.getAttribute("msgcognomeP");
        String mailP = (String) request.getAttribute("msgmailP");
        String usernameP = (String) request.getAttribute("msgusernameP");
        String passwordP = (String) request.getAttribute("msgpasswordP");
        String controllomail = (String) request.getAttribute("msgcontrollomail");
        String controllopassword = (String) request.getAttribute("msgcontrollopassword");
        String usernameinuso = (String) request.getAttribute("msgusernameinuso");
        String mailinuso = (String) request.getAttribute("msgmailinuso");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-registrazione" class="center">

    <%if(msg != null){%>
        <p>${msg}</p>
    <%}%>

    <form action="conferma-modifiche-utente-cliente" method="post" name="modifica-utente" onsubmit="return validateFormModificaUtente()" >
        <label for = "nome">Nome : </label> <br>
        <p id="nomeP"><%if (nomeP != null){%><%=nomeP%><%}%></p>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <p id="cognomeP"><%if (cognomeP != null){%><%=cognomeP%><%}%></p>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-username">Username : <%if (usernameinuso != null){%><%=usernameinuso%><%}%></label><br>
        <p id="usernameP"><%if (usernameP != null){%><%=usernameP%><%}%></p>
        <p id="controllousername"></p>
        <input type="text" name="usernamer" id="controlla-username" onblur="ControllaUsername()" required><br>
        <label for = "password">Password : </label>
        <p id="passwordP"><%if (passwordP != null){%><%=passwordP%><%}%></p>
        <p id="controllopassword"><%if (controllopassword != null){%><%=controllopassword%><%}%></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false"><br>
        <input type="submit" value="Conferma Modifiche">
        <button formaction="area-riservata">Annulla</button>
    </form>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
