<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contattaci</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-cdc.css">
</head>
<body>
<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<div id="container-cdc" class="center">
    <h1>Dove reperirci:</h1>
    <p>Se hai bisogno di assistenza, offriamo numerose opzioni per il servizio clienti. Puoi:</p>
    <h2>Contattare telefonicamente il servizio clienti:</h2>
    <p>Attivo dal lunedì al venerdì dalle 08:00 alle 17:00.</p>
    <a href="tel:123-456-7890">0926-836482</a>
    <h2>Scriverci una mail:</h2>
    <p>Risponderemo entro 2 giorni lavorativi.</p>
    <a href = "mailto:assistenzalibri@gmail.com?subject = Richiesta di assistenza = Message">assistenzalibri@gmail.com</a>
    <h2>Visitare il nostro profilo Instagram:</h2>
    <p>Non dimenticare di seguirci per restare aggiornato sulle ultime uscite!</p>
    <a href="https://www.instagram.com/librimondadori/"><img src="./img/ig-icon.png" alt="Icona Instagram" style="width:42px;height:42px;"></a>
</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
