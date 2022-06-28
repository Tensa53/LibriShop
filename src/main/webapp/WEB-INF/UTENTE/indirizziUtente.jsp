<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Indirizzi</title>
  <%List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");%>
  <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>


<form action="inserisci-indirizzo" method="post">
    <label for="viar">Via : </label><br>
    <input type="text" name="indirizzor" id="viar"><br>
    <label for="civicor">Civico : </label><br>
    <input type="text" name="indirizzor" id="civicor"><br>
    <label for="provinciar">Provincia : </label><br>
    <input type="text" name="indirizzor" id="provinciar"><br>
    <label for="cittar">Città : </label><br>
    <input type="text" name="indirizzor" id="cittar"><br>
    <label for="capr">CAP : </label><br>
    <input type="text" name="indirizzor" id="capr"><br>
    <input type="submit" value="Inserisci un nuovo Indirizzo">
</form>


<%for(Indirizzo indirizzo : indirizzi){%>
<div id="container-indirizzo-utente">
<form method="post">
    <input type="hidden" value="<%=indirizzo.getVia()%>" name="viaF">
    <input type="hidden" value="<%=indirizzo.getCivico()%>" name="civicoF">
    <input type="hidden" value="<%=indirizzo.getCitta()%>" name="cittaF">
    <label for="via">Via : </label><br>
    <input type="text" name="via" id="via" value="<%=indirizzo.getVia()%>"><br>
    <label for="civico">Civico : </label><br>
    <input type="text" name="civico" id="civico" value="<%=indirizzo.getCivico()%>"><br>
    <label for="provincia">Provincia : </label><br>
    <input type="text" name="provincia" id="provincia" value="<%=indirizzo.getProvincia()%>"><br>
    <label for="citta">Città : </label><br>
    <input type="text" name="citta" id="citta" value="<%=indirizzo.getCitta()%>"><br>
    <label for="cap">CAP : </label><br>
    <input type="text" name="cap" id="cap" value="<%=indirizzo.getCAP()%>"><br>
    <button formaction="conferma-modifiche-indirizzo">Modifica Indirizzo</button>
    <button formaction="rimuovi-indirizzo">Rimuovi Indirizzo</button>
    <button formaction="area-riservata">Annulla</button>
</form>
</div>
<%}%>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>