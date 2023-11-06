<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/navbar.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/script/FormValidationCliente.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/script/search.js"></script>

<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="control.LogIn"%>
<%@ page import="control.NewUser"%>
<%@ page import="control.SearchServlet" %>

<script>
  $(document).ready(function () {
    $("#search-button").click(function () {
      var searchQuery = $("#search").val();

      $.ajax({
        url: "searchServlet",
        method: "GET",
        data: { query: searchQuery },
        dataType: "json",
        success: function (data) {


          // Manipola i dati JSON ricevuti e mostra i risultati
          var resultsHtml = "<ul>";

          // Itera su ciascun oggetto JSON nell'array
          for (var i = 0; i < data.length; i++) {
            resultsHtml += "<a href='prodotto-singolo?idProdotto=" + data[i].id + "'>";
            resultsHtml += "<img width='80px' src =image/PathOggetti/" + data[i].id + "/1.jpg>";
            resultsHtml += "<li>" + data[i].nome + "</li>";
            resultsHtml += "</a>";
          }

          resultsHtml += "</ul>";

          // Inserisci i risultati nell'elemento con ID "search-results"
          $("#search-results").html(resultsHtml);
        },
        error: function (error) {
          console.log("Errore nella richiesta AJAX: " + error);
        }
      });
    });
  });
</script>

<div class="topnav">
  <a href="">Traccia Ordine</a>
  <a href="">Aiuto</a>
  <c:choose>
    <c:when test="${sessionScope.user != null}">
      <div >
        <a href="direct-servlet">Il mio account</a>
      </div>
    </c:when>
    <c:otherwise>
      <div onclick="LogRegForm()">
        <a> Login </a>
      </div>
    </c:otherwise>
  </c:choose>

  <c:choose>
  <c:when test="${sessionScope.user != null}">
    <a href="carrello-servlet">
      <img src="${pageContext.request.contextPath}/image/cart.png">
    </a>
  </c:when>
  </c:choose>

</div>

<div class="centernav">
  <a href="home">
    <img src="${pageContext.request.contextPath}/image/logo.png" id="Logo" width="100" height="120" class="d-inline-block align-text-top">
  </a>
  <div class="containerForm">
    <div class="searchform">
      <input type="text" name="query" id="search" placeholder="Cerca.." title="Inserisci quello che vuoi cercare" class="search-input">
      <ul id="suggestions"></ul>
      <button type="button" id="search-button" class="search-button">
        <img src="${pageContext.request.contextPath}/image/search.png" id="search-image">
      </button>
    </div>
    <div id="search-results" class="search-results"></div>
  </div>

</div>

<div class="downbar">
  <!-- Dropdown 1 -->
  <div class="dropdown">
    <button class="dropbtn">SHOP BY TEAM</button>
    <div class="dropdown-content">
      <%
        response.setContentType("text/html");
        List<Squadra> squadre = (List<Squadra>)request.getSession().getAttribute("squadre");
        PrintWriter outf = response.getWriter();
        for (Squadra s : squadre) {
      %>
      <a href="leggi-prodotto?squadraScelta=<%=  s.getNomeSquadra()%>">
        <img height="40px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
        <%= s.getNomeSquadra() %>
      </a>
      <%
        }
      %>
    </div>
  </div>

  <!-- Dropdown 2 -->
  <div class="dropdown">
    <button name="uomo" id="uomo" value="uomo" class="dropbtn">UOMO</button>

    <div class="dropdown-content">
      <%
        for (Squadra s : squadre) {
      %>
      <a href="#">
        <img height="40px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
        <%= s.getNomeSquadra() %>
      </a>
      <%
        }
      %>
    </div>
  </div>

  <!-- Dropdown 3 -->
  <div class="dropdown">
    <button name="donna" id="donna" value="donna" class="dropbtn">DONNA</button>
    <div class="dropdown-content">
      <%
        for (Squadra s : squadre) {
      %>
      <a href="#">
        <img height="40px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
        <%= s.getNomeSquadra() %>
      </a>
      <%
        }
      %>
    </div>
  </div>

  <!-- Dropdown 4 -->
  <div class="dropdown">
    <button id="bambino" name="bambino" value="bambino" class="dropbtn">BAMBINI</button>
    <div class="dropdown-content">
      <%
        for (Squadra s : squadre) {
      %>
      <a href="#">
        <img height="40px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
        <%= s.getNomeSquadra() %>
      </a>
      <%
        }
      %>
    </div>
  </div>

  <div class="dropdown">
    <form action="leggi-prodotto">
  <button name="btn" id="maglia" value="maglia" class="dropbtn">MAGLIA</button>
    </form>
  <div class="dropdown-content">
  </div>
  </div>

  <div class="dropdown">
    <form action="leggi-prodotto">
  <button name="btn" id="pantaloncini" value="pantaloncini" class="dropbtn">PANTALONCINI</button>
    </form>
    <div class="dropdown-content">
</div>
  </div>

  <div class="dropdown">
    <form action="leggi-prodotto">
      <button name="btn" id="calzettoni" value="calzettoni" class="dropbtn">CALZETTONI</button>
    </form>
  <div class="dropdown-content">
  </div>
  </div>
</div>

<!--Login Form-->
<div id="LogRegForm" class="container Hidden">
  <input type="checkbox" id="check">
  <div class="login form">
    <header>Login</header>
    <form action="log-in" method="post">
      <input name="logusername" id="logusername" type="text" placeholder="Enter your username" onkeyup="usernameValidation(this.form.logusername)">
      <input name="logpassword" id="logpassword" type="password" placeholder="Enter your password" onkeyup="passwordValidation(this.form.logpassword)">
      <a href="#">Forgot password?</a>
      <input type="submit" class="button" value="Login">
    </form>
    <div class="signup">
        <span class="signup">Don't have an account?
         <label for="check">Signup</label>
        </span>
    </div>
  </div>
  <div class="registration form">
    <header>Signup</header>
    <form action="new-user">
      <input type="text" placeholder="Enter your Username" id="username" name="username" onkeyup="usernameValidation(this.form.username)">
      <input type="text" placeholder="Enter your Name" id="nome" name="nome" onkeyup="nomeClienteValidation(this.form.nome)">
      <input type="text" placeholder="Enter your Surname" id="surname" name="cognome" onkeyup="cognomeValidation(this.label.surname)">
      <input type="text" placeholder="Enter your email" id="email" name="email" onkeyup="emailValidation(this.form.email);">
      <input type="text" placeholder="Enter your Phone number" id="telefono" name="telefono" onkeyup="telefonoValidation(this.form.telefono)">
      <input type="text" placeholder="Enter your Address" id="indirizzo" name="indirizzo" onkeyup="indirizzoValidation(this.form.indirizzo)">
      <input type="password" placeholder="Create a password" id="password" name="password" onkeyup="passwordValidation(this.form.password)">
      <input type="submit" class="button" value="Signup">
    </form>
    <div class="signup">
        <span class="signup">Already have an account?
         <label for="check">Login</label>
        </span>
    </div>
  </div>
</div>

<script>
  document.addEventListener('keyup', function (event){
    if(event.key === "Escape")
      LogRegForm();
  });
</script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    // Seleziona il pulsante "UOMO" per il reindirizzamento
    var uomoButton = document.getElementById('uomo');
    var bambinoButton= document.getElementById("bambino");
    var donnaButton= document.getElementById("donna");

    // Aggiungi un gestore di eventi al clic del pulsante "UOMO"
    uomoButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'elenco-servlet?buttonName=uomo';
    });
    donnaButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'elenco-servlet?buttonName=donna';
    });

    bambinoButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'elenco-servlet?buttonName=bambino';
    });

  });
</script>
