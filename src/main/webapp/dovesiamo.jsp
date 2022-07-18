<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dove siamo</title>
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

    <h1>Dove puoi trovarci?</h1>
    <p>Usa la mappa per scoprire dove trovare i nostri store fisici. Abbiamo in programma di aprire
    nuovi punti vendita per soddisfare le esigenze dei clienti di tutta Italia. Se il tuo paese non
    ha ancora uno store dedicato, ricorda che ordinando online riceverai il tuo ordine in soli 2 giorni lavorativi.</p>
    <ul>
        <li>Milano Store. Via Manzoni, 80.</li>
        <li>Roma Store. Via Giosuè Carducci, 13.</li>
        <li>Salerno Store. Via Dei Mercanti, 56.</li>
        <li>Cagliari Store. Via Roma, 22.</li>
    </ul>
    <img src="./img/cartina.jpg" alt="Cartina Italia">
</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>

</body>
</html>
