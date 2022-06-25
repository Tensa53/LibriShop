<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <script src="./script/autore.js" type="text/javascript"></script>
    <script src="./script/validateFormInsLibro.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<form name="inseriscilibro" action="inserisci-libro" method="post" onsubmit="return validateFormInsLibro()" enctype="multipart/form-data">
    <label for = "isbn">ISBN : </label> <br>
    <p id="isbnP"></p>
    <input type="text" name="isbn" id="isbn" required><br>
    <label for = "titolo">Titolo : </label> <br>
    <p id="titoloP"></p>
    <input type="text" name="titolo" id="titolo" required><br>
    <label for = "autore">Autore : </label> <br>
    <p id="controlloautore"></p>
    <p id="autoreP"></p>
    <input type="text" name="autore" id="autore" onblur="ControllaAutore()" required><br>
    <label for= "genere">Genere : </label><br>
    <div id="genere">
    <%List<Genere> generi = (List<Genere>) request.getAttribute("generi");
        for (Genere g : generi) {
    %>
        <input type="checkbox" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
    <%}%>
    <label for="altro">Altro Genere : </label>
    <p id="altroP"></p>
    <input type="text" id="altro" name="altro">
    </div><br>
    <label for = "descrizione">Descrizione : </label> <br>
    <p id="descrizioneP"></p>
    <textarea name="descrizione" id="descrizione" required></textarea><br>
    <label for = "prezzo">Prezzo : </label> <br>
    <input type="number" step="0.1" min="0" name="prezzo" id="prezzo" required><br>
    <label for = "dataPubblicazione">Data di Pubblicazione : </label> <br>
    <input type="date" name="dataPubblicazione" id="dataPubblicazione" required><br>
    <label for = "editore">Editore : </label> <br>
    <input type="text" name="editore" id="editore" required><br>
    <p id="editoreP"></p>
    <label for = "sconto">Sconto : </label> <br>
    <input type="number" name="sconto" id="sconto"><br>
    <label for = "disponibilita">Disponibilita : </label> <br>
    <input type="number" name="disponibilita" id="disponibilita" required><br>
    <label for = "foto">Foto : </label> <br>
    <input type="file" name="foto" id="foto" required><br>
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