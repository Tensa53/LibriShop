<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Indirizzi</title>
  <%List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");
    String msg = (String) request.getAttribute("mgs");
  %>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/validateFormInserisciIndirizzo.js" type="text/javascript"></script>
    <script src="./script/validateFormModificaIndirizzo.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center">

<%if(msg != null){%>
    <p>${msg}</p>
<%}%>

<form action="inserisci-indirizzo" name="inserisci-indirizzo" method="post" onsubmit="return validateFormInserisciIndirizzo()">
    <label for="viar">Via : </label><br>
    <p id="viaP"></p>
    <input type="text" name="viar" id="viar" required><br>
    <label for="civicor">Civico : </label><br>
    <p id="civicoP"></p>
    <p id="civicoP2"></p>
    <input type="text" name="civicor" id="civicor" required><br>
    <label for="provinciar">Provincia : </label><br>
    <p id="provinciaP"></p>
    <input type="text" name="provinciar" id="provinciar" required><br>
    <label for="cittar">Città : </label><br>
    <p id="cittaP"></p>
    <input type="text" name="cittar" id="cittar" required><br>
    <label for="capr">CAP : </label><br>
    <p id="capP"></p>
    <p id="capP2"></p>
    <input type="text" name="capr" id="capr" required><br>
    <input type="submit" value="Inserisci un nuovo Indirizzo">
</form>


<%for(Indirizzo indirizzo : indirizzi){%>
<div id="container-indirizzo-utente">
<form method="post" name="modifica-indirizzo">
    <input type="hidden" value="<%=indirizzo.getVia()%>" name="viaF">
    <input type="hidden" value="<%=indirizzo.getCivico()%>" name="civicoF">
    <input type="hidden" value="<%=indirizzo.getCitta()%>" name="cittaF">
    <label for="via">Via : </label><br>
    <p id="viaP3"></p>
    <input type="text" name="via" id="via" value="<%=indirizzo.getVia()%>" required><br>
    <label for="civico">Civico : </label><br>
    <p id="civicoP3"></p>
    <p id="civicoP4"></p>
    <input type="text" name="civico" id="civico" value="<%=indirizzo.getCivico()%>" required><br>
    <label for="provincia">Provincia : </label><br>
    <p id="provinciaP3"></p>
    <input type="text" name="provincia" id="provincia" value="<%=indirizzo.getProvincia()%>" required><br>
    <label for="citta">Città : </label><br>
    <p id="cittaP3"></p>
    <input type="text" name="citta" id="citta" value="<%=indirizzo.getCitta()%>" required><br>
    <label for="cap">CAP : </label><br>
    <p id="capP3"></p>
    <p id="capP4"></p>
    <input type="text" name="cap" id="cap" value="<%=indirizzo.getCAP()%>" required><br>
    <button onclick="return validateFormModificaIndirizzo()" formaction="conferma-modifiche-indirizzo">Modifica Indirizzo</button>
    <button formaction="rimuovi-indirizzo">Rimuovi Indirizzo</button>
    <button formaction="area-riservata">Annulla</button>
</form>
</div>
<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>