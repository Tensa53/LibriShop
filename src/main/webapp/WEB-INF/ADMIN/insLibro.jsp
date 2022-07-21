<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Genere" %>
<%@ page import="com.example.progettotsw.model.Autore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateFormInsLibro.js" type="text/javascript"></script>
    <%
        List<Genere> generi = (List<Genere>) request.getAttribute("generi");
        List<Autore> autori = (List<Autore>) request.getAttribute("autori");
        String isbnP = (String) request.getAttribute("msgisbnP");
        String controlloisbn = (String) request.getAttribute("msgcontrolloisbn");
        String titoloP = (String) request.getAttribute("msgtitoloP");
        String altroP = (String) request.getAttribute("msgaltroP");
        String controllogenere = (String) request.getAttribute("msgcontrollogenere");
        String controllogenerealtro = (String) request.getAttribute("msgcontrollogenerealtro");
        String descrizioneP = (String) request.getAttribute("msgdescrizioneP");
        String editoreP = (String) request.getAttribute("msgeditoreP");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>


<div class="center" id="container-forms">

    <%String msg = (String) request.getAttribute("msg");
        if (msg != null){
    %>
    <h3 class="green success">${msg}</h3>
    <%}%>

<form name="inseriscilibro" action="inserisci-libro" method="post" onsubmit="return validateFormInsLibro()" enctype="multipart/form-data">

    <div id="form-libro-1">
    <label for = "isbn">ISBN : <span id="controlloisbn"><%if(controlloisbn != null){%><%=controlloisbn%><%}%></span></label> <br>
    <p class="error" id="isbnP"><%if(isbnP != null){%><%=isbnP%><%}%></p>
    <input type="number" name="isbn" id="isbn" required><br>
    <label for = "titolo">Titolo : </label> <br>
    <p class="error" id="titoloP"><%if(titoloP != null){%><%=titoloP%><%}%></p>
    <input type="text" name="titolo" id="titolo" required><br>
    <label for = "autore">Autore : </label>
    <select name="autore" id="autore">
        <%for (Autore a : autori){%>
            <option value="<%=a.getCF()%>"><%=a.getNome()%></option>
        <%}%>
    </select><br>
    <label for= "genere">Genere : <span id="controllogenere" class="error"><%if (controllogenere != null){%><%=controllogenere%><%}%></span></label><br>
    <div id="genere">
    <%
        for (Genere g : generi) {
    %>
        <input type="checkbox" name="genere" value="<%=g.getNome()%>"><%=g.getNome()%><br>
    <%}%>
    <label for="altro">Altro Genere : </label>
    <p class="error" id="altroP"><%if(altroP != null){%><%=altroP%><%}%></p>
    <p class="error" id="controllogenerealtro"><%if(controllogenerealtro != null){%><%=controllogenerealtro%><%}%></p>
    <input type="text" id="altro" name="altro">
    </div><br>
    <label for = "dataPubblicazione">Data di Pubblicazione : </label> <br>
    <input type="date" name="dataPubblicazione" id="dataPubblicazione" max="" required><br>
    <label for = "editore">Editore : </label> <br>
    <p class="error" id="editoreP"><%if(editoreP != null){%><%=editoreP%><%}%></p>
    <input type="text" name="editore" id="editore" required><br>
    </div>

    <div id="form-libro-2">
    <label for = "descrizione">Descrizione : </label> <br>
    <p class="error" id="descrizioneP"><%if(descrizioneP != null){%><%=descrizioneP%><%}%></p>
    <textarea name="descrizione" id="descrizione" rows="16" required></textarea><br>
    <label for = "prezzo">Prezzo (€) : </label> <br>
    <input type="number" step="0.1" min="0" name="prezzo" id="prezzo" required><br>
    <label for = "sconto">Sconto (%) : </label> <br>
    <input type="number" name="sconto" id="sconto" min="0" max="99" step="1"><br>
    <label for = "disponibilita">Disponibilita : </label> <br>
    <input type="number" name="disponibilita" id="disponibilita" required><br>
    <label for = "foto">Foto : </label> <br>
    <input type="file" name="foto" id="foto" accept="image/*" required><br><br>
    <input type="submit" value="inserisci">
    </div>

</form>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>