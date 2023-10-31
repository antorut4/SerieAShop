
<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!--<%//@ include file="nav.jsp" %>-->
<link href="css/index.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<jsp:include page="nav.jsp"></jsp:include>
<head>
    <script>
        $(function() {
            // Recupera gli errori dall'oggetto `Map`
            var errori = $(document).find("input[name='errori']").val();

            // Visualizza gli errori in un pop up
            if (errori) {
                var alert = $("<div class='alert alert-danger'></div>");
                alert.html(errori);
                alert.appendTo("body");
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