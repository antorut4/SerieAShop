
<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!--<%//@ include file="nav.jsp" %>-->
<link href="css/index.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<jsp:include page="nav.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<head>
    <title>Index</title>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Ottieni il valore della chiave "errori" dalla sessione
            const sessionStorage = window.sessionStorage;
            const errori = sessionStorage.getItem("errori");

            // Itera sull'array di errori e mostra ogni elemento in una finestra di avviso
            if (errori) {
                errori.forEach((elemento) => {
                    window.alert(elemento);
                });

                // Rimuove la chiave "errori" dalla sessione
                sessionStorage.removeItem("errori");
            }
        });
    </script>
</head>
<h2 style="text-align: center;">Serie A Teams</h2>
<div class="containerIndex">
    <div class="stemma">
        <%
            response.setContentType("text/html");
            List<Squadra> squadre = (List<Squadra>)request.getSession().getAttribute("squadre");
            PrintWriter outf = response.getWriter();
            for (Squadra s : squadre) {
        %>
        <a href="leggi-prodotto?squadraScelta=<%=  s.getNomeSquadra()%>">
            <img height="80px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
        </a>
        <%
            }
        %>
    </div>
</div>
<br>
<br>
<br>
<br>
<div class="banner">
    <a href="">
        <img src="image/Banner1.jpg">
    </a>
</div>
<br>
<div class="banner">
    <a href="">
        <img src="image/Banner2.jpg">
    </a>
</div>