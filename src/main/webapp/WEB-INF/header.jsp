<%@ page import="com.example.progettotsw.model.Utente" %>
<header>
    <a href="http://localhost:8080/progettoTSW_war_exploded/home"><img src="./img/book-icon.png" alt="Icona home"></a>
    <div class="dropdown right">
        <a href="http://www.google.com"><img src="./img/carrello.png" alt="Icona carrello"></a>
        <div class="dropdown-content bottom">
            <p> Contenuto del carrello </p>
            <a href="http://www.google.com">Vai al carrello</a>
        </div>
    </div>
        <div class="dropdown right">
       <a href="http://www.google.com"><img src="./img/utente.png" alt="Icona utente"></a>
        <div class="dropdown-content bottom">
            <%  Utente utente = (Utente) session.getAttribute("utente");
                if(utente != null){
            %>
                    <a href="http://www.google.com">Area Personale</a>
                    <br>
                    <form action="logout">
                        <input type="submit" value="Logout">
                    </form>
            <%}else{%>
                    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    <br>
                    <a href="http://www.google.com">Registrazione</a>
            <%}%>
        </div>
        </div>
    <span id="slogan">Il tuo posto sicuro dove acquistare libri</span>
</header>