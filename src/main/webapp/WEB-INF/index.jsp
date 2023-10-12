
<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!--<%//@ include file="nav.jsp" %>-->
<link href="css/index.css" rel="stylesheet">
<jsp:include page="nav.jsp"></jsp:include>

<h2 style="text-align: center;">Serie A Teams</h2>
<div class="containerIndex">
    <div class="stemma">
        <%
            response.setContentType("text/html");
            List<Squadra> squadre = (List<Squadra>)request.getSession().getAttribute("squadre");
            PrintWriter outf = response.getWriter();
            for (Squadra s : squadre) {
        %>
        <img height="80px" src=".<%= s.getPathLogo() %>" alt="<%= s.getNomeSquadra() %>">
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