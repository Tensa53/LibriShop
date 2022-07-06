<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
    <link rel="stylesheet" type="text/css" href="./css/body-form.css">
    <script src="./script/validateFormLogin.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<div class="center">

<form name="login" action="login" onsubmit="return validateFormLogin()">
    <label for = "mail">Mail : </label> <br>
    <p id="mailP"></p>
    <input type="email" name="mail" id="mail" required><br>
    <label for = "password">Password : </label><br>
    <p id="passwordP"></p>
    <input type="password" name="password" id="password" required><br>
    <input type="submit" value="Login">
</form>

<%
    String msg = (String) request.getAttribute("msg");
    System.out.println(msg);
    if (msg != null){
%>

<div class="error">${msg}</div>

<%}else {%>

<%}%>


<p>oppure <a href="${pageContext.request.contextPath}/registrazione.jsp">registrati</a></p>

</div>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
