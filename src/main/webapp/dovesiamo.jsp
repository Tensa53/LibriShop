<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dove siamo</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<div id="container-cdc">

    <h1>Dove puoi trovarci?</h1>
    <p>Usa la mappa per scoprire dove trovare i nostri store fisici. Abbiamo in programma di aprire
    nuovi punti vendita per soddisfare le esigenze dei clienti di tutta Italia. Se il tuo paese non
    ha ancora uno store dedicato, ricorda che ordinando online riceverai il tuo ordine in soli 2 giorni lavorativi.</p>
    <p id="store">Clicca sulla mappa!</p>
    <img src="./img/cartina.jpg" alt="Cartina Italia" usemap="#workmap">

    <map name="workmap">
        <area shape="circle" onclick="$('#store').html('Hai scelto Milano Store. Via Manzoni, 80.');"
              coords="130,160,40" alt="MilanoStore" href="#">
        <area shape="circle" onclick="$('#store').html('Hai scelto Roma Store. Via Giosuè Carducci, 13.');"
              coords="300,360,40" alt="RomaStore" href="#">
        <area shape="circle" onclick="$('#store').html('Hai scelto Salerno Store. Via Dei Mercanti, 56.');"
              coords="390,410,40" alt="SalernoStore" href="#">
        <area shape="circle" onclick="$('#store').html('Hai scelto Cagliari Store. Via Roma, 22.');"
              coords="100,550,40" alt="CagliariStore" href="#">
    </map>

</div>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

</body>
</html>
