<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="com.example.progettotsw.model.Carrello" %>
<header>
<%--    <script src="${pageContext.request.contextPath}/script/dropdownMenuU.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/script/dropdownMenuC.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/script/dropdownMenuR.js"></script>--%>

    <a href="${pageContext.request.contextPath}/home"><img src="./img/book-icon.png" alt="Icona home"></a>

    <span id="slogan">Il tuo posto sicuro dove acquistare libri</span>

    <%Utente utente = (Utente) request.getSession().getAttribute("utente");
        String urlstr = request.getContextPath();
      Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        if(utente != null){
    %>

    <script>

    </script>

    <div class="dropdown right">
        <div class="icona-menu"><img src="./img/utente.png" alt="Icona utente" id="iconaU" onclick="dropdownMenuU()"></div>
        <div class="dropdown-content utenteu">
            <a href="${pageContext.request.contextPath}/area-riservata">Area Riservata</a>
                <form action="logout">
                    <a> Logout <input class="right inline-block" type="image" src="./img/logout.png" alt="logout"> </a>
                </form>
        </div>
    </div>

    <%}else{%>

    <div class="dropdown right">
        <div class="icona-menu"><img src="./img/utente.png" alt="Icona utente" id="iconaR" onclick="dropdownMenuR()"></div>
        <div class="dropdown-content utenter">
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
            <a href="${pageContext.request.contextPath}/registrazione.jsp">Registrazione</a>
        </div>
    </div>

    <%}%>

    <div class="dropdown right">
        <div class="icona-menu"><img src="./img/carrello.png" alt="Icona carrello" id="iconaC" onclick="dropdownMenuC()"></div>
        <div class="dropdown-content carrello">
                <%if(carrello != null){
                    if(carrello.getNumeroProdotti() == 1){%>
                        <a id="counter-carrello"><%=carrello.getNumeroProdotti()%> libro nel carrello</a>
                    <%}else{%>
                        <a id="counter-carrello"><%=carrello.getNumeroProdotti()%> libri nel carrello</a>
                <%}}else{%>
                    <a id="counter-carrello">0 libri nel carrello</a>
                <%}%>
            <a href="${pageContext.request.contextPath}/carrello">Vai al Carrello</a>
        </div>
    </div>

</header>