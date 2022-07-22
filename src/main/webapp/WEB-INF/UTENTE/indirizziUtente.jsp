<%@ page import="com.example.progettotsw.model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.progettotsw.model.Provincia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Indirizzi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<div class="center" id="container-forms">

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
    <p class="error" id="viaP"><%if(viaP != null){%><%=viaP%><%}%></p>
    <input type="text" name="viar" id="viar" required><br>
    <label for="civicor">Civico : </label><br>
    <p class="error" id="civicoP"><%if(civicoP != null){%><%=civicoP%><%}%></p>
    <input type="number" name="civicor" id="civicor" min="1" required><br>
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
    <p class="error" id="capP"><%if(capP != null){%><%=capP%><%}%></p>
    <input type="number" name="capr" id="capr" required><br><br>
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

    <button formaction="cerca-indirizzo-da-modificare">Modifica Indirizzo</button>
    <button formaction="rimuovi-indirizzo">Rimuovi Indirizzo</button>
</form>
</div>
<%}%>

</div>

<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</body>
</html>