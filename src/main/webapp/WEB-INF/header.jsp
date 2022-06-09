<%@ page import="com.example.progettotsw.model.Utente" %>
<%@ page import="com.example.progettotsw.model.Carrello" %>
<header>
    <script src="${pageContext.request.contextPath}/script/navbar.js"></script>

    <a href="http://localhost:8080/progettoTSW_war_exploded/home"><img src="./img/book-icon.png" alt="Icona home"></a>

    <span id="slogan">Il tuo posto sicuro dove acquistare libri</span>

    <%Utente utente = (Utente) request.getSession().getAttribute("utente");
      Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
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
            <a href="/progettoTSW_war_exploded/registrazione.jsp">Registrazione</a>
        </div>
    </div>

    <%}%>

    <div class="dropdown right">
        <img src="./img/carrello.png" alt="Icona carrello"></a>
        <div class="dropdown-content">
                <%if(carrello.getNumeroProdotti() > 0){
                    if(carrello.getNumeroProdotti() == 1){%>
                        <a id="counter-carrello"><%=carrello.getNumeroProdotti()%> libro nel carrello</a>
                    <%}else{%>
                        <a id="counter-carrello"><%=carrello.getNumeroProdotti()%> libri nel carrello</a>
                <%}}else{%>
                    <a id="counter-carrello">0 libri nel carrello</a>
                <%}%>
            <a href="${pageContext.request.contextPath}/carrello.jsp">Vai al Carrello</a>
        </div>
    </div>

</header>