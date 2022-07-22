<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Libro</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/ajax/immagineLibro.js" type="text/javascript"></script>
    <script src="./script/form/validateFormModLibro.js" type="text/javascript"></script>
    <%
        String msg = (String) request.getAttribute("msg");
        String msgerr = (String) request.getAttribute("msgerror");
        List<Libro> libri = (List<Libro>) request.getAttribute("libri");
      List<Genere> generi = (List<Genere>) request.getAttribute("generi");
      List<Autore> autori = (List<Autore>) request.getAttribute("autori");
      List<Genere> generiLibro = (List<Genere>) request.getAttribute("generi-libro");
      Libro libro = (Libro) request.getAttribute("libro");
      Autore autore = (Autore) request.getAttribute("autore");
        String titoloP = (String) request.getAttribute("msgtitoloP");
        String altroP = (String) request.getAttribute("msgaltroP");
        String controllogenere = (String) request.getAttribute("msgcontrollogenere");
        String controllogenerealtro = (String) request.getAttribute("controllogenerealtro");
        String descrizioneP = (String) request.getAttribute("msgdescrizioneP");
        String editoreP = (String) request.getAttribute("msgeditoreP");
    %>
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center" id="container-forms">

    <%if (msg != null){%>
    <h3 class="success">${msg}</h3>
    <%}%>


    <%if (msgerr != null){%>
    <h3 class="error">
        <ul class="nobullet">
            <li><%=msgerr%></li>

            <%if (titoloP != null) {%>
            <li><%=titoloP%>
            </li>
            <%}%>

            <%if (controllogenere != null) {%>
            <li><%=controllogenere%>
            </li>
            <%}%>

            <%if (altroP != null) {%>
            <li><%=altroP%>
            </li>
            <%}%>

            <%if (descrizioneP != null) {%>
            <li><%=titoloP%>
            </li>
            <%}%>

            <%if (editoreP != null) {%>
            <li><%=editoreP%>
            </li>
            <%}%>
        </ul>
    </h3>
    <%}%>

<%if(libri != null){%>
<p>Scegli un libro da modificare selezionando il suo codice ISBN</p>
<form action="cerca-libro-da-modificare" method="post">
    <select name="isbn-libro" onchange="immagineLibro(this.value)">
        <%for(Libro l : libri){%>
            <option value="<%=l.getISBN()%>"><%=l.getISBN()%> - <%=l.getTitolo()%></option>
        <%}%>
    </select>
    <input type="submit" value="Modifica Libro">
    <button formaction="rimuovi-libro-catalogo">Rimuovi Libro</button>
</form>
<%if (libro != null){%>
    <img id="fotolibro" src="<%=libro.getFoto()%>">
<%}else {%>
    <img id="fotolibro" src="<%=libri.get(0).getFoto()%>">
<%}%>
<%}%>


<%if(libro != null) {;%>

<form name="modificalibro" action="conferma-modifiche-libro" method="post" enctype="multipart/form-data" onsubmit="return validateFormModLibro()">

    <div id="form-libro-1">
    <label for = "isbn">ISBN : <%=libro.getISBN()%></label> <br>
    <p class="error" id="isbnP"></p>
    <input type="hidden" name="isbn" id="isbn" value="<%=libro.getISBN()%>" required><br>
    <label for = "titolo">Titolo : </label> <br>
    <p class="error" id="titoloP"></p>
    <input type="text" name="titolo" id="titolo" value="<%=libro.getTitolo()%>" required><br>
    <label for = "autore">Autore : </label>
    <select name="autore" id="autore">
        <%for (Autore a : autori){%>
        <option value="<%=a.getCF()%>"><%=a.getNome()%></option>
        <%}%>
    </select><br>
    <label for= "genere">Genere : <span class="error" id="controllogenere"><%if (controllogenere != null){%><%=controllogenere%><%}%></span></label><br>
    <div id="genere" class="checkbox-grid" required>
        <%
            for (Genere g : generi) {
                if(g.contenutoIn(generiLibro)) {
        %>
                    <input type="checkbox" name="genere" value="<%=g.getNome()%>" checked><%=g.getNome()%><br>
        <%} else {%>
                    <input type="checkbox" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
        <%}}%>
        <label for="altro">Altro Genere : </label>
        <p class="error" id="altroP"></p>
        <p class="error" id="controllogenerealtro"><%if(controllogenerealtro != null){%><%=controllogenerealtro%><%}%></p>
        <input type="text" id="altro" name="altro">
    </div><br>
    <label for = "dataPubblicazione">Data di Pubblicazione : </label> <br>
    <input type="date" name="dataPubblicazione" id="dataPubblicazione" required value="<%=libro.getDataPubblicazioneReversedString()%>"><br>
    <label for = "editore">Editore : </label> <br>
    <p class="error" id="editoreP"></p>
    <input type="text" name="editore" id="editore" required value="<%=libro.getEditore()%>"><br>
    </div>

    <div id="form-libro-2">
    <label for = "descrizione">Descrizione : </label> <br>
    <p class="error" id="descrizioneP"></p>
    <textarea required name="descrizione" id="descrizione" rows="12"><%=libro.getDescrizione()%></textarea><br>
    <label for = "prezzo">Prezzo (€) : </label> <br>
    <input type="number" step="0.1" min="0" name="prezzo" id="prezzo" required value="<%=libro.getPrezzo()%>"><br>
    <label for = "sconto">Sconto (%) : </label> <br>
    <input type="number" name="sconto" id="sconto" min="0" max="99" step="1" value="<%=libro.getSconto().toString()%>"><br>
    <label for = "disponibilita">Disponibilita : </label> <br>
    <input type="number" name="disponibilita" id="disponibilita" required value="<%=libro.getDisponibilita()%>"><br>
    <label for = "foto">Foto : </label> <br>
    <input type="file" name="foto" id="foto" accept="image/*"><br><br>
    <input type="submit" value="Conferma Modifiche"><br>
    </div>

</form>

<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
