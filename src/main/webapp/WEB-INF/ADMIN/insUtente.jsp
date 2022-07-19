
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Utente</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/ajax/mail.js" type="text/javascript"></script>
    <script src="./script/checkPassword.js"></script>
    <script src="./script/form/validateFormInsUtente.js" type="text/javascript"></script>
    <%String msg = (String) request.getAttribute("msg");
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cognomeP = (String) request.getAttribute("msgcognomeP");
        String mailP = (String) request.getAttribute("msgmailP");
        String passwordP = (String) request.getAttribute("msgpasswordP");
        String controllomail = (String) request.getAttribute("msgcontrollomail");
        String controllopassword = (String) request.getAttribute("msgcontrollopassword");
        String mailinuso = (String) request.getAttribute("msgmailinuso");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<body>

<div id="container-forms" class="center">

<% if(msg != null) {%>
<p class="green">${msg}</p>
<%}%>

    <form name="registrazione" action="inserisci-utente" method="post" onsubmit="return validateFormInsUtente()">
        <label for = "nome">Nome : </label> <br>
        <p class="error" id="nomeP"><%if (nomeP != null){%><%=nomeP%><%}%></p>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <p class="error" id="cognomeP"><%if (cognomeP != null){%><%=cognomeP%><%}%></p>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-mail">Mail : <span class="error"><%if (mailinuso != null){%><%=mailinuso%><%}%></span></label> <br>
        <p class="error" id="mailP"><%if (mailP != null){%><%=mailP%>><%}%></p>
        <p class="error" id="controllomail"><%if (controllomail != null){%><%=controllomail%><%}%></p>
        <input type="email" name="mailr" id="controlla-mail" onblur="ControllaMail()" required><br>
        <label for = "password">Password : </label>
        <p class="error" id="passwordP"><%if (passwordP != null){%><%=passwordP%><%}%></p>
        <p class="error" id="controllopassword"><%if (controllopassword != null){%><%=controllopassword%><%}%></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <input type="checkbox" onclick="checkPassword()">Mostra Password<br><br>
        <label>Amministratore : </label>
        <input type="radio" name="amministratore" value="true">SI
        <input type="radio" name="amministratore" value="false">NO<br><br>
        <input type="submit" value="Inserisci">
    </form>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>


</body>
</html>
