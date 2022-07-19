<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operazioni Autore e Genere</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateModificaNomeAutore.js" type="text/javascript"></script>
    <script src="./script/form/validateInserisciNuovoAutore.js" type="text/javascript"></script>
    <script src="./script/form/validateFormGenere.js"></script>
    <%
        List<Autore> autori = (List<Autore>) request.getAttribute("autori");
        List<Genere> generi = (List<Genere>) request.getAttribute("generi");
        Autore autoreMod = (Autore) request.getAttribute("autore");
        String msg = (String) request.getAttribute("msg");
        String genereP = (String) request.getAttribute("msggenereP");
        String controllogenere = (String) request.getAttribute("msgcontrollogenere");
        String nomeP = (String) request.getAttribute("msgnomeP");
        String cfP = (String) request.getAttribute("msgCFP");
        String controlloCF = (String) request.getAttribute("msgcontrolloCF");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center" id="container-forms">

    <%if(msg != null){%>
    <p>${msg}</p>
    <%}%>

<p>Inserisci un genere : <%if(controllogenere != null){%><%=controllogenere%><%}%></p>
<p id="genereP"><%if(genereP != null){%><%=genereP%><%}%></p>
<form method="post" name="inseriscigen" action="inserisci-genere" onsubmit="return validateFormGenere()">
    <input type="text" name="genere" id="genere" required>
    <input type="submit" value="Inserisci">
</form>

<p>Elimina un genere : </p>
<form method="post" action="rimuovi-genere">
    <select name="nome-genere">
        <%for (Genere g : generi){%>
        <option value="<%=g.getNome()%>"><%=g.getNome()%></option>
        <%}%>
    </select>
    <input type="submit" value="Rimuovi genere">
</form>

<p>Scegli un Autore da modificare o rimuovere :</p>
<form method="post">
    <select name="cf-autore">
        <%for (Autore a : autori) {%>
        <option value="<%=a.getCF()%>"><%=a.getCF()%> - <%=a.getNome()%> </option>
        <%}%>
    </select>
    <button formaction="cerca-autore-da-modificare">Modifica Autore</button>
    <button formaction="rimuovi-autore">Rimuovi Autore</button>
</form>

<p>Inserisci o modifica un Autore : </p>

<form method="post" name="gestisci-autore">
    <%if(autoreMod == null){%>
    <label for="CF">CF : <%if(controlloCF != null){%><%=controlloCF%><%}%></label><br>
    <p class="error" id="cfP"><%if(cfP != null){%><%=cfP%><%}%></p>
    <input type="text" id="CF" name="CF"><br>
    <p class="error" id="CFP"></p>
    <label for="nome">Nome Autore : </label><br>
    <p class="error" id="nomeP"> <%if(nomeP != null){%><%=nomeP%><%}%></p>
    <input type="text" name="nome" id="nome"><br>
    <button onclick="return validateInserisciNuovoAutore()" formaction="inserisci-autore">Inserisci Autore</button>
    <%} else {%>
    <label for="CF">CF : <%=autoreMod.getCF()%></label><br>
    <input type="hidden" name="CF" id="CF" value="<%=autoreMod.getCF()%>">
    <p class="error" id="nomeP"></p>
    <label for="nome">Nome Autore : </label><br>
    <input type="text" name="nome" id="nome" value="<%=autoreMod.getNome()%>"><br><br>
    <button onclick="return validateModificaNomeAutore()" formaction="conferma-modifiche-autore">Conferma Modifiche</button>
    <%}%>
</form>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
