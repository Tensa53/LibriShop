<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordine Completato</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
</head>
<body>

<jsp:include page="../INCLUDE/header.jsp"></jsp:include>

<jsp:include page="../INCLUDE/nav.jsp"></jsp:include>

<h3 class="success center">Ordine completato, torna alla <a href="<%=request.getContextPath() + "/home"%>">home</a></h3>
</body>


<jsp:include page="../INCLUDE/footer.jsp"></jsp:include>

</html>
