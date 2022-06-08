<%@ page import="com.example.progettotsw.model.Utente" %>
<header>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>

    <a href="http://localhost:8080/progettoTSW_war_exploded/home"><img src="./img/book-icon.png" alt="Icona home"></a>

    <span id="slogan">Il tuo posto sicuro dove acquistare libri</span>

    <%Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente != null){
    %>

    <div class="dropdown right">
        <img src="./img/utente.png" alt="Icona utente"></a>
        <div class="dropdown-content">
            <%if(utente.isAmministratore()){%>
                <a href="/progettoTSW_war_exploded/admin.jsp">Area Admin</a>
            <%}else{%>
                <a href="/progettoTSW_war_exploded/personale.jsp">Area Personale</a>
            <%}%>
                <form action="logout">
                    <a> Logout <input class="right inline-block" type="image" src="./img/logout.png" alt="logout"> </a>
                </form>
        </div>
    </div>

    <%}else{%>

    <div class="dropdown right">
        <img src="./img/utente.png" alt="Icona utente"></a>
        <div class="dropdown-content">
            <a href="/progettoTSW_war_exploded/login.jsp">Login</a>
            <a href="/progettoTSW_war_explodedregistrazione.jsp">Registrazione</a>
        </div>
    </div>

    <%}%>

    <div class="dropdown right">
        <img src="./img/carrello.png" alt="Icona carrello"></a>
        <div class="dropdown-content">
            <a id="counter-carrello">0 libri nel carrello</a>
            <a href="${pageContext.request.contextPath}/carrello.jsp">Vai al Carrello</a>
        </div>
    </div>

</header>