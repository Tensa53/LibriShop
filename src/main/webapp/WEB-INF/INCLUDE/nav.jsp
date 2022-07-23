<nav>
  <script src="${pageContext.request.contextPath}/script/navbar.js"></script>
  <script src="${pageContext.request.contextPath}/script/apriNavbar.js"></script>
  <script src="${pageContext.request.contextPath}/script/chiudiNavbar.js"></script>

  <ul>
    <li><a href="${pageContext.request.contextPath}/home" id="home">Home</a></li>
    <button id="bottonenavopen" class="navbutton" onclick="apriNavbar()">&#8595</button>
    <button id="bottonenavclose" class="navbutton" onclick="chiudiNavbar()">&#8593</button>
    <li><a class="notnav" href="${pageContext.request.contextPath}/chisiamo.jsp" id="chisiamo">Chi Siamo</a></li>
    <li><a class="notnav" href="${pageContext.request.contextPath}/dovesiamo.jsp" id="dovesiamo">Dove Siamo</a></li>
    <li><a class="notnav" href="${pageContext.request.contextPath}/contattaci.jsp" id="contattaci">Contattaci</a></li>
  </ul>
</nav>
