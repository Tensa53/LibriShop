<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Libro" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <script src="./script/autore.js" type="text/javascript"></script>
    <script src="./script/immagineLibro.js" type="text/javascript"></script>
    <%List<Libro> libri = (List<Libro>) request.getSession().getAttribute("libri");
      List<Genere> generi = (List<Genere>) request.getSession().getAttribute("generi");
      List<Genere> generiLibro = (List<Genere>) request.getAttribute("generi-libro");
      Libro libro = (Libro) request.getAttribute("libro");
      Autore autore = (Autore) request.getAttribute("autore");
    %>
</head>
<body>
<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

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
<img id="fotolibro" src="<%=libri.get(0).getFoto()%>">
<%}%>


<%if(libro != null) {;%>

<form action="conferma-modifiche-libro" method="post" enctype="multipart/form-data">
    <label for = "isbn">ISBN : <%=libro.getISBN()%></label> <br>
    <input type="hidden" name="isbn" id="isbn" value="<%=libro.getISBN()%>"><br>
    <label for = "titolo">Titolo : </label> <br>
    <input type="text" name="titolo" id="titolo" value="<%=libro.getTitolo()%>"><br>
    <label for = "autore">Autore : </label> <br>
    <p id="controlloautore"></p>
    <input type="text" name="autore" id="autore" onblur="ControllaAutore()" value="<%=autore.getNome()%>"><br>
    <label for= "genere">Genere : </label><br>
    <div id="genere">
        <%
            for (Genere g : generi) {
                if(g.contenutoIn(generiLibro)) {
        %>
                    <input type="checkbox" name="genere" value="<%=g.getNome()%>" checked><%=g.getNome()%><br>
        <%} else {%>
                    <input type="checkbox" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
        <%}}%>
        <label for="altro">Altro Genere : </label>
        <input type="text" id="altro" name="altro">
    </div><br>
    <label for = "descrizione">Descrizione : </label> <br>
    <textarea name="descrizione" id="descrizione"><%=libro.getDescrizione()%></textarea><br>
    <label for = "prezzo">Prezzo : </label> <br>
    <input type="number" step="0.1" min="0" name="prezzo" id="prezzo" value="<%=libro.getPrezzo()%>"><br>
    <label for = "dataPubblicazione">Data di Pubblicazione : </label> <br>
    <input type="date" name="dataPubblicazione" id="dataPubblicazione" value="<%=libro.getDataPubblicazioneReversedString()%>"><br>
    <label for = "editore">Editore : </label> <br>
    <input type="text" name="editore" id="editore" value="<%=libro.getEditore()%>"><br>
    <label for = "sconto">Sconto (%) : </label> <br>
    <input type="number" name="sconto" id="sconto" min="1" max="99" step="1" value="<%=libro.getSconto().toString()%>"><br>
    <label for = "disponibilita">Disponibilita : </label> <br>
    <input type="number" name="disponibilita" id="disponibilita" value="<%=libro.getDisponibilita()%>"><br>
    <label for = "foto">Foto : </label> <br>
    <input type="file" name="foto" id="foto"><br>
    <input type="submit" value="Conferma Modifiche"><br>
</form>

<%}%>

<%String msg = (String) request.getAttribute("msg");
    if (msg != null){
%>
<p class="green">${msg}</p>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
