<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operazioni Autore e Genere</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <%
        List<Autore> autori = (List<Autore>) request.getAttribute("autori");
        List<Genere> generi = (List<Genere>) request.getAttribute("generi");
        Autore autoreMod = (Autore) request.getAttribute("autore");
        String msg = (String) request.getAttribute("msg");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>


<%if(msg != null){%>
<p>${msg}</p>
<%}%>

<p>Elimina un genere : </p>
<form action="rimuovi-genere" method="post">
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

<form method="post">
    <%if(autoreMod == null){%>
    <label for="CF">CF : </label><br>
    <input type="text" id="CF" name="CF"><br>
    <label for="nome">Nome Autore : </label><br>
    <input type="text" name="nome" id="nome"><br>
    <button formaction="inserisci-autore">Inserisci Autore</button>
    <%} else {%>
    <label for="CF">CF : <%=autoreMod.getCF()%></label><br>
    <input type="hidden" name="CF" id="CF" value="<%=autoreMod.getCF()%>">
    <label for="nome">Nome Autore : </label><br>
    <input type="text" name="nome" id="nome" value="<%=autoreMod.getNome()%>"><br>
    <button formaction="conferma-modifiche-autore">Conferma Modifiche</button>
    <%}%>
</form>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
