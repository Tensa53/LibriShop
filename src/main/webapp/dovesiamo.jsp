<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dove siamo</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/nav.jsp"></jsp:include>

<div id="container-cdc">

    <h1>Dove puoi trovarci?</h1>
    <p>Clicca sulla mappa per scoprire dove trovare i nostri store fisici. Abbiamo in programma di aprire
    nuovi punti vendita per soddisfare le esigenze dei clienti di tutta Italia. Se il tuo paese non
    ha ancora uno store dedicato, ricorda che ordinando online riceverai il tuo ordine in soli 2 giorni lavorativi.</p>

    <img src="./img/cartina.jpg" alt="Cartina Italia" usemap="#workmap">

    <map name="workmap">
        <area shape="circle" coords="130,160,40" alt="MilanoStore" href="http://www.image-map.net/ ">
        <area shape="circle" coords="300,360,40" alt="RomaStore" href="http://www.google.com/ ">
        <area shape="circle" coords="390,410,40" alt="SalernoStore" href="http://www.twitter.com/ ">
        <area shape="circle" coords="100,550,40" alt="CagliariStore" href="http://www.youtube.com/ ">
    </map>

</div>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

</body>
</html>
