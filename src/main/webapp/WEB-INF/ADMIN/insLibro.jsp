<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Genere" %><%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 14/06/22
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<form action="inserisci-libro" method="post" enctype="multipart/form-data">
    <label for = "isbn">ISBN : </label> <br>
    <input type="text" name="isbn" id="isbn"><br>
    <label for = "titolo">Titolo : </label> <br>
    <input type="text" name="titolo" id="titolo"><br>
    <label for = "autore">Autore : </label> <br>
    <input type="text" name="autore" id="autore"><br>
    <label for= "genere">Genere : </label><br>
    <div id="genere">
    <%List<Genere> generi = (List<Genere>) request.getAttribute("generi");
        for (Genere g : generi) {
    %>
        <input type="checkbox" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
    <%}%>
    </div><br>
    <label for = "descrizione">Descrizione : </label> <br>
    <textarea name="descrizione" id="descrizione"></textarea><br>
    <label for = "prezzo">Prezzo : </label> <br>
    <input type="number" step="0.1" min="0" name="prezzo" id="prezzo"><br>
    <label for = "dataPubblicazione">Data di Pubblicazione : </label> <br>
    <input type="date" name="dataPubblicazione" id="dataPubblicazione"><br>
    <label for = "editore">Editore : </label> <br>
    <input type="text" name="editore" id="editore"><br>
    <label for = "sconto">Sconto : </label> <br>
    <input type="number" name="sconto" id="sconto"><br>
    <label for = "disponibilita">Disponibilita : </label> <br>
    <input type="number" name="disponibilita" id="disponibilita"><br>
    <label for = "foto">Foto : </label> <br>
    <input type="file" name="foto" id="foto"><br>
    <input type="submit" value="inserisci"><br>
</form>

<%String msg = (String) request.getAttribute("msg");
    if (msg != null){
%>
        <p class="green">${msg}</p>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>