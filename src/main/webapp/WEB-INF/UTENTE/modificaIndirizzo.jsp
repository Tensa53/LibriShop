<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="com.example.progettotsw.model.Provincia" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Indirizzo</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateFormModificaIndirizzo.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="./script/ajax/comuni.js"></script>
    <%Indirizzo indirizzo = (Indirizzo) request.getAttribute("indirizzo");
      List<Provincia> province = (List<Provincia>) request.getAttribute("province");
      Provincia provincia = (Provincia) request.getAttribute("provincia");
    %>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div id="container-forms" class="center">
    <form method="post" name="modifica-indirizzo">
        <input type="hidden" value="<%=indirizzo.getVia()%>" name="viaF">
        <input type="hidden" value="<%=indirizzo.getCivico()%>" name="civicoF">
        <input type="hidden" value="<%=indirizzo.getCitta()%>" name="cittaF">

        <label for="via">Via/Viale/Piazza : </label><br>
        <p id="viaP"></p>
        <input type="text" name="via" id="via" value="<%=indirizzo.getVia()%>" required><br>
        <label for="civico">Civico : </label><br>
        <p id="civicoP"></p>
        <input type="number" name="civico" id="civico" value="<%=indirizzo.getCivico()%>" required><br>
        <label for="provincia">Provincia : </label><br>
        <select name="provincia" id="provincia" oninput="comuni(this.value)">
            <option value="<%=provincia.getId()%>"><%=provincia.getNome()%></option>
            <option value="0">Selezionare...</option>
            <%for (Provincia p : province){%>
            <option value="<%=p.getId()%>"><%=p.getNome()%></option>
            <%}%>
        </select><br>
        <label for="citta">Citta : </label><br>
        <select name="citta" id="citta">
            <option value="<%=indirizzo.getCitta()%>"><%=indirizzo.getCitta()%></option>
        </select><br>
        <label for="cap">CAP : </label><br>
        <p id="capP"></p>
        <input type="number" name="cap" id="cap" value="<%=indirizzo.getCAP()%>" required><br><br>
        <button formaction="conferma-modifiche-indirizzo" onclick="return validateFormModificaIndirizzo()">Conferma Modifiche Indirizzo</button>
        <button formaction="area-riservata">Annulla</button>
    </form>
</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
