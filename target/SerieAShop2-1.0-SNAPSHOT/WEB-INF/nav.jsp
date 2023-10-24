<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/navbar.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/script/FormValidationCliente.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="control.LogIn"%>
<%@ page import="control.NewUser"%>

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
  <a href="">
    <img src="${pageContext.request.contextPath}/image/cart.png">
  </a>
</div>

<div class="centernav">
  <a href="home">
    <img src="${pageContext.request.contextPath}/image/logo.png" id="Logo" width="100" height="120" class="d-inline-block align-text-top">
  </a>
  <div class="containerForm">
    <div class="searchform">
      <input type="text" id="search-input" placeholder="Cerca.." title="Inserisci quello che vuoi cercare">
      <button id="search-button" type="submit"><img src="${pageContext.request.contextPath}/image/search.png" id="search-image"></button>
    </div>
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
    <button id="bambino" name="bambino" value="bambin0" class="dropbtn">BAMBINI</button>
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
      <input name="logemail" id="logemail" type="text" placeholder="Enter your email">
      <input name="logpassword" id="logpassword" type="password" placeholder="Enter your password">
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
    <form action="#">
      <input type="text" placeholder="Enter your Name" id="name" onkeyup="nomeClienteValidation(this.form.id);">
      <input type="text" placeholder="Enter your Surname" id="surname" onkeyup="cognomeValidation(this.label.id);">
      <input type="text" placeholder="Enter your email" id="email" onkeyup="emailValidation(this.form.id);">
      <input type="password" placeholder="Create a password" id="password" onkeyup="passwordValidation(this.form.id);">
      <input type="password" placeholder="Confirm your password">
      <input type="button" class="button" value="Signup">
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
    var tshirtButton=document.getElementById("tshirt");
    var retroButton=document.getElementById("retro");
    var saldiButton=document.getElementById("saldi");

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

    tshirtButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'elenco-servlet?buttonName=tshirt';
    });

    retroButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'elenco-servlet?buttonName=retro';
    });

    saldiButton.addEventListener('click', function() {
      // Reindirizza l'utente alla servlet "direct-servlet"
      window.location.href = 'direct-servlet?buttonName=saldi';
    });

  });
</script>
