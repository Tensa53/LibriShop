<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Provincia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Indirizzi</title>
  <%List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");
    String msg = (String) request.getAttribute("msg");
    List<Provincia> province = (List<Provincia>) request.getAttribute("province");
    String msgerrmod = (String) request.getAttribute("msgerrmod");
    String controlloindirizzo = (String) request.getAttribute("controlloindirizzo");
    String viaP = (String) request.getAttribute("msgviaP");
    String civicoP = (String) request.getAttribute("msgcivicoP");
    String capP = (String) request.getAttribute("msgcapP");

  %>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/form/validateFormInserisciIndirizzo.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="./script/ajax/comuni.js"></script>
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<div class="center">

<%if(msg != null){%>
    <h3 class="success">${msg}</h3>
<%}%>

<%if(msgerrmod != null){%>
    <h3 class="error">
        <ul class="nobullet">
            <li><%=msgerrmod%></li>

            <%if(controlloindirizzo != null){%>
                <li><%=controlloindirizzo%></li>
            <%}%>

            <%if(viaP != null){%>
            <li><%=viaP%></li>
            <%}%>

            <%if(civicoP != null){%>
            <li><%=civicoP%></li>
            <%}%>

            <%if(capP != null){%>
            <li><%=capP%></li>
            <%}%>
        </ul>
    </h3>

<%}%>

<fieldset>
    <legend>Inserisci un nuovo indirizzo : <span class="error"><%if(controlloindirizzo != null){%><%=controlloindirizzo%><%}%></span></legend>
<form action="inserisci-indirizzo" name="inserisci-indirizzo" method="post" onsubmit="return validateFormInserisciIndirizzo()">
    <label for="viar">Via/Viale/Piazza : </label><br>
    <p id="viaP"><%if(viaP != null){%><%=viaP%><%}%></p>
    <input type="text" name="viar" id="viar" required><br>
    <label for="civicor">Civico : </label><br>
    <p id="civicoP"><%if(civicoP != null){%><%=civicoP%><%}%></p>
<%--    <p id="civicoP2"></p>--%>
    <input type="number" name="civicor" id="civicor" required><br>
    <label for="provinciar">Provincia : </label><br>
    <select name="provinciar" id="provinciar" onchange="comuni(this.value)">
    <option value="0">Selezionare...</option>
    <%for (Provincia p : province){%>
        <option value="<%=p.getId()%>"><%=p.getNome()%></option>
    <%}%>
    </select><br>
    <label for="cittar">Citta : </label><br>
    <select name="cittar" id="cittar">

    </select><br>
    <label for="capr">CAP : </label><br>
    <p id="capP"><%if(capP != null){%><%=capP%><%}%></p>
<%--    <p id="capP2"></p>--%>
    <input type="number" name="capr" id="capr" required><br>
    <input type="submit" value="Inserisci un nuovo Indirizzo">
</form>
</fieldset>

<%for(Indirizzo indirizzo : indirizzi){%>
<div id="container-indirizzo-utente">
<form method="post">

    <input type="hidden" value="<%=indirizzo.getVia()%>" name="viaF">
    <input type="hidden" value="<%=indirizzo.getCivico()%>" name="civicoF">
    <input type="hidden" value="<%=indirizzo.getCitta()%>" name="cittaF">

    <ul>
        <li>Via/Viale/Piazza : <%=indirizzo.getVia()%></li>
        <li>Civico : <%=indirizzo.getCivico()%></li>
        <li>Provincia : <%=indirizzo.getProvincia()%></li>
        <li>Città : <%=indirizzo.getCitta()%></li>
        <li>CAP : <%=indirizzo.getCAP()%></li>
    </ul>

<%--    <label for="via">Via : </label><br>--%>
<%--    <p id="viaP3"></p>--%>
<%--    <input type="text" name="via" id="via" value="<%=indirizzo.getVia()%>" required><br>--%>
<%--    <label for="civico">Civico : </label><br>--%>
<%--    <p id="civicoP3"></p>--%>
<%--    <p id="civicoP4"></p>--%>
<%--    <input type="text" name="civico" id="civico" value="<%=indirizzo.getCivico()%>" required><br>--%>
<%--    <label for="provincia">Provincia : </label><br>--%>
<%--    <p id="provinciaP3"></p>--%>
<%--    <input type="text" name="provincia" id="provincia" value="<%=indirizzo.getProvincia()%>" required><br>--%>
<%--    <label for="citta">Città : </label><br>--%>
<%--    <p id="cittaP3"></p>--%>
<%--    <input type="text" name="citta" id="citta" value="<%=indirizzo.getCitta()%>" required><br>--%>
<%--    <label for="cap">CAP : </label><br>--%>
<%--    <p id="capP3"></p>--%>
<%--    <p id="capP4"></p>--%>
<%--    <input type="text" name="cap" id="cap" value="<%=indirizzo.getCAP()%>"><br>--%>
    <button formaction="cerca-indirizzo-da-modificare">Modifica Indirizzo</button>
    <button formaction="rimuovi-indirizzo">Rimuovi Indirizzo</button>
</form>
</div>
<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>