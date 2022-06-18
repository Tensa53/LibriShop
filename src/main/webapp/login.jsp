<%--
  Created by IntelliJ IDEA.
  User: daniele
  Date: 01/06/22
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./css/stile.css">
</head>
<body>
<jsp:include page="WEB-INF/INCLUDE/header.jsp"></jsp:include>

<jsp:include page="WEB-INF/INCLUDE/nav.jsp"></jsp:include>

<form action="login">
    <label for = "mail">Mail : </label> <br>
    <input type="email" name="mail" id="mail" required><br>
    <label for = "password">Password : </label><br>
    <input type="password" name="password" id="password" required><br>
    <input type="submit" value="Login">
</form>

<%
    String msg = (String) request.getAttribute("msg");
    System.out.println(msg);
    if (msg != null){
%>

<p class="error">${msg}</p>

<%}else {%>

<%}%>



<p>oppure <a href="${pageContext.request.contextPath}/registrazione.jsp">registrati</a></p>

<jsp:include page="WEB-INF/INCLUDE/footer.jsp"></jsp:include>
</body>
</html>
