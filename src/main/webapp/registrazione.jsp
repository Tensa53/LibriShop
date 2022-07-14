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
    <%
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cognomeP = (String) request.getAttribute("msgcognomeP");
        String mailP = (String) request.getAttribute("msgmailP");
        String passwordP = (String) request.getAttribute("msgpasswordP");
        String controllomail = (String) request.getAttribute("msgcontrollomail");
        String controllopassword = (String) request.getAttribute("msgcontrollopassword");
        String mailinuso = (String) request.getAttribute("msgmailinuso");
    %>
</head>

<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<body>
<div id="container-registrazione" class="center">
    <form name="Registrazione" action="register" onsubmit="return validateFormRegistrazione()">
        <label for = "nome">Nome : </label> <br>
        <p id="nomeP"><%if (nomeP != null){%><%=nomeP%><%}%></p>
        <input type="text" name="nomer" id="nome" required><br>
        <label for = "cognome">Cognome : </label> <br>
        <p id="cognomeP"><%if (cognomeP != null){%><%=cognomeP%><%}%></p>
        <input type="text" name="cognomer" id="cognome" required><br>
        <label for = "controlla-mail">Mail : <%if (mailinuso != null){%><%=mailinuso%><%}%></label> <br>
        <p id="mailP"><%if (mailP != null){%><%=mailP%>><%}%></p>
        <p id="controllomail"><%if (controllomail != null){%><%=controllomail%><%}%></p>
        <input type="email" name="mailr" id="controlla-mail" onblur="ControllaMail()" required><br>
        <p id="controllousername"></p>
        <label for = "password">Password : </label>
        <p id="passwordP"><%if (passwordP != null){%><%=passwordP%><%}%></p>
        <p id="controllopassword"><%if (controllopassword != null){%><%=controllopassword%><%}%></p>
        <p>(La password deve contenere almeno 8 caratteri di cui almeno uno maiuscolo, un carattere speciale, un numero.)</p>
        <input type="password" name="passwordr" id="password" pattern="(?=.*[!@#$%^&*])(?=.*\d)(?=.*[A-Z]).{8,}" required><br>
        <input type="hidden" name="amminstratorer" id="amministratore" value="false"><br>
        <input type="submit" value="Registrati">
    </form>
</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>

</body>
</html>